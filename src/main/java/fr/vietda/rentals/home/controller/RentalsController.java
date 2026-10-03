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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "api/rentals")
public class RentalsController {
	
	private final RentalsService rentalsService;
	
	public RentalsController(RentalsService rentalsService) {
		super();
		this.rentalsService = rentalsService;
	}

	@Tag(name = "get", description = "GET methode pour tous les rentals API")
	@GetMapping
	@Operation(summary = "Liste tous rentals", description = "Recupérer tous rentals. La réponse est la liste des objets RentalsDto. ")
	public ResponseEntity<RentalsReponseAllDto> listOrders() {
		RentalsReponseAllDto rentalsDto = new RentalsReponseAllDto();
		rentalsDto.setRentals(rentalsService.recupererTousRentals().toArray(new RentalsDto[0]));
		return ResponseEntity.ok(rentalsDto);
	}
	
	@Tag(name = "get", description = "GET methode pour recupérer les détails d'un rental API")
	@GetMapping("/{id}")
	@Operation(summary = "Récupérer les détails d'un rentals", description = "Recupérer les détails d'un rental. La réponse est un objet RentalsDto. ")
    public ResponseEntity<RentalsDto> detailRentals(@PathVariable int id) throws EntityNotFoundException, ParseException {
        return ResponseEntity.ok(rentalsService.recupererRentalsParId(id));
    }
	
	@Tag(name = "Post", description = "POST methode pour créer un rental API")
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "Créer d'un nouveau rentals", description = "Créer d'un nouveau rentals. La réponse est un objet RentalsDto du rentals cré. ")
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

	@Tag(name = "Put", description = "PUT methode pour mettre à jour un rental API")
	@PutMapping("/update/{id}")
	@Operation(summary = "Mettre à jour un rentals", description = "Mettre à jour un rentals. La réponse est un objet RentalsDto du rentals modifié. ")
    public ResponseEntity<RentalsReponseDto> updateRendezVous(@PathVariable int id, @RequestBody RentalsDto rendezvousDetails) throws EntityNotFoundException, ParseException {
        rentalsService.mettreAJourRentals(id, rendezvousDetails);
        RentalsReponseDto rentalsReponse = new RentalsReponseDto();
	    rentalsReponse.setMessage("Rentals " + id +  "  est mis à jour.");
        return ResponseEntity.ok(rentalsReponse);
    }

}
