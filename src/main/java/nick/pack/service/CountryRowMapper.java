package nick.pack.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import nick.pack.models.Country;

public class CountryRowMapper implements RowMapper<Country> {

	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int id = rs.getInt("id");
		String name = rs.getString("country_name");
		Country country = new Country(id, name);
		return country;
	}

}