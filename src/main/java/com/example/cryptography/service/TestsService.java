package com.example.cryptography.service;

import com.example.cryptography.model.Problem;
import com.example.cryptography.model.Solution;
import com.example.cryptography.model.Tests;

import javax.transaction.Transactional;
import java.util.List;

public interface TestsService {
    List<Tests> listAll(Long id) throws Exception;

    Tests update(String username, Problem problem, Solution solution);

    Tests getActiveTicketCart(String username);

    @Transactional
    Tests addTicketToTicketCart(String username, Long problemID, Long solutionID) throws Exception;

    Tests findById(Long id);
}
