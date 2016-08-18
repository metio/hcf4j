package com.github.sebhoss.adapters.http.client.builder;

/**
 *
 *
 */
public interface HttpPostRequestBuilder {

    /**
     * @param content
     *            The request body to POST.
     * @return A request builder to further configure the POST.
     */
    HttpPostWithContentRequestBuilder content(String content);

}
