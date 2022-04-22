package com.sist.vo;

public class BookVO {

    private int book_id;
    private String book_title;
    private String book_img;
    private int book_sold;
    private String book_link;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public int getBook_sold() {
        return book_sold;
    }

    public void setBook_sold(int book_sold) {
        this.book_sold = book_sold;
    }

    public String getBook_link() {
        return book_link;
    }

    public void setBook_link(String book_link) {
        this.book_link = book_link;
    }
}
