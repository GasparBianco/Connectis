package com.connectis.connectis.services;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.exception.ProvinceNotFoundException;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.connectis.connectis.mapper.ProvinceMapper.provinceDtoToProvinceEntity;
import static com.connectis.connectis.mapper.ProvinceMapper.provinceEntityToProvinceDto;

@Service
@Transactional
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public ProvinceDto addProvince(ProvinceDto provinceDto){
        return provinceEntityToProvinceDto(provinceRepository.save(provinceDtoToProvinceEntity(provinceDto)));
    }
    public ProvinceEntity getProvinceById(Long id) throws Exception {
        return provinceRepository.findById(id).orElseThrow(ProvinceNotFoundException::new);
    }
    public ProvinceEntity getProvinceByExactName(String name){
        return provinceRepository.findByName(name);
    }
    public List<ProvinceEntity> getProvinceByNameContaining(String name){
        List<ProvinceEntity> provinces = provinceRepository.findByNameContainingIgnoreCase(name);
        return provinces.isEmpty() ? null : provinces;
    }
    public boolean deleteProvinceById(Long id) {
        Optional<ProvinceEntity> provinceOptional = provinceRepository.findById(id);
        if (provinceOptional.isPresent()) {
            provinceRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<ProvinceEntity> getAllProvinces() {
        return (List<ProvinceEntity>) provinceRepository.findAll();
    }

}
