package com.connectis.connectis.repository;

import com.connectis.connectis.models.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
    ProvinceEntity findByName(String name);

    List<ProvinceEntity> findByNameContainingIgnoreCase(String name);
}