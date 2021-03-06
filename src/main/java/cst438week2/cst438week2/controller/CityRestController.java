package cst438week2.cst438week2.controller;

import cst438week2.cst438week2.domain.City;
import cst438week2.cst438week2.domain.CityInfo;
import cst438week2.cst438week2.domain.CityRepository;
import cst438week2.cst438week2.service.CityService;
import cst438week2.cst438week2.service.WeatherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {

		// lookup cities in the database that match the city
		List<City> cities = cityRepository.findByName(cityName);
		if (cities.size() == 0) {
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND); //city name not found send 404
		}
		else {
			City currentCity = cities.get(0); //in case of multiple cities, take the first one
			CityInfo restCity = cityService.getCityInfo(currentCity.getName()); // get city info
			return new ResponseEntity<CityInfo>(restCity, HttpStatus.OK); // send 200 and JSON city info
		}
	}
}
