package com.smallworldfs.helloservice.api.mapper;

import com.smallworldfs.helloservice.api.dto.ItemRequestDto;
import com.smallworldfs.helloservice.api.dto.ItemResponseDto;
import com.smallworldfs.helloservice.db.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemDtoMapper {
    ItemResponseDto toDto(Item item);

    Item toItem(ItemRequestDto itemRequestDto);
}
