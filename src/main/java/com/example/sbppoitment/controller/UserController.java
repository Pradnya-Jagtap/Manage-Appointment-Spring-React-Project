package com.example.sbppoitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbppoitment.dto.Logindto;
import com.example.sbppoitment.dto.UserDto;
import com.example.sbppoitment.model.User;
import com.example.sbppoitment.service.UserServices;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")

public class UserController {
@Autowired
 private UserServices userservice;
@Autowired
  AuthenticationManager authentcationManager;
   
@PostMapping("/users")
public User insert(@RequestBody UserDto  dto )
{
	User u= userservice.insert(dto);
	return u;
}
 
@GetMapping("/users/{uid}")
public User search(@PathVariable ("uid") Long uid) {
	User u=userservice.search(uid);
	return u;
}
@DeleteMapping("/users/{uid}")
 public ResponseEntity<Void>delete(@PathVariable("uid")Long uid){
	userservice.delete(uid);
	return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	
}
@GetMapping("/users")
	public List<User>getAll(){
	   return userservice.getAll();
}
@PutMapping("/users/{uid}")
public User update(@PathVariable("uid")Long uid ,@RequestBody User u) {
	u.setId(uid);
	return userservice.update(u);
	
}
@PostMapping("/login")
public ResponseEntity<User> login(@Validated @RequestBody Logindto u ,HttpServletRequest request){
	Authentication au= authentcationManager.authenticate(new UsernamePasswordAuthenticationToken(
			       u.getUsername(),
			       u.getPassword()
			       ));
	User user =userservice.SerachByUsername(au.getName());
	SecurityContextHolder.getContext().setAuthentication(au);
	request.getSession(true);
	return new ResponseEntity<>(user,HttpStatus.OK);
}

@PostMapping("/logout")
public ResponseEntity<Map<String,String>> logout(HttpServletRequest request, HttpServletResponse response){
	 Cookie cookie = new  Cookie("JSESSIONID",null);
	 cookie.setMaxAge(0);
	 cookie.setPath("/");
	 
	 response.addCookie(cookie);
	 Map<String,String>map=new HashMap<>();
	 map.put("message","Logged out");
	 return new ResponseEntity<>(map, HttpStatus.OK);
	 
	
}


}
