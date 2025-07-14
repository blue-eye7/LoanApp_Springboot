package com.Why.Loan1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Why.Loan1.Entity.PaidLoans;

public interface PaidLoansRepo extends JpaRepository<PaidLoans,Long> {

}
