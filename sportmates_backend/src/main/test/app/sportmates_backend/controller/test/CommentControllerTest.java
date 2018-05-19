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

import app.sportmates_backend.controller.CommentController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CommentControllerTest {

	
	@Autowired
	private TestRestTemplate rest;

	@Autowired
	private CommentController controller;

	@LocalServerPort
	private int port=5000;
	
	@Test
	public void testHttpRequestForAllIsOk(){
		assertThat(this.rest.getForObject("http://localhost:"+port+"/comment/all", String.class)).contains("Polozgai");
	}

	@Test
	public void testControllerIsNotNull(){
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testHttpRequestForAllNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/comment/all", String.class).contains("ELTE"), false);
		
	}
	
	@Test
	public void tetsHttpRequestForEventIdIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/comment/by_event_id?eventId=1", String.class).contains("1"), true);
	}
	
	@Test
	public void tetsHttpRequestForEventIdNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/comment/by_event_id?eventId=1", String.class).contains("100"), false);
	}
	
	@Test
	public void testHttpRequestForUserIdIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/comment/by_user_id?userId=1", String.class).contains("Kertész Kászon (kaszon)"), true);		
		
	}
	
	@Test
	public void testHttpRequestForUserIdNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/comment/by_user_id?userId=1", String.class).contains("Polozgai"), false);		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
