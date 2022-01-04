package com.example.bookstore;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Shoppingcart extends LitePalSupport implements Serializable {
    private int id;
    private int book_id;
    private int quantity;

    public Shoppingcart(int book_id, int quantity) {
        this.book_id = book_id;
        this.quantity = quantity;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
