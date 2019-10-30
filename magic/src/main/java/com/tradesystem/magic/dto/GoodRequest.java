package com.tradesystem.magic.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodRequest {

    @NotEmpty
    private String name;
    private Long price;
    private String description;
}
