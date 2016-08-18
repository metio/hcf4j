package com.github.sebhoss.adapters.http.client.builder;

import com.github.sebhoss.adapters.http.client.HttpRequest;

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
