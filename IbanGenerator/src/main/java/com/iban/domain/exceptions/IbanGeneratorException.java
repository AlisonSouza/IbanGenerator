package com.iban.domain.exceptions;

public abstract class IbanGeneratorException extends RuntimeException {

	private static final long serialVersionUID = 80090093361346088L;

	public IbanGeneratorException() {
        super();
    }

    public IbanGeneratorException(final String message) {
        super(message);
    }

    public IbanGeneratorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IbanGeneratorException(final Throwable cause) {
        super(cause);
    }
}
