package com.github.sebhoss.adapters.http.client.okhttp3;

import com.github.sebhoss.adapters.http.client.HttpRequest;
import com.github.sebhoss.adapters.http.client.builder.HttpPostWithContentRequestBuilder;

import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

final class OkHttp3HttpPostWithContentRequestBuilder extends AbstractOkHttp3Adapter
        implements HttpPostWithContentRequestBuilder {

    private final String content;

    OkHttp3HttpPostWithContentRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final String content) {
        super(adapter);
        this.content = content;
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final MediaType okHttpMediaType = mediaTypeCreator.apply(mediaType);
        final RequestBody body = RequestBody.create(okHttpMediaType, content);
        final Builder post = requestBuilder.post(body);

        return new OkHttp3HttpRequestAdapter(this, post);
    }

}
