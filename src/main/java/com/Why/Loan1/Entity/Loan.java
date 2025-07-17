package com.Why.Loan1.Entity;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double loan_amount;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private LocalDateTime issued_date = LocalDateTime.now();
	
	
	public LocalDateTime getIssued_date() {
		return issued_date;
	}

	private double paid;
	
	private double balance;
	
	@OneToMany(mappedBy = "loan",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Transaction> transaction;
	
	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.getTransaction().add(transaction);
		transaction.setLoan(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;


	
	
}

