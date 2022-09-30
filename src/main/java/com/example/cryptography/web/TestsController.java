package com.example.cryptography.web;

import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Solution;
import com.example.cryptography.model.Tests;
import com.example.cryptography.model.dto.ShowAsks;
import com.example.cryptography.model.dto.ShowTests;
import com.example.cryptography.service.ProblemService;
import com.example.cryptography.service.SolutionService;
import com.example.cryptography.service.TestsService;
import com.example.cryptography.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestsController {

    private final TestsService testsService;
    private final ProblemService problemService;
    private final SolutionService solutionService;
    private final UserService userService;

    public TestsController(TestsService testsService, ProblemService problemService, SolutionService solutionService, UserService userService) {
        this.testsService = testsService;
        this.problemService = problemService;
        this.solutionService = solutionService;
        this.userService = userService;
    }

    @GetMapping("my-solutions")
    public String getAsks(Model model, HttpServletRequest request){
        String username = request.getRemoteUser();
        Tests test = this.testsService.getActiveTicketCart(username);
        List<ShowTests> showTestsList = new ArrayList<>();
        test.getProblemSolutionMap().forEach((key, value) -> showTestsList.add(new ShowTests(key, value)));
        model.addAttribute("tests",showTestsList);

        return "my-solutions";
    }

    @GetMapping("tests/{idP}/{idS}")
    public String showProblemAndSolution(@PathVariable Long idP,@PathVariable Long idS,Model model){
        Problem problem = this.problemService.findById(idP);
        Solution solution = this.solutionService.findById(idS);

        model.addAttribute("problem",problem);
        model.addAttribute("solution",solution);

        return "problem-solution";
    }
}
