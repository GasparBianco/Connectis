package com.connectis.connectis.repository;

import com.connectis.connectis.models.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvince extends JpaRepository<ProvinceEntity, Long> {
}
