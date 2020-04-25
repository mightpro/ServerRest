package fr.jbavril.restServer.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//Afin de gérer les problèmes réseaux dû au cas où le serveur et le client ne sont pas sur le même serveur
@Component
public class CrossDomainFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); //Toutes les URI sont autorisées
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, contentype, accept, x-req");
		filterChain.doFilter(request, response);
		
	}

}
