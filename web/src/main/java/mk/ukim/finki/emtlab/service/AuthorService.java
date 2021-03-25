package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    List<AuthorDto> findAllDto();

    Author findById(Long id);

    Author findByName(String name);

    Author findBySurname(String surname);

    Author save(String name, String surname, Long countryId);

    Author edit(Long id, String name, String surname, Long countryId);

    Author deleteById(Long id);
}
