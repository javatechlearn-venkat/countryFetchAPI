package com.acnt.java.country.entity;

import java.util.List;

/**
 * The Class CountryEntity.
 */
public class CountryEntity {

	/** The name. */
	private CountryNameEntity name;

	/** The borders. */
	private List<String> borders;

	/** The area. */
	private double area;

	/** The population. */
	private long population;

	/** The region. */
	private String region;

	/**
	 * Gets the borders.
	 *
	 * @return the borders
	 */
	public List<String> getBorders() {
		return borders;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public CountryNameEntity getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(CountryNameEntity name) {
		this.name = name;
	}

	/**
	 * Gets the area.
	 *
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 *
	 * @param area the new area
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public long getPopulation() {
		return population;
	}

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(long population) {
		getPopulationDense();
		this.population = population;
	}

	/**
	 * Gets the population dense.
	 *
	 * @return the population dense
	 */
	public double getPopulationDense() {

		double value = 0;
		// to avoid divide by zero error.
		if (this.area > 0) {
			value = population / area;
		}

		return value;
	}

	/**
	 * Sets the population dense.
	 *
	 * @param population the population
	 * @param area       the area
	 */
	public void setPopulationDense(long population, double area) {
	}

	/**
	 * Sets the borders.
	 *
	 * @param borders the new borders
	 */
	public void setBorders(List<String> borders) {
		this.borders = borders;
	}

	/**
	 * Gets the region.
	 *
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Sets the region.
	 *
	 * @param region the new region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

}
