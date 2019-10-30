package com.tradesystem.magic.service;

import java.util.List;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

public interface GoodService {

    Good findById(Long id) throws NotFoundException;
    List<Good> findAll();
    Long createGood(GoodRequest goodRequest);
}
