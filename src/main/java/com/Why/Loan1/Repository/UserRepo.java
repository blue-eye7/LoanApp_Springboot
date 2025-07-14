package com.Why.Loan1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Why.Loan1.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	 User findByEmail(String email);
	 
	 User findByMobile(long mobile);

	 User findByUsername(String username);
}
