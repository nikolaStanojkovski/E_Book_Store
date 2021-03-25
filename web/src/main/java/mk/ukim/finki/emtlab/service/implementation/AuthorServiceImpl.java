package mk.ukim.finki.emtlab.service.implementation;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidAuthorNameException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidAuthorSurnameException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<AuthorDto> findAllDto() {
        List<AuthorDto> allAuthors = new ArrayList<>();
        findAll().forEach(i -> allAuthors.add(new AuthorDto(i.getName(), i.getSurname(), i.getCountry())));

        return allAuthors;
    }

    @Override
    public Author findById(Long id) throws InvalidAuthorIdException {
        return this.authorRepository.findById(id)
                .orElseThrow(() -> new InvalidAuthorIdException(id));
    }

    @Override
    public Author findByName(String name) {
        return this.authorRepository.findByName(name)
                .orElseThrow(() -> new InvalidAuthorNameException(name));
    }

    @Override
    public Author findBySurname(String surname) {
        return this.authorRepository.findBySurname(surname)
                .orElseThrow(() -> new InvalidAuthorSurnameException(surname));
    }

    @Override
    public Author save(String name, String surname, Long countryId) throws InvalidCountryIdException, IllegalArgumentException {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new InvalidCountryIdException(countryId));

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(surname == null || surname.isEmpty())
            throw new IllegalArgumentException(surname);

        Author author = new Author(name, surname, country);

        return this.authorRepository.save(author);
    }

    @Override
    public Author edit(Long id, String name, String surname, Long countryId) throws InvalidCountryIdException,
            IllegalArgumentException, InvalidAuthorIdException {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new InvalidCountryIdException(countryId));

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(surname == null || surname.isEmpty())
            throw new IllegalArgumentException(surname);

        Author author = findById(id);

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author deleteById(Long id) throws InvalidAuthorIdException {
        Author author = findById(id);
        this.authorRepository.delete(author);

        return author;
    }
}
