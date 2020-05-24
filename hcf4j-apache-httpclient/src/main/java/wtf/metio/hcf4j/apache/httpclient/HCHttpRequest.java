/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.apache.httpclient;

import ch.qos.cal10n.IMessageConveyor;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.function.Function;

final class HCHttpRequest<TYPE extends HttpRequestBase> extends AbstractHCHttpRequest<TYPE> {

    protected HCHttpRequest(final AbstractHCAdapter<TYPE> adapter) {
        super(adapter);
    }

    protected HCHttpRequest(
            final CloseableHttpClient client,
            final Function<String, ContentType> mediaTypeCreator,
            final IMessageConveyor messages,
            final TYPE requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

}
