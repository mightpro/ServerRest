package fr.jbavril.restServer.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice est une spécialisation de l'annotation de @Component introduit en Spirng 3.2
@ControllerAdvice(basePackages = {"fr.jbavril.restServer"}) //Spring >= 3.2
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler {
	
	
	private Logger logger = LoggerFactory.getLogger(GlobalHandlerControllerException.class); 
	/**
	 * @ ExceptionHandler Intercepte toutes les exceptions dites gloables quelles que soient leurs origines
	 * @ ModelAttribule Permet de créer un object model global par ex pour la créa et initia d'une vue ou d'une page 
	 * @ InitBinder Permet une initialisation globale 
	 */
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		// Vous pouvez initialiser toute autre donnée ici
	}
	
	@ModelAttribute
	public void globalAttributes(Model model) {
		//La variable technicalError pourra être exploité n'importe où dans l'application
		model.addAttribute("TechnicalError", "Une erreur technique est survenue !");
	}
	
	@ExceptionHandler(TechnicalErrorException.class)
	public ModelAndView technicalErrorException(Exception exception){
		logger.error("technicalErrorException");
		ModelAndView mav = new ModelAndView();
		mav.addObject("Exception", exception.getMessage());
		mav.setViewName("error");
		return mav; //renvoi vers une page JSP
	}
	
	@ExceptionHandler(Exception.class) //Toutes les autres exception sont interceptés ici
	public ResponseEntity<BusinessResourceExceptionResponse> unknowError(HttpServletRequest req, Exception ex) {
		logger.error("unknowError");
		BusinessResourceExceptionResponse response = new BusinessResourceExceptionResponse();
		response.setErrorCode("Technical Error");
		response.setErrorMessage(ex.getMessage());
		response.setRequestURL(req.getRequestURL().toString());
		return new ResponseEntity<BusinessResourceExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessResourceException.class)
	public ResponseEntity<BusinessResourceExceptionResponse> resourceNotFound(HttpServletRequest req, BusinessResourceException ex){
		logger.error("resourceNotFound");
		BusinessResourceExceptionResponse response = new BusinessResourceExceptionResponse();
		response.setStatus(ex.getStatus());
		response.setErrorCode(ex.getErrorCode());
		response.setErrorMessage(ex.getMessage());
		response.setRequestURL(req.getRequestURL().toString());
		return new ResponseEntity<BusinessResourceExceptionResponse>(response, ex.getStatus());
	}
	
}
