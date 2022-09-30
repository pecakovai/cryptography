package com.example.cryptography.repository;

import com.example.cryptography.model.Ask;
import com.example.cryptography.model.Tests;
import com.example.cryptography.model.User;
import com.example.cryptography.model.enumerations.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestsRepository extends JpaRepository<Tests,Long> {

    Optional<Tests> findByUserAndStatus(User user, TicketStatus ticketStatus);

}
