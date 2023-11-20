package com.connectis.connectis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "province")
@Getter
@Setter
public class ProvinceEntity extends Region{

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "provinceEntity", cascade = CascadeType.ALL)
    private List<CityEntity> cityEntityList;
}
