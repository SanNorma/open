package com.tradesystem.magic.service.impl;

import java.util.List;

import com.tradesystem.magic.converter.Converter;
import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.domain.projection.NamesDescriptions;
import com.tradesystem.magic.dto.GoodRequest;
import com.tradesystem.magic.exception.GoodNotFoundException;
import com.tradesystem.magic.repository.GoodRepository;
import com.tradesystem.magic.service.GoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Log4j2
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;

    //TODO write appropriate log messages

    @Override
    public Good findById(Long id) {
        log.info("findById");
        return goodRepository.findById(id)
                .orElseThrow(() -> new GoodNotFoundException(String.format("Good with id: '%s' not found", id)));
    }

    @Override
    public NamesDescriptions findNameDescriptionById(Long id) {
        log.info("findNameDescriptionById");
        return goodRepository.findNameDescriptionById(id);
    }

    @Override
    public List<Good> findAll() {
        log.info("findAll");
        return goodRepository.findAll();
    }

    @Override
    public Long createGood(GoodRequest goodRequest) {
        log.info("createGood");
        var good = Converter.toGood(goodRequest);
        if (goodRepository.existsByName(good.getName())) {
            return goodRepository.findByName(good.getName()).getId();
        }
        return goodRepository.save(good).getId();
    }

    @Transactional
    @Override
    public Good updateGood(Long id, GoodRequest request) {
        log.info("updateGood");
        var good = Converter.toGood(request).setId(id);
        if (goodRepository.existsById(id)) {
            goodRepository.save(good);
        } else throw new GoodNotFoundException(String.format("Good with id: '%s' not found", id));
        return good;
    }

    public void deleteById(Long id) {
        log.info("deleteById");
        goodRepository.deleteById(id);
    }
}
