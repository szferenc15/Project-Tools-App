package app.sportmates_backend.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import app.sportmates_backend.controller.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Autowired
	private UserController controller;
	
	@LocalServerPort
	private int port=5000;	
	
	@Test
	public void testControllerNotNull() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testControllerAllNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/user/all", String.class).contains("ELTE"), false);
	}
	
	@Test
	public void testControllerAllIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/user/all", String.class).contains("Polozgai"), true);
	}
	
	@Test
	public void testControllerByUsernameIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/user/by_username?username=kaszon", String.class).contains("Kertész"), true);
	}
	
	@Test
	public void testControllerByUsernameNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/user/by_username?username=kaszon", String.class).contains("Polozgai"), false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
