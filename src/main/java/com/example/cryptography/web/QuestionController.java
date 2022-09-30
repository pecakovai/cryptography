package com.example.cryptography.web;


import com.example.cryptography.model.Ask;
import com.example.cryptography.model.Question;
import com.example.cryptography.model.User;
import com.example.cryptography.model.enumerations.Role;
import com.example.cryptography.service.AskService;
import com.example.cryptography.service.QuestionService;
import com.example.cryptography.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;
    private final AskService askService;

    public QuestionController(QuestionService questionService, UserService userService, AskService askService) {
        this.questionService = questionService;
        this.userService = userService;
        this.askService = askService;
    }


    @GetMapping
    public String getQuestion(Model model){
        return "questions";
    }

    @PostMapping
    public String addQuestion(@RequestParam String question, HttpServletRequest req) throws Exception {
        String username = req.getRemoteUser();
        System.out.println("IN QUESTION ADD");
        if (username != null){
            Question q = this.questionService.create(question,username);
            System.out.println("Question: " + q.getQuestion());
            Ask ask = this.askService.addTicketToTicketCart(username,q.getId(),null);
            return "redirect:/home";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("all-question")
    public String getAllQuestion(Model model,HttpServletRequest request){
        String username = request.getRemoteUser();
        User user = this.userService.findByUsername(username);
        List<Question> questionList = new ArrayList<>();
        List<Question> answered = new ArrayList<>();
        if(user.getRole().equals(Role.ROLE_ADMIN)){
            questionList = this.questionService.listAll().stream().filter(q -> !q.isAnswered()).collect(Collectors.toList());
            answered = this.questionService.listAll().stream().filter(Question::isAnswered).collect(Collectors.toList());

        }
        model.addAttribute("questions",questionList);
        model.addAttribute("answered",answered);
        return "all-questions";
    }
}
