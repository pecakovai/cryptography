package com.example.cryptography.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String username;
    private boolean isAnswered;
    private LocalDateTime dateCreated;


    public Question() {
    }

    public Question(String question, String username) {
        this.question = question;
        this.username = username;
        this.isAnswered = false;
        dateCreated = LocalDateTime.now();
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswered() {
        return isAnswered;
    }
}

