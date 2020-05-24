/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.tck.get;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockserver.client.MockServerClient;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.tck.HttpClientTCK;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Verifies that GET request can be executed
 */
public interface GetUrlTCK extends HttpClientTCK {

    @TestFactory
    @DisplayName("should GET response from server")
    default Stream<DynamicTest> shouldGetResponseFromServer(final MockServerClient mockServer) {
        // given
        final var httpClient = createHttpClient();

        // when
        final var testCases = Stream.of(
                new GetUrlTestCase("/test", 200, "test"),
                new GetUrlTestCase("/test", 300, "test"),
                new GetUrlTestCase("/test", 400, "test"),
                new GetUrlTestCase("/test", 500, "test"),
                new GetUrlTestCase("/some/other/path", 123, ""),
                new GetUrlTestCase("", 123, "")
        );

        // then
        return testCases.map(testCase -> verifyRequestResponse(testCase, mockServer, httpClient, this::path))
                .filter(Objects::nonNull);
    }

    record GetUrlTestCase(String path, int statusCode, String body) {
    }

    default boolean supportsGetUrlTestCase(GetUrlTestCase testCase) {
        return true;
    }

    private DynamicTest verifyRequestResponse(
            final GetUrlTestCase testCase,
            final MockServerClient mockServer,
            final HttpClient httpClient,
            final Function<String, String> resolve) {
        if (supportsGetUrlTestCase(testCase)) {
            return dynamicTest(createDisplayName(testCase), () -> {
                mockServer.reset().when(request()
                        .withPath(testCase.path())
                        .withMethod("GET"))
                        .respond(response()
                                .withStatusCode(testCase.statusCode())
                                .withBody(testCase.body()));

                final var response = httpClient.get(resolve.apply(testCase.path())).executeOnCallingThread();

                Assertions.assertAll("response",
                        () -> Assertions.assertEquals(testCase.body(), response.body()),
                        () -> Assertions.assertEquals(testCase.statusCode(), response.statusCode())
                );
            });
        }
        return null;
    }

    private static String createDisplayName(final GetUrlTestCase testCase) {
        return String.format("should GET response from server [%s] with [%s]: '%s'",
                testCase.path(), testCase.statusCode(), testCase.body());
    }

}
