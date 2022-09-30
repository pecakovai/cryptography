package com.example.cryptography.service;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Question;

import java.util.List;

public interface AnswerService {

    List<Answer> listAll();

    Answer findById(Long id);

    Answer create(String answer, String giveAnswerBy,String answerFor);
    Answer create(Long id, String answer, String giveAnswerBy,String answerFor);
}
