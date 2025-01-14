package com.yossavorn.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class Loans extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="loan_number")
    private String loanNumber;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private int totalLoan;

    @Column(name="amount_paid")
    private int amountPaid;

    @Column(name="outstanding_amount")
    private int outstandingAmount;

}
