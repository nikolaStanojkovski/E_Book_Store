package mk.ukim.finki.emtlab.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.enumerations.Category;

@Data
public class BookDto {

    public String name;

    public Category category;

    public Long authorId;

    public Integer availableCopies;

    public BookDto() {}

    public BookDto(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
