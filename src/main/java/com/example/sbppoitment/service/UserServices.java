package com.example.sbppoitment.service;

import java.util.List;

import com.example.sbppoitment.dto.UserDto;
import com.example.sbppoitment.model.User;

public interface UserServices {
	User insert(UserDto ud);
	User search (Long id);
	void delete (Long pid);
	List<User>getAll();
	User update (User p);
	User SerachByUsername(String  uname);
	

}
