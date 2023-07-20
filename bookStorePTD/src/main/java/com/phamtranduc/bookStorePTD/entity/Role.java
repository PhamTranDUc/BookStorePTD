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
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;



    @ManyToMany(mappedBy = "roles" )
    private List<User> users;
    public Role(String name) {
        this.name = name;
    }

    public void addUser(User user){
        if(users==null){
            users=new ArrayList<User>();
        }
        users.add(user);
    }

}
