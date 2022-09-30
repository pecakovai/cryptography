package com.example.cryptography.web;

import com.example.cryptography.model.Problem;
import com.example.cryptography.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/test/problems")
    public String getProblems(Model model){
        List<Problem> problemList = this.problemService.listAll().stream().sorted(Comparator.comparing(Problem::getDateCreated)).collect(Collectors.toList());
        model.addAttribute("problems",problemList);
        return "problems";
    }

    @GetMapping("/test/problem")
    public String getProblems(@RequestParam String searchProblem, Model model){
        List<Problem> problemList = this.problemService.listAll().stream().filter(p -> p.getTitle().contains(searchProblem))
                .sorted(Comparator.comparing(Problem::getDateCreated)).collect(Collectors.toList());
        model.addAttribute("problems",problemList);
        return "problems";
    }

    @GetMapping("/test/add-problem")
    public String addAgencies(Model model) {
        model.addAttribute("title","Add problem");

        return "add-problem";
    }

    @PostMapping("/test/problems")
    public String create(@RequestParam String title,
                         @RequestParam String problem) {
        this.problemService.create(title,problem);
        return "redirect:/test/problems";

    }
    @PostMapping("/test/problems/{id}")
    public String update(@PathVariable Long id, @RequestParam String title,
                         @RequestParam String problem) {
        this.problemService.create(title,problem);
        return "redirect:/test/problems";

    }

    @GetMapping("/test/{id}/problems/edit")
    public String showEdit(@PathVariable Long id,Model model) {
        Problem problem = this.problemService.findById(id);

        model.addAttribute("problem",problem);
        model.addAttribute("title","Edit problem");



        return "add-problem";
    }
}
