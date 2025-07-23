package com.fleethub.hub.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fleethub.hub.exceptions.CustomAccessDeniedHandlerException;
import com.fleethub.hub.exceptions.CustomAuthenticationEntryPointException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private UsuarioDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/auth/**").permitAll()
									.anyRequest().authenticated())
				.exceptionHandling(exception -> exception
		                .authenticationEntryPoint(new CustomAuthenticationEntryPointException())
		                .accessDeniedHandler(new CustomAccessDeniedHandlerException())		            )
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		

		return http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ origem do Angular
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // se usar cookies, JWT, etc

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

	@Bean
	public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		return authenticationManagerBuilder.build();
	}

}