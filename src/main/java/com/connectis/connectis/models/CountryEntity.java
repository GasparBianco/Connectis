package com.connectis.connectis.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "country")
@Setter
@Getter
public class CountryEntity extends Region{

    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL)
    private List<ProvinceEntity> provinceEntityList;
}
