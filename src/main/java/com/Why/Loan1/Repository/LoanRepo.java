package com.Why.Loan1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Why.Loan1.Entity.Loan;

public interface LoanRepo extends JpaRepository<Loan,Long>{

}
