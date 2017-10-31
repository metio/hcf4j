/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.okhttp3;

import java.util.function.Function;

import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostWithContentRequestBuilder;

final class OkHttp3HttpPostRequestBuilder extends AbstractOkHttp3Adapter implements HttpPostRequestBuilder {

    OkHttp3HttpPostRequestBuilder(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpPostRequestBuilder(
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

    @Override
    public HttpRequest emptyBody() {
        final RequestBody body = RequestBody.create(null, new byte[0]);
        final Builder post = requestBuilder.post(body);

        return new OkHttp3HttpRequest(this, Checks.requireNonNull(post));
    }

}
