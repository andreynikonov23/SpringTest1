package nick.pack.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nick.pack.models.Client;
import nick.pack.models.Country;
import nick.pack.service.ClientService;
import nick.pack.service.ClientsBase;
import nick.pack.service.CountriesBase;
import nick.pack.service.CountryService;

@Controller
public class MainController {
	@Autowired
	private CountriesBase countries;
	@Autowired
	private ClientsBase clients;
	@Autowired
	private ClientService clientService;
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/")
	public String mainGet(Model model1, Model model2) {
		model1.addAttribute("clients", clients.getClients());
		model2.addAttribute("countries", clients.getCountyList());
		return "index";
	}
	@GetMapping("/new")
	public String newClientForm(Model model) {
		model.addAttribute("client", new Client());
		model.addAttribute("countries", countries.getCountries());
		return "add-client";
	}
	@GetMapping("/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		int index = clients.binarySearchClientIndex(id);
		Client client = clients.getClients().get(index);
		model.addAttribute("client", client);
		model.addAttribute("countries", countries.getCountries());
		return "edit-client";
		
	}
	@PostMapping("/create")
	public String create(@ModelAttribute("client") Client client, @ModelAttribute("countries") Country country, BindingResult validator) {
		if (client == null) {
			System.out.println("Shiiiiit");
		}
		System.out.println(client);
		if (validator.hasErrors()) {
			return "add-client";
		} else {
			System.out.println("Валидатор сосет хуй");
			
			clientService.insert(client);
			clients.addClient(client);
		}
		return "redirect:/";
	}
	@PostMapping("/{id}/update")
	public String update(@PathVariable("id") int id, @ModelAttribute("client") @Valid Client client, BindingResult validator) {
		if (validator.hasErrors()) {
			System.out.println("Ошибка");
			return null;
		}
		System.out.println(client);
		clientService.update(client);
		clients.updateClient(client);
		return "redirect:/";
	}
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		int i = clients.binarySearchClientIndex(id);
		Client client = clients.getClients().get(i);
		clientService.delete(client);
		System.out.println(clientService.selectAll());
		clients.deleteClient(client);
		return "redirect:/";
	}
	@GetMapping("/create")
	public String createError() {
		System.out.println("Validator is suck");
		return "redirect:/new";
	}
}