package com.devdbigode.bibliotecavirtual.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devdbigode.bibliotecavirtual.entity.User;
import com.devdbigode.bibliotecavirtual.entity.dto.UserDTO;
import com.devdbigode.bibliotecavirtual.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @Autowired
    private UserService service; 

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll(); 
        List<UserDTO> listDtos = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDtos); 
    }
}
