package com.example.cryptography.repository;

import com.example.cryptography.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution,Long> {
}
