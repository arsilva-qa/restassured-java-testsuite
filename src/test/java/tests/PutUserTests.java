package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.ApiSetup;
import utils.CriarUsuario;
import io.restassured.module.jsv.JsonSchemaValidator;

//CLASSE QUE CONTÉM OS TESTES PARA OPERAÇÕES DE ALTERAÇÃO (PUT) NA API DE USUÁRIOS.
public class PutUserTests {
    private ApiSetup api = new ApiSetup();
    private CriarUsuario createUser = new CriarUsuario();
    
    @BeforeTest
    public void setUp() 
    {
        api.setUpUrl();	//CONFIGURA A URL BASE ANTES DA EXECUÇÃO DOS TESTES.
    }
    
    @Test
    public void alterarEnderecoUsuario() 
    {        	        
        String lat = "-37.3159";
    	String lng = "81.1496";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";

        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        
        Map<String, Object> userParams = createUser.criarUsuario(null, null, null, address, null, null, null);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.put("/{id}", 4)
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(200)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("utils/schemaPutEnderecoUsuario.json"))
			.assertThat().body("address.street", is(street))
			.assertThat().body("address.suite", is(suite))
			.assertThat().body("address.city", is(city))
			.assertThat().body("address.zipcode", is(zipcode))
			.assertThat().body("address.geo.lat", is(lat))
			.assertThat().body("address.geo.lng", is(lng))
			.assertThat().body("id", is(4));
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void alterarUsuarioNaoCadastrado() 
    {        	        
        String lat = "-37.3159";
    	String lng = "81.1496";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";

        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        
        Map<String, Object> userParams = createUser.criarUsuario(null, null, null, address, null, null, null);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.put("/{id}", 9999)
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(404);
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void alterarUsuarioInformandoDadoslInvalidos() 
    {        	
    	String lat = "ABC";
    	String lng = "DEF";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";

    	String email = "beverlykbacher.dayrep";
    	String phone = "AAAAAAAAAAAAA";
        
        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        
        Map<String, Object> userParams = createUser.criarUsuario(null, null, email, address, phone, null, null);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.put("/{id}", 6)
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(400);
    }
}