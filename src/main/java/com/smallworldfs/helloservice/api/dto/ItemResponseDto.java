package com.smallworldfs.helloservice.api.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {

    private String grantLevel;
    @NotNull(message = "item must not be null")
    private Integer item;
}
