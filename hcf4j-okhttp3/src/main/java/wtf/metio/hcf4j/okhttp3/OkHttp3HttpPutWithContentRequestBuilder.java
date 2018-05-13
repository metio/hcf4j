package wtf.metio.hcf4j.okhttp3;

import org.eclipse.jdt.annotation.Checks;

import okhttp3.RequestBody;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPutWithContentRequestBuilder;

final class OkHttp3HttpPutWithContentRequestBuilder extends AbstractOkHttp3Adapter
        implements HttpPutWithContentRequestBuilder {

    private final String content;

    OkHttp3HttpPutWithContentRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final String content) {
        super(adapter);
        this.content = content;
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final var okHttpMediaType = mediaTypeCreator.apply(mediaType);
        final var body = RequestBody.create(okHttpMediaType, content);
        final var post = requestBuilder.put(body);

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(post));
    }

}
