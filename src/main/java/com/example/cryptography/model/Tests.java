package com.example.cryptography.model;


import com.example.cryptography.model.enumerations.TicketStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class Tests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;


    @ManyToMany
    private Map<Problem,Solution> problemSolutionMap;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Tests() {
    }

    public Tests(User user) {
        this.user = user;
        this.problemSolutionMap = new HashMap<>();
        this.status = TicketStatus.CREATED;
        dateCreated = LocalDateTime.now();
    }
}


