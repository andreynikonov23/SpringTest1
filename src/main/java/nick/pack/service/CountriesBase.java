package nick.pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import nick.pack.config.SpringConfig;
import nick.pack.models.*;

@Component
public class CountriesBase {
	private CountryService countryService;
	
	private final List<Country> countries = new ArrayList<Country>();
	
	
	@Autowired
	public CountriesBase(CountryService countryService) {
		this.countryService = countryService;
		countries.addAll(countryService.selectAll());
	}
	
	
	
	public List<Country> getCountries(){
		return countries;
	}
	
	
}