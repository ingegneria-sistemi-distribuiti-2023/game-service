package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/*
 * @RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations
 * @RequestMapping maps HTTP requests to handler methods of MVC and REST controllers
 * @RequestParam binds a web request parameter to a method parameter
 * @PathVariable binds a URI template variable to a method parameter
 * @RequestBody binds the HTTP request body to a transfer or domain object, enabling automatic deserialization of the inbound HTTP request body onto a Java object
 * @ResponseBody indicates that a method return value should be bound to the web response body
 * @ResponseStatus configures the HTTP response status code for an exception
 * @ExceptionHandler configures global handling for specific exceptions
 * @SessionAttributes makes model attributes available to the web session
 * @SessionAttribute retrieves a specific model attribute from the web session 
 * @Controller is a specialization of @Component that allows for implementation classes to be autodetected through classpath scanning
 * 
 */
@RestController // @Controller + @ResponseBody
public class DemoController {
	@RequestMapping(path = "/dummy1", method = RequestMethod.GET)
	public String dummy1() {
		return "dummy1";
	}
	
	// dummy2: take a get some parameters and return them as a string
	@RequestMapping(path = "/dummy2", method = RequestMethod.GET)
	public String dummy2(@RequestParam String name, @RequestParam Optional<String> surname) {
		return name + " " + surname.orElse("surname not provided -- ");
	}
}
