package com.isd.game.controller;

import org.springframework.web.bind.annotation.RestController;

import com.isd.game.dto.Dummy2Dto;
import com.isd.game.mapper.DummyMapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
@RestController // @Controller + @ResponseBody
public class DemoController {

	@Autowired
	private DummyMapperService dummyMapperService;

	@RequestMapping(path = "/dummy1", method = RequestMethod.GET)
	public String dummy1() {
		return "dummy1";
	}
	
	// dummy2: take a get some parameters and return them as a string
	@RequestMapping(path = "/dummy2", method = RequestMethod.POST)
	public String dummy2(@RequestBody Dummy2Dto dummy2dto) {
		return dummy2dto.getName() + " " + dummy2dto.getSurname();
	}

	// dummy all: get all the data from the database
	@RequestMapping(path = "/dummyall", method = RequestMethod.GET)
	public String dummyall() {
		return dummyMapperService.getAllData().toString();
	}
}
