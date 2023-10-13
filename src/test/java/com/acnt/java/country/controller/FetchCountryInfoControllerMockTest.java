package com.acnt.java.country.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acnt.java.country.entity.CountryEntity;
import com.acnt.java.country.service.CountryApiService;
import com.acnt.java.country.service.CountryApiServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class FetchCountryInfoControllerMockTest {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CountryEntity countryEntity;

	@Mock
	private CountryApiService countryApiService = new CountryApiServiceImpl();

	@InjectMocks
	private FetchCountryInfoController fetchCountryApi;

	@Value("${countriesApi.Url}")
	private String restCountriesUrl;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(fetchCountryApi).build();
	}

	@Test
	void testGetPopulationByDensity() throws Exception {

		// when
		MockHttpServletResponse response = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/country/getPopulationByDensity").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void testGetMostBordersCountry() throws Exception {

		// when
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/country/getMostBordersCountry").param("regionName", "Asia")
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
