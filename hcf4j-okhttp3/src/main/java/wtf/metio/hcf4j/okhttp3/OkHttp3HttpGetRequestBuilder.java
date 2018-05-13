package wtf.metio.hcf4j.okhttp3;

import java.util.function.Function;

import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;

final class OkHttp3HttpGetRequestBuilder extends AbstractOkHttp3HttpRequest implements HttpGetRequestBuilder {

    OkHttp3HttpGetRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpGetRequestBuilder(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final var get = requestBuilder.get().addHeader("Accept", mediaType); //$NON-NLS-1$

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(get));
    }

}
