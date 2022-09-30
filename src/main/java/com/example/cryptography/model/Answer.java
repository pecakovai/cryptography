package com.example.cryptography.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;
    @ManyToOne
    @JoinColumn(name = "give_answer_by_username")
    private User giveAnswerBy;
    @ManyToOne
    @JoinColumn(name = "answer_for_username")
    private User answerFor;
    private LocalDateTime dateCreated;

    public Answer() {
    }

    public Answer(String answer, User giveAnswerBy, User answerFor) {
        this.answer = answer;
        this.giveAnswerBy = giveAnswerBy;
        this.answerFor = answerFor;
        dateCreated = LocalDateTime.now();
    }
}

