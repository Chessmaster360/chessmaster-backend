package com.chessmaster.backend;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


@EnableWebSecurity
@DirtiesContext
@SpringBootTest()
@ActiveProfiles("test")
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}


}
