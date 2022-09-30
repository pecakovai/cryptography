package com.example.cryptography.service;

import com.example.cryptography.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> listAll();

    Question findById(Long id);

    Question create(String question, String username);
    Question update(Long id);


}
