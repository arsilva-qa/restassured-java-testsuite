package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.ApiSetup;

//CLASSE QUE CONTÉM OS TESTES PARA OPERAÇÕES DE EXCLUSÃO (DELETE) NA API DE USUÁRIOS.
public class DeleteUserTests {
    private ApiSetup api = new ApiSetup();
    
    @BeforeTest
    public void setUp() 
    {
        api.setUpUrl();	//CONFIGURA A URL BASE ANTES DA EXECUÇÃO DOS TESTES.
    }
    
    @Test
    public void deletarUsuario() 
    {        	
        given()
		.when()
			.delete("/{id}", 4)
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(200);
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void deletarUsuarioNaoCadastrado() 
    {        	
        given()
		.when()
			.delete("/{id}", 9999)
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(400);
    }
}