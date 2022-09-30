package com.example.cryptography.service.impl;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.User;
import com.example.cryptography.repository.AnswerRepository;
import com.example.cryptography.repository.UserRepository;
import com.example.cryptography.service.AnswerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service

public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Answer> listAll() {
        return this.answerRepository.findAll();
    }

    @Override
    public Answer findById(Long id) {
        Answer answer = this.answerRepository.findById(id).orElseThrow();
        return answer;
    }

    @Override
    public Answer create(String answer, String giveAnswerBy,String answerFor) {
        User answerBy = this.userRepository.findByUsername(giveAnswerBy).orElseThrow();
        User answerTo = this.userRepository.findByUsername(answerFor).orElseThrow();
        Answer answer1 = new Answer(answer,answerBy,answerTo);
        this.answerRepository.save(answer1);
        return answer1;
    }

    @Override
    public Answer create(Long id, String answer, String giveAnswerBy, String answerFor) {
        Answer answer1 = this.answerRepository.findById(id).orElseThrow();
        User answerBy = this.userRepository.findByUsername(giveAnswerBy).orElseThrow();
        User answerTo = this.userRepository.findByUsername(answerFor).orElseThrow();
        answer1.setAnswer(answer);
        answer1.setGiveAnswerBy(answerBy);
        answer1.setAnswerFor(answerTo);
        answer1.setDateCreated(LocalDateTime.now());

        this.answerRepository.save(answer1);
        return answer1;
    }
}
