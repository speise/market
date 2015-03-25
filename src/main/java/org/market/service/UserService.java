package org.market.service;

import java.util.Date;

import org.market.domain.User;
import org.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/user/all")
	public String greeting() {

		userRepository.save(new User("Name" + new Date()));
		return new String("Response");
	}
}
