package com.Why.Loan1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Why.Loan1.Entity.Loan;
import com.Why.Loan1.Entity.PaidLoans;
import com.Why.Loan1.Entity.User;
import com.Why.Loan1.Repository.LoanRepo;
import com.Why.Loan1.Repository.PaidLoansRepo;

import jakarta.transaction.Transactional;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepo loanrepo;
	@Autowired 
	private PaidLoansRepo prepo;

	public Loan createLoan(double amount) {
		Loan l=new Loan();
		l.setLoan_amount(amount);
		l.setBalance(l.getLoan_amount()-l.getBalance());
		return l;
	}
	@Transactional
	public User payloan(long id, double amount) {
		Loan l=loanrepo.findById(id).orElseThrow();
		
			l.setPaid(l.getPaid()+amount);
			l.setBalance(l.getBalance()-amount);
			User u=l.getUser();
			if(l.getBalance()==0) {
				PaidLoans p=new PaidLoans();
				p.setLoanamount(l.getLoan_amount());
				prepo.save(p);
				
				//l.setUser(null);
				//loanrepo.delete(l);
				u.getLoans().remove(l);
				List<PaidLoans> p1=u.getPaid();
				p1.add(p);
				u.setPaid(p1);
				
			}
			u.setLoanlimit(u.getLoanlimit()+amount);
			
			return u;
			
		
		
		
	}
}

