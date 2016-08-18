package de.xn__ho_hia.adapters.http.client.exception;

/**
 *
 *
 */
public final class HttpRequestException extends RuntimeException {

    private static final long serialVersionUID = 8880626795092004381L;

    /**
     * @param throwable
     *            The exception to wrap.
     */
    public HttpRequestException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * @param message
     *            The error message to use.
     */
    public HttpRequestException(final String message) {
        super(message);
    }

    /**
     * @param message
     *            The error message to use.
     * @param throwable
     *            The exception to wrap.
     */
    public HttpRequestException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
