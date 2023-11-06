package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
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
        Author prafull = new Author();
        prafull.setFirstName("Prafull");
        prafull.setLastName("Salunke");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("`123456789");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("5432167890");

        Publisher pub1 = new Publisher();
        pub1.setPublisherName("Creative Publishers");
        pub1.setAddress("501, Street, Artesia, California, USA");
        pub1.setCity("Artesia");
        pub1.setState("California");
        pub1.setZip("90007");

        Publisher pub2 = new Publisher();
        pub2.setPublisherName("Penguin Creatives");
        pub2.setAddress("Suite 1, LA, California, USA");
        pub2.setCity("Los Angeles");
        pub2.setState("California");
        pub2.setZip("90001");

        Author prafullSaved = authorRepository.save(prafull);
        Author rodSaved = authorRepository.save(rod);
        Book dddSaved = bookRepository.save(ddd);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher pub1Saved = publisherRepository.save(pub1);
        Publisher pub2Saved = publisherRepository.save(pub2);

        prafullSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(prafullSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        authorRepository.save(prafullSaved);
        authorRepository.save(rodSaved);
        publisherRepository.save(pub1Saved);
        publisherRepository.save(pub2Saved);

        dddSaved.setPublisher(pub1Saved);
        noEJBSaved.setPublisher(pub2Saved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);


        System.out.println("In Bootstrap");
        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());
        System.out.println("Publisher Count:" + publisherRepository.count());
    }
}
