package com.webapp.neo.model;


import javax.persistence.*;


@Entity
@Table(name="IPDetails")
public class IPDetails {
    @Id
    @GeneratedValue
    private Long id;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    @Column(name = "IP")
    private String IP;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
