package nick.pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nick.pack.models.Client;
import nick.pack.models.Country;

@Component
public class ClientsBase {
	private ClientService service;
	private CountriesBase base;
	private final List<Client> clients = new ArrayList<Client>();
	
	@Autowired
	public ClientsBase (CountriesBase base, ClientService service) {
		this.service = service;
		this.base = base;
		clients.addAll(service.selectAll());
	}
	
	
	public List<Client> getClients(){
		return clients;
	}
	public void addClient(Client client) {
		if (client.getId() == 0) {
			int id = clients.get(clients.size() - 1).getId() + 1;
			client.setId(id);
		}
		clients.add(client);
	}
	public void updateClient(Client client) {
		int index = binarySearchClientIndex(client.getId());
		clients.set(index, client);
	}
	public void deleteClient(Client client) {
		clients.remove(client);
	}
	
	public List<Country> getCountyList(){
		List<Country> countryIdListObject = new ArrayList<Country>();
		for (Client client : clients) {
			for (Country country : base.getCountries()) {
				if (client.getCountry() == country.getId()) {
					countryIdListObject.add(country);
				}
			}
		}
		return countryIdListObject;
	}
	public int binarySearchClientIndex(int id) {
		int low = 0;
		int high = clients.size() -1;
		
		while (low <= high) {
			int mid = (low + high)/2;
			int guess = clients.get(mid).getId();
			
			if (guess==id) {
				return mid;
			}
			if (guess > id) {
				high = mid -1;
			}
			if (guess < id) {
				low = mid + 1;
			}
		}
		return -1;
	}
}