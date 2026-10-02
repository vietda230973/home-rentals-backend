package fr.vietda.rentals.home.service.messages.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import fr.vietda.rentals.home.mapper.MessagesMapper;
import fr.vietda.rentals.home.model.dto.MessagesDto;
import fr.vietda.rentals.home.model.dto.RentalsDto;
import fr.vietda.rentals.home.model.entity.Messages;
import fr.vietda.rentals.home.model.entity.Rentals;
import fr.vietda.rentals.home.model.entity.Users;
import fr.vietda.rentals.home.repository.MessagesRepository;
import fr.vietda.rentals.home.repository.RentalsRepository;
import fr.vietda.rentals.home.repository.UsersRepository;
import fr.vietda.rentals.home.service.messages.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService {
	
	private final MessagesRepository messagesRepository;
	
	private final RentalsRepository rentalsRepository;
	
	private final MessagesMapper messagesMapper;
	
	private final UsersRepository usersRepository;
	
	
	
	public MessagesServiceImpl(MessagesRepository messagesRepository, RentalsRepository rentalsRepository,
			MessagesMapper messagesMapper, UsersRepository usersRepository) {
		super();
		this.messagesRepository = messagesRepository;
		this.rentalsRepository = rentalsRepository;
		this.messagesMapper = messagesMapper;
		this.usersRepository = usersRepository;
	}


	@Override
	public MessagesDto sendMessages (MessagesDto messagesDto) {
		Messages messages = new Messages();
		
		if (Objects.nonNull(messagesDto.getRentals())) {
			RentalsDto rentals = messagesDto.getRentals();
			
			Rentals rentalsSave = rentalsRepository.getById(rentals.getId());
			Users usersSave = usersRepository.getById(rentals.getOwnerId());
			rentalsSave.setUsers(usersSave);
			
			messages.setRentals(rentalsSave);
			messages.setUsers(usersSave);
		}
		messages.setMessage(messagesDto.getMessage());
		Messages messagesSave = messagesRepository.save(messages);
		return messagesMapper.toDTO(messagesSave);
				
	}

}
