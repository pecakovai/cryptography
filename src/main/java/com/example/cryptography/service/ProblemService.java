package com.example.cryptography.service;

import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Question;

import java.util.List;

public interface ProblemService {
    List<Problem> listAll();

    Problem findById(Long id);

    Problem create(String title, String problem);
    Problem update(Long id,String title,String problem);
}
