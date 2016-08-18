package com.github.sebhoss.adapters.http.client;

import com.github.sebhoss.adapters.http.client.builder.HttpGetRequestBuilder;
import com.github.sebhoss.adapters.http.client.builder.HttpPostRequestBuilder;

/**
 *
 *
 */
public interface HttpClient {

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the GET request.
     */
    HttpGetRequestBuilder get(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the POST request.
     */
    HttpPostRequestBuilder post(String url);

}
