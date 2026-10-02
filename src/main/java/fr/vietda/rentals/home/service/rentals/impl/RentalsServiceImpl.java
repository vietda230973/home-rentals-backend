package fr.vietda.rentals.home.service.rentals.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.vietda.rentals.home.mapper.RentalsMapper;
import fr.vietda.rentals.home.model.dto.RentalsDto;
import fr.vietda.rentals.home.model.entity.Rentals;
import fr.vietda.rentals.home.model.entity.Users;
import fr.vietda.rentals.home.repository.RentalsRepository;
import fr.vietda.rentals.home.repository.UsersRepository;
import fr.vietda.rentals.home.service.rentals.RentalsService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalsServiceImpl implements RentalsService {
	
	private final RentalsRepository rentalsRepository;
	
	private final UsersRepository usersRepository;
	
	private final RentalsMapper rentalsMapper;
	
	private final String uploadDir = "uploads/rentals/";

	public RentalsServiceImpl(RentalsRepository rentalsRepository, UsersRepository usersRepository, RentalsMapper rentalsMapper) {
		super();
		this.rentalsRepository = rentalsRepository;
		this.usersRepository = usersRepository;
		this.rentalsMapper = rentalsMapper;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RentalsDto> recupererTousRentals () 
	{
		List<RentalsDto> listDto =  rentalsRepository.findAll().stream().map(rentalsMapper::toDTO).toList();
		return listDto;
				
	}
	
	@Override
	public RentalsDto recupererRentalsParId (Integer id) throws EntityNotFoundException {
		
		
		Rentals rentals = rentalsRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Le rentant est introuvable : " + id));
		
		return rentalsMapper.toDTO(rentals);
		
	}
	
	@Override
	public RentalsDto creerRentals (RentalsDto rentalsDto) throws IOException {
		
		Rentals rentals = new Rentals();
		rentals.setName(rentalsDto.getName());
		rentals.setDescription(rentalsDto.getDescription());
		
		rentals.setPrice(rentalsDto.getPrice());
		rentals.setSurface(rentalsDto.getSurface());
		rentals.setCreatedAt(rentalsDto.getCreatedAt());
		rentals.setUpdatedAt(rentalsDto.getUpdatedAt());
		
		 // 1. Créer le dossier s'il n'existe pas
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        
        // 2. Générer un nom de fichier unique
        String fileName = UUID.randomUUID() + "_" + rentalsDto.getPictureImage().getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // 3. Écrire le fichier sur disque
        Files.copy(rentalsDto.getPictureImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 4. Construire l'URL accessible
        String pictureUrl = "http://localhost:9090/uploads/rentals/" + fileName;

        rentals.setPicture(pictureUrl);
		
		if (rentalsDto.getOwnerId() != null) {
			Users users = usersRepository.getById(rentalsDto.getOwnerId());
			rentals.setUsers(users);
		}
		Rentals rentalsSave = rentalsRepository.save(rentals);
		return rentalsMapper.toDTO(rentalsSave);
		
	}
	
	@Override
	public RentalsDto mettreAJourRentals (Integer id, RentalsDto rentalsDto) {
		
		Rentals rentals;
		if (id != null) {
			rentals =rentalsRepository.findById(id).
	    		orElseThrow(() -> new EntityNotFoundException("Le rentant est introuvable : " + id));
		} else {
			rentals = new Rentals();
		}
		
		rentals.setName(rentalsDto.getName());
		rentals.setDescription(rentalsDto.getDescription());
		rentals.setPicture(rentalsDto.getPicture());
		rentals.setPrice(rentalsDto.getPrice());
		rentals.setSurface(rentalsDto.getSurface());
		rentals.setCreatedAt(rentalsDto.getCreatedAt());
		rentals.setUpdatedAt(rentalsDto.getUpdatedAt());
	    

	    Rentals sauvegarde = rentalsRepository.save(rentals);
		return rentalsMapper.toDTO(sauvegarde);
	}

}
