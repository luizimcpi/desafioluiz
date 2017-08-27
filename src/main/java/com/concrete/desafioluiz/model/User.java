package com.concrete.desafioluiz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Long id;

	@Column(nullable = false)
	private String name;
    @Column(nullable = false)
	private String email;
    @Column(nullable = false)
	private String password;
    
    @Column(name = "dt_created", nullable = false)
    private String created;

    @Column(name = "dt_last_login", nullable = false)
    private String last_login;
    
    @Column(name = "dt_modified")
    private String modified;
    
    @Column(nullable = false)
   	private String token;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonManagedReference
    private List<Phone> phones;
    
    
	public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
}

