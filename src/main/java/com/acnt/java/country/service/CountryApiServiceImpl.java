package com.acnt.java.country.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.acnt.java.country.entity.CountryEntity;
import com.acnt.java.country.exception.CountryExternalApiDataException;
import com.acnt.java.country.exception.CountryExternalApiException;

/**
 * The Class CountryApiService.
 */
@Service
public class CountryApiServiceImpl implements CountryApiService {

	/** The log. */
	Logger log = LoggerFactory.getLogger(CountryApiServiceImpl.class);

	/**
	 * Find country population by density.
	 *
	 * @return the stream
	 * @throws CountryExternalApiException     the country external api exception
	 * @throws CountryExternalApiDataException the country external api data
	 *                                         exception
	 */
	public Stream<CountryEntity> findCountryPopulationByDensity(RestTemplate countriesRestApi, String restCountriesUrl)
			throws CountryExternalApiException, CountryExternalApiDataException {

		log.info("findCountryPopulationByDensity: Started ");
		Stream<CountryEntity> populationByDenseCountries;

		try {
			Stream<CountryEntity> countryDetails = callRestcountriesApi(countriesRestApi, restCountriesUrl);

			populationByDenseCountries = countryDetails
					.sorted(Comparator.comparingDouble(CountryEntity::getPopulationDense).reversed());
			log.info("findCountryPopulationByDensity: Sorted by descending. ");

		}

		catch (CountryExternalApiDataException cdEx) {
			throw new CountryExternalApiDataException(
					"Data Processed on Country data has an issue as " + cdEx.getMessage());
		}

		log.info("findCountryPopulationByDensity: ended ");

		return populationByDenseCountries;

	}

	/**
	 * Find most borders country.
	 *
	 * @param regionName the region name
	 * @return the stream of country data
	 * @throws CountryExternalApiException     the country external api exception
	 * @throws CountryExternalApiDataException the country external api data
	 *                                         exception
	 */
	public Stream<CountryEntity> findMostBordersCountry(RestTemplate countriesRestApi, String restCountriesUrl,
			String regionName) throws CountryExternalApiException, CountryExternalApiDataException {

		log.info("findMostBordersCountry: Started ");
		Stream<CountryEntity> mostBorderCountries;
		try {

			Stream<CountryEntity> countryDetails = callRestcountriesApi(countriesRestApi, restCountriesUrl);

			mostBorderCountries = countryDetails
					.filter(countryEntity -> (countryEntity.getRegion().equals(regionName))).sorted(Comparator
							.comparing(countryEntity -> ((CountryEntity) countryEntity).getBorders().size()).reversed())
					.limit(1);
		}

		catch (CountryExternalApiDataException cdEx) {
			throw new CountryExternalApiDataException(
					"Data Processed on Country data has an issue as " + cdEx.getMessage());
		}

		log.info("finMostBordersCountry: Sorted by descending and Ended ");

		return mostBorderCountries;

	}

	/**
	 * Call restcountries api.
	 * 
	 * @param countriesRestApi
	 *
	 * @param restCountriesUrl the rest countries url
	 * @return the stream
	 * @throws CountryExternalApiException the country external api exception
	 */
	private Stream<CountryEntity> callRestcountriesApi(RestTemplate countriesRestApi, String restCountriesUrl)
			throws CountryExternalApiException {
		log.info("callRestcountriesApi: Country URL response called. ");

		Stream<CountryEntity> countryDetails;
		try {
			CountryEntity[] response = countriesRestApi.getForObject(restCountriesUrl, CountryEntity[].class);
			log.info("callRestcountriesApi: Country URL response received. ");

			countryDetails = Stream.of(response);

		}

		catch (HttpClientErrorException httpEx) {
			throw new CountryExternalApiException("Rest Call Country API has an issue as " + httpEx.getMessage());
		}

		return countryDetails;
	}

}
