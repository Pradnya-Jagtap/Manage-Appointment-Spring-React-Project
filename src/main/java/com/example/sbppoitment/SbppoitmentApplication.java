package com.example.sbppoitment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sbppoitment.model.User;
import com.example.sbppoitment.repository.UserRepository;

@SpringBootApplication
public class SbppoitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbppoitmentApplication.class, args);
	}
	 @Bean
	 CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder) {
		return args->{
			System.out.println("Craeting admin check...");
			if(repo.findByUsername("admin").orElse(null)==null) {
				User admin =new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRole("ADMIN");
				repo.save(admin);
				System.out.println("Admin Created");
				
			}
			else
			{
				System.out.println("Admin already exists");
			}
		};
	 }

}
