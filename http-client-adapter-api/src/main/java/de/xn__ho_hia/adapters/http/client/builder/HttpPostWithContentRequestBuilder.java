package de.xn__ho_hia.adapters.http.client.builder;

import de.xn__ho_hia.adapters.http.client.HttpRequest;

/**
 *
 *
 */
public interface HttpPostWithContentRequestBuilder {

    /**
     * @param mediaType
     *            The media type to use.
     * @return The configured {@link HttpRequest}.
     */
    HttpRequest mediaType(String mediaType);

}
