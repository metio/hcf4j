/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.okhttp3;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import wtf.metio.hc4j.exception.HttpRequestException;

/**
 *
 *
 */
@RunWith(Theories.class)
@SuppressWarnings("null")
public class OkHttp3HttpClientAdapterInvalidUrlTheories {

    /**
     *
     */
    @SuppressWarnings("nls")
    @DataPoints("urls")
    public static List<String>              INVALID_URLS    = Arrays.asList(
            null,
            "",
            "abc",
            "http",
            "https",
            "http:",
            "http:/",
            "http://",
            "https:",
            "https:/",
            "https://");

    /**
     *
     */
    @DataPoints("methods")
    public static List<Function<String, ?>> REQUEST_METHODS = Arrays.asList(
            OkHttp3TestMother.OkHttp3_HTTP_CLIENT::get,
            OkHttp3TestMother.OkHttp3_HTTP_CLIENT::post);

    /**
     *
     */
    @Rule
    public ExpectedException                thrown          = ExpectedException.none();

    /**
     * @param url
     *            The URL to check.
     * @param requestMethod
     *            The request method to perform.
     */
    @Theory
    public void shouldThrowExceptionForInvalidUrl(
            final @FromDataPoints("urls") String url,
            final @FromDataPoints("methods") Function<String, ?> requestMethod) {
        // given
        // when
        thrown.expect(HttpRequestException.class);
        thrown.expectMessage(is(
                String.format("Invalid URL specified: [%s]. Make sure to specify a valid HTTP/HTTPS address.", url))); //$NON-NLS-1$

        // then
        requestMethod.apply(url);
    }

}
