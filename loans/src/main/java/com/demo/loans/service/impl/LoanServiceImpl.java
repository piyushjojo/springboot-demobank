package com.demo.loans.service.impl;

import com.demo.loans.constants.LoansConstants;
import com.demo.loans.dto.LoanDto;
import com.demo.loans.entity.Loans;
import com.demo.loans.exception.LoanAlreadyExistException;
import com.demo.loans.exception.ResourceNotFoundException;
import com.demo.loans.mapper.LoanMapper;
import com.demo.loans.repository.LoanRepository;
import com.demo.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loanRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()){
            throw new LoanAlreadyExistException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    public LoanDto fetchLoanData(String mobileNumber){
        Loans loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Details", "Mobile Number", mobileNumber));
        return LoanMapper.mapLoansToLoanDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoanDetails(LoanDto loanDto) {
        Loans loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("LoanDetails", "Loan Number", loanDto.getLoanNumber()));
        LoanMapper.mapLoanDtoToLoans(loanDto, loan);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoanDetails(String mobileNumber) {
        Loans loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Details", "Loan Number", mobileNumber));
        loanRepository.delete(loan);
        return true ;
    }


}
