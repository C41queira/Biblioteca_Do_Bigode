package com.devdbigode.bibliotecavirtual.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.repository.UserRepository;

@Configuration
public class ConfigTest implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        
        User u1 = new User(null, "Paulo", "532.124.557-29", "paulinhooliveira@gmail.com", "55dft@S");

        userRepository.save(u1); 
    } 
    
}
