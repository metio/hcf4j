package wtf.metio.hcf4j.okhttp3;

import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import wtf.metio.hcf4j.builder.HttpPatchRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPatchWithContentRequestBuilder;

final class OkHttp3HttpPatchRequestBuilder extends AbstractOkHttp3Adapter implements HttpPatchRequestBuilder {

    OkHttp3HttpPatchRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpPatchRequestBuilder(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpPatchWithContentRequestBuilder content(final String content) {
        return new OkHttp3HttpPatchWithContentRequestBuilder(this, content);
    }

}
