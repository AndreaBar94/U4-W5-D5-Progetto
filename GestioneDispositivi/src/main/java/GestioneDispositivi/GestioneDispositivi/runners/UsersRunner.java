package GestioneDispositivi.GestioneDispositivi.runners;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import GestioneDispositivi.GestioneDispositivi.payloads.UserRegistrationPayload;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;



@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UsersService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 10; i++) {
			String username = faker.name().username();
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = faker.internet().password();
			UserRegistrationPayload user = new UserRegistrationPayload();
			user.setUsername(username);
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password);
			//usersService.create(user);
		}

	}

}
