/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.tck.post;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.tck.HttpClientTCK;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * Verifies that POST request can can send a body
 */
public interface PostBodyTCK extends HttpClientTCK {

    @TestFactory
    @DisplayName("should POST body to server")
    default Stream<DynamicTest> shouldPostBodyToServer(final MockServerClient mockServer) {
        // given
        final var httpClient = createHttpClient();

        // when
        final var testCases = Stream.of(
                new PostBodyTestCase("/test", 200, "test", "text/plain; charset=utf-8"),
                new PostBodyTestCase("/test", 300, "test", "text/plain; charset=utf-8"),
                new PostBodyTestCase("/test", 400, "test", "text/plain; charset=utf-8"),
                new PostBodyTestCase("/test", 500, "test", "text/plain; charset=utf-8"),
                new PostBodyTestCase("/some/other/path", 123, "", "text/plain; charset=utf-8"),
                new PostBodyTestCase("", 123, "", "text/plain; charset=utf-8")
        );

        // then
        return testCases.map(testCase -> verifyRequestResponse(testCase, mockServer, httpClient, this::path))
                .filter(Objects::nonNull);
    }

    record PostBodyTestCase(String path, int statusCode, String body, String mediaType) {
    }

    default boolean supportsPostBodyTestCase(PostBodyTestCase testCase) {
        return true;
    }

    private DynamicTest verifyRequestResponse(
            final PostBodyTestCase testCase,
            final MockServerClient mockServer,
            final HttpClient httpClient,
            final Function<String, String> resolve) {
        if (supportsPostBodyTestCase(testCase)) {
            return dynamicTest(createDisplayName(testCase), () -> {
                mockServer.reset().when(request()
                        .withPath(testCase.path())
                        .withBody(testCase.body())
                        .withContentType(MediaType.parse(testCase.mediaType()))
                        .withMethod("POST"))
                        .respond(response()
                                .withStatusCode(testCase.statusCode())
                                .withBody(testCase.body()));

                final var response = httpClient.post(resolve.apply(testCase.path()))
                        .content(testCase.body())
                        .mediaType(testCase.mediaType())
                        .executeOnCallingThread();

                Assertions.assertAll("response",
                        () -> Assertions.assertEquals(testCase.body(), response.body()),
                        () -> Assertions.assertEquals(testCase.statusCode(), response.statusCode())
                );
            });
        }
        return null;
    }

    private static String createDisplayName(final PostBodyTestCase testCase) {
        return String.format("should POST body to server [%s] with [%s]: '%s'",
                testCase.path(), testCase.statusCode(), testCase.body());
    }

}
