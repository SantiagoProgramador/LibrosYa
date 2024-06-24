package com.riwi.LibrosYa.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.LibrosYa.api.dto.request.LoanRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;
import com.riwi.LibrosYa.domain.model.Loan;
import com.riwi.LibrosYa.domain.repositories.LoanRepository;
import com.riwi.LibrosYa.infrastructure.abstracts.ILoanService;
import com.riwi.LibrosYa.infrastructure.mappers.LoanMapper;
import com.riwi.LibrosYa.infrastructure.persistence.LoanEntity;
import com.riwi.LibrosYa.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService{

    @Autowired
    private final LoanRepository loanRepository;

    @Autowired
    private final LoanMapper loanMapper;

    @Override
    public Page<LoanResponse> getAll(int size, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);

        return this.loanRepository.findAll(pageable).map(loan -> loanMapper.loanToLoanResponse(loanMapper.loanEntityToLoan(loan)));
    }

    @Override
    public LoanResponse getById(Long id) {
        Loan loan = loanMapper.loanEntityToLoan(findLoanEntity(id));

        return loanMapper.loanToLoanResponse(loan);
    }

    private LoanEntity findLoanEntity(Long id){

        return this.loanRepository.findById(id).orElseThrow(()-> new IdNotFoundException("loans"));
    }

    @Override
    public LoanResponse create(LoanRequest request) {
        Loan loan = loanMapper.loanRequestToLoan(request);

        return loanMapper.loanToLoanResponse(loanMapper.loanEntityToLoan(this.loanRepository.save(loanMapper.loanToLoanEntity(loan))));
    }

    @Override
    public LoanResponse update(Long id, LoanRequest request) {
        Loan loan = loanMapper.loanEntityToLoan(findLoanEntity(id));
        loan = loanMapper.loanRequestToLoan(request);

        return loanMapper.loanToLoanResponse(loanMapper.loanEntityToLoan(this.loanRepository.save(loanMapper.loanToLoanEntity(loan))));
    }

    @Override
    public void delete(Long id) {
        LoanEntity loanEntity = findLoanEntity(id);

        this.loanRepository.delete(loanEntity);
    }
    
}
