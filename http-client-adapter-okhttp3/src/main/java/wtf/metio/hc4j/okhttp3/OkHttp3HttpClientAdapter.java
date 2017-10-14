/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.okhttp3;

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
import wtf.metio.hc4j.HttpClient;
import wtf.metio.hc4j.builder.HttpGetRequestBuilder;
import wtf.metio.hc4j.builder.HttpPostRequestBuilder;
import wtf.metio.hc4j.errors.UrlErrors;
import wtf.metio.hc4j.exception.HttpRequestException;

final class OkHttp3HttpClientAdapter implements HttpClient {

    private final OkHttpClient                client;
    private final Function<String, MediaType> mediaTypeCreator;
    private final IMessageConveyor            messages;

    OkHttp3HttpClientAdapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
        this.messages = messages;
    }

    @Override
    public HttpGetRequestBuilder get(final String url) {
        return new OkHttp3HttpRequestAdapter(client, mediaTypeCreator, messages,
                Checks.requireNonNull(url(url).get()));
    }

    @Override
    public HttpPostRequestBuilder post(final String url) {
        return new OkHttp3HttpPostRequestBuilderAdapter(client, mediaTypeCreator, messages, url(url));
    }

    private Request.Builder url(final String url) {
        return Optional.ofNullable(url)
                .filter(Objects::nonNull)
                .filter(OkHttp3HttpClientAdapter::isValidUrl)
                .map(OkHttp3HttpClientAdapter::builder)
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

    @Override
    public void head(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void put(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void patch(final String url) {
        // TODO Auto-generated method stub

    }

}
