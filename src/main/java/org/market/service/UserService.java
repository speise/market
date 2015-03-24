package org.market.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserService {
	@RequestMapping("/user/all")
	public String greeting() {
		return new String("Response");
	}
}
