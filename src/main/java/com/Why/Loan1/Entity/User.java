package com.Why.Loan1.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String email;
	
	private String pass;
	
	private long mobile;
	
	private String gender;
	
	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private double Loanlimit;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Loan> loans;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonManagedReference
	private KYC kyc;
	
	@OneToMany
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private List<PaidLoans> paid;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction tx) {
		this.getTransactions().add(tx);
		tx.setUser(this);
	}

	public List<PaidLoans> getPaid() {
		return paid;
	}

	public void setPaid(List<PaidLoans> paid) {
		this.paid = paid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public double getLoanlimit() {
		return Loanlimit;
	}

	public void setLoanlimit(double loanlimit) {
		Loanlimit = loanlimit;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Loan loans) {
		List<Loan> l=this.getLoans();
		l.add(loans);
		loans.setUser(this);
		
	}

	public KYC getKyc() {
		return kyc;
	}

	public void setKyc(KYC kyc) {
		this.kyc = kyc;
		kyc.setUser(this);
	}
	
	
		
}
