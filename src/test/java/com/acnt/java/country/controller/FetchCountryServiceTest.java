package com.acnt.java.country.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.acnt.java.country.entity.CountryEntity;
import com.acnt.java.country.exception.CountryExternalApiException;
import com.acnt.java.country.service.CountryApiService;
import com.acnt.java.country.service.CountryApiServiceImpl;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
public class FetchCountryServiceTest {

	@InjectMocks
	private CountryApiService countryApiService = new CountryApiServiceImpl();

	@Value("${countriesApi.Url}")
	private String restCountriesUrl;

	@Value("${countriesApi_Nt.Url}")
	private String restCountriesUrlWrng;

	static List<CountryEntity> countryEntities;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	void testGetCountryPopulationByDensity() throws IOException {

		readCountriesJson("countriespopulation.json");
		Stream<CountryEntity> countryTest = countryApiService.findCountryPopulationByDensity(restTemplate,
				restCountriesUrl);

		assertEquals(countryTest.toList().get(0).getName().getCommon(), countryEntities.get(0).getName().getCommon());

	}

	private void readCountriesJson(String fileName) throws IOException, UnsupportedEncodingException {
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
				JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));) {
			Gson gson = new Gson();
			countryEntities = new ArrayList<>();
			reader.beginArray();
			while (reader.hasNext()) {
				CountryEntity country = gson.fromJson(reader, CountryEntity.class);
				countryEntities.add(country);
			}
		}
	}

	@Test
	void testGetMostBordersCountry() throws IOException {
		readCountriesJson("china.json");
		Stream<CountryEntity> countryTest = countryApiService.findMostBordersCountry(restTemplate, restCountriesUrl,
				"Asia");

		assertEquals(countryTest.toList().get(0).getName().getCommon(), countryEntities.get(0).getName().getCommon());

	}

	@Test
	void testApiException() throws IOException {
		assertThrows(CountryExternalApiException.class, () -> {
			countryApiService.findCountryPopulationByDensity(restTemplate, restCountriesUrlWrng);
		});
		
		
		assertThrows(CountryExternalApiException.class, () -> {
			countryApiService.findMostBordersCountry(restTemplate, restCountriesUrlWrng, "wrong");
		});

	}

}
