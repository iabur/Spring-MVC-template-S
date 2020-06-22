package com.second.template.application.controllers;

import com.second.template.application.model.Role;
import com.second.template.application.model.User;
import com.second.template.application.repositories.AuthorityRepository;
import com.second.template.application.repositories.UserRepository;
import com.second.template.application.service.AuthorityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RootController {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	
	private final PasswordEncoder passwordEncoder;
	private final AuthorityService authorityService;

	public RootController(AuthorityRepository authorityRepository,UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		 this.authorityService = authorityService;
		 this.authorityRepository = authorityRepository;
	}

	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="error", required = false) String error) {
		
		generateRoles();
		generateUsers();
		
		model.addAttribute("error", error);
		return "auth/login";
	}
	 private void generateRoles() {
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_ADMIN"));
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_COACH"));
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_PLAYER"));
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_ICC_EMPLOYEE"));
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_TEAM_MANAGER"));
//		 authorityService.create(new Role(System.nanoTime(),"ROLE_CAPTAIN"));
//		 if(authorityRepository.findByRoleName("ROLE_ADMIN").getRoleName().isEmpty()) {
//			 authorityService.create(new Role(System.nanoTime(),"ROLE_ADMIN"));
//		 }
//		 if(authorityRepository.findByRoleName("ROLE_COACH").getRoleName().isEmpty()) {
//			 authorityService.create(new Role(System.nanoTime(),"ROLE_COACH"));
//		 }
//		 if(authorityRepository.findByRoleName("ROLE_PLAYER").getRoleName().isEmpty()) {
//			 authorityService.create(new Role(System.nanoTime(),"ROLE_PLAYER"));
//		 }
//		 if(authorityRepository.findByRoleName("ROLE_ICC_EMPLOYEE").getRoleName().isEmpty()) {
//			 authorityService.create(new Role(System.nanoTime(),"ROLE_ICC_EMPLOYEE"));
//		 }
//		 if(authorityRepository.findByRoleName("ROLE_TEAM_MANAGER").getRoleName().isEmpty()) {
//			 authorityService.create(new Role(System.nanoTime(),"ROLE_TEAM_MANAGER"));
//		 }
	}
	private void generateUsers() {
//
		if (userRepository.findByUsername("admin").isEmpty()) {
			var user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("secret"));
			
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_ADMIN"));
			user.setRoles(roles);
			
			userRepository.save(user);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			var user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("secret"));		
			
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
			user.setRoles(roles);
			
			userRepository.save(user);
		}
		if (userRepository.findByUsername("icc").isEmpty()) {
			var user = new User();
			user.setUsername("icc");
			user.setPassword(passwordEncoder.encode("secret"));
						
			Set<Role> roles = new HashSet<>();
			roles.add(authorityService.findByRoleName("ROLE_ICC_EMPLOYEE"));
			user.setRoles(roles);
			
			userRepository.save(user);
		}
	}
}
