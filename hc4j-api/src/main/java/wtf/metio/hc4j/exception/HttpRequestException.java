/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.exception;

/**
 *
 *
 */
public final class HttpRequestException extends RuntimeException {

    private static final long serialVersionUID = 8880626795092004381L;

    /**
     * Default constructor w/o any data
     */
    public HttpRequestException() {
        super();
    }

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
