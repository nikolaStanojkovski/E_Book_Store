package mk.ukim.finki.emtlab.service.implementation;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.model.dto.CountryDto;
import mk.ukim.finki.emtlab.model.exceptions.InvalidCountryContinentException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidCountryNameException;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public List<CountryDto> findAllDto() {
        List<CountryDto> allCountries = new ArrayList<>();
        findAll().forEach(i -> allCountries.add(new CountryDto(i.getName(), i.getContinent())));

        return allCountries;
    }

    @Override
    public Country findById(Long id) throws InvalidCountryIdException {
        return this.countryRepository.findById(id)
                .orElseThrow(() -> new InvalidCountryIdException(id));
    }

    @Override
    public Country findByName(String name) throws InvalidCountryNameException {
        return this.countryRepository.findByName(name)
                .orElseThrow(() -> new InvalidCountryNameException(name));
    }

    @Override
    public Country findByContinent(String continent) throws InvalidCountryContinentException{
        return this.countryRepository.findByContinent(continent)
                .orElseThrow(() -> new InvalidCountryContinentException(continent));
    }

    @Override
    public Country save(String name, String continent) throws IllegalArgumentException {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(continent == null || continent.isEmpty())
            throw new IllegalArgumentException(continent);

        Country country = new Country(name, continent);
        this.countryRepository.save(country);

        return country;
    }

    @Override
    public Country edit(Long id, String name, String continent) throws InvalidCountryIdException, IllegalArgumentException {
        Country country = findById(id);

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(continent == null || continent.isEmpty())
            throw new IllegalArgumentException(continent);

        country.setName(name);
        country.setContinent(continent);

        return this.countryRepository.save(country);
    }

    @Override
    public Country deleteById(Long id) throws InvalidCountryIdException {
        Country country = findById(id);
        this.countryRepository.delete(country);

        return country;
    }
}
