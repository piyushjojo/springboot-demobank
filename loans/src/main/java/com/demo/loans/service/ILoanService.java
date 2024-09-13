package com.demo.loans.service;

import com.demo.loans.dto.LoanDto;
import org.springframework.stereotype.Service;

@Service
public interface ILoanService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoanData(String mobileNumber);

    boolean updateLoanDetails(LoanDto loanDto);

    boolean deleteLoanDetails(String mobileNumber);
}
