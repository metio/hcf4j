/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.okhttp3;

import static org.eclipse.jdt.annotation.Checks.requireNonNull;

import java.util.Locale;
import java.util.function.Function;

import org.eclipse.jdt.annotation.Nullable;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import de.xn__ho_hia.memoization.map.MapMemoize;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import wtf.metio.hc4j.HttpClient;
import wtf.metio.hc4j.factory.HttpClientFactory;

final class OkHttp3HttpClientFactory implements HttpClientFactory {

    private @Nullable OkHttpClient                client;
    private @Nullable Function<String, MediaType> mediaTypeCreator;
    private @Nullable IMessageConveyor            messages;

    @Override
    public HttpClient buildHttpClient() {
        if (client == null) {
            rebuildClient();
        }
        if (mediaTypeCreator == null) {
            rebuildMediaTypeCreator();
        }
        if (messages == null) {
            rebuildMessageConveyor();
        }
        return new OkHttp3HttpClientAdapter(
                requireNonNull(client),
                requireNonNull(mediaTypeCreator),
                requireNonNull(messages));
    }

    private void rebuildClient() {
        client = new OkHttpClient();
    }

    @SuppressWarnings("null")
    private void rebuildMediaTypeCreator() {
        mediaTypeCreator = MapMemoize.function(MediaType::parse);
    }

    private void rebuildMessageConveyor() {
        messages = new MessageConveyor(Locale.ENGLISH);
    }

}
