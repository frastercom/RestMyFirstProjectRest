package com.example.restmyfirstproject.domain;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String inputUser;
    private String text;
    private String tag;
    private String outputUser;

    public Message() {
    }

    public Message(String text, String tag) {
        this.inputUser = "";
        this.text = text;
        this.tag = tag;
        this.outputUser = "";
    }

    public Message(String inputUser, String text, String tag, String outputUser) {
        this.inputUser = inputUser;
        this.text = text;
        this.tag = tag;
        this.outputUser = outputUser;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getOutputUser() {
        return outputUser;
    }

    public void setOutputUser(String outputUser) {
        this.outputUser = outputUser;
    }
}
