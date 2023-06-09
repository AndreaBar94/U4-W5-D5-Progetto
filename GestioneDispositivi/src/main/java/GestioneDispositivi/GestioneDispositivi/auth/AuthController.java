package GestioneDispositivi.GestioneDispositivi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import GestioneDispositivi.GestioneDispositivi.auth.AuthenticationSuccessfullPayload.AuthenticationSuccessfullPayload;
import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.exceptions.NotFoundException;
import GestioneDispositivi.GestioneDispositivi.exceptions.UnauthorizedException;
import GestioneDispositivi.GestioneDispositivi.payloads.UserLoginPayload;
import GestioneDispositivi.GestioneDispositivi.payloads.UserRegistrationPayload;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UsersService usersService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {
		User createdUser = usersService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body) throws NotFoundException{
		
		//cerco la mail inserita nel login tra quelle degli utenti
		User user = usersService.findByEmail(body.getEmail());
		
		//se la trovo faccio il check sulla password, se non corrisponde lancio errore 401
		if(!body.getPassword().matches(user.getPassword())) throw new UnauthorizedException("Credenziali non valide");
		
		//se corrisponde creo token
		String token = JWTTools.createToken(user);
		
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}


}
