package com.tradesystem.magic.service;

import java.util.List;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.domain.projection.NamesDescriptions;
import com.tradesystem.magic.dto.GoodRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface GoodService {

    Good findById(Long id) throws NotFoundException;
    NamesDescriptions findNameDescriptionById(Long id);
    List<Good> findAll();
    Long createGood(GoodRequest goodRequest);
    Good updateGood(Long id, GoodRequest request);
    void deleteById(Long id);
}
