package com.example.demo.dto;


/*
 * a DTO is a Data Transfer Object, a simple object that is used to transfer data between processes (e.g. between a client and a server)
 */
public class Dummy2Dto {
    private Integer userId;
    private String name;
    private String surname;
    private Integer anInteger;
    
    public Dummy2Dto() {
        super();
    }
    
    public Dummy2Dto( Integer userId, String name, String surname, Integer anInteger) {
        super();
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.anInteger = anInteger;
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
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAnInteger() {
        return anInteger;
    }

    public void setAnInteger(Integer anInteger) {
        this.anInteger = anInteger;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // to string method override 
    @Override
    public String toString() {
        return "Dummy2Dto [userId=" + userId + ", name=" + name + ", surname=" + surname + ", anInteger=" + anInteger
                + "]";
    }
}
