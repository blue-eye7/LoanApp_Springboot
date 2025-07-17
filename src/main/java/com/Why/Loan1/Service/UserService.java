package com.Why.Loan1.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Why.Loan1.Entity.KYC;
import com.Why.Loan1.Entity.Loan;
import com.Why.Loan1.Entity.User;
import com.Why.Loan1.Repository.KYCRepo;
import com.Why.Loan1.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private KYCRepo kycrepo;
	
	
	
	public ResponseEntity<?> AddUser(User user) {
	if(repo.existsByMobile(user.getMobile())) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already exists");
	}
	repo.save(user);
	return ResponseEntity.status(HttpStatus.CREATED).body("created");
	}
	
	public ResponseEntity<?> Login( User user) {
	if(user.getEmail()!=null) {	
	 User v1=repo.findByEmail(user.getEmail());
	 if(v1!=null) {
	 if(v1.getPass().equals(user.getPass())) {
		 return ResponseEntity.ok(v1);}
	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password or email incorrect");
	 }}
	else {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email not found");
	}
	User v2=repo.findByMobile(user.getMobile());
	if(v2!=null) {
		if(v2.getPass().equals(user.getPass())) {
			 return ResponseEntity.ok(v2);}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password or mobilenumber incorrect");
	}
	
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mobile number not found");
	
	}
	
	
	public ResponseEntity<?> AddKyc(long id, KYC kyc) {
		User u=repo.findById(id).orElse(null);
		if(kycrepo.existsByAccno(kyc.getAccno())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("acc no already present");
		}
		u.setLoanlimit(u.getLoanlimit()+2000.00);
		u.setKyc(kyc);		
		return ResponseEntity.ok(repo.save(u));
	}
	
	public ResponseEntity<?> applyloan(long id, Loan l) {
		User u=repo.findById(id).orElse(null);
			List<Loan> previousloans=u.getLoans();
			
			if(u.getLoanlimit()>=l.getLoan_amount()) {
			u.setLoanlimit(u.getLoanlimit()-l.getLoan_amount());
			u.setLoans(l);
			
			return ResponseEntity.ok(repo.save(u));
			}
			
			return ResponseEntity.badRequest().body("Loan limit exceeded");
		
		
	}

}

