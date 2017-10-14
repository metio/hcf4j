/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.okhttp3;

import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import wtf.metio.hc4j.shared.AbstractHttpClientAdapter;

abstract class AbstractOkHttp3Adapter extends AbstractHttpClientAdapter<OkHttpClient, MediaType> {

    protected final Builder requestBuilder;

    protected AbstractOkHttp3Adapter(final AbstractOkHttp3Adapter adapter) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.messages, adapter.requestBuilder);
    }

    protected AbstractOkHttp3Adapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.messages, requestBuilder);
    }

    protected AbstractOkHttp3Adapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages);
        this.requestBuilder = requestBuilder;
    }

}
