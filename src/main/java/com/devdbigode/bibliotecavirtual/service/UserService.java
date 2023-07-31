package com.devdbigode.bibliotecavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.entity.dto.UserDTO;
import com.devdbigode.bibliotecavirtual.repository.UserRepository;
import com.devdbigode.bibliotecavirtual.service.exception.ObjectNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    
    public List<User> findAll(){
        return userRepository.findAll(); 
    }

    public User findById(String id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found")); 
    }

    public User insert(User user){
        return userRepository.insert(user); 
    }

    public void delete(String id){
        findById(id); 
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj); 
        return userRepository.save(newObj);
    }

    public User fromDto(UserDTO obj){
        return new User(obj.getId(), obj.getName(), obj.getDocument(), obj.getEmail(), obj.getSenha());
    }

    public void updateData(User newObj, User obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setDocument(obj.getDocument());
        newObj.setSenha(obj.getSenha());
    }
}
