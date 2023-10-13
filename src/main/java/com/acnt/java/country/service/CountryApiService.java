package com.acnt.java.country.service;

import java.util.stream.Stream;

import org.springframework.web.client.RestTemplate;

import com.acnt.java.country.entity.CountryEntity;
import com.acnt.java.country.exception.CountryExternalApiDataException;
import com.acnt.java.country.exception.CountryExternalApiException;

/**
 * The Class CountryApiService.
 */
public interface CountryApiService {

	/**
	 * Find country population by density.
	 *
	 * @param restTemplate the rest template
	 * @param restUrl      the rest url
	 * @return the stream
	 * @throws CountryExternalApiException     the country external api exception
	 * @throws CountryExternalApiDataException the country external api data
	 *                                         exception
	 */
	public Stream<CountryEntity> findCountryPopulationByDensity(RestTemplate restTemplate, String restUrl)
			throws CountryExternalApiException, CountryExternalApiDataException;

	/**
	 * Find most borders country.
	 *
	 * @param restTemplate the rest template
	 * @param restUrl      the rest url
	 * @param regionName   the region name
	 * @return the stream
	 * @throws CountryExternalApiException     the country external api exception
	 * @throws CountryExternalApiDataException the country external api data
	 *                                         exception
	 */
	public Stream<CountryEntity> findMostBordersCountry(RestTemplate restTemplate, String restUrl, String regionName)
			throws CountryExternalApiException, CountryExternalApiDataException;

}
