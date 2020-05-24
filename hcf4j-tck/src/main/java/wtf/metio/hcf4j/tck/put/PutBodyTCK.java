/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.tck.put;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.tck.HttpClientTCK;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Verifies that PUT request can can send a body
 */
public interface PutBodyTCK extends HttpClientTCK {

    @TestFactory
    @DisplayName("should PUT body to server")
    default Stream<DynamicTest> shouldPutBodyToServer(final MockServerClient mockServer) {
        // given
        final var httpClient = createHttpClient();

        // when
        final var testCases = Stream.of(
                new TestCase("/test", 200, "test", "text/plain; charset=utf-8"),
                new TestCase("/test", 300, "test", "text/plain; charset=utf-8"),
                new TestCase("/test", 400, "test", "text/plain; charset=utf-8"),
                new TestCase("/test", 500, "test", "text/plain; charset=utf-8"),
                new TestCase("/some/other/path", 123, "", "text/plain; charset=utf-8"),
                new TestCase("", 123, "", "text/plain; charset=utf-8")
        );

        // then
        return testCases.map(testCase -> verifyRequestResponse(testCase, mockServer, httpClient, this::path));
    }

    record TestCase(String path, int statusCode, String body, String mediaType) {
    }

    private static DynamicTest verifyRequestResponse(
            final TestCase testCase,
            final MockServerClient mockServer,
            final HttpClient httpClient,
            final Function<String, String> resolve) {
        return dynamicTest(createDisplayName(testCase), () -> {
            mockServer.reset().when(request()
                    .withPath(testCase.path())
                    .withBody(testCase.body())
                    .withContentType(MediaType.parse(testCase.mediaType()))
                    .withMethod("PUT"))
                    .respond(response()
                            .withStatusCode(testCase.statusCode())
                            .withBody(testCase.body()));

            // when
            final var response = httpClient.put(resolve.apply(testCase.path()))
                    .content(testCase.body())
                    .mediaType(testCase.mediaType())
                    .executeOnCallingThread();

            // then
            Assertions.assertAll("response",
                    () -> Assertions.assertEquals(testCase.body(), response.body()),
                    () -> Assertions.assertEquals(testCase.statusCode(), response.statusCode())
            );
        });
    }

    private static String createDisplayName(final TestCase testCase) {
        return String.format("should PUT body to server [%s] with [%s]: '%s'", testCase.path(), testCase.statusCode(), testCase.body());
    }

}
