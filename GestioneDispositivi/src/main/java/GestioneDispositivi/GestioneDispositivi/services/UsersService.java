package GestioneDispositivi.GestioneDispositivi.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.exceptions.BadRequestException;
import GestioneDispositivi.GestioneDispositivi.exceptions.NotFoundException;
import GestioneDispositivi.GestioneDispositivi.payloads.UserRegistrationPayload;
import GestioneDispositivi.GestioneDispositivi.repositories.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepo;

	public User create(UserRegistrationPayload u) {
		//check if email already exist
		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> 
		{throw new BadRequestException("Email " + u.getEmail() + " already exist");
		});
		User newUser = new User(u.getUsername(), u.getName(), u.getSurname(), u.getEmail(), u.getPassword());
		return usersRepo.save(newUser);
	}
	
	public Page<User> find(int page, int size, String sortBy){
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		
		return usersRepo.findAll(pageable);
	}
	
	public User findByEmail(String email) throws NotFoundException {
		return usersRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("No match found for this email"));
	}
	
	public User findByUsername(String username) throws Exception{
		return usersRepo.findByUsername(username).orElseThrow(()-> new Exception("User not found"));
	}
	
	public User findByUsernameAndUpdate(String username, User u) throws Exception {
		User found = this.findByUsername(username);
		
		found.setName(u.getName());
		found.setSurname(u.getSurname());
		found.setEmail(u.getEmail());
		found.setPassword(u.getPassword());
		return usersRepo.save(found);
	}
	
	public void findByUsernameAndDelete(String username) throws Exception {
		User found = this.findByUsername(username);
		usersRepo.delete(found);
	}
}
