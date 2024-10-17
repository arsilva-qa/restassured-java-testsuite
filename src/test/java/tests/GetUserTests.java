package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import config.ApiSetup;
import io.restassured.module.jsv.JsonSchemaValidator;

//CLASSE QUE CONTÉM OS TESTES PARA OPERAÇÕES DE LEITURA (GET) NA API DE USUÁRIOS.
public class GetUserTests 
{
    private ApiSetup api = new ApiSetup();
    
    @BeforeTest
    public void setUp() 
    {
        api.setUpUrl(); //CONFIGURA A URL BASE ANTES DA EXECUÇÃO DOS TESTES.
    }
    
    @Test
    public void consultarListaCompletaUsuarios() 
    {        
        given()
        .when()
            .get()
        .then()
            .log().status()
            .log().body()
            .assertThat().statusCode(200)
            .assertThat().body("$", hasSize(10))
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("utils/schemaGetListaUsuarios.json"));
    }
    
    @Test
    public void consultarUsuarioPorUsername() 
    {
    	String lat = "29.4572";
    	String lng = "-164.2990";

    	String street = "Hoeger Mall";
    	String suite = "Apt. 692";
    	String city = "South Elvis";
    	String zipcode = "53919-4257";
        
    	String companyName = "Robel-Corkery";
    	String catchPhrase = "Multi-tiered zero tolerance productivity";
    	String bs = "transition cutting-edge web services";

    	String name = "Patricia Lebsack";
    	String username = "Karianne";
    	String email = "Julianne.OConner@kory.org";
    	String phone = "493-170-9623 x156";
    	String website = "kale.biz";
        
        given()
        .when()
            .get("?username={username}", "Karianne")
        .then()
            .log().status()
            .log().body()
            .assertThat().statusCode(200)
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("utils/schemaGetListaUsuarios.json"))
            .assertThat().body("id[0]", is(4))
            .assertThat().body("name[0]", is(name ))
			.assertThat().body("username[0]", is(username))
			.assertThat().body("email[0]", is(email))
			.assertThat().body("address.street[0]", is(street))
			.assertThat().body("address.suite[0]", is(suite))
			.assertThat().body("address.city[0]", is(city))
			.assertThat().body("address.zipcode[0]", is(zipcode))
			.assertThat().body("address.geo.lat[0]", is(lat))
			.assertThat().body("address.geo.lng[0]", is(lng))
			.assertThat().body("phone[0]", is(phone))
			.assertThat().body("website[0]", is(website))
			.assertThat().body("company.name[0]", is(companyName))
			.assertThat().body("company.catchPhrase[0]", is(catchPhrase))
			.assertThat().body("company.bs[0]", is(bs));
    }
    
    @Test
    public void consultarUsuarioPorId() 
    {   
    	String lat = "-14.3990";
    	String lng = "-120.7677";

    	String street = "Ellsworth Summit";
    	String suite = "Suite 729";
    	String city = "Aliyaview";
    	String zipcode = "45169";
        
    	String companyName = "Abernathy Group";
    	String catchPhrase = "Implemented secondary concept";
    	String bs = "e-enable extensible e-tailers";

    	String name = "Nicholas Runolfsdottir V";
    	String username = "Maxime_Nienow";
    	String email = "Sherwood@rosamond.me";
    	String phone = "586.493.6943 x140";
    	String website = "jacynthe.com";
    	
        given()
        .when()
        	.get("/{id}", 8)
        .then()
            .log().status()
            .log().body()
            .assertThat().statusCode(200)
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
			.assertThat().body("company.bs", is(bs));
    }
    
    @Test
    public void consultarUsuarioNaoCadastrado() 
    {        
        given()
        .when()
        	.get("/{id}", 9999)
        .then()
            .log().status()
            .log().body()
            .assertThat().statusCode(404);
    }
}