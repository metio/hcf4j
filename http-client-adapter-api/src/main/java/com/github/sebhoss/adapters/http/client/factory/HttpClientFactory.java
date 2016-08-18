package com.github.sebhoss.adapters.http.client.factory;

import com.github.sebhoss.adapters.http.client.HttpClient;

/**
 *
 */
public interface HttpClientFactory {

    /**
     * @return A newly or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

}
