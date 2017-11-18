package com.intellect.modal;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.intellect.validators.DateAndTimeDeserialize;

public class User implements Serializable {
	
	private String id;
	private String fName;
	private String lName;
	private String email;
	private Double pinCode;
	@JsonDeserialize(using =DateAndTimeDeserialize.class)
	//@DateTimeFormat(pattern="dd-MMM-yyyy")
    //@NotNull @Past
	private Date birthDate; //DD-MON-YYYY
	private Boolean isActive;
	
	public String getId() {	
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getPinCode() {
		return pinCode;
	}
	public void setPinCode(Double pinCode) {
		this.pinCode = pinCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", pinCode=" + pinCode
				+ ", birthDate=" + birthDate + "]";
	}
	
	
	
	

}
