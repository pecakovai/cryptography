package com.example.cryptography.service;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Ask;
import com.example.cryptography.model.Question;

import java.util.List;

public interface AskService {

    Ask getActiveTicketCart(String username);

    Ask addTicketToTicketCart(String username, Long questionID,Long answerID)throws Exception;
    Ask findById(Long id);

    List<Ask> listAll(Long id) throws Exception;
    Ask update(String username, Question question, Answer answer);
}
