package com.tradesystem.magic;

import java.util.stream.IntStream;

import com.github.javafaker.Faker;
import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MagicInitializer implements CommandLineRunner {

    private static final int INITIAL_GOODS_QUANTITY = 20;
    private final GoodRepository goodRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        log.info("Here's a fun fact I wanna tell ya... \n {}", faker.chuckNorris().fact());

        log.info("Goods initialization has been started...");

        IntStream.range(0, INITIAL_GOODS_QUANTITY).forEach(i -> {
            Good good = new Good();
            good.setName(faker.funnyName().name())
                    .setPrice((long) faker.number().randomDigitNotZero())
                    .setDescription(faker.lebowski().quote());
            goodRepository.save(good);
        });

        log.info("Goods initialized. Total: {}", INITIAL_GOODS_QUANTITY);
    }
}
