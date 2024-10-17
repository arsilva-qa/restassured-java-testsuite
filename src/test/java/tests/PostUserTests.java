package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.ApiSetup;
import utils.CriarUsuario;
import io.restassured.module.jsv.JsonSchemaValidator;

//CLASSE QUE CONTÉM OS TESTES PARA OPERAÇÕES DE CRIAÇÃO (POST) NA API DE USUÁRIOS.
public class PostUserTests {
    private ApiSetup api = new ApiSetup();
    private CriarUsuario createUser = new CriarUsuario();
    
    @BeforeTest
    public void setUp() 
    {
        api.setUpUrl();	//CONFIGURA A URL BASE ANTES DA EXECUÇÃO DOS TESTES.
    }
    
    @Test
    public void incluirUsuarioPreenchendoTodasInformacoesComDadosValidos() 
    {        	
    	String lat = "-37.3159";
    	String lng = "81.1496";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";
        
    	String companyName = "Considine-Lockman";
    	String catchPhrase = "Synchronised bottom-line interface";
    	String bs = "e-enable innovative applications";

    	String name = "Beverly K. Bacher";
    	String username = "Bev";
    	String email = "beverlykbacher@dayrep.com";
    	String phone = "724-914-0640";
    	String website = "beverlykbacher.com";
        
        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        Map<String, Object> company = createUser.criarCompanhia(companyName, catchPhrase, bs);
        
        Map<String, Object> userParams = createUser.criarUsuario(name, username, email, address, phone, website, company);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.post()
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(201)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("utils/schemaGetUsuarioUnico.json"))
			.assertThat().body("name", is(name ))
			.assertThat().body("username", is(username))
			.assertThat().body("email", is(email))
			.assertThat().body("address.street", is(street))
			.assertThat().body("address.suite", is(suite))
			.assertThat().body("address.city", is(city))
			.assertThat().body("address.zipcode", is(zipcode))
			.assertThat().body("address.geo.lat", is(lat))
			.assertThat().body("address.geo.lng", is(lng))
			.assertThat().body("phone", is(phone))
			.assertThat().body("website", is(website))
			.assertThat().body("company.name", is(companyName))
			.assertThat().body("company.catchPhrase", is(catchPhrase))
			.assertThat().body("company.bs", is(bs))
			.assertThat().body("id", is(11));
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void incluirUsuarioSemEnvioDeDados() 
    {        	                   	  	
        given()
			.contentType("application/json")
		.when()
			.post()
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(400);
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void incluirUsuarioSemEnvioDeDadosObrigatorios() 
    {        	        
    	String lat = "-37.3159";
    	String lng = "81.1496";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";
        
    	String companyName = "Considine-Lockman";
    	String catchPhrase = "Synchronised bottom-line interface";
    	String bs = "e-enable innovative applications";

    	String email = "beverlykbacher@dayrep.com";
    	String phone = "724-914-0640";
    	String website = "beverlykbacher.com";
        
        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        Map<String, Object> company = createUser.criarCompanhia(companyName, catchPhrase, bs);
        
        Map<String, Object> userParams = createUser.criarUsuario(null, null, email, address, phone, website, company);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.post()
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(400)
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("utils/schemaGetUsuarioUnico.json"))
			.assertThat().body("email", is(email))
			.assertThat().body("address.street", is(street))
			.assertThat().body("address.suite", is(suite))
			.assertThat().body("address.city", is(city))
			.assertThat().body("address.zipcode", is(zipcode))
			.assertThat().body("address.geo.lat", is(lat))
			.assertThat().body("address.geo.lng", is(lng))
			.assertThat().body("phone", is(phone))
			.assertThat().body("website", is(website))
			.assertThat().body("company.name", is(companyName))
			.assertThat().body("company.catchPhrase", is(catchPhrase))
			.assertThat().body("company.bs", is(bs))
			.assertThat().body("id", is(11));
    }
    
    @Test //TESTE CRIADO APENAS PARA ILUSTRAR O CENÁRIO, POIS ESSA VALIDAÇÃO NÃO É REALIZADA
    public void incluirUsuarioInformandoDadosInvalidos() 
    {        	
    	String lat = "ABC";
    	String lng = "DEF";

    	String street = "Kulas Light";
    	String suite = "Apt. 307";
    	String city = "Gwenborough";
    	String zipcode = "92998-3874";
        
    	String companyName = "Considine-Lockman";
    	String catchPhrase = "Synchronised bottom-line interface";
    	String bs = "e-enable innovative applications";

    	String name = "Beverly K. Bacher";
    	String username = "Bev";
    	String email = "beverlykbacher.dayrep";
    	String phone = "AAAAAAAAAAAAA";
    	String website = "beverlykbacher.com";
        
        Map<String, Object> geo = createUser.criarGeo(lat, lng);
        Map<String, Object> address = createUser.criarEndereco(street, suite, city, zipcode, geo);
        Map<String, Object> company = createUser.criarCompanhia(companyName, catchPhrase, bs);
        
        Map<String, Object> userParams = createUser.criarUsuario(name, username, email, address, phone, website, company);
    	  	
        given()
			.contentType("application/json")
			.body(userParams)
		.when()
			.post()
		.then()
			.log().status()
			.log().body()
			.assertThat().statusCode(400);
    }
}