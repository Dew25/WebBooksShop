/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Melnikov
 */
@Entity
@XmlRootElement
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Integer publishedYear;
    private String isbn;
    private int price;
    @OneToOne
    private Cover cover;
    @OneToOne
    private Text text;
    private int discount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date discountDate;
    private int discountDuration;
    
    

    public Book() {
    }

    public Book(String name, String author, Integer publishedYear, String isbn, int price, Cover cover, Text text) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.price = price;
        this.cover = cover;
        this.text = text;
    }
    
    public Book(String name, String author, Integer publishedYear, String isbn, double price, Cover cover, Text text) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.setPriceDouble(price);
        this.cover = cover;
        this.text = text;
    }
    public Book(String name, String author, Integer publishedYear, String isbn, String price, Cover cover, Text text) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.setPriceStr(price);
        this.cover = cover;
        this.text = text;
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

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "id=" + id 
                + ", name=" + name 
                + ", author=" + author 
                + ", publishedYear=" + publishedYear 
                + ", isbn=" + isbn 
                + ", price=" + price 
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.author);
        hash = 41 * hash + Objects.hashCode(this.publishedYear);
        hash = 41 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.publishedYear, other.publishedYear)) {
            return false;
        }
        return true;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public int getPrice() {
        return price;
    }
    public double getPriceDouble() {
        return (double)this.price/100;
    }
    public String getPriceStr() {
        Double dPrice = (double)this.price/100;
        return dPrice.toString();
    }

    public void setPriceStr(String price) {
        price = price.trim().replaceAll(",", ".");
        try {
            double dPrice = Double.parseDouble(price);
            this.price = (int)(dPrice * 100);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неправильный формат числа");
        }
    }
    public void setPriceDouble(double price) {
        this.price = (int)(price * 100);
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }

    public int getDiscountDuration() {
        return discountDuration;
    }

    public void setDiscountDuration(int discountDuration) {
        this.discountDuration = discountDuration;
    }
    
    
    
}
