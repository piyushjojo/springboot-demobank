package com.demo.loans.mapper;

import com.demo.loans.dto.LoanDto;
import com.demo.loans.entity.Loans;

public class LoanMapper {

    public static Loans mapLoanDtoToLoans(LoanDto loanDto, Loans loans){
        loans.setLoanNumber(loanDto.getLoanNumber());
        loans.setLoanType(loanDto.getLoanType());
        loans.setTotalLoan(loanDto.getTotalLoan());
        loans.setAmountPaid(loanDto.getAmountPaid());
        loans.setMobileNumber(loanDto.getMobileNumber());
        loans.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loans;
    }

    public static LoanDto mapLoansToLoanDto(Loans loans, LoanDto loanDto){
        loanDto.setLoanNumber(loans.getLoanNumber());
        loanDto.setLoanType(loans.getLoanType());
        loanDto.setTotalLoan(loans.getTotalLoan());
        loanDto.setAmountPaid(loans.getAmountPaid());
        loanDto.setMobileNumber(loans.getMobileNumber());
        loanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDto;
    }
}
