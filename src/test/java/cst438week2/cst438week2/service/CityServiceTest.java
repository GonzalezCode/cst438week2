package cst438week2.cst438week2.service;

import cst438week2.cst438week2.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	@Test
	public void contextLoads() {
	}

	@BeforeEach
	public void setUpEach(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCityFound() throws Exception {
		City city = new City(1,"TestCity","TST","DistrictTest",10000);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		CityInfo expected = new CityInfo(city,"TestCountry", 1, "2");

		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode("TST")).willReturn(new Country("TST","TestCountry"));
		given(weatherService.getTempAndTime("TestCity")).willReturn(new TempAndTime(1, 2, 3));

		List<City> result = cityRepository.findByName("TestCity");
		Country country = countryRepository.findByCode(city.getCountryCode());
		TempAndTime tempAndTime = weatherService.getTempAndTime(city.getName());

		CityInfo cityInfoResult = cityService.getCityInfo("TestCity");
		cityInfoResult.setTemp(1);

		assertEquals(cities, result);
		assertEquals(expected, cityInfoResult);

	}
	
	@Test
	public void  testCityNotFound() {
		City city = new City(1,"TestCity","TST","DistrictTest",10000);
		List<City> cities = new ArrayList<City>();
		given(cityRepository.findByName("TestCity")).willReturn(cities);

		assertEquals(cityService.getCityInfo(city.getName()), null);
	}
	//public CityInfo(City city, String countryName, double temp, String time)
	@Test
	public void  testCityMultiple() {
		int temp = 1;
		City city = new City(1,"TestCity","TST","DistrictTest",10000);
		City city2 = new City(2,"TestCity","TST2","DistrictTest2",10000);
		CityInfo expected = new CityInfo(city, "TestCountry", temp,"2");
		expected.setTemp((temp - 273.15) * 9.0/5.0 + 32.0);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		cities.add(city2);

		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode("TST")).willReturn(new Country("TST","TestCountry"));
		given(weatherService.getTempAndTime("TestCity")).willReturn(new TempAndTime(1, 2, 3));

		assertEquals(expected, cityService.getCityInfo("TestCity"));
	}

}
