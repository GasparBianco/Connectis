package com.connectis.connectis.services;

import com.connectis.connectis.models.CountryEntity;
import com.connectis.connectis.models.ProvinceEntity;
import com.connectis.connectis.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProvinceServices {
    @Autowired
    private IProvinceRepository iProvinceRepository;

    public ProvinceEntity addProvince(ProvinceEntity province){
        return iProvinceRepository.save(province);
    }
    public ProvinceEntity getProvinceById(Long id){
        return iProvinceRepository.findById(id).orElse(null);
    }
    public ProvinceEntity getProvinceByExactName(String name){
        return iProvinceRepository.findByName(name);
    }
    public List<ProvinceEntity> getProvinceByNameContaining(String name){
        List<ProvinceEntity> provinces = iProvinceRepository.findByNameContainingIgnoreCase(name);
        return provinces.isEmpty() ? null : provinces;
    }
    public boolean deleteProvinceById(Long id) {
        Optional<ProvinceEntity> provinceOptional = iProvinceRepository.findById(id);
        if (provinceOptional.isPresent()) {
            iProvinceRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public ProvinceEntity updateProvince(ProvinceEntity province){
        Optional<ProvinceEntity> optionalProvinceToUpdate = iProvinceRepository.findById(province.getId());
        if (optionalProvinceToUpdate.isPresent()) {
            ProvinceEntity existingProvince = optionalProvinceToUpdate.get();
            existingProvince.setName(province.getName());
            existingProvince.setCountryEntity(province.getCountryEntity());
            return iProvinceRepository.save(existingProvince);
        }
        return null;
    }
}
