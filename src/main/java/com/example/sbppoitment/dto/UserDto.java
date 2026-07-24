package com.example.sbppoitment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
  @NotBlank(message=" UserName Can Not Blank")	
 private String username;
  @NotBlank(message="Password Can Not Blank")
private String password;
  /*
  public String getUsername() {
	return username;
  }
  public void setUsername(String username) {
	this.username = username;
  }
  public String getPassword() {
	return password;
  }
  public void setPassword(String password) {
	this.password = password;
  }
  */
  
  
}
