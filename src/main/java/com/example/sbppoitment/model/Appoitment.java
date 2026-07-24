package com.example.sbppoitment.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Appoitment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private  Long appid;
	@NotBlank(message=" Username Can Not Blank")
	private String username;
	//@NotBlank(message="Appoitment date canot be Blank")
	private LocalDate appdate;
	//@NotBlank(message="Appoitment Time Can not be Blank")
	private LocalTime apptime;
	@NotBlank(message ="Appoitment complainet can not be Blank")
	private String complainet;
	
	@NotBlank(message="Please Provide Status ")
	private String status;
	/*
	public Long getAppid() {
		return appid;
	}
	public void setAppid(Long appid) {
		this.appid = appid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getAppdate() {
		return appdate;
	}
	public void setAppdate(LocalDate appdate) {
		this.appdate = appdate;
	}
	public LocalTime getApptime() {
		return apptime;
	}
	public void setApptime(LocalTime apptime) {
		this.apptime = apptime;
	}
	public String getComplainet() {
		return complainet;
	}
	public void setComplainet(String complainet) {
		this.complainet = complainet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
*/
	 
	 
}
