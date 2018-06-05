package com.rvilardo.store.exception;

import static java.lang.String.format;

/**
 * Exception class for validation failure (not found record).
 * 
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -841540785468580102L;

    /**
     * Main constructor which uses {@link String#format(String, Object...)} to
     * generate it's message.
     * 
     * <code>
     * "The error patterns are matched against the entire string of the
     * <strong>errorMessage property</strong> in the Lambda response"
     * </code>
     * 
     * @param format
     *            string format
     * @param args
     *            format arguments
     */
    public NotFoundException(String format, Object... args) {
        super(format("[%s]: %s", ValidationException.class.getSimpleName(), format(format, args)));
    }
}