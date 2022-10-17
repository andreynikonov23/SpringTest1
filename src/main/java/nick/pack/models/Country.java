package nick.pack.models;

import java.util.Objects;

public class Country {
	private int id;
	private String countryName;
	
	
	//Constructors
	public Country(int id, String countryName) {
		this.id = id;
		this.countryName = countryName;
	}
	public Country() {}
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getId() {
		return id;
	}
	public String getCountryName() {
		return countryName;
	}
	
	
	
	
	public int hashCode() {
		return Objects.hash(id, countryName);
	}
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;	
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Country)) {
			return false;
		}
		Country country = (Country) obj;
		if (this.hashCode() == country.hashCode()) {
			return id == country.getId() && countryName.equals(country.getCountryName()) ? true : false;
		} else
			return false;
	}
	
	
	public String toString() {
		return "Country: " + id + " " + countryName.trim() + ";";
	}
}