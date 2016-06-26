package com.iban.domain;

import com.iban.domain.enums.CountryCode;
import com.iban.domain.exceptions.UnsupportedCountryException;

/**
 * International Bank Account Number
 *
 * <a href="http://en.wikipedia.org/wiki/ISO_13616">ISO_13616</a>.
 */
public final class Iban {

	private static final String DEFAULT_CHECK_DIGIT = "00";
	private static final int COUNTRY_CODE_INDEX = 0;
	private static final int COUNTRY_CODE_LENGTH = 2;
	private static final int CHECK_DIGIT_INDEX = COUNTRY_CODE_LENGTH;
	private static final int CHECK_DIGIT_LENGTH = 2;
	private static final int BBAN_INDEX = CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH;

	private String value;
	private CountryCode countryCode;
	private String bankCode;
	private String accountNumber;

	public Iban() {
		
	}
	
	public Iban(CountryCode countryCode) {
		this.countryCode = countryCode;
		buildRandom();
	}

	private void buildRandom() throws IllegalArgumentException, UnsupportedCountryException {
		fillFieldsRandomly();
		build();
	}

	private void build() throws UnsupportedCountryException {

		final String formattedIban = formatIban();
		String reformattedIban = replaceCheckDigit(formattedIban, Iban.DEFAULT_CHECK_DIGIT);
		final String checkDigit = Modulo97.calculateCheckDigit(reformattedIban);
		this.value = replaceCheckDigit(formattedIban, checkDigit);
	}

	private String replaceCheckDigit(final String iban, final String checkDigit) {
		return getCountryCode(iban) + checkDigit + getBban(iban);
	}

	private String getBban(final String iban) {
		return iban.substring(BBAN_INDEX);
	}

	private String getCountryCode(final String iban) {
		return iban.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
	}

	private String formatIban() {
		final StringBuilder sb = new StringBuilder();
		sb.append(countryCode.getAlpha2());
		sb.append(DEFAULT_CHECK_DIGIT);
		sb.append(formatBban());
		return sb.toString();
	}

	private String formatBban() {
		final StringBuilder sb = new StringBuilder();
		final BbanStructure structure = BbanStructure.forCountry(countryCode);

		if (structure == null) {
			throw new UnsupportedCountryException(countryCode.toString(), "Country code is not supported.");
		}

		for (final BbanStructureEntry entry : structure.getEntries()) {
			switch (entry.getEntryType()) {
			case bank_code:
				sb.append(bankCode);
				break;
			case account_number:
				sb.append(accountNumber);
				break;
			}
		}
		return sb.toString();
	}

	private void fillFieldsRandomly() {
		final BbanStructure structure = BbanStructure.forCountry(countryCode);

		if (structure == null) {
			throw new UnsupportedCountryException(countryCode.toString(), "Country code is not supported.");
		}

		for (final BbanStructureEntry entry : structure.getEntries()) {
			switch (entry.getEntryType()) {
			case bank_code:
				bankCode = entry.getRandom();
				break;
			case account_number:
				accountNumber = entry.getRandom();
				break;
			}
		}
	}

	@Override
	public String toString() {
		return value;
	}

	public String toFormattedString() {
		final StringBuilder ibanBuffer = new StringBuilder(value);
		final int length = ibanBuffer.length();

		for (int i = 0; i < length / 4; i++) {
			ibanBuffer.insert((i + 1) * 4 + i, ' ');
		}

		return ibanBuffer.toString().trim();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Iban) {
			return value.equals(((Iban) obj).value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
}
