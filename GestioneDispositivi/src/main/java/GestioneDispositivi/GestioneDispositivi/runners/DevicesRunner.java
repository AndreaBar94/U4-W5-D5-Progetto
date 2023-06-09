package GestioneDispositivi.GestioneDispositivi.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import GestioneDispositivi.GestioneDispositivi.entities.Laptop;
import GestioneDispositivi.GestioneDispositivi.entities.Smartphone;
import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.enums.State;
import GestioneDispositivi.GestioneDispositivi.services.DevicesService;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;

@Component
public class DevicesRunner implements CommandLineRunner {

    @Autowired
    private UsersService usersService;

    @Autowired
    private DevicesService devicesService;

    @Override
    public void run(String... args) throws Exception {
        Page<User> users = usersService.find(0, 10, "username"); // Recupera tutti gli utenti dal database

        for (User user : users) {
            // Crea due dispositivi e li assegna all'utente
            Laptop laptop = new Laptop();
            laptop.setState(State.ASSIGNED);
            laptop.setUser(user);
            //devicesService.create(laptop);

            Smartphone smartphone = new Smartphone();
            smartphone.setState(State.ASSIGNED);
            smartphone.setUser(user);
            //devicesService.create(smartphone);
        }
    }
}
