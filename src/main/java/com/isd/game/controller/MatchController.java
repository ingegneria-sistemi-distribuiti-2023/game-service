package com.isd.game.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isd.game.commons.error.CustomServiceException;
import com.isd.game.dto.MatchDTO;
import com.isd.game.mapper.MatchService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * The controller (presentation layer, or port) is a protocol interface which exposes application functionality as RESTful web services.
 * It should to that and nothing more.
 *
 * @RestController is a convenience annotation that does nothing more than adding the Controller and ResponseBody annotations
 * @RequestMapping maps HTTP requests to handler methods of MVC and REST controllers
 * @RequestParam binds a web request parameter to a method parameter
 * @PathVariable binds a URI template variable to a method parameter
 * @RequestBody binds the HTTP request body to a transfer or domain object, enabling automatic deserialization of the inbound HTTP request body onto a Java object
 * @ResponseBody indicates that a method return value should be bound to the web response body
 * @ResponseStatus configures the HTTP response status code for an exception
 * @ExceptionHandler configures global handling for specific exceptions
 * @SessionAttributes makes model attributes available to the web session
 * @SessionAttribute retrieves a specific model attribute from the web session 
 * @Controller is a specialization of Component that allows for implementation classes to be autodetected through classpath scanning
 */
@RestController()// @Controller + @ResponseBody
@RequestMapping("/game/match")
@RequiredArgsConstructor
public class MatchController {
	private final MatchService matchService;

	// return all the matches
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<MatchDTO>> all(@RequestHeader("Secret-Key") String secretKey) {
		return new ResponseEntity<>(matchService.getAllData(), HttpStatus.OK);
	}

	// return a match by id
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<MatchDTO> id(@PathVariable("id") Integer id, @RequestHeader("Secret-Key") String secretKey) throws CustomServiceException {
		return new ResponseEntity<>(matchService.findMatch(id), HttpStatus.OK);
	}
}
