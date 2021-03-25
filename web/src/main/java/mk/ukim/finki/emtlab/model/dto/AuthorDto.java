package mk.ukim.finki.emtlab.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlab.model.Country;

@Data
public class AuthorDto {

    public String name;

    public String surname;

    public Country country;

    public AuthorDto() {}

    public AuthorDto(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

}
