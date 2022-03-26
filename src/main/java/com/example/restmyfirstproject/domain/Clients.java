package com.example.restmyfirstproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("bankAccaunt")
    @OneToMany(mappedBy = "client", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<BankAccaunt> bankAccaunt;

    @JsonProperty("contacts")
    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacts> contacts;

    public Clients()
    {

    }

    public Clients(Long id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BankAccaunt> getBankAccaunt() {
        return bankAccaunt;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setBankAccaunt(List<BankAccaunt> bankAccaunt) {
        this.bankAccaunt=bankAccaunt;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
