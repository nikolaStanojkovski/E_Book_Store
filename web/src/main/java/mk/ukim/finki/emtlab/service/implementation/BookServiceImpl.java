package mk.ukim.finki.emtlab.service.implementation;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.model.enumerations.Category;
import mk.ukim.finki.emtlab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emtlab.model.exceptions.InvalidBookNameException;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) throws InvalidBookIdException {
        if(this.bookRepository.findById(id).isEmpty())
            throw new InvalidBookIdException(id);

        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) throws InvalidBookNameException {
        if(this.bookRepository.findByName(name).isEmpty())
            throw new InvalidBookNameException(name);

        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) throws InvalidAuthorIdException, IllegalArgumentException {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new InvalidAuthorIdException(authorId));

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(category == null)
            throw new IllegalArgumentException("Category not defined");
        if(availableCopies == null)
            throw new IllegalArgumentException("Number of available copies not defined");

        Book book = new Book(name, category, author, availableCopies);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) throws InvalidAuthorIdException, IllegalArgumentException {
        return save(bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies());
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) throws InvalidAuthorIdException, IllegalArgumentException, InvalidBookIdException {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new InvalidAuthorIdException(authorId));

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException(name);
        if(category == null)
            throw new IllegalArgumentException("Category not defined");
        if(availableCopies == null)
            throw new IllegalArgumentException("Number of available copies not defined");

        Book book = findById(id).get();

        book.setName(name);
        book.setAuthor(author);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        return edit(id, bookDto.getName(), bookDto.getCategory(), bookDto.getAuthorId(), bookDto.getAvailableCopies());
    }

    @Override
    public void decrementAvailableCopies(Long id) throws InvalidBookIdException {
        Book book = findById(id).get();

        if(book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
        }
    }

    @Override
    public Optional<Book> deleteById(Long id) throws InvalidBookIdException {
        Book book = findById(id).get();
        this.bookRepository.delete(book);

        return Optional.of(book);
    }
}
