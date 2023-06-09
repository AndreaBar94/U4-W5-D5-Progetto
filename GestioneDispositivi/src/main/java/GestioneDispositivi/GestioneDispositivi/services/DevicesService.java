package GestioneDispositivi.GestioneDispositivi.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import GestioneDispositivi.GestioneDispositivi.entities.Device;
import GestioneDispositivi.GestioneDispositivi.entities.Laptop;
import GestioneDispositivi.GestioneDispositivi.entities.Smartphone;
import GestioneDispositivi.GestioneDispositivi.entities.Tablet;
import GestioneDispositivi.GestioneDispositivi.repositories.DevicesRepository;

@Service
public class DevicesService {
	
	@Autowired
	private DevicesRepository devicesRepo;

	public Device create(Device d) {
		return devicesRepo.save(d);
	}
	
	public Smartphone createSmartphone(Smartphone smartphone) {
	    return (Smartphone) devicesRepo.save(smartphone);
	}

	public Tablet createTablet(Tablet tablet) {
	    return (Tablet) devicesRepo.save(tablet);
	}

	public Laptop createLaptop(Laptop laptop) {
	    return (Laptop) devicesRepo.save(laptop);
	}
	public Page<Device> find(int page, int size, String sortedBy){
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
		
		return devicesRepo.findAll(pageable);
	}
	
	public Device findById(UUID id) throws Exception{
		return devicesRepo.findById(id).orElseThrow(()-> new Exception("Postazione non trovata"));
	}
	
	public Device findByIdAndUpdate(UUID id, Device d) throws Exception {
		Device found = this.findById(id);
		found.setId(d.getId());
		found.setState(d.getState());
		found.setUser(d.getUser());
		return devicesRepo.save(found);
	}
	
	public Smartphone findByIdAndUpdateSmartphone(UUID id, Smartphone smartphone) throws Exception {
	    Smartphone found = (Smartphone) findById(id);
	    found.setId(smartphone.getId());
	    found.setState(smartphone.getState());
	    found.setUser(smartphone.getUser());
	    return (Smartphone) devicesRepo.save(found);
	}

	public Tablet findByIdAndUpdateTablet(UUID id, Tablet tablet) throws Exception {
	    Tablet found = (Tablet) findById(id);
	    found.setId(tablet.getId());
	    found.setState(tablet.getState());
	    found.setUser(tablet.getUser());
	    return (Tablet) devicesRepo.save(found);
	}

	public Laptop findByIdAndUpdateLaptop(UUID id, Laptop laptop) throws Exception {
	    Laptop found = (Laptop) findById(id);
	    found.setId(laptop.getId());
	    found.setState(laptop.getState());
	    found.setUser(laptop.getUser());
	    return (Laptop) devicesRepo.save(found);
	}
	
	public void findByIdAndDelete(UUID id) throws Exception {
		Device found = this.findById(id);
		devicesRepo.delete(found);
	}
}
