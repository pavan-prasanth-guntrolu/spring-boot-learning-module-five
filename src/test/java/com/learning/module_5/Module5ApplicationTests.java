package com.learning.module_5;

import com.learning.module_5.entities.UserEntity;
import com.learning.module_5.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Module5ApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		UserEntity user=new UserEntity(4L,"pavanprasanth48850@gmail.com","1234","pavan");

		String token= jwtService.generateToken(user);

		System.out.println(token);

		Long id=jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
