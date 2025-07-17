package com.Why.Loan1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Why.Loan1.Entity.PaidLoans;
import com.Why.Loan1.Entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {

}
