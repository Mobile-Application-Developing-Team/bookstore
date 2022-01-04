package com.example.bookstore;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.io.Serializable;

public class Book extends LitePalSupport implements Serializable {

    private int id;

    private String name;

    private String author;

    private double price;

    private String type;//书的类型

    public Book(int id,String name,String author,double price,String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
