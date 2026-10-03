package fr.vietda.rentals.home.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class SpringSecurityConfig {

	private String jwtKey = "v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq";

//	private static final String[] AUTH_WHITELIST = {
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/api/public/**",
//            "/api/public/authenticate",
//            "/actuator/*",
//            "/swagger-ui/**",
//            "/api/auth/**",
//            "/v3/api-docs/**",
//            "/docs/**",
//            "/webjars/**"
//            };
	
	private static final String[] AUTH_WHITELIST = {
	        "/swagger-ui.html",
	        "/swagger-ui/**",
	        "/v3/api-docs",
	        "/v3/api-docs/**",
	        "/webjars/**",
	        "/api/public/**",
	        "/api/auth/**",
	        "/actuator/*"
	};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
		                // Autorise explicitement toutes les routes d'authentification
		                .requestMatchers(AUTH_WHITELIST).permitAll()
		                // Toutes les autres requêtes nécessitent une authentification
		                .anyRequest().authenticated())
				.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey()));
	}
	
	private SecretKey secretKey() {
	    return new SecretKeySpec(jwtKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withSecretKey(secretKey()).macAlgorithm(MacAlgorithm.HS256).build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}