package com.mlefevre.app.archiving.entity;

import javax.persistence.*;

@Entity
@Table(name = "ENTITY")
public class EntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;


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

    @Override
    public String toString() {
        return "EntityClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
