/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.shared;

import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;

/**
 * @param <CLIENT>
 *            The type of the http client.
 * @param <MEDIA_TYPE>
 *            The client specific media-type type.
 */
public abstract class AbstractHttpClientAdapter<CLIENT, MEDIA_TYPE> {

    protected final CLIENT                       client;
    protected final Function<String, MEDIA_TYPE> mediaTypeCreator;
    protected final IMessageConveyor             messages;

    protected AbstractHttpClientAdapter(final AbstractHttpClientAdapter<CLIENT, MEDIA_TYPE> adapter) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.messages);
    }

    protected AbstractHttpClientAdapter(
            final CLIENT client,
            final Function<String, MEDIA_TYPE> mediaTypeCreator,
            final IMessageConveyor messages) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
        this.messages = messages;
    }

    /**
     * @return The wrapped client itself.
     */
    public final CLIENT getClient() {
        return client;
    }

    /**
     * @return The client specific media-type creator.
     */
    public final Function<String, MEDIA_TYPE> getMediaTypeCreator() {
        return mediaTypeCreator;
    }

    /**
     * @return The message conveyor to use.
     */
    public final IMessageConveyor getMessages() {
        return messages;
    }

}
