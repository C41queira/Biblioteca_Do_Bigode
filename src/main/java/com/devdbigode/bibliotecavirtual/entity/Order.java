package com.devdbigode.bibliotecavirtual.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devdbigode.bibliotecavirtual.entity.dto.ItemDTO;
import com.devdbigode.bibliotecavirtual.entity.dto.UserDTO;
import com.devdbigode.bibliotecavirtual.entity.enums.OrderStatus;

@Document
public class Order implements Serializable{
    
    @Id
    private String id; 
    private Date moment; 
    private OrderStatus orderStatus;
    private Double price = 0.0; 

    private UserDTO user; 

    private List<ItemDTO> listItens = new ArrayList<>(); 
    
    public Order() {
    }

    public Order(String id, Date moment, OrderStatus orderStatus, UserDTO user) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.user = user; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ItemDTO> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemDTO> listItens) {
        this.listItens = listItens;
    }

    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    } 

    public void subTotal(){
        Double priceTotal = 0.0; 

        for (ItemDTO itemDTO : listItens) {
            priceTotal += itemDTO.getPrice();  
        }

        this.price = priceTotal; 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
