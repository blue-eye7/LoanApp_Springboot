package com.Why.Loan1.Controller;



import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Why.Loan1.Entity.KYC;
import com.Why.Loan1.Entity.Loan;
import com.Why.Loan1.Entity.User;
import com.Why.Loan1.Service.LoanService;
import com.Why.Loan1.Service.TransactionService;
import com.Why.Loan1.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	UserService userservice;
	@Autowired
	LoanService loanservice;
	@Autowired
	TransactionService transactionservice;
	

	@PostMapping("AddUser")
	public ResponseEntity<?> AddUser(@RequestBody User user) {
		System.out.println("email:"+user.getEmail());
		return userservice.AddUser(user);
	}
	
	@GetMapping("test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("application working ...");
	}
	@PostMapping("Login")
	public ResponseEntity<?> Login(@RequestBody User user) {
		return userservice.Login(user);
	}
	
	@PostMapping("AddKyc")
	public ResponseEntity<?> KycVerification(@RequestParam long id,@RequestBody KYC kyc) {
		return userservice.AddKyc(id,kyc);
	}
	@PostMapping("ApplyLoan")
	public ResponseEntity<?> applyloan(@RequestParam long id,@RequestParam double amount) {
		Loan L=loanservice.createLoan(amount);
		return userservice.applyloan(id,L);
		
	}
	//Pay the loan
	@PostMapping("PayLoan")
	public ResponseEntity<?> PayLoan(@RequestParam long loanid,@RequestParam double amount,@RequestParam int userid) {
		transactionservice.add(loanid,userid,amount);
		return loanservice.payloan(loanid,amount);
	} 
	
}

