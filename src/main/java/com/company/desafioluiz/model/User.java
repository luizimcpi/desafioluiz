package com.company.desafioluiz.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class User implements Serializable {

    private static final long serialVersionUID = -8487801845484140274L;

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
    private LocalDateTime created;

    @Column(name = "dt_last_login", nullable = false)
    private LocalDateTime last_login;

    @Column(name = "dt_modified")
    private LocalDateTime modified;

    @Column(nullable = false)
    private String token;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Phone> phones;

    public User() {

    }

    public User(final String name, final String email, final String password, final LocalDateTime created, final LocalDateTime last_login, final LocalDateTime modified, final String token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.last_login = last_login;
        this.modified = modified;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public List<Phone> getPhones() {
        return phones;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public LocalDateTime getModified() {
        return modified;
    }


}

