package com.example.cryptography.service.impl;

import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Solution;
import com.example.cryptography.model.User;
import com.example.cryptography.repository.ProblemRepository;
import com.example.cryptography.repository.SolutionRepository;
import com.example.cryptography.repository.UserRepository;
import com.example.cryptography.service.SolutionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {

    private final SolutionRepository solutionRepository;
    private final ProblemRepository problemRepository;

    private final UserRepository userRepository;

    public SolutionServiceImpl(SolutionRepository solutionRepository, ProblemRepository problemRepository, UserRepository userRepository) {
        this.solutionRepository = solutionRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Solution> listAll() {
        return this.solutionRepository.findAll();
    }

    @Override
    public Solution findById(Long id) {
        return this.solutionRepository.findById(id).orElseThrow();
    }

    @Override
    public Solution create(String username, String solution, Long problemId) {
        User user = this.userRepository.findByUsername(username).orElseThrow();
        Problem problem = this.problemRepository.findById(problemId).orElseThrow();
        Solution solution1 = new Solution(user,solution,problem);

        this.solutionRepository.save(solution1);
        return solution1;
    }

    @Override
    public Solution create(Long id, String username, String solution, Long problemId) {
        return null;
    }
}
