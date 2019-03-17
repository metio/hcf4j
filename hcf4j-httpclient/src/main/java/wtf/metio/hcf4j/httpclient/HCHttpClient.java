/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import java.util.function.Function;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.builder.HttpDeleteRequestBuilder;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;
import wtf.metio.hcf4j.builder.HttpHeadRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPatchRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPutRequestBuilder;

final class HCHttpClient implements HttpClient {

    private final CloseableHttpClient      client;
    private final Function<String, String> mediaTypeCreator;
    private final IMessageConveyor         messages;

    HCHttpClient(
            final CloseableHttpClient client,
            final Function<String, String> mediaTypeCreator,
            final IMessageConveyor messages) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
        this.messages = messages;
    }

    @Override
    public HttpGetRequestBuilder get(final String url) {
        return new HCHttpGetRequestBuilder(client, mediaTypeCreator, messages,
                new HttpGet(url));
    }

    @Override
    public HttpPostRequestBuilder post(final String url) {
        return new HCHttpPostRequestBuilder(client, mediaTypeCreator, messages,
                new HttpPost(url));
    }

    @Override
    @SuppressWarnings("null")
    public HttpHeadRequestBuilder head(final String url) {
        return null;
    }

    @Override
    @SuppressWarnings("null")
    public HttpPutRequestBuilder put(final String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("null")
    public HttpDeleteRequestBuilder delete(final String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("null")
    public HttpPatchRequestBuilder patch(final String url) {
        // TODO Auto-generated method stub
        return null;
    }

}
