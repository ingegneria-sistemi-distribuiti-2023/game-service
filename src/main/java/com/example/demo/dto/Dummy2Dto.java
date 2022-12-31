package com.example.demo.dto;

import java.util.Optional;

/*
 * a DTO is a Data Transfer Object, a simple object that is used to transfer data between processes (e.g. between a client and a server)
 */
public class Dummy2Dto {
    private String name;
    private String surname;
    
    public Dummy2Dto() {
        super();
    }
    
    public Dummy2Dto(String name, Optional<String> surname) {
        super();
        this.name = name;
        this.surname = surname.orElse("not provided");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(Optional<String> surname) {
        this.surname = surname.orElse("not provided");
    }
}
