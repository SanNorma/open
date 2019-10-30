package com.tradesystem.magic.converter;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;

public class GoodConverter {

    public static Good toGood(GoodRequest goodRequest) {
        Good good = new Good();
        good.setName(goodRequest.getName())
                .setPrice(goodRequest.getPrice())
                .setDescription(goodRequest.getDescription());
        return good;
    }

    public static GoodRequest toGoodRequest(Good good) {
        return GoodRequest.builder()
                .name(good.getName())
                .price(good.getPrice())
                .description(good.getDescription())
                .build();
    }
}
