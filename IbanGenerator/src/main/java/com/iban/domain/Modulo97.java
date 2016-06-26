package com.iban.domain;

public class Modulo97 {

	private static final int COUNTRY_CODE_INDEX = 0;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGIT_INDEX = COUNTRY_CODE_LENGTH;
    private static final int CHECK_DIGIT_LENGTH = 2;
    private static final int BBAN_INDEX = CHECK_DIGIT_INDEX + CHECK_DIGIT_LENGTH;
    private static final int MOD = 97;
    private static final long MAX = 999999999;
	
	/**
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param iban String value
     * @return modulo 97
     */
    public static int calculateMod(final String iban) {
        final String reformattedIban = getBban(iban) + getCountryCodeAndCheckDigit(iban);
        long total = 0;
        for (int i = 0; i < reformattedIban.length(); i++) {
            final int numericValue = Character.getNumericValue(reformattedIban.charAt(i));
            total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;

            if (total > MAX) {
                total = (total % MOD);
            }

        }
        return (int) (total % MOD);
    }
    
    public static String calculateCheckDigit(final String reformattedIban) {
        final int modResult = calculateMod(reformattedIban);
        final int checkDigitIntValue = (98 - modResult);
        final String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }
    
	public static String getBban(final String iban) {
        return iban.substring(BBAN_INDEX);
    }
	
	public static String getCountryCodeAndCheckDigit(final String iban) {
        return iban.substring(COUNTRY_CODE_INDEX,
                COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH + CHECK_DIGIT_LENGTH);
    }
}
