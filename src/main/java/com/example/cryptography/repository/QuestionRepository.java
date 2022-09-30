package com.example.cryptography.repository;

import com.example.cryptography.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Question findByUsername(String username);
}
