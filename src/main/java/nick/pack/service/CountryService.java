package nick.pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import nick.pack.models.Client;
import nick.pack.models.Country;

@Component ("countryService")
public class CountryService {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CountryService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Country> selectAll() {
		return jdbcTemplate.query("SELECT * FROM countries", new CountryRowMapper());
	}
}