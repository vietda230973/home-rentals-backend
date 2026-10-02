package fr.vietda.rentals.home.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.vietda.rentals.home.model.dto.RentalsDto;
import fr.vietda.rentals.home.model.dto.RentalsReponseAllDto;
import fr.vietda.rentals.home.model.dto.RentalsReponseDto;
import fr.vietda.rentals.home.service.rentals.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "api/rentals")
public class RentalsController {
	
	private final RentalsService rentalsService;
	
	public RentalsController(RentalsService rentalsService) {
		super();
		this.rentalsService = rentalsService;
	}

	@GetMapping
	@Operation(summary = "List tous rentals")
	public ResponseEntity<RentalsReponseAllDto> listOrders() {
		RentalsReponseAllDto rentalsDto = new RentalsReponseAllDto();
		rentalsDto.setRentals(rentalsService.recupererTousRentals().toArray(new RentalsDto[0]));
		return ResponseEntity.ok(rentalsDto);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Récupérer les détails d'un rentals")
    public ResponseEntity<RentalsDto> detailRentals(@PathVariable int id) throws EntityNotFoundException, ParseException {
        return ResponseEntity.ok(rentalsService.recupererRentalsParId(id));
    }
	
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<RentalsReponseDto> creer( @RequestParam("name") String name,
	        @RequestParam("surface") BigDecimal surface,
	        @RequestParam("price") BigDecimal price,
	        @RequestParam("description") String description,
	        @RequestParam(value = "picture", required = false) MultipartFile picture,
	        @RequestParam("userId") String userId) throws EntityNotFoundException, ParseException, IOException {
	    
		RentalsDto dto = new RentalsDto();
		dto.setName(name);
		dto.setSurface(surface);
		dto.setPrice(price);
		dto.setDescription(description);
		dto.setOwnerId(Integer.valueOf(userId));
		dto.setPictureImage(picture);
		
		rentalsService.creerRentals(dto);
	    RentalsReponseDto rentalsReponse = new RentalsReponseDto();
	    rentalsReponse.setMessage("Rentals est crée.");
	    return ResponseEntity.status(HttpStatus.CREATED).body(rentalsReponse);
	}

	
	@PutMapping("/update/{id}")
    public ResponseEntity<RentalsReponseDto> updateRendezVous(@PathVariable int id, @RequestBody RentalsDto rendezvousDetails) throws EntityNotFoundException, ParseException {
        rentalsService.mettreAJourRentals(id, rendezvousDetails);
        RentalsReponseDto rentalsReponse = new RentalsReponseDto();
	    rentalsReponse.setMessage("Rentals " + id +  "  est crée.");
        return ResponseEntity.ok(rentalsReponse);
    }

}
