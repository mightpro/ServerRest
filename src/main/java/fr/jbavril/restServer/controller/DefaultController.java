package fr.jbavril.restServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Le service par défaut permet de détecter si le serveur ecoute
 * @author jb
 *
 */
@Controller
public class DefaultController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	
	
	//Ou @RequestMapping(method=RequestMethod.GET, value="/")
	@GetMapping(value = "/")
	public ResponseEntity<String> pong(){
		logger.info("Démarrage des services OK...");
		//Si on n'a pas besoin de spécifier les codes http ont peut utiliser ResponseBody
		//ResponseEntity corresponds à ResponseBody + HttpStatus
		return new ResponseEntity<String>("Réponse du serveur : " + HttpStatus.OK.name(), HttpStatus.OK);
	}
	
}
