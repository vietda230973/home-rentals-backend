package fr.vietda.rentals.home.model.dto;

import java.util.Date;

public class MessagesDto {

	private Integer id;

	private RentalsDto rentals;
	
	private String message;

	private Date createdAt;

	private Date updatedAt;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public RentalsDto getRentals() {
		return rentals;
	}

	public void setRentals(RentalsDto rentals) {
		this.rentals = rentals;
	}

}
