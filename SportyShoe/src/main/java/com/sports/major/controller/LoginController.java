package com.sports.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sports.major.global.GlobalData;
import com.sports.major.model.Role;
import com.sports.major.model.User;
import com.sports.major.repository.RoleRepository;
import com.sports.major.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();                                               // for clearing cart
		return "login";
	}
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")                            // user ko directly login (autoLogedin)
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request)throws ServletException{
		String password=user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		
		List<Role> roles =new ArrayList<>();
		roles.add(roleRepository.findById(2).get());    //add Role as 2.USER_ROLE
		user.setRoles(roles);
		userRepository.save(user);
		
		request.login(user.getEmail(), password); 		 // it is used for autologin
		
		return "redirect:/";
		
	}
}
