package fr.vietda.rentals.home.controller;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vietda.rentals.home.model.dto.MessageReponseDto;
import fr.vietda.rentals.home.model.dto.MessageRequestDto;
import fr.vietda.rentals.home.model.dto.MessagesDto;
import fr.vietda.rentals.home.model.dto.RentalsDto;
import fr.vietda.rentals.home.service.messages.MessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "api/messages")
public class MessagesController {
	
	private final MessagesService messagesService;
	
	public MessagesController(MessagesService messagesService) {
		super();
		this.messagesService = messagesService;
	}

	@Tag(name = "Post", description = "POST methode pour créer un message rentals API")
	@PostMapping()
	@Operation(summary = "Créer d'un message rentals", description = "Créer d'un nouveau message rentals. La réponse est un objet MessageReponseDto du message rentals cré. ")
	public ResponseEntity<MessageReponseDto> creer(@RequestBody MessageRequestDto dto) throws EntityNotFoundException, ParseException {
		MessagesDto messageDto = new MessagesDto();
		messageDto.setMessage(dto.getMessage());
		RentalsDto rentalsDto = new RentalsDto();
		rentalsDto.setId(dto.getRental_id());
		rentalsDto.setOwnerId(dto.getUser_id());
		messageDto.setRentals(rentalsDto);
		messagesService.sendMessages(messageDto);
		MessageReponseDto rentalsReponse = new MessageReponseDto();
		rentalsReponse.setMessage("Message est enregsitré.");
		return ResponseEntity.status(HttpStatus.CREATED).body(rentalsReponse);
	}
}
