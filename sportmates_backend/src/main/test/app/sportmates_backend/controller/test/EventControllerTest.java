package app.sportmates_backend.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import app.sportmates_backend.controller.EventController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Autowired
	private EventController controller;
	
	@LocalServerPort
	private int port=5000;	
	
	@Test
	public void testControllerNotNull() {
		assertThat(controller).isNotNull();	
		
	}
	
	@Test
	public void testControllerAllIsOk(){
		assertThat(this.rest.getForObject("http://localhost:"+port+"/event/all", String.class)).contains("Hegymászás");
		
	}
	
	@Test
	public void testControllerAllNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/event/all", String.class).contains("ELTE"), false);
		
	}
	
	@Test
	public void testControllerByIdNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/event/by_id?id=1", String.class).contains("ELTE"), false);
		
	}	
	
	@Test
	public void testControllerByIdIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/event/by_id?id=1", String.class).contains("Hegymászás"), true);
				
		
	}
	
	
	
	
	
	
	
	
	
	

}
