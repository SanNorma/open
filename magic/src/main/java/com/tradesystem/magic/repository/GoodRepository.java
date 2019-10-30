package com.tradesystem.magic.repository;

import com.tradesystem.magic.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long> {

    boolean existsByName(String name);

    Good findByName(String name);
}
