package com.phamtranduc.bookStorePTD.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH} )
    @JoinTable(
           name = "author_book",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;


    @Column(name = "price")
    private Double price;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "book_category",
            joinColumns =@JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @Column(name = "img")
    private String pathImg;




    public void addAuthor(Author author)
    {
        if(authors==null){
            authors=new ArrayList<Author>();

        }
        authors.add(author);

    }
    public void addCategory(Category category){
        if(categories==null){
            categories=new ArrayList<Category>();
        }
        categories.add(category);
    }
    public Book(String name, Double price, Boolean availability, int quantity, Country country,String description) {
        this.name = name;

        this.price = price;
        this.availability = availability;
        this.quantity = quantity;
        this.country = country;
        this.description=description;
    }

    public Book(String name, Double price, Boolean availability, int quantity) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.quantity = quantity;
    }
}
