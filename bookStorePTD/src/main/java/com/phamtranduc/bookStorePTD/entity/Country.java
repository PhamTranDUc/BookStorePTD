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
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "country",fetch = FetchType.EAGER,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    private List<Book> books;

    public void addBook(Book book){
        if(books==null){
            books=new ArrayList<>();
        }
        books.add(book);
        book.setCountry(this);
    }
    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
