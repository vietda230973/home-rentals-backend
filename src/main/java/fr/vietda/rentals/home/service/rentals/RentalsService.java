package fr.vietda.rentals.home.service.rentals;

import java.io.IOException;
import java.util.List;

import fr.vietda.rentals.home.model.dto.RentalsDto;
import jakarta.persistence.EntityNotFoundException;

public interface RentalsService {

	List<RentalsDto> recupererTousRentals();

	RentalsDto recupererRentalsParId(Integer id) throws EntityNotFoundException;

	RentalsDto creerRentals(RentalsDto rentalsDto) throws IOException;

	RentalsDto mettreAJourRentals(Integer id, RentalsDto rentalsDto);

}
