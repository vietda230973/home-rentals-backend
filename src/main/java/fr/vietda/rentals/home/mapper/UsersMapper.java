package fr.vietda.rentals.home.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import fr.vietda.rentals.home.model.dto.UsersDto;
import fr.vietda.rentals.home.model.entity.Users;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsersMapper {
	
	UsersDto toDTO(Users rentals);

	List<UsersDto> toDTOList(List<Users> rentalsList);
	
	@Mapping(target = "id", ignore = true)
	Users toEntity(UsersDto dto);

}
