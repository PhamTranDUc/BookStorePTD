package com.phamtranduc.bookStorePTD.service;

import com.phamtranduc.bookStorePTD.entity.Country;
import com.phamtranduc.bookStorePTD.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll(){
       return countryRepository.findAll();
    }
    public void addCountry(Country country){
        countryRepository.save(country);
    }

    public Country findById(Long id){
        return countryRepository.findById(id).get();
    }

}
