/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.tck;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.exception.HttpRequestException;

/**
 * Verifies that invalid URLs are correctly handled.
 */
@SuppressWarnings({ "nls", "null" })
public abstract class InvalidUrlTCK implements HttpClientTCK {

    @TestFactory
    Stream<DynamicTest> invalidUrls() {
        final HttpClient httpClient = createHttpClient();
        return Stream.of(
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
                "https://")
                .flatMap(url -> Stream.of(
                        throwExceptionDuringExecution(url, "GET", httpClient::get),
                        throwExceptionDuringExecution(url, "POST", httpClient::post)));
    }

    private static DynamicTest throwExceptionDuringExecution(final String url, final String methodName,
            final Function<String, ?> requestMethod) {
        return dynamicTest(createDisplayName(url, methodName), () -> {
            final HttpRequestException exception = Assertions.assertThrows(HttpRequestException.class,
                    () -> requestMethod.apply(url));
            Assertions.assertEquals(createExceptionMessage(url), exception.getMessage());
        });
    }

    private static String createDisplayName(final String url, final String requestMethod) {
        return String.format("Should throw exception for invalid URL '%s' during '%s'", url, requestMethod);
    }

    private static String createExceptionMessage(final String url) {
        return String.format("Invalid URL specified: [%s]. Make sure to specify a valid HTTP/HTTPS address.", url);
    }

}
