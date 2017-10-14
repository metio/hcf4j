/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client.okhttp3;

import org.eclipse.jdt.annotation.Checks;

import de.xn__ho_hia.adapters.http.client.HttpRequest;
import de.xn__ho_hia.adapters.http.client.builder.HttpPostWithContentRequestBuilder;
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

        return new OkHttp3HttpRequestAdapter(this, Checks.requireNonNull(post));
    }

}
