package com.app.base.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="role", schema="db_security")
@Getter @Setter
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Long id;
	
	@Column(name="role")
	private String rolename;
	
	/*
	@ManyToMany(mappedBy = "roles")
	private Set<AppUser> users = new HashSet<>();
	*/

}
