package fr.vietda.rentals.home.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "api/auth")
public class AuthetificationController {
	
	private JwtService jwtService;
	
	public AuthetificationController(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@Tag(name = "Post", description = "POST methode pour enregister au système par l'authentification API")
	@PostMapping("/register")
	@Operation(summary = "Enregister au système par l'authentification", description = "Enregister au système par l'authentification. La réponse est un objet TokenDto du token de l'authentification. ")
	public ResponseEntity<TokenDto> registrer(@RequestBody RegisterRequestDto dto) throws EntityNotFoundException, ParseException {
	
		String token = jwtService.generateToken(dto.getEmail());
		TokenDto tokenReponse = new TokenDto();
		tokenReponse.setToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(tokenReponse);
	}
	
	@Tag(name = "Post", description = "POST methode pour connecter au système par l'authentification API")
	@PostMapping("/login")
	@Operation(summary = "Connecter au système par l'authentification", description = "Connecter au système par l'authentification. La réponse est un objet TokenDto du token de l'authentification. ")
	public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto dto) throws EntityNotFoundException, ParseException {
	
		String token = jwtService.generateToken(dto.getEmail());
		TokenDto tokenReponse = new TokenDto();
		tokenReponse.setToken(token);
		return ResponseEntity.status(HttpStatus.OK).body(tokenReponse);
	}
	
	@Tag(name = "Get", description = "GET methode pour recupérer le détail d'un utilisateur connecté API")
	@GetMapping("/me")
	@Operation(summary = "recupérer le détail d'un utilisateur connecté", description = "recupérer le détail d'un utilisateur connecté. La réponse est un objet UserDto de l'utilisateur connecté. ")
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
