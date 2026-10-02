package fr.vietda.rentals.home.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import fr.vietda.rentals.home.model.dto.MessagesDto;
import fr.vietda.rentals.home.model.entity.Messages;

@Mapper(componentModel = "spring", uses = {RentalsMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MessagesMapper {
	
	MessagesDto toDTO(Messages rentals);

	List<MessagesDto> toDTOList(List<Messages> rentalsList);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "users", ignore = true)
	Messages toEntity(MessagesDto dto);

}
