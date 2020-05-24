/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.apache.httpclient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostWithContentRequestBuilder extends AbstractHCHttpRequest<HttpPost>
        implements HttpPostWithContentRequestBuilder {

    private final String content;

    protected HCHttpPostWithContentRequestBuilder(final AbstractHCAdapter<HttpPost> adapter, final String content) {
        super(adapter);
        this.content = content;
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        final var hcMediaType = mediaTypeCreator.apply(mediaType);
        requestType.setEntity(new StringEntity(content, hcMediaType));

        return new HCHttpRequest<>(this);
    }

}
