package com.iban.domain.exceptions;

/**
 * Thrown to indicate that requested country is not supported.
 */
public class UnsupportedCountryException extends IbanGeneratorException {

    private static final long serialVersionUID = -3733353745417164234L;

    private String countryCode;

    public UnsupportedCountryException() {
        super();
    }

    public UnsupportedCountryException(final String s) {
        super(s);
    }

    public UnsupportedCountryException(String countryCode, final String s) {
        super(s);
        this.countryCode = countryCode;
    }

    public UnsupportedCountryException(final String s, final Throwable t) {
        super(s, t);
    }

    public UnsupportedCountryException(final Throwable t) {
        super(t);
    }

    public String getCountryCode() {
        return countryCode;
    }
}