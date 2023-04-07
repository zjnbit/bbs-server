package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BaseDictDto {
    private String name;
    private List<BaseDictItemDto> items;
}
