/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.tck.urls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import wtf.metio.hcf4j.exception.HttpRequestException;
import wtf.metio.hcf4j.tck.HttpClientTCK;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Verifies that invalid URLs are correctly handled.
 */
public interface InvalidUrlTCK extends HttpClientTCK {

    @TestFactory
    @DisplayName("should throw exception for invalid URLs")
    default Stream<DynamicTest> shouldThrowExceptionForInvalidURLs() {
        // given
        final var httpClient = createHttpClient();

        // when
        final var testCases = Stream.of(
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

        // then
        return testCases.flatMap(url -> Stream.of(
                throwExceptionDuringExecution(url, "GET", httpClient::get),
                throwExceptionDuringExecution(url, "POST", httpClient::post),
                throwExceptionDuringExecution(url, "PUT", httpClient::put),
                throwExceptionDuringExecution(url, "DELETE", httpClient::delete),
                throwExceptionDuringExecution(url, "HEAD", httpClient::head),
                throwExceptionDuringExecution(url, "PATCH", httpClient::patch)
        ));
    }

    private static DynamicTest throwExceptionDuringExecution(final String url, final String methodName,
                                                             final Function<String, ?> requestMethod) {
        return dynamicTest(createDisplayName(url, methodName), () -> {
            final HttpRequestException exception = Assertions.assertThrows(HttpRequestException.class,
                    () -> requestMethod.apply(url));
            Assertions.assertEquals(invalidUrl(url), exception.getMessage());
        });
    }

    private static String createDisplayName(final String url, final String requestMethod) {
        return String.format("should throw exception for invalid URL '%s' during '%s'", url, requestMethod);
    }

    private static String invalidUrl(final String url) {
        return String.format("Invalid URL specified: [%s]. Make sure to specify a valid HTTP/HTTPS address.", url);
    }

}
