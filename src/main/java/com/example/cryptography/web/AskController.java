package com.example.cryptography.web;

import com.example.cryptography.model.Ask;
import com.example.cryptography.model.dto.ShowAsks;
import com.example.cryptography.service.AnswerService;
import com.example.cryptography.service.AskService;
import com.example.cryptography.service.QuestionService;
import com.example.cryptography.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("my-questions")
public class AskController {

    private final AskService askService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    public AskController(AskService askService, QuestionService questionService, AnswerService answerService, UserService userService) {
        this.askService = askService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @GetMapping
    public String getAsks(Model model, HttpServletRequest request){
        String username = request.getRemoteUser();
        Ask ask = this.askService.getActiveTicketCart(username);
        List<ShowAsks> showAsksList = new ArrayList<>();
        ask.getQuestionAnswerMap().forEach((key, value) -> showAsksList.add(new ShowAsks(key, value)));
        model.addAttribute("asks",showAsksList);

        return "my-questions";
    }
}
