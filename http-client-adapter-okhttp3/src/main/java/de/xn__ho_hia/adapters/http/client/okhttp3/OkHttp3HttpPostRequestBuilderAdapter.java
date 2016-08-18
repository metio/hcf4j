package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import de.xn__ho_hia.adapters.http.client.builder.HttpPostRequestBuilder;
import de.xn__ho_hia.adapters.http.client.builder.HttpPostWithContentRequestBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

final class OkHttp3HttpPostRequestBuilderAdapter extends AbstractOkHttp3Adapter
        implements HttpPostRequestBuilder {

    OkHttp3HttpPostRequestBuilderAdapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpPostRequestBuilderAdapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpPostWithContentRequestBuilder content(final String content) {
        return new OkHttp3HttpPostWithContentRequestBuilder(this, content);
    }

}
