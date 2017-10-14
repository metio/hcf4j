/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.httpclient;

import org.apache.http.impl.client.CloseableHttpClient;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hc4j.HttpClient;
import wtf.metio.hc4j.builder.HttpGetRequestBuilder;
import wtf.metio.hc4j.builder.HttpPostRequestBuilder;

final class HCHttpClient implements HttpClient {

    private final CloseableHttpClient httpClient;
    private final IMessageConveyor    messages;

    HCHttpClient(
            final CloseableHttpClient httpClient,
            final IMessageConveyor messages) {
        this.httpClient = httpClient;
        this.messages = messages;
    }

    @Override
    public HttpGetRequestBuilder get(final String url) {
        return new HCHttpGetRequestBuilder(httpClient, messages);
    }

    @Override
    public HttpPostRequestBuilder post(final String url) {
        return new HCHttpPostRequestBuilder(httpClient, messages);
    }

    @Override
    public void head(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void put(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(final String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public void patch(final String url) {
        // TODO Auto-generated method stub

    }

}
