package com.example.cryptography.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String problem;
    private LocalDateTime dateCreated;

    public Problem() {
    }

    public Problem(String title, String problem) {
        dateCreated = LocalDateTime.now();
        this.title = title;
        this.problem = problem;
    }


}
