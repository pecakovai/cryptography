package com.example.cryptography.service.impl;

import com.example.cryptography.model.Problem;
import com.example.cryptography.repository.ProblemRepository;
import com.example.cryptography.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public List<Problem> listAll() {
        return this.problemRepository.findAll();
    }

    @Override
    public Problem findById(Long id) {
        return this.problemRepository.findById(id).orElseThrow();
    }

    @Override
    public Problem create(String title, String problem) {
        Problem problem1 = new Problem(title,problem);
        return this.problemRepository.save(problem1);
    }

    @Override
    public Problem update(Long id,String title,String problem) {
        Problem problem1 = this.findById(id);
        problem1.setTitle(title);
        problem1.setProblem(title);

        return this.problemRepository.save(problem1);
    }
}
