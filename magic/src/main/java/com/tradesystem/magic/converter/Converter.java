package com.tradesystem.magic.converter;

import com.tradesystem.magic.domain.Good;
import com.tradesystem.magic.dto.GoodRequest;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class Converter extends ConfigurableMapper {

    private static MapperFacade mapperFacade = new Converter();

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Good.class, GoodRequest.class).byDefault().register();
    }

    public static Good toGood(GoodRequest goodRequest) {
        return mapperFacade.map(goodRequest, Good.class);
    }

    public static GoodRequest toGoodRequest(Good good) {
        return mapperFacade.map(good, GoodRequest.class);
    }
}
