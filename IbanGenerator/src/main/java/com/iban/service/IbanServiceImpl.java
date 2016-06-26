package com.iban.service;

import java.util.HashSet;
import java.util.Set;

import com.iban.domain.Iban;
import com.iban.domain.enums.CountryCode;
import com.iban.domain.exceptions.UnsupportedCountryException;

public class IbanServiceImpl implements IIbanService {

	Set<String> ibansCodeCache = new HashSet<>();
	
	@Override
	public synchronized String generateRandomIban(String countryCode) throws UnsupportedCountryException {
		
		Iban iban;
		
		try {
			iban = new Iban(CountryCode.valueOf(countryCode));
			while(ibansCodeCache.contains(iban.toString())) {
				generateRandomIban(countryCode);
			}
			ibansCodeCache.add(iban.toString());
		} catch (IllegalArgumentException ex) {
			throw new UnsupportedCountryException();
		}
			
		return iban.toString();
	}
}
