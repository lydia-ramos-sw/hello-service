package com.smallworldfs.helloservice.api;

import com.smallworldfs.error.model.ErrorDto;
import com.smallworldfs.helloservice.api.dto.ItemRequestDto;
import com.smallworldfs.helloservice.api.dto.ItemResponseDto;
import com.smallworldfs.helloservice.api.mapper.ItemDtoMapper;
import com.smallworldfs.helloservice.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloServiceController {

    private final ItemDtoMapper mapper = Mappers.getMapper(ItemDtoMapper.class);
    private final HelloService helloService;

    @Operation(
            summary = "A method that says hello",
            tags = {"Hello Method"})
    @GetMapping
    public String hello() {
        return "Hello Services";
    }

    @Operation(
            summary = "A method that gives you an item, if it is available and you have enough grants",
            tags = {"Hello get an item"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{item}/{grant}")
    public ItemResponseDto getItem(@PathVariable Integer item, @PathVariable String grant) {
        return mapper.toDto(helloService.getItem(item, grant));
    }

    @Operation(
            summary = "A method that creates an item, if you have enough grants",
            tags = {"Hello create an item"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ItemResponseDto createItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        return mapper.toDto(helloService.createItem(mapper.toItem(itemRequestDto)));
    }

    @Operation(
            summary = "A method that updates an item, if you have enough grants",
            tags = {"Hello update an item"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Access",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public ItemResponseDto updateItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        return (mapper.toDto(helloService.updateItem(mapper.toItem(itemRequestDto))));
    }

    @Operation(
            summary = "A method that deletes an item, if you have enough grants",
            tags = {"Hello delete an item"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Access",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDto.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public void deleteItem(@Valid @RequestBody Integer item) {
        // to develop
    }
}
