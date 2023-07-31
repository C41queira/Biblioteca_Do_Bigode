package com.devdbigode.bibliotecavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devdbigode.bibliotecavirtual.entity.Book;
import com.devdbigode.bibliotecavirtual.entity.dto.BookDTO;
import com.devdbigode.bibliotecavirtual.repository.BookRepository;
import com.devdbigode.bibliotecavirtual.service.exception.ObjectNotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository; 

    public List<Book> findAll(){
        return bookRepository.findAll(); 
    }

    public Book findById(String id){
        Optional<Book> obj = bookRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found")); 
    }

    public Book insert(Book Book){
        return bookRepository.insert(Book); 
    }

    public void delete(String id){
        findById(id); 
        bookRepository.deleteById(id);
    }

    public Book update(Book obj){
        Book newObj = findById(obj.getId());
        updateData(newObj, obj); 
        return bookRepository.save(newObj);
    }

    public void updateData(Book newObj, Book obj){
        newObj.setTitle(obj.getTitle());
        newObj.setPrice(obj.getPrice());
        newObj.setInfo(obj.getInfo());
        newObj.setTheme(obj.getTheme());
        newObj.setPages(obj.getPages());
    }

    public Book fromDto(BookDTO objDto){
        return new Book(objDto.getId(), objDto.getTitle(), objDto.getPrice(),
         objDto.getPages(), objDto.getTheme(), objDto.getInfo());
    }
    
}
