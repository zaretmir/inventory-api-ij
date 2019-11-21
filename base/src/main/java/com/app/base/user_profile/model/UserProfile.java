package com.app.base.user_profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.base.auth.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "appuserprofile")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_app_user_id", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	private AppUser user;
	
	@Column(name = "first_name")
	private String name;

	@Column(name = "family_name")
	private String surname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private Integer phoneNumber;
	
	public UserProfile() {}

    public UserProfile(AppUser user) {
        this.user = user;
        user.setUserProfile(this);
    }

}
