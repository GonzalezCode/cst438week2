package cst438week2.cst438week2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cst438week2.cst438week2.domain.*;
import cst438week2.cst438week2.service.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void getCityInfo() throws Exception {
		City city = new City(1,"TestCity","TST","DistrictTest",10000);
		CityInfo expected = new CityInfo(city,"TestCountry", 1, "2");
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(city,"TestCountry", 1, "2"));
		CityInfo result = cityService.getCityInfo("TestCity");
		assertEquals(expected, result);
	}

}
