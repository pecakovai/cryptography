package com.example.cryptography.service.impl;

import com.example.cryptography.model.*;
import com.example.cryptography.model.enumerations.TicketStatus;
import com.example.cryptography.repository.*;
import com.example.cryptography.service.TestsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestsServiceImpl implements TestsService {

    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;
    private final TestsRepository testsRepository;

    private final UserRepository userRepository;

    public TestsServiceImpl(ProblemRepository problemRepository, SolutionRepository solutionRepository, TestsRepository testsRepository, UserRepository userRepository) {
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
        this.testsRepository = testsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tests> listAll(Long id) throws Exception {
        if (!this.testsRepository.findById(id).isPresent())
            throw new Exception();
        return (List<Tests>) this.testsRepository.findById(id).get().getProblemSolutionMap();
    }

    @Override
    public Tests update(String username, Problem problem, Solution solution) {
        Tests test = getActiveTicketCart(username);
        test.getProblemSolutionMap().put(problem,solution);
        this.testsRepository.save(test);
        return test;
    }

    @Override
    public Tests getActiveTicketCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow();

        return this.testsRepository
                .findByUserAndStatus(user, TicketStatus.CREATED)
                .orElseGet(() -> {
                    Tests Tests = new Tests(user);
                    return this.testsRepository.save(Tests);
                });
    }

    @Override
    @Transactional
    public Tests addTicketToTicketCart(String username, Long problemID, Long solutionID) throws Exception {
        Tests test = this.getActiveTicketCart(username);
        Problem problem = this.problemRepository.findById(problemID).orElseThrow();
        Solution solution =  this.solutionRepository.findById(solutionID).orElseThrow();

        test.getProblemSolutionMap().put(problem,solution);

        return this.testsRepository.save(test);
    }

    @Override
    public Tests findById(Long id) {
        Tests test = this.testsRepository.findById(id).orElseThrow();

        return test;
    }
}
