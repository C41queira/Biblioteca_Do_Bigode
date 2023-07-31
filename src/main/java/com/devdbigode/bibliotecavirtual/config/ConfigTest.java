package com.devdbigode.bibliotecavirtual.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.devdbigode.bibliotecavirtual.entity.Book;
import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.repository.BookRepository;
import com.devdbigode.bibliotecavirtual.repository.UserRepository;

@Configuration
public class ConfigTest implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        bookRepository.deleteAll();
        
        
        User u1 = new User(null, "Paulo", "532.124.557-29", 
        "paulinhooliveira@gmail.com", "55dft@S");

        Book b1 = new Book(null, "A Lenda de Ruff Ghanor Volume 1", 54.90, 318, "Fantasia/Medieval", 
        "Entretanto nos confins de uma terra inclemente, assolada por monstros e governada pelo terrível dragão Zamir, ergue-se o mosteiro de São Arnaldo. Os clérigos tentam viver em paz, sob o jugo do tirano, quando encontram um estranho garoto. Uma criança selvagem, dotada de poderes misteriosos, que luta como um adulto. Seu nome é como um rugido: Ruff Ghanor.");
        Book b2 = new Book(null, "A Danca dos Dragoes. As Cronicas de Gelo e Fogo - Livro 5", 92.90, 832, "Fantasia/Medieval", 
        "A serie fantastica que conquistou leitores e telespectadores esta de volta: As Cronicas de Gelo e Fogo.");


        userRepository.save(u1);
        bookRepository.saveAll(Arrays.asList(b1, b2)); 
    } 
    
}
