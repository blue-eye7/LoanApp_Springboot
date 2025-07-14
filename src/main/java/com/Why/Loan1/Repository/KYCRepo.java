package com.Why.Loan1.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.Why.Loan1.Entity.KYC;

@Service
public interface KYCRepo extends JpaRepository<KYC, Long> {
	
	
}