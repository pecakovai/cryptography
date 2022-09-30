package com.example.cryptography.service;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Solution;

import java.util.List;

public interface SolutionService {

    List<Solution> listAll();

    Solution findById(Long id);

    Solution create(String username, String solution, Long problemId);
    Solution create(Long id, String username, String solution, Long problemId);
}
