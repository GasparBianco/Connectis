package com.connectis.connectis.services;

import com.connectis.connectis.dto.ProvinceDto;
import com.connectis.connectis.exception.ProvinceNotFoundException;
import com.connectis.connectis.mapper.ProvinceMapper;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.connectis.connectis.mapper.ProvinceMapper.provinceDtoToProvinceEntity;
import static com.connectis.connectis.mapper.ProvinceMapper.provinceEntityToProvinceDto;

@Service
@Transactional
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;


    public ProvinceDto addProvince(ProvinceDto provinceDto){
        if (provinceDto == null || provinceDto.getName() == null || provinceDto.getName().length() <= 3){
            throw new IllegalArgumentException();
        }
        return provinceEntityToProvinceDto(provinceRepository.save(provinceDtoToProvinceEntity(provinceDto)));
    }

    public ProvinceDto getProvinceById(Long id) {
        return provinceEntityToProvinceDto(provinceRepository.findById(id).orElseThrow(ProvinceNotFoundException::new));
    }

    public ProvinceDto getProvinceByExactName(String name){
        ProvinceEntity provinceEntity = provinceRepository.findByName(name);
        if (provinceEntity == null){
            throw new ProvinceNotFoundException();
        }
        return provinceEntityToProvinceDto(provinceEntity);
    }

    public List<ProvinceDto> getProvinceByNameContaining(String name){
        List<ProvinceEntity> provinceEntityList = provinceRepository.findByNameContainingIgnoreCase(name);
        if (provinceEntityList.isEmpty()){
            throw new ProvinceNotFoundException();
        }
        return provinceEntityList.stream()
                .map(ProvinceMapper::provinceEntityToProvinceDto)
                .collect(Collectors.toList());
    }

    public void deleteProvinceById(Long id){
        provinceRepository.findById(id).orElseThrow(ProvinceNotFoundException::new);
        provinceRepository.deleteById(id);
    }

    public List<ProvinceDto> getAllProvinces() {
        List<ProvinceEntity> provinceEntityList = provinceRepository.findAll();
        return provinceEntityList.stream()
                .map(ProvinceMapper::provinceEntityToProvinceDto)
                .collect(Collectors.toList());
    }
}