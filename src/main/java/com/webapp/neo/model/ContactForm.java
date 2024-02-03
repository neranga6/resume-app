package com.webapp.neo.model;


import javax.persistence.*;


@Entity
@Table(name = "contact_form")
public class ContactForm {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "message")
    private String message;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
