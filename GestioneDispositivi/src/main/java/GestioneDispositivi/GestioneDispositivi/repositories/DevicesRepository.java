package GestioneDispositivi.GestioneDispositivi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import GestioneDispositivi.GestioneDispositivi.entities.Device;

public interface DevicesRepository extends JpaRepository<Device, UUID>{

}
