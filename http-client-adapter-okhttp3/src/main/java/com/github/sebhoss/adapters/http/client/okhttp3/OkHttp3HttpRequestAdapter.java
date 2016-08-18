package com.github.sebhoss.adapters.http.client.okhttp3;

import java.io.IOException;
import java.util.function.Function;

import com.github.sebhoss.adapters.http.client.HttpResponse;
import com.github.sebhoss.adapters.http.client.builder.HttpGetRequestBuilder;
import com.github.sebhoss.adapters.http.client.exception.HttpRequestException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

final class OkHttp3HttpRequestAdapter extends AbstractOkHttp3Adapter
        implements HttpGetRequestBuilder {

    OkHttp3HttpRequestAdapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), requestBuilder);
    }

    OkHttp3HttpRequestAdapter(
            final OkHttpClient client,
            final Function<String, MediaType> memoizer,
            final Builder requestBuilder) {
        super(client, memoizer, requestBuilder);
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        try {
            final Request request = requestBuilder.build();
            final Call call = client.newCall(request);
            final Response response = call.execute();
            return new OkHttp3HttpResponseAdapter(response);
        } catch (final IOException exception) {
            throw new HttpRequestException(exception);
        }
    }

}
