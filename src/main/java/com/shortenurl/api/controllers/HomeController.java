package com.shortenurl.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController class.
 * 
 * @author devetude
 */
@Controller
public class HomeController {
	@GetMapping("")
	public String index() {
		return "index";
	}
}