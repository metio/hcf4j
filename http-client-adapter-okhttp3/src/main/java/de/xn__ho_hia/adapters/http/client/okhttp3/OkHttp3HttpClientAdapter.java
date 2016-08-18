package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.qos.cal10n.IMessageConveyor;
import de.xn__ho_hia.adapters.http.client.HttpClient;
import de.xn__ho_hia.adapters.http.client.builder.HttpGetRequestBuilder;
import de.xn__ho_hia.adapters.http.client.builder.HttpPostRequestBuilder;
import de.xn__ho_hia.adapters.http.client.errors.UrlErrors;
import de.xn__ho_hia.adapters.http.client.exception.HttpRequestException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
        return new OkHttp3HttpRequestAdapter(client, mediaTypeCreator, messages, url(url).get());
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
        return new Request.Builder().url(url);
    }

    private Supplier<HttpRequestException> httpRequestException(final String url) {
        return () -> new HttpRequestException(messages.getMessage(UrlErrors.INVALID_URL, url));
    }

}
