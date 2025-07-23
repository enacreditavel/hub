package com.fleethub.hub.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String userEmail = null;
		String jwt = null;

		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				userEmail = jwtUtil.extractEmail(jwt);
			}
			
			

			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsuarioDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
				if (jwtUtil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
					return;
				}
			}
		} catch (Exception e) {
			logger.error("Erro no filtro JWT: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro ao processar token");
			return;
		}

		chain.doFilter(request, response);
	}

}
