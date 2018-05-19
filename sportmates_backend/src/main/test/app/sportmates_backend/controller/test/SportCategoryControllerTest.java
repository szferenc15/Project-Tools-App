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

import app.sportmates_backend.controller.SportCategoryController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SportCategoryControllerTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Autowired
	private SportCategoryController controller;
	
	@LocalServerPort
	private int port=5000;	
	
	@Test
	public void testControllerNotNull() {
		assertThat(controller).isNotNull();	
	}
	
	@Test
	public void testControllerAllNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/sport_category/all", String.class).contains("ELTE"), false);
	}
	
	@Test
	public void testControllerAllIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/sport_category/all", String.class).contains("Hegymászás"), true);	
	}
	
	@Test
	public void testControllerByCategoryIsOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/sport_category/by_category?category=Hegymászás", String.class).contains("Hegymászás"), true);	
		
	}
	
	@Test
	public void testControllerByCategoryNotOk(){
		assertSame(this.rest.getForObject("http://localhost:"+port+"/sport_category/by_category?category=Hegymászás", String.class).contains("Elte"), false);	
		
	}
	
	
	
	
	
	
	
	

}
