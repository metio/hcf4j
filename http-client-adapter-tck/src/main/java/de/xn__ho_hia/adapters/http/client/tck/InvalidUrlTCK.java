package de.xn__ho_hia.adapters.http.client.tck;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import de.xn__ho_hia.adapters.http.client.HttpClient;
import de.xn__ho_hia.adapters.http.client.exception.HttpRequestException;

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
            final HttpRequestException exception = Assertions.expectThrows(HttpRequestException.class,
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
