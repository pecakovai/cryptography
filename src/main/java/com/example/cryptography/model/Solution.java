package com.example.cryptography.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username")
    private User user;

    @Column(length = 4000)
    private String solution;

    private LocalDateTime dateCreated;

    @ManyToOne
    private Problem problem;

    public Solution() {
    }

    public Solution(User user, String solution,Problem problem) {
        this.user = user;
        this.solution = solution;
        dateCreated = LocalDateTime.now();
        this.problem = problem;
    }
}

