package com.Why.Loan1.Controller;



import org.springframework.beans.factory.annotation.Autowired;
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
import com.Why.Loan1.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	UserService userservice;
	@Autowired
	LoanService loanservice;
	

	@PostMapping("AddUser")
	public User AddUser(@RequestBody User user) {
		return userservice.AddUser(user);
	}
	@GetMapping("GetUser")
	public User getUser(@RequestParam long id) {
		return userservice.getUser(id);
	}
	//validate email for Signup
	@GetMapping("Validateemail")
	public String Validateemail(@RequestParam String email) {
		return userservice.Validateemail(email);
	}
	@GetMapping("Validatemobile")
	public String Validatemobile(@RequestParam long mobile) {
		return userservice.Validatemobile(mobile);
	}
	@GetMapping("test")
	public String test() {
		return "App Working:";
	}
	@PostMapping("Login")
	public User Login(@RequestBody User user) {
		return userservice.CheckLogin(user);
	}
	@GetMapping("KycVerification")
	public String KycVerification(@RequestParam long id) {
		return userservice.IsKycDone(id);
	}
	@PostMapping("AddKyc")
	public User KycVerification(@RequestParam long id,@RequestBody KYC kyc) {
		return userservice.AddKyc(id,kyc);
	}
	@PostMapping("ApplyLoan")
	public String applyloan(@RequestParam long id,@RequestParam double amount) {
		Loan L=loanservice.createLoan(amount);
		return userservice.applyloan(id,L);
		
	}
	//Pay the loan
	@PostMapping("PayLoan")
	public User PayLoan(@RequestParam long loanid,@RequestParam double amount) {
		return loanservice.payloan(loanid,amount);
	} 
}

