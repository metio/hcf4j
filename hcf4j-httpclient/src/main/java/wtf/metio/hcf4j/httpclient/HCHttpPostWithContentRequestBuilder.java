/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import java.util.function.Function;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostWithContentRequestBuilder extends AbstractHCHttpRequest<HttpPost>
        implements HttpPostWithContentRequestBuilder {

    protected HCHttpPostWithContentRequestBuilder(final AbstractHCAdapter<HttpPost> adapter) {
        super(adapter);
    }

    protected HCHttpPostWithContentRequestBuilder(
            final HttpClient client,
            final Function<String, String> mediaTypeCreator,
            final IMessageConveyor messages,
            final HttpPost requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final var hcMediaType = mediaTypeCreator.apply(mediaType);
        requestType.addHeader("Accept", hcMediaType); //$NON-NLS-1$

        return new HCHttpRequest<>(this);
    }

}
