package com.example.cryptography.model.dto;

import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Solution;
import lombok.Data;

@Data
public class ShowTests {

    Problem problem;
    Solution solution;

    public ShowTests(Problem problem, Solution solution) {
        this.problem = problem;
        this.solution = solution;
    }
}
