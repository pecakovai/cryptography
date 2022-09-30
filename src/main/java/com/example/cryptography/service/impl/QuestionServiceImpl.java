package com.example.cryptography.service.impl;

import com.example.cryptography.model.Question;
import com.example.cryptography.repository.QuestionRepository;
import com.example.cryptography.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public List<Question> listAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question findById(Long id) {
        Question question = this.questionRepository.findById(id).orElseThrow();
        return question;
    }

    @Override
    public Question create(String questions, String username) {
        Question question = new Question(questions,username);
        this.questionRepository.save(question);
        return question;
    }

    @Override
    public Question update(Long id) {
        Question question = this.findById(id);
        question.setAnswered(true);
        this.questionRepository.save(question);
        return question;
    }


}
