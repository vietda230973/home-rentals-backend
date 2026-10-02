package fr.vietda.rentals.home.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import fr.vietda.rentals.home.model.dto.RentalsDto;
import fr.vietda.rentals.home.model.entity.Rentals;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RentalsMapper {
	
	@Mapping(source = "users.id", target = "ownerId")
	@Mapping(target = "pictureImage", ignore = true)
	RentalsDto toDTO(Rentals rentals);

	List<RentalsDto> toDTOList(List<Rentals> rentalsList);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "users", ignore = true)
	Rentals toEntity(RentalsDto dto);

}
