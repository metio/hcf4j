/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.apache.httpclient;

import java.util.function.Function;

import org.apache.http.client.methods.HttpPost;

import ch.qos.cal10n.IMessageConveyor;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostRequestBuilder extends AbstractHCHttpRequest<HttpPost> implements HttpPostRequestBuilder {

    protected HCHttpPostRequestBuilder(final AbstractHCAdapter<HttpPost> adapter) {
        super(adapter);
    }

    protected HCHttpPostRequestBuilder(
            final CloseableHttpClient client,
            final Function<String, ContentType> mediaTypeCreator,
            final IMessageConveyor messages,
            final HttpPost requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

    @Override
    public HttpPostWithContentRequestBuilder content(final String content) {
        return new HCHttpPostWithContentRequestBuilder(this, content);
    }

    @Override
    public HttpRequest emptyBody() {
        return new HCHttpRequest<>(this);
    }

}
