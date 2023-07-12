package com.smallworldfs.helloservice.api.dto;

import com.smallworldfs.helloservice.api.validation.ItemIsLegal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ItemIsLegal
public class ItemRequestDto {

    private String grantLevel;
    @NotNull(message = "item must not be null")
    private Integer item;
}
