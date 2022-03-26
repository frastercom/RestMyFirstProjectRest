package com.example.restmyfirstproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("number_phone")
    private String numberPhone;

    @ManyToOne
    @JoinColumn(name = "contacts_id")
    private Clients clients;

    @JsonIgnore
    public Clients getClients() {
        return clients;
    }

    @JsonProperty("cid")
    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
