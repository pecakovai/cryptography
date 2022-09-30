package com.example.cryptography.repository;

import com.example.cryptography.model.Ask;
import com.example.cryptography.model.User;
import com.example.cryptography.model.enumerations.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AskRepository extends JpaRepository<Ask,Long> {

    Optional<Ask> findByUserAndStatus(User user, TicketStatus ticketStatus);
}
