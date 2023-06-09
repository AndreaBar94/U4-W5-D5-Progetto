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
import GestioneDispositivi.GestioneDispositivi.services.DevicesService;

@RestController
@RequestMapping("/devices")
public class DevicesController {
	
	@Autowired
	DevicesService devicesService;
	
		//---------------------------GET_ALL---------------------------------------//
		@GetMapping("")
		public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "5") int size,
				@RequestParam(defaultValue = "id") String sortedBy){
			return devicesService.find(page, size, sortedBy);
		}
		
		//---------------------------GET-------------------------------------------//
		@GetMapping("/{userId}")
		public Device findById(@PathVariable UUID deviceId) throws Exception {
			return devicesService.findById(deviceId);
		}
		
		//---------------------------POST------------------------------------------//
		@PostMapping("")
		@ResponseStatus(HttpStatus.CREATED)
		public Device saveDevice(@RequestBody @Validated Device body) {
			return devicesService.create(body);
		}
		
		//---------------------------PUT------------------------------------------//
		@PutMapping("/{userId}")
		public Device findByIdAndUpdate(@PathVariable UUID deviceId, @RequestBody Device body) throws Exception {
			return devicesService.findByIdAndUpdate(deviceId, body);
		}
		
		//---------------------------DELETE---------------------------------------//
		@DeleteMapping("/{userId}")
		@ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
		public void findByIdAndDelete(@PathVariable UUID userId) throws Exception {
			devicesService.findByIdAndDelete(userId);
		}
}
