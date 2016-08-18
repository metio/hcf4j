package de.xn__ho_hia.adapters.http.client.exception;

/**
 *
 *
 */
public class HttpResponseException extends RuntimeException {

    private static final long serialVersionUID = 2237006160600278676L;

    /**
     * @param message
     *            The error message to use.
     * @param throwable
     *            The exception to wrap.
     */
    public HttpResponseException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
