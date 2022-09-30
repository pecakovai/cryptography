package com.example.cryptography.service.impl;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Ask;
import com.example.cryptography.model.Question;
import com.example.cryptography.model.User;
import com.example.cryptography.model.enumerations.TicketStatus;
import com.example.cryptography.repository.AnswerRepository;
import com.example.cryptography.repository.AskRepository;
import com.example.cryptography.repository.QuestionRepository;
import com.example.cryptography.repository.UserRepository;
import com.example.cryptography.service.AskService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AskServiceImpl implements AskService {

    private final AskRepository askRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public AskServiceImpl(AskRepository askRepository, UserRepository userRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.askRepository = askRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Ask> listAll(Long id) throws Exception {
        if (!this.askRepository.findById(id).isPresent())
            throw new Exception();
        return (List<Ask>) this.askRepository.findById(id).get().getQuestionAnswerMap();
    }

    @Override
    public Ask update(String username, Question question, Answer answer) {
        Ask ask = getActiveTicketCart(username);
        ask.getQuestionAnswerMap().put(question,answer);
        this.askRepository.save(ask);
        return ask;
    }

    @Override
    public Ask getActiveTicketCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow();

        return this.askRepository
                .findByUserAndStatus(user, TicketStatus.CREATED)
                .orElseGet(() -> {
                    Ask ask = new Ask(user);
                    return this.askRepository.save(ask);
                });
    }

    @Override
    @Transactional
    public Ask addTicketToTicketCart(String username, Long questionID,Long answerID) throws Exception {
        Ask ask = this.getActiveTicketCart(username);
        Question question = this.questionRepository.findById(questionID).orElseThrow();
        Answer answer = null;
        if(answerID != null) {
            answer = this.answerRepository.findById(answerID).orElseThrow();
        }else{
            answer = new Answer();
            this.answerRepository.save(answer);
        }



        ask.getQuestionAnswerMap().put(question,answer);


        return this.askRepository.save(ask);
    }

    @Override
    public Ask findById(Long id) {
        Ask ask = this.askRepository.findById(id).orElseThrow();

        return ask;
    }
}
