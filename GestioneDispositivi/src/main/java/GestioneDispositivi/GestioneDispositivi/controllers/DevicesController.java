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
import GestioneDispositivi.GestioneDispositivi.entities.Device;
import GestioneDispositivi.GestioneDispositivi.entities.Laptop;
import GestioneDispositivi.GestioneDispositivi.entities.Smartphone;
import GestioneDispositivi.GestioneDispositivi.entities.Tablet;
import GestioneDispositivi.GestioneDispositivi.entities.User;
import GestioneDispositivi.GestioneDispositivi.services.DevicesService;
import GestioneDispositivi.GestioneDispositivi.services.UsersService;

@RestController
@RequestMapping("/devices")
public class DevicesController {
	
	@Autowired
	DevicesService devicesService;
	@Autowired
	UsersService usersService;
	
		//---------------------------GET_ALL---------------------------------------//
		@GetMapping("")
		public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "5") int size,
				@RequestParam(defaultValue = "id") String sortedBy){
			return devicesService.find(page, size, sortedBy);
		}
		
		
		//---------------------------GET-------------------------------------------//
		@GetMapping("/{deviceId}")
		public Device findById(@PathVariable UUID deviceId) throws Exception {
			return devicesService.findById(deviceId);
		}
		
		//---------------------------POST------------------------------------------//
		@PostMapping("")
		@ResponseStatus(HttpStatus.CREATED)
		public Device saveDevice(@RequestBody @Validated Device body) {
			return devicesService.create(body);
		}
		
		@PostMapping("/smartphone")
		@ResponseStatus(HttpStatus.CREATED)
		public Smartphone saveSmartphone(@RequestBody @Validated Smartphone body, @RequestParam String username) throws Exception {
		    User user = usersService.findByUsername(username);
		    body.setUser(user);
		    return (Smartphone) devicesService.create(body);
		}

		
		@PostMapping("/tablet")
		@ResponseStatus(HttpStatus.CREATED)
		public Tablet saveTablet(@RequestBody @Validated Tablet body, @RequestParam String username) throws Exception {
		    User user = usersService.findByUsername(username);
		    body.setUser(user);
		    return (Tablet) devicesService.create(body);
		}

		@PostMapping("/laptop")
		@ResponseStatus(HttpStatus.CREATED)
		public Laptop saveLaptop(@RequestBody @Validated Laptop body, @RequestParam String username) throws Exception {
		    User user = usersService.findByUsername(username);
		    body.setUser(user);
		    return (Laptop) devicesService.create(body);
		}

		
		//---------------------------PUT------------------------------------------//
		@PutMapping("/{deviceId}")
		public Device findByIdAndUpdate(@PathVariable UUID deviceId, @RequestBody Device body) throws Exception {
			return devicesService.findByIdAndUpdate(deviceId, body);
		}
		
		@PutMapping("/smartphone/{deviceId}")
		public Smartphone findByIdAndUpdateSmartphone(@PathVariable UUID deviceId, @RequestBody Smartphone body) throws Exception {
		    Smartphone found = (Smartphone) devicesService.findById(deviceId);
		    found.setState(body.getState());
		    found.setUser(body.getUser());
		    return (Smartphone) devicesService.findByIdAndUpdate(deviceId, found);
		}


		@PutMapping("/tablet/{deviceId}")
		public Tablet findByIdAndUpdateTablet(@PathVariable UUID deviceId, @RequestBody Tablet body) throws Exception {
		    Tablet found = (Tablet) devicesService.findById(deviceId);
		    found.setState(body.getState());
		    found.setUser(body.getUser());
		    return (Tablet) devicesService.findByIdAndUpdate(deviceId, found);
		}

		@PutMapping("/laptop/{deviceId}")
		public Laptop findByIdAndUpdateLaptop(@PathVariable UUID deviceId, @RequestBody Laptop body) throws Exception {
		    Laptop found = (Laptop) devicesService.findById(deviceId);
		    found.setState(body.getState());
		    found.setUser(body.getUser());
		    return (Laptop) devicesService.findByIdAndUpdate(deviceId, found);
		}


		
		//---------------------------DELETE---------------------------------------//
		@DeleteMapping("/{deviceId}")
		@ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
		public void findByIdAndDelete(@PathVariable UUID deviceId) throws Exception {
			devicesService.findByIdAndDelete(deviceId);
		}
}
