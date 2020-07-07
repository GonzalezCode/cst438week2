package cst438week2.cst438week2.service;

import cst438week2.cst438week2.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		// TODO your code goes here
		List<City> cities = cityRepository.findByName(cityName);
		if (cities.size() == 0){return null;}
		else{
			City city = cities.get(0);
			Country country = countryRepository.findByCode(city.getCountryCode());
			TempAndTime tempAndTime = weatherService.getTempAndTime(city.getName());
			double temp = (tempAndTime.temp - 273.15) * 9.0/5.0 + 32.0;
			return new CityInfo(city, country.getName(), temp, Long.toString(tempAndTime.time));
		}
	}
	
}
