package com.github.sebhoss.adapters.http.client.okhttp3;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.github.sebhoss.adapters.http.client.exception.HttpRequestException;

import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 *
 *
 */
@RunWith(Theories.class)
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
        thrown.expectMessage(is("Invalid URL specified: " + url)); //$NON-NLS-1$

        // then
        requestMethod.apply(url);
    }

}
