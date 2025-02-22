package br.es.pews.back;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.properties")
class ApplicationTests {
	@Test
	void contextLoads() {
		System.out.println("Hello World");
	}
}