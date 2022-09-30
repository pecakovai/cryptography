package com.example.cryptography.model;


import com.example.cryptography.model.enumerations.TicketStatus;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private Map<Question,Answer> questionAnswerMap;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ask() {
    }

    public Ask(User user) {
        this.user = user;
        this.questionAnswerMap = new HashMap<>();
        this.status = TicketStatus.CREATED;
        dateCreated = LocalDateTime.now();
    }
}

