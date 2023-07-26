package com.devdbigode.bibliotecavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.entity.dto.UserDTO;
import com.devdbigode.bibliotecavirtual.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    
    public List<User> findAll(){
        return userRepository.findAll(); 
    }

    public UserDTO fromDto(User obj){
        return new UserDTO(obj);
    }
}
