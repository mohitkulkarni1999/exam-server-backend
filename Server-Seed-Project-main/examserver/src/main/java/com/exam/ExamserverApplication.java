 package com.exam;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
	
		SpringApplication.run(ExamserverApplication.class, args);
		
	
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
     	User user = new User();
    	user.setFirstName("ADMIN");		user.setLastName("SEED");
		user.setUsername("admin1");
		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
		user.setPhone("1234567890");
		user.setEmail("abc@gamail.com");
		user.setProfile("default.png");
		
		Role role1 = new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet= new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole );
		
	User user1 = this.userService.createUser(user, userRoleSet);
	System.out.println(user1.getUsername());
	//watch video no 18	
		}catch(UserFoundException e) {
			e.printStackTrace();
		}
		System.err.println("No Error Found in Project...");
		System.err.println("Server is Running...");
		System.out.println("connecting...");
		
		
        
}
	}
