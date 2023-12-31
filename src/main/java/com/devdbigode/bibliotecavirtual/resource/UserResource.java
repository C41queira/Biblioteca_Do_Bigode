package com.devdbigode.bibliotecavirtual.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devdbigode.bibliotecavirtual.entity.Order;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id); 
        return ResponseEntity.ok().body(new UserDTO(user)); 
    }

    @GetMapping(value = "/{id}/orders")
    public ResponseEntity<List<Order>> findOrders(@PathVariable String id){
        User user = service.findById(id); 
        List<Order> list = user.getOrders(); 
        return ResponseEntity.ok().body(list); 
    }

    @PostMapping
    public ResponseEntity<Void> inserEntity(@RequestBody UserDTO objDto){
        User user = service.fromDto(objDto); 
        user = service.insert(user); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build(); 
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateEntity(@RequestBody UserDTO objDto, @PathVariable String id){
        User user = service.fromDto(objDto);
        user.setId(id); 
        user = service.update(user); 
        return ResponseEntity.noContent().build();    
    }
}
