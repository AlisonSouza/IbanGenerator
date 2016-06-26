package com.iban.service;

import com.iban.domain.exceptions.UnsupportedCountryException;

public interface IIbanService {

	/**
	 * Supported only Country code from Germany: DE;
	 * 								   Austria: AT;
	 * 								   Netherlands: NL.
	 * 
	 * @param countryCode
	 * @throws UnsupportedCountryException
	 * @return Iban code
	 */
	String generateRandomIban(String countryCode) throws UnsupportedCountryException;
}
