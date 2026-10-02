package fr.vietda.rentals.home.repository;

import org.springframework.stereotype.Repository;

import fr.vietda.rentals.home.model.entity.Rentals;

@Repository
public interface RentalsRepository extends BaseRepository<Rentals, Integer> {

}
