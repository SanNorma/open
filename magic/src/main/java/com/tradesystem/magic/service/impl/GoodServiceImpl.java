package com.tradesystem.magic.service.impl;

import java.util.List;

import com.tradesystem.magic.converter.GoodConverter;
import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;
import com.tradesystem.magic.repository.GoodRepository;
import com.tradesystem.magic.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoodServiceImpl implements GoodService {

    private final GoodRepository goodRepository;

    @Override
    public Good findById(Long id) throws NotFoundException{
        return goodRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    @Override
    public Long createGood(GoodRequest goodRequest) {
        Good good = GoodConverter.toGood(goodRequest);
        if (goodRepository.existsByName(good.getName())) {
            return goodRepository.findByName(good.getName()).getId();
        }
        return goodRepository.save(good)
                .getId();
    }
}
