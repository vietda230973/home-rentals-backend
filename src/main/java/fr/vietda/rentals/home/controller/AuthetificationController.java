package fr.vietda.rentals.home.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vietda.rentals.home.model.dto.LoginRequestDto;
import fr.vietda.rentals.home.model.dto.RegisterRequestDto;
import fr.vietda.rentals.home.model.dto.TokenDto;
import fr.vietda.rentals.home.model.dto.UsersDto;
import fr.vietda.rentals.home.service.JwtService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "api/auth")
public class AuthetificationController {
	
	private JwtService jwtService;
	
	public AuthetificationController(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<TokenDto> registrer(@RequestBody RegisterRequestDto dto) throws EntityNotFoundException, ParseException {
	
		String token = jwtService.generateToken(dto.getEmail());
		TokenDto tokenReponse = new TokenDto();
		tokenReponse.setToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(tokenReponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto dto) throws EntityNotFoundException, ParseException {
	
		String token = jwtService.generateToken(dto.getEmail());
		TokenDto tokenReponse = new TokenDto();
		tokenReponse.setToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(tokenReponse);
	}
	
	@GetMapping("/me")
	public ResponseEntity<UsersDto> getMe() {
		
		UsersDto users = new UsersDto();
		users.setId(1);
		users.setName("Test TEST");
		users.setEmail("test@test.com");
		users.setCreatedAt(new Date("2022/02/02"));
		users.setUpdatedAt(new Date("2022/08/02"));
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
}
