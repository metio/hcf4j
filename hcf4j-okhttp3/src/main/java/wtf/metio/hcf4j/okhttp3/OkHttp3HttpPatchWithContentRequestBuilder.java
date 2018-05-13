package wtf.metio.hcf4j.okhttp3;

import org.eclipse.jdt.annotation.Checks;

import okhttp3.RequestBody;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPatchWithContentRequestBuilder;

final class OkHttp3HttpPatchWithContentRequestBuilder extends AbstractOkHttp3Adapter
        implements HttpPatchWithContentRequestBuilder {

    private final String content;

    OkHttp3HttpPatchWithContentRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final String content) {
        super(adapter);
        this.content = content;
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final var okHttpMediaType = mediaTypeCreator.apply(mediaType);
        final var body = RequestBody.create(okHttpMediaType, content);
        final var patch = requestBuilder.patch(body);

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(patch));
    }

}
