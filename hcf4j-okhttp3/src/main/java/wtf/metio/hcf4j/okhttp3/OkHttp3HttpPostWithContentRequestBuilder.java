/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.okhttp3;

import org.eclipse.jdt.annotation.Checks;

import okhttp3.RequestBody;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPostWithContentRequestBuilder;

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
        final var okHttpMediaType = mediaTypeCreator.apply(mediaType);
        final var body = RequestBody.create(okHttpMediaType, content);
        final var post = requestBuilder.post(body);

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(post));
    }

}
