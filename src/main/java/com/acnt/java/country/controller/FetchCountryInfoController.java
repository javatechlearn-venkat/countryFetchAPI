package com.acnt.java.country.controller;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.acnt.java.country.exception.CountryExternalApiDataException;
import com.acnt.java.country.exception.CountryExternalApiException;
import com.acnt.java.country.entity.CountryEntity;
import com.acnt.java.country.service.CountryApiService;

/**
 * The Class FetchCountryInfoController.
 */
@RestController
@RequestMapping("/api/country")
public class FetchCountryInfoController {

	Logger log = LoggerFactory.getLogger(FetchCountryInfoController.class);

	@Autowired
	private CountryApiService countryApiService;

	/** The countries rest api. */
	@Autowired
	private RestTemplate countriesRestApi;

	/** The rest countries url. */
	@Value("${countriesApi.Url}")
	private String restCountriesUrl;

	/**
	 * Get list of countries population by density in descending order.
	 *
	 * @return the countries list
	 */
	@GetMapping(value = "/getPopulationByDensity")
	public Stream<CountryEntity> getCountryPopulationByDensity()
			throws CountryExternalApiException, CountryExternalApiDataException {

		log.info("getCountryPopulationByDensity: Started");

		Stream<CountryEntity> populationByDenseCountries = countryApiService
				.findCountryPopulationByDensity(countriesRestApi, restCountriesUrl);

		log.info("getCountryPopulationByDensity: Ended");
		return populationByDenseCountries;

	}

	/**
	 * Get list of countries on region containing the most bordering countries of a
	 * different region.
	 *
	 * @return the most borders country
	 */
	@GetMapping(value = "/getMostBordersCountry")
	public Stream<CountryEntity> getMostBordersCountry(@RequestParam("regionName") String regionName)
			throws CountryExternalApiException, CountryExternalApiDataException {

		log.info("FetchCountryInfoController:getMostBordersCountry: Started");

		Stream<CountryEntity> mostBorderCountries = countryApiService.findMostBordersCountry(countriesRestApi,
				restCountriesUrl, regionName);

		log.info("FetchCountryInfoController:getMostBordersCountry: Ended");

		return mostBorderCountries;

	}

}
