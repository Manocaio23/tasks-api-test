package br.ce.wcaquino.tasks.apirest;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	@BeforeClass
	public static void setup() {
		
		RestAssured.baseURI="http://localhost:8001/tasks-backend";
	}
	@Test
	public void retonarTarefasCompleto() {
		RestAssured.given()
			.log().all()
		.when()
		
			.get("/todo")
		.then()
		.statusCode(200)
			.log().all()
		;
		
	
		}
	
	@Test
	public void adicionarTarefaSucesso() {
		RestAssured.given()
			.body("{\"task\":\"Teste via API\",\"dueDate\":\"2029-12-30\"}")
			.contentType(ContentType.JSON)
			.log().all()
		.when()
		
			.post("/todo")
		.then()
		.statusCode(201)
			.log().all()
		;
		
		
		}
	
	@Test
	public void naoAdicionarTafefaInvalida() {
		RestAssured.given()
			.body("{\"task\":\"Teste via API\",\"dueDate\":\"2020-12-11\"}")
			.contentType(ContentType.JSON)
			.log().all()
		.when()
		
			.post("/todo")
		.then()
		.statusCode(400)
		.body("message",CoreMatchers.is("Duee date must not be in past"))
			.log().all()
		;
		
		
		}
	
	

}
