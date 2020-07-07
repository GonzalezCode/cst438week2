package cst438week2.cst438week2.controller;

import cst438week2.cst438week2.domain.*;
import cst438week2.cst438week2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {

		// TODO Your code goes here
		// TODO delete the following line
		model.addAttribute("city",cityService.getCityInfo(cityName));
		return "city";
	} 
	
}