package com.Why.Loan1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Why.Loan1.Entity.KYC;
import com.Why.Loan1.Entity.Loan;
import com.Why.Loan1.Entity.User;
import com.Why.Loan1.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	
	
	public User AddUser(User user) {
		return repo.save(user);
	}
	public String Validateemail(String email) {
		User u=repo.findByEmail(email);
		return u!=null?"":"ok";
	}
	public String Validatemobile(long mobile) {
		User u=repo.findByMobile(mobile);
		return u!=null?"":"ok";
	}
	public User CheckLogin( User user) {
	if(user.getEmail()!=null) {	
	 User v1=repo.findByEmail(user.getEmail());
	 if(v1.getPass().equals(user.getPass())) {
		 return(v1);}
	 return null;
	 }
	User v2=repo.findByMobile(user.getMobile());
	if(v2.getPass().equals(user.getPass())) {
		 return(v2);}
	return null;
	}
	public String IsKycDone(long id) {
	    User u = repo.findById(id).orElse(null);
	    if (u != null && u.getKyc() != null) {
	        return "ok";
	    }
	    return "";
	}
	public User AddKyc(long id, KYC kyc) {
		User u=repo.findById(id).orElse(null);
		u.setLoanlimit(u.getLoanlimit()+2000.00);
		u.setKyc(kyc);
		
		
		return repo.save(u);
	}
	public String applyloan(long id, Loan l) {
		User u=repo.findById(id).orElse(null);
		if(u!=null) {
			List<Loan> previousloans=u.getLoans();
			
			if(u.getLoanlimit()>=l.getLoan_amount()) {
			u.setLoanlimit(u.getLoanlimit()-l.getLoan_amount());
			u.setLoans(l);
			repo.save(u);
			return "ok";}
			System.out.println("loan limit:"+u.getLoanlimit());
			return "limit ended";
		}
		return "";
		
	}
	public User getUser(long id) {
		
		return repo.findById(id).orElse(null);
	}
}

