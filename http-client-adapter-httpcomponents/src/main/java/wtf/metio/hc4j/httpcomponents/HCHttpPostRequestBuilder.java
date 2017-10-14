/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.httpcomponents;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hc4j.builder.HttpPostRequestBuilder;
import wtf.metio.hc4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostRequestBuilder implements HttpPostRequestBuilder {

    private final IMessageConveyor messages;

    HCHttpPostRequestBuilder(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public HttpPostWithContentRequestBuilder content(final String content) {
        return new HCHttpPostWithContentRequestBuilder(messages);
    }

}
