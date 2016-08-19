/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import de.xn__ho_hia.adapters.http.client.HttpResponse;
import de.xn__ho_hia.adapters.http.client.builder.HttpGetRequestBuilder;
import de.xn__ho_hia.adapters.http.client.errors.ConnectionErrors;
import de.xn__ho_hia.adapters.http.client.errors.EncodingErrors;
import de.xn__ho_hia.adapters.http.client.errors.ThirdPartyErrors;
import de.xn__ho_hia.adapters.http.client.exception.HttpRequestException;
import de.xn__ho_hia.adapters.http.client.exception.HttpResponseException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

final class OkHttp3HttpRequestAdapter extends AbstractOkHttp3Adapter implements HttpGetRequestBuilder {

    OkHttp3HttpRequestAdapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpRequestAdapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        try {
            final Request request = requestBuilder.build();
            final Call call = client.newCall(request);
            try (final Response response = call.execute()) {
                return new OkHttp3HttpResponseAdapter(response.body().string());
            }
        } catch (final UnsupportedEncodingException exception) {
            throw new HttpResponseException(messages.getMessage(EncodingErrors.INVALID_CHARSET), exception);
        } catch (final IOException exception) {
            throw new HttpRequestException(messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT), exception);
        } catch (final IllegalStateException exception) {
            throw new HttpRequestException(messages.getMessage(ThirdPartyErrors.ALREADY_EXECUTED), exception);
        }
    }

}
