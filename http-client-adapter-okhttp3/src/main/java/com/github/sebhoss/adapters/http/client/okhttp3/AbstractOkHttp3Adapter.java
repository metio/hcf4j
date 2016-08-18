package com.github.sebhoss.adapters.http.client.okhttp3;

import java.util.function.Function;

import com.github.sebhoss.adapters.http.client.shared.AbstractHttpClientAdapter;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

abstract class AbstractOkHttp3Adapter extends AbstractHttpClientAdapter<OkHttpClient, MediaType> {

    protected final Builder requestBuilder;

    protected AbstractOkHttp3Adapter(final AbstractOkHttp3Adapter adapter) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.requestBuilder);
    }

    protected AbstractOkHttp3Adapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.client, adapter.mediaTypeCreator, requestBuilder);
    }

    protected AbstractOkHttp3Adapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator);
        this.requestBuilder = requestBuilder;
    }

}
