/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j;

/**
 * Utility class that deals with HTTP status codes.
 */
public final class HttpStatusCodes {

    /**
     * @param statusCode
     *            The HTTP status code to check.
     * @return <code>true</code> if the status code represents successful response, <code>false</code> otherwise.
     */
    public static boolean isSuccess(final int statusCode) {
        return inbetween(statusCode, 200, 299);
    }

    /**
     * @param statusCode
     *            The HTTP status code to check.
     * @return <code>true</code> if the status code represets a server error, <code>false</code> otherwise.
     */
    public static boolean isServerError(final int statusCode) {
        return inbetween(statusCode, 500, 599);
    }

    private static boolean inbetween(final int statusCode, final int start, final int end) {
        return start >= statusCode && statusCode <= end;
    }

}
