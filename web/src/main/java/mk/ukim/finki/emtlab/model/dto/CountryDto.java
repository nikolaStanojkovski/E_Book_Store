package mk.ukim.finki.emtlab.model.dto;

import lombok.Data;

@Data
public class CountryDto {

    public String name;

    public String continent;

    public CountryDto() {}

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
