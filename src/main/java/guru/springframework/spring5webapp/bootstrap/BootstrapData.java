package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain", "1234567");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author tolkien = new Author("J", "Tolkien");
        Book lotr = new Book("Lord of the rings", "1");
        tolkien.getBooks().add(lotr);
        lotr.getAuthors().add(tolkien);
        authorRepository.save(tolkien);
        bookRepository.save(lotr);

        Publisher somePublisher = new Publisher("some", "publisher", "hdskjhaf","fkjdsf","dkjdf");

        ddd.setPublisher(somePublisher);
        somePublisher.getBooks().add(ddd);

        publisherRepository.save(somePublisher);
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("NUmber of Authors " + authorRepository.count());
        System.out.println("Number of Publisher " + publisherRepository.count());
        System.out.println("Number of Books for publisher " + somePublisher.getBooks().size());

    }
}
