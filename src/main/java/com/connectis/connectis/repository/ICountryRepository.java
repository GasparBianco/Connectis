package com.connectis.connectis.repository;

import com.connectis.connectis.models.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {
    CountryEntity findByName(String name);
}