package nick.pack.models;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Client {
	private int id;
	@Size (min = 2, max = 30, message = "size of name should be from 2 to 30 ")
	private String name;
	@Email (message = "Field Email is invalid")
	@NotEmpty
	private String email;
	private int country;
	
	
	//Constructors
	public Client(int id, String name, String email, int country) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	public Client(String name, String email, int country) {
		this.name = name;
		this.email = email;
		this.country = country;
	}
	public Client() {}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String phone) {
		this.email = phone;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(country, id, name, email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return country == other.country && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(email, other.email);
	}
	@Override
	public String toString() {
		return String.format("Client: %d, %s, %s, %d;--------", id, name, email, country);
	}
}
