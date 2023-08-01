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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devdbigode.bibliotecavirtual.entity.Book;
import com.devdbigode.bibliotecavirtual.entity.dto.BookDTO;
import com.devdbigode.bibliotecavirtual.resource.util.URL;
import com.devdbigode.bibliotecavirtual.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookResource {
    
    @Autowired
    private BookService service; 

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        List<Book> list = service.findAll(); 
        List<BookDTO> listDtos = list.stream().map(x -> new BookDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDtos); 
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable String id){
        Book Book = service.findById(id); 
        return ResponseEntity.ok().body(new BookDTO(Book)); 
    }

    @GetMapping(value = "/booksearch")
    public ResponseEntity<List<Book>> findByBooks(@RequestParam(value="text", defaultValue="") String text){
        text = URL.decodeParam(text);
        List<Book> list = service.findByBooks(text); 
        return ResponseEntity.ok().body(list); 
    }

    @PostMapping
    public ResponseEntity<Void> inserEntity(@RequestBody BookDTO objDto){
        Book Book = service.fromDto(objDto); 
        Book = service.insert(Book); 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Book.getId()).toUri();
        return ResponseEntity.created(uri).build(); 
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build(); 
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateEntity(@RequestBody BookDTO objDto, @PathVariable String id){
        Book Book = service.fromDto(objDto);
        Book.setId(id); 
        Book = service.update(Book); 
        return ResponseEntity.noContent().build();    
    }
}
