package com.github.sebhoss.adapters.http.client.shared;

import java.util.function.Function;

/**
 * @param <CLIENT>
 *            The type of the http client.
 * @param <MEDIA_TYPE>
 *            The client specific media-type type.
 */
public abstract class AbstractHttpClientAdapter<CLIENT, MEDIA_TYPE> {

    protected final CLIENT                       client;
    protected final Function<String, MEDIA_TYPE> mediaTypeCreator;

    protected AbstractHttpClientAdapter(final AbstractHttpClientAdapter<CLIENT, MEDIA_TYPE> adapter) {
        this(adapter.client, adapter.mediaTypeCreator);
    }

    protected AbstractHttpClientAdapter(
            final CLIENT client,
            final Function<String, MEDIA_TYPE> mediaTypeCreator) {
        this.client = client;
        this.mediaTypeCreator = mediaTypeCreator;
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

}
