package wtf.metio.hcf4j.okhttp3;

import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import wtf.metio.hcf4j.builder.HttpDeleteRequestBuilder;

final class OkHttp3HttpDeleteRequestBuilder extends AbstractOkHttp3HttpRequest implements HttpDeleteRequestBuilder {

    OkHttp3HttpDeleteRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpDeleteRequestBuilder(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

}
