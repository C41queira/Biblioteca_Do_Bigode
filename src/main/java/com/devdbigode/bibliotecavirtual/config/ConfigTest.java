package com.devdbigode.bibliotecavirtual.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.devdbigode.bibliotecavirtual.entity.Book;
import com.devdbigode.bibliotecavirtual.entity.Order;
import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.entity.dto.ItemDTO;
import com.devdbigode.bibliotecavirtual.entity.dto.UserDTO;
import com.devdbigode.bibliotecavirtual.entity.enums.OrderStatus;
import com.devdbigode.bibliotecavirtual.repository.BookRepository;
import com.devdbigode.bibliotecavirtual.repository.OrderRepository;
import com.devdbigode.bibliotecavirtual.repository.UserRepository;

@Configuration
public class ConfigTest implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        userRepository.deleteAll();
        bookRepository.deleteAll();
        orderRepository.deleteAll();
        
        
        User u1 = new User(null, "Paulo", "532.124.557-29", 
        "paulinhooliveira@gmail.com", "55dft@S");

        Book b1 = new Book(null, "A Lenda de Ruff Ghanor Volume 1", 54.90, 318, "Fantasia/Medieval", 
        "Entretanto nos confins de uma terra inclemente, assolada por monstros e governada pelo terrível dragão Zamir, ergue-se o mosteiro de São Arnaldo. Os clérigos tentam viver em paz, sob o jugo do tirano, quando encontram um estranho garoto. Uma criança selvagem, dotada de poderes misteriosos, que luta como um adulto. Seu nome é como um rugido: Ruff Ghanor.");
        Book b2 = new Book(null, "A Danca dos Dragoes. As Cronicas de Gelo e Fogo - Livro 5", 92.90, 832, "Fantasia/Medieval", 
        "A serie fantastica que conquistou leitores e telespectadores esta de volta: As Cronicas de Gelo e Fogo.");

        bookRepository.saveAll(Arrays.asList(b1, b2));

        Order o1 = new Order(null, sdf.parse("23/08/2022"), OrderStatus.PAID, new UserDTO(u1));
        orderRepository.save(o1); 

        o1.getListItens().addAll(Arrays.asList(new ItemDTO(b2), new ItemDTO(b1)));
        o1.subTotal(); 

        u1.getOrders().add(o1);

        orderRepository.save(o1); 
        userRepository.save(u1);
        
    } 
    
}
