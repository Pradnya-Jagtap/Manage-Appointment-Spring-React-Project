package com.example.sbppoitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sbppoitment.dto.UserDto;
import com.example.sbppoitment.model.User;
import com.example.sbppoitment.repository.UserRepository;
@Service
public class UserServicesImpl implements UserServices,UserDetailsService {
 @Qualifier("userRepo")
 @Autowired 
 private UserRepository userRepo;
 @Autowired
 PasswordEncoder encoder;
	@Override
	public User insert(UserDto ud) {
		// TODO Auto-generated method stub
		Optional<User> opt= userRepo.findByUsername(ud.getUsername());
		if(opt.isPresent())
		{
			throw new RuntimeException("UserName already exist");
		}
		User  u= new User();
		u.setUsername(ud.getUsername());
		u.setPassword(encoder.encode(ud.getPassword()));
		u.setRole("USER");
		return userRepo.save(u);
	}

	@Override
	public User search(Long id) {
		// TODO Auto-generated method stub
	    User u= userRepo.findById(id).orElse(null);
		return u;
	}

	@Override
	public void delete(Long  pid) {
		// TODO Auto-generated method stub
		userRepo.deleteById(pid);
		
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User update(User p) {
		// TODO Auto-generated method stub
		User u=userRepo.findById(p.getId()).orElse(null);
		if(u !=null)
		{
			u.setPassword(encoder.encode(p.getPassword()));
			return userRepo.save(u);
		}
		return null;
	}

	@Override
	public User SerachByUsername(String uname) {
		// TODO Auto-generated method stub
		User u= userRepo.findByUsername(uname).orElse(null);
		return u;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUsername(username)
				 .orElseThrow(()-> new UsernameNotFoundException("User  not found"));
		return  org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}

}
