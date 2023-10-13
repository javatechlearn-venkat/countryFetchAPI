package com.acnt.java.country.entity;

import java.util.List;
import java.util.Map;

/**
 * The Class CountryNameEntity.
 * 
 * Author : Venkat
 */
public class CountryNameEntity {

	/** The common. */
	private String common;

	/** The official. */
	private String official;

	/** The native name. */
	private Map<String, NativeName> nativeName;

	/** The alt spellings. */
	private List<String> altSpellings;

	/**
	 * Gets the common.
	 *
	 * @return the common
	 */
	public String getCommon() {
		return common;
	}

	/**
	 * Sets the common.
	 *
	 * @param common the new common
	 */
	public void setCommon(String common) {
		this.common = common;
	}

	/**
	 * Gets the official.
	 *
	 * @return the official
	 */
	public String getOfficial() {
		return official;
	}

	/**
	 * Sets the official.
	 *
	 * @param official the new official
	 */
	public void setOfficial(String official) {
		this.official = official;
	}

	/**
	 * Gets the native name.
	 *
	 * @return the native name
	 */
	public Map<String, NativeName> getNativeName() {
		return nativeName;
	}

	/**
	 * Sets the native name.
	 *
	 * @param nativeName the native name
	 */
	public void setNativeName(Map<String, NativeName> nativeName) {
		this.nativeName = nativeName;
	}

	/**
	 * Gets the alt spellings.
	 *
	 * @return the alt spellings
	 */
	public List<String> getAltSpellings() {
		return altSpellings;
	}

	/**
	 * Sets the alt spellings.
	 *
	 * @param altSpellings the new alt spellings
	 */
	public void setAltSpellings(List<String> altSpellings) {
		this.altSpellings = altSpellings;
	}
}
