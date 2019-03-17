/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import java.util.function.Function;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;

final class HCHttpGetRequestBuilder extends AbstractHCHttpRequest<HttpGet> implements HttpGetRequestBuilder {

    protected HCHttpGetRequestBuilder(final AbstractHCAdapter<HttpGet> adapter) {
        super(adapter);
    }

    protected HCHttpGetRequestBuilder(
            final HttpClient client,
            final Function<String, String> mediaTypeCreator,
            final IMessageConveyor messages,
            final HttpGet requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        requestType.addHeader("Accept", mediaType); //$NON-NLS-1$
        return new HCHttpRequest<>(this);
    }

}
