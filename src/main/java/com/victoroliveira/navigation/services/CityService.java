package com.victoroliveira.navigation.services;

import com.victoroliveira.navigation.domain.City;
import com.victoroliveira.navigation.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public void saveListCities(List<City> listCities) {
        repository.saveAll(listCities);
    }

    public List<City> findCapitals() {
        List<City> capitals;
        capitals = repository.findAll().stream().filter(City::isCapital).collect(Collectors.toList());
        capitals.sort(Comparator.comparing(City::getCity));
        return capitals;
    }

    public List<City> findUF(String uf) {
        List<City> citiesUF;
        citiesUF = repository.findAll().stream().filter(city -> uf.equals(city.getUf())).collect(Collectors.toList());
        citiesUF.sort(Comparator.comparing(City::getCity));
        return citiesUF;
    }

    public City findCity(Integer ibgeId) {
        Optional<City> obj = repository.findById(ibgeId);
        return obj.orElse(null);
    }

    public void deleteCity(Integer ibgeId) {
        repository.delete(findCity(ibgeId));
    }

    public void saveCity(City obj) {
        repository.save(obj);
    }

    public int count() {
        return repository.findAll().size();
    }
}
