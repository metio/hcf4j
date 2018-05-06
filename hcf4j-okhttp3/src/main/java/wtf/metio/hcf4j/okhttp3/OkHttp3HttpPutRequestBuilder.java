package wtf.metio.hcf4j.okhttp3;

import java.util.function.Function;

import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPutRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPutWithContentRequestBuilder;

final class OkHttp3HttpPutRequestBuilder extends AbstractOkHttp3Adapter implements HttpPutRequestBuilder {

    OkHttp3HttpPutRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpPutRequestBuilder(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpRequest emptyBody() {
        final RequestBody body = RequestBody.create(null, new byte[0]);
        final Builder post = requestBuilder.put(body);

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(post));
    }

    @Override
    public HttpPutWithContentRequestBuilder content(final String content) {
        return new OkHttp3HttpPutWithContentRequestBuilder(this, content);
    }

}
