package com.Why.Loan1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Why.Loan1.Entity.Loan;
import com.Why.Loan1.Entity.Transaction;
import com.Why.Loan1.Entity.User;
import com.Why.Loan1.Repository.LoanRepo;
import com.Why.Loan1.Repository.TransactionRepo;
import com.Why.Loan1.Repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	@Autowired private UserRepo userrepo;
	@Autowired private LoanRepo loanrepo;
	@Autowired private TransactionRepo trepo;
	
	@Transactional
	public void add(long loanid, long userid,double amount) {
		
		User u =userrepo.findById(userid).orElse(null);
		Loan l=loanrepo.findById(loanid).orElse(null);
		Transaction tx=new Transaction();
		tx.setTransaction_amount(amount);
		u.setTransactions(tx);
		l.setTransaction(tx);
		trepo.save(tx);
		
		
	}

}
