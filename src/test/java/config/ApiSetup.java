package config;

import io.restassured.RestAssured;

//CLASSE RESPONSÁVEL PELA CONFIGURAÇÃO DA URL PARA A API UTILIZADA NOS TESTES. 
public class ApiSetup
{
	public void setUpUrl()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		RestAssured.basePath = "/users";
	}
}
