package com.connectis.connectis.models;

import jakarta.persistence.*;

@Entity(name = "city")
public class CityEntity extends Region {

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity provinceEntity;
}
