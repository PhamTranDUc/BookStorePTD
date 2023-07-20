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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(
            mappedBy = "categories"
    )
    private List<Book> books;

    public Category(String name) {
        this.name = name;
    }
    public void addBook(Book book){
        if(books==null){
            books=new ArrayList<Book>();
        }
        books.add(book);
    }
}
