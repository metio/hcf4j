/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.HttpResponse;
import wtf.metio.hcf4j.errors.ConnectionErrors;
import wtf.metio.hcf4j.exception.HttpRequestException;

final class HCHttpRequest implements HttpRequest {

    private final HttpClient       httpClient;
    private final IMessageConveyor messages;

    HCHttpRequest(final HttpClient httpClient, final IMessageConveyor messages) {
        this.httpClient = httpClient;
        this.messages = messages;
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        try {
            final HttpGet httpGet = new HttpGet("http://targethost/homepage"); //$NON-NLS-1$
            final org.apache.http.HttpResponse response = httpClient.execute(httpGet);
            final HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);

            return new HCHttpResponse(
                    Checks.requireNonNull(EntityUtils.toString(entity, StandardCharsets.UTF_8)),
                    response.getStatusLine().getStatusCode());
        } catch (final IOException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT)), exception);
        }
    }

}
