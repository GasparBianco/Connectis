package com.connectis.connectis.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "country")
public class CountryEntity extends Region{

    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL)
    private List<ProvinceEntity> provinceEntityList;
}
