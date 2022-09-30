package com.example.cryptography.web;

import com.example.cryptography.model.Answer;
import com.example.cryptography.model.Question;
import com.example.cryptography.service.AnswerService;
import com.example.cryptography.service.AskService;
import com.example.cryptography.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AskService askService;

    public AnswerController(AnswerService answerService, QuestionService questionService, AskService askService) {
        this.answerService = answerService;
        this.questionService = questionService;
        this.askService = askService;
    }

    @GetMapping("answers/{id}")
    public String showQuestionForAnswer(@PathVariable Long id, Model model){
        Question question = this.questionService.findById(id);
        model.addAttribute("question",question);

        return "answer-quest";
    }

    @PostMapping("answers/{id}")
    public String addAnswer(@PathVariable Long id, @RequestParam String answer, Model model, HttpServletRequest request){
        Question question = this.questionService.update(id);
        Map<Question,Answer> map = this.askService.getActiveTicketCart(question.getUsername()).getQuestionAnswerMap();
        Answer answer1 = map.get(question);
        Answer answer2 = this.answerService.create(answer1.getId(),answer,request.getRemoteUser(),question.getUsername());
        this.askService.update(question.getUsername(),question,answer2);
        model.addAttribute("question",question);

        return "redirect:/question/all-question";
    }
}
