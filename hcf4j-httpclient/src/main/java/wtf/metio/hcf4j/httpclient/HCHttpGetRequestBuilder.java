/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import org.apache.http.client.HttpClient;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpResponse;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;

final class HCHttpGetRequestBuilder implements HttpGetRequestBuilder {

    private final HttpClient       httpClient;
    private final IMessageConveyor messages;

    public HCHttpGetRequestBuilder(final HttpClient httpClient, final IMessageConveyor messages) {
        this.httpClient = httpClient;
        this.messages = messages;
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        return new HCHttpRequest(httpClient, messages).executeOnCallingThread();
    }

}
