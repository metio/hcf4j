/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.apache.httpclient;

import java.util.function.Function;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.builder.HttpDeleteRequestBuilder;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;
import wtf.metio.hcf4j.builder.HttpHeadRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPatchRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPutRequestBuilder;
import wtf.metio.hcf4j.shared.SafeGuards;

final class HCHttpClient implements HttpClient {

    private final CloseableHttpClient      client;
    private final Function<String, ContentType> mediaTypeCreator;
    private final IMessageConveyor         messages;

    HCHttpClient(
            final CloseableHttpClient client,
            final Function<String, ContentType> mediaTypeCreator,
            final IMessageConveyor messages) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
        this.messages = messages;
    }

    @Override
    public HttpGetRequestBuilder get(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return new HCHttpGetRequestBuilder(client, mediaTypeCreator, messages,
                new HttpGet(url));
    }

    @Override
    public HttpPostRequestBuilder post(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return new HCHttpPostRequestBuilder(client, mediaTypeCreator, messages,
                new HttpPost(url));
    }

    @Override
    public HttpHeadRequestBuilder head(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return null;
    }

    @Override
    public HttpPutRequestBuilder put(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return null;
    }

    @Override
    public HttpDeleteRequestBuilder delete(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return null;
    }

    @Override
    public HttpPatchRequestBuilder patch(final String url) {
        SafeGuards.invalidUrl(url, messages);
        return null;
    }

}
