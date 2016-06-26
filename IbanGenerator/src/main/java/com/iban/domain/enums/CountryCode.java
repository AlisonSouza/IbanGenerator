package com.iban.domain.enums;

public enum CountryCode {
	
	DE("Germany"),
	AT("Austria"),
	NL("Netherlands");
	
    private final String name;

    private CountryCode(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAlpha2() {
        return name();
    }
}
