package com.connectis.connectis.repository;

import com.connectis.connectis.models.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<CityEntity, Long>  {
}
