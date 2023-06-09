package GestioneDispositivi.GestioneDispositivi.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.payloads.UserRegistrationPayload;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;



@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	//---------------------------GET_ALL---------------------------------------//
	@GetMapping("")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy){
		return usersService.find(page, size, sortBy);
	}
	
	//---------------------------GET-------------------------------------------//
	@GetMapping("/{username}")
	public User findByUsername(@PathVariable String username) throws Exception {
		return usersService.findByUsername(username);
	}
	
	//---------------------------POST------------------------------------------//
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated UserRegistrationPayload body) {
		return usersService.create(body);
	}
	
	//---------------------------PUT------------------------------------------//
	@PutMapping("/{username}")
	public User findByUsernameAndUpdate(@PathVariable String username, @RequestBody User body) throws Exception {
		return usersService.findByUsernameAndUpdate(username, body);
	}
	
	//---------------------------DELETE---------------------------------------//
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
	public void findByUsernameAndDelete(@PathVariable String username) throws Exception {
		usersService.findByUsernameAndDelete(username);
	}
}
