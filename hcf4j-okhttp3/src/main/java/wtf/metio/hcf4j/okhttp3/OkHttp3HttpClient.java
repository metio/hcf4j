/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.okhttp3;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.builder.HttpDeleteRequestBuilder;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;
import wtf.metio.hcf4j.builder.HttpHeadRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPatchRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPutRequestBuilder;
import wtf.metio.hcf4j.errors.UrlErrors;
import wtf.metio.hcf4j.exception.HttpRequestException;

final class OkHttp3HttpClient implements HttpClient {

    private final OkHttpClient                client;
    private final Function<String, MediaType> mediaTypeCreator;
    private final IMessageConveyor            messages;

    OkHttp3HttpClient(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
        this.messages = messages;
    }

    @Override
    public HttpGetRequestBuilder get(final String url) {
        return new OkHttp3HttpGetRequestBuilder(client, mediaTypeCreator, messages,
                Checks.requireNonNull(url(url).get()));
    }

    @Override
    public HttpHeadRequestBuilder head(final String url) {
        return new OkHttp3HttpHeadRequestBuilder(client, mediaTypeCreator, messages,
                Checks.requireNonNull(url(url).head()));
    }

    @Override
    public HttpPostRequestBuilder post(final String url) {
        return new OkHttp3HttpPostRequestBuilder(client, mediaTypeCreator, messages, url(url));
    }

    @Override
    public HttpPutRequestBuilder put(final String url) {
        return new OkHttp3HttpPutRequestBuilder(client, mediaTypeCreator, messages, url(url));
    }

    @Override
    public HttpDeleteRequestBuilder delete(final String url) {
        return new OkHttp3HttpDeleteRequestBuilder(client, mediaTypeCreator, messages,
                Checks.requireNonNull(url(url).delete()));
    }

    @Override
    public HttpPatchRequestBuilder patch(final String url) {
        return new OkHttp3HttpPatchRequestBuilder(client, mediaTypeCreator, messages, url(url));
    }

    private Request.Builder url(final String url) {
        return Optional.ofNullable(url)
                .filter(Objects::nonNull)
                .filter(OkHttp3HttpClient::isValidUrl)
                .map(OkHttp3HttpClient::builder)
                .orElseThrow(httpRequestException(url));
    }

    private static boolean isValidUrl(final String urlToTest) {
        return Optional.ofNullable(HttpUrl.parse(urlToTest))
                .isPresent();
    }

    private static Request.Builder builder(final String url) {
        return Checks.requireNonNull(new Request.Builder().url(url));
    }

    private Supplier<HttpRequestException> httpRequestException(final String url) {
        return () -> new HttpRequestException(Checks.requireNonEmpty(
                messages.getMessage(UrlErrors.INVALID_URL, url)));
    }

}
