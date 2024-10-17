package utils;

import java.util.HashMap;
import java.util.Map;

//CLASSE RESPONSÁVEL PELA CRIAÇÃO DE OBJETOS COM OS DADOS DE USUARIO A SEREM UTILIZADOS NAS REQUISIÇÕES POST E PUT.
public class CriarUsuario 
{
	//MÉTODO RESPONSÁVEL PELA CRIAÇÃO DO MAPA QUE ARMAZENA OS DADOS DE GEOLOCALIZAÇÃO DO ENDEREÇO. 
    public Map<String, Object> criarGeo(String lat, String lng) 
    {
    	Map<String, Object> geo = new HashMap<>();
        if (lat != null && !lat.isEmpty()) {
            geo.put("lat", lat);
        }
        if (lng != null && !lng.isEmpty()) {
            geo.put("lng", lng);
        }
        return geo;
    }
    
    //MÉTODO RESPONSÁVEL PELA CRIAÇÃO DO MAPA QUE ARMAZENA OS DADOS DO ENDEREÇO. 
    public Map<String, Object> criarEndereco(String street, String suite, String city, String zipcode, Map<String, Object> geo) 
    {
    	Map<String, Object> address = new HashMap<>();
        if (street != null && !street.isEmpty()) {
            address.put("street", street);
        }
        if (suite != null && !suite.isEmpty()) {
            address.put("suite", suite);
        }
        if (city != null && !city.isEmpty()) {
            address.put("city", city);
        }
        if (zipcode != null && !zipcode.isEmpty()) {
            address.put("zipcode", zipcode);
        }
        if (geo != null && !geo.isEmpty()) {
            address.put("geo", geo);
        }
        return address;
    }
    
    //MÉTODO RESPONSÁVEL PELA CRIAÇÃO DO MAPA QUE ARMAZENA OS DADOS DA COMPANHIA. 
    public Map<String, Object> criarCompanhia(String name, String catchPhrase, String bs) 
    {
    	Map<String, Object> company = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            company.put("name", name);
        }
        if (catchPhrase != null && !catchPhrase.isEmpty()) {
            company.put("catchPhrase", catchPhrase);
        }
        if (bs != null && !bs.isEmpty()) {
            company.put("bs", bs);
        }
        return company;
    }

    //MÉTODO RESPONSÁVEL PELA CRIAÇÃO DO MAPA QUE ARMAZENA OS DADOS DO USUÁRIO. 
    public Map<String, Object> criarUsuario(String name, String username, String email, Map<String, Object> address, String phone, String website, Map<String, Object> company) 
    {
    	Map<String, Object> userParams = new HashMap<>();
        if (email != null && !email.isEmpty()) {
            userParams.put("email", email);
        }
        if (address != null && !address.isEmpty()) {
            userParams.put("address", address);
        }
        if (phone != null && !phone.isEmpty()) {
            userParams.put("phone", phone);
        }
        if (website != null && !website.isEmpty()) {
            userParams.put("website", website);
        }
        if (company != null && !company.isEmpty()) {
            userParams.put("company", company);
        }
        return userParams;
    }
}
