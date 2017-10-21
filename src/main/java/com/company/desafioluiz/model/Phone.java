package com.company.desafioluiz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Phone implements Serializable {

	private static final long serialVersionUID = 4685664254935732476L;

	@Id
    @GeneratedValue
    private Long id;
    
    private String number;
    private String ddd;
    
    @ManyToOne
    @JoinColumn(name = "user_fk")
	@JsonBackReference
    private User user;

	public Phone(final Long id, final String number, final String ddd, final User user) {
		this.id = id;
		this.number = number;
		this.ddd = ddd;
		this.user = user;
	}

	public String getNumber() {
		return number;
	}
	public String getDdd() {
		return ddd;
	}
	public User getUser() { return user; }

}
