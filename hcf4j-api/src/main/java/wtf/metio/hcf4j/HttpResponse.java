/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j;

/**
 * Represents the server response received after sending a {@link HttpRequest}.
 *
 * @see HttpRequest
 */
public interface HttpResponse {

    /**
     * @return The response body as a {@link String}.
     */
    String body();

    /**
     * @return The HTTP status code of the response.
     */
    int statusCode();

    /**
     * @return <code>true</code> if the response contains a 2xx status code.
     */
    boolean isSuccess();

}
