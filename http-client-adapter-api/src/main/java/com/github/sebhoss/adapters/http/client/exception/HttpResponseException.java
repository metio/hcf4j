package com.github.sebhoss.adapters.http.client.exception;

/**
 *
 *
 */
public class HttpResponseException extends RuntimeException {

    private static final long serialVersionUID = 2237006160600278676L;

    /**
     * @param throwable
     *            The exception to wrap.
     */
    public HttpResponseException(final Throwable throwable) {
        super(throwable);
    }

}
