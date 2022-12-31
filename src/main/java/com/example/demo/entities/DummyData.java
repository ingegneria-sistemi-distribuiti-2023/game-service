package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * this class represents an Entity (or domain object) in the application
 * it is a Java representation of a database table
 */
@Entity
@Table(name = "dummy_data")
public class DummyData {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in MySQL
    private int id;

    private String data1;

    private int data2;

    public DummyData() {
    }

    public DummyData(String data1, int data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public int getData2() {
        return data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return "DummyData [data1=" + data1 + ", data2=" + data2 + ", id=" + id + "]";
    }
}
