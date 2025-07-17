package com.fleethub.hub.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fleethub.hub.usuario.DTO.UsuarioDTO;
import com.fleethub.hub.usuario.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UsuarioService usuarioService;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private JwtUtil jwtUtil;

	private UsuarioDetailsServiceImpl userDetailsService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UsuarioDTO usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {
			System.out.println("tentativa de autenticar");
			System.out.println(loginRequest.getEmail() + " " + loginRequest.getSenha());
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));
		} catch (BadCredentialsException e) {
			System.out.println("BadCredentialsException: " + e.getMessage());
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos", e);
		}

		final UsuarioDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		System.out.println( "Usuário autenticado: " + userDetails.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);
		System.out.println("JWT gerado: " + jwt);
		

		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername()));
	}

}