package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    List<CountryDto> findAllDto();

    Country findById(Long id);

    Country findByName(String name);

    Country findByContinent(String continent);

    Country save(String name, String continent);

    Country edit(Long id, String name, String continent);

    Country deleteById(Long id);

}
