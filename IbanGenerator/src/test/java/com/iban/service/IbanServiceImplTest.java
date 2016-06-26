package com.iban.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.iban.domain.exceptions.UnsupportedCountryException;

public class IbanServiceImplTest {

	IIbanService iIbanService = new IbanServiceImpl();
	
	@Test
	public void shouldReturnValidIbanForGermany() {
		String iban = iIbanService.generateRandomIban("DE");
		
		Assert.assertNotNull(iban);
		Assert.assertEquals("DE62669904521485209735".length(), iban.toString().length());
	}
	
	@Test
	public void shouldReturnValidIbanForAustria() {
		String iban = iIbanService.generateRandomIban("AT");
		
		Assert.assertNotNull(iban);
		Assert.assertEquals("AT918260629018231368".length(), iban.toString().length());
	}
	
	@Test
	public void shouldReturnValidIbanForNetherlands() {
		String iban = iIbanService.generateRandomIban("NL");
		
		Assert.assertNotNull(iban);
		Assert.assertEquals("NL07WEPU1584069329".length(), iban.toString().length());
	}
	
	@Test(expected = UnsupportedCountryException.class)
	public void shouldReturnUnsupportedCountryCode() {
		iIbanService.generateRandomIban("BR");
	}
	
	@Test
	public void shouldReturn10000IbansCode() {
		
		List<String> ibans = new ArrayList<>();
		
		for(int index = 0; index < 10000; index++) {
			ibans.add(iIbanService.generateRandomIban("DE"));
		}
		
		Assert.assertEquals(10000, ibans.size());
	}
}
