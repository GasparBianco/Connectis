package com.connectis.connectis.mapper;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.models.ProvinceEntity;

public class ProvinceMapper {

    public static ProvinceEntity provinceDtoToProvinceEntity(ProvinceDto provinceDto) {
        ProvinceEntity provinceEntity = new ProvinceEntity();
        provinceEntity.setId(provinceDto.getId());
        provinceEntity.setName(provinceDto.getName());
        return provinceEntity;
    }

    public static ProvinceDto provinceEntityToProvinceDto(ProvinceEntity provinceEntity) {
        return ProvinceDto.builder()
                .id(provinceEntity.getId())
                .name(provinceEntity.getName())
                .build();
    }

}
