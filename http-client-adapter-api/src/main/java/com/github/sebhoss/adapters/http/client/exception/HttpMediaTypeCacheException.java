package com.github.sebhoss.adapters.http.client.exception;

/**
 *
 *
 */
public class HttpMediaTypeCacheException extends RuntimeException {

    private static final long serialVersionUID = -1547519566091374123L;

    /**
     * @param e
     *            The exception to wrap.
     */
    public HttpMediaTypeCacheException(final Throwable e) {
        super(e);
    }

}
