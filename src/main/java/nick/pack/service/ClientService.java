package nick.pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import nick.pack.models.Client;
import nick.pack.models.Country;

@Component
public class ClientService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Client> selectAll(){
		return jdbcTemplate.query("SELECT * FROM clients", new ClientRowMapper());
	}
	
	public void insert(Client client) {
		String name = client.getName();
		String email = client.getEmail();
		int countryId = client.getCountry();
		jdbcTemplate.update("INSERT INTO clients (name, email, country) VALUES (?, ?, ?)", name, email, countryId);
		
	}
	public void update(Client client) {
		int id = client.getId();
		String name = client.getName();
		String email = client.getEmail();
		int countryId = client.getCountry();
		jdbcTemplate.update("UPDATE clients SET name=?, email=?, country=? WHERE id=?", name, email, countryId, id);
		
	}
	public void delete(Client client) {
		int id = client.getId();
		jdbcTemplate.update("DELETE FROM clients WHERE id=?", id);
		
	}
}