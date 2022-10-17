package nick.pack.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import nick.pack.config.SpringConfig;
import nick.pack.models.Client;
import nick.pack.models.Country;


public class ClientRowMapper implements RowMapper<Client> {
	
	
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int id = rs.getInt("id");
		System.out.println(id);
		String name = rs.getString("name");
		System.out.println(name);
		String email = rs.getString("email");
		System.out.println(email);
		int countryId = rs.getInt("country");
		System.out.println(countryId);
		Client client = new Client(id, name, email, countryId);
		System.out.println();
		return client;
	}

}