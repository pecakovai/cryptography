package com.example.cryptography.web;


import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Solution;
import com.example.cryptography.service.ProblemService;
import com.example.cryptography.service.SolutionService;
import com.example.cryptography.service.TestsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SolutionController {

    private final SolutionService solutionService;
    private final ProblemService problemService;

    private final TestsService testsService;
    public SolutionController(SolutionService solutionService, ProblemService problemService, TestsService testsService) {
        this.solutionService = solutionService;
        this.problemService = problemService;
        this.testsService = testsService;
    }


    @GetMapping("solution/{id}")
    public String showQuestionForAnswer(@PathVariable Long id, Model model){
        Problem problem = this.problemService.findById(id);
        model.addAttribute("problem",problem);

        return "add-solve";
    }

    @PostMapping("solution/{id}")
    public String addAnswer(@PathVariable Long id, @RequestParam String solution, Model model, HttpServletRequest request) throws Exception {
        String username = request.getRemoteUser();
        Problem problem = this.problemService.findById(id);
        Solution solution1 = this.solutionService.create(username,solution,id);
        this.testsService.addTicketToTicketCart(username,id,solution1.getId());

        return "redirect:/my-solutions";
    }

    @GetMapping("show-solutions/{id}")
    public String showAllSolution(@PathVariable Long id,Model model){
        List<Solution> solutions = this.solutionService.listAll().stream().filter(s -> s.getProblem().getId() == id).collect(Collectors.toList());
        String problemTitle = this.problemService.findById(id).getTitle();
        model.addAttribute("solutions",solutions);
        model.addAttribute("problemTitle",problemTitle);

        return "show-solutions";
    }
}
