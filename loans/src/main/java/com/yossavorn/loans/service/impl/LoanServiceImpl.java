package com.yossavorn.loans.service.impl;

import com.yossavorn.loans.dto.LoansDto;
import com.yossavorn.loans.entity.Loans;
import com.yossavorn.loans.exception.DuplicateRecordException;
import com.yossavorn.loans.exception.NotFoundException;
import com.yossavorn.loans.mapper.LoansMapper;
import com.yossavorn.loans.repository.LoanRepository;
import com.yossavorn.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoansService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(LoansDto loansDto) {
        Optional<Loans> existedLoan = loanRepository.findByMobileNumber(loansDto.getMobileNumber());

        if(existedLoan.isPresent()) {
            throw new DuplicateRecordException("Loan with mobile number " + loansDto.getMobileNumber() + " already exists.");
        }

        Loans loanToSave = LoansMapper.mapToLoans(loansDto, new Loans());


        loanRepository.save(loanToSave);

    }

    @Override
    public LoansDto getOneLoan(String mobileNumber) {
        Loans loan = findOneLoanByMobileNumber(mobileNumber);
        return LoansMapper.mapToLoansDto(loan, new LoansDto());
    }

    @Override
    public void updateLoan(String mobileNumber, LoansDto cardDto) {
        Loans loan = findOneLoanByMobileNumber(mobileNumber);

        Loans cardsToSave = LoansMapper.mapToLoans(cardDto, loan);
        loanRepository.save(cardsToSave);
    }

    @Override
    public void deleteLoan(String mobileNumber) {
        Loans loan = findOneLoanByMobileNumber(mobileNumber);
        //Loans[] cards = new Loans[]{card};
        // cardRepository.deleteAll(List.of(cards));
        loanRepository.delete(loan);
    }

    private Loans findOneLoanByMobileNumber(String mobileNumber) {
        Optional<Loans> loan = loanRepository.findByMobileNumber(mobileNumber);

        if(loan.isEmpty()) {
            throw new NotFoundException("Loan", "mobile number", mobileNumber);
        }
        return loan.get();

    }
}
