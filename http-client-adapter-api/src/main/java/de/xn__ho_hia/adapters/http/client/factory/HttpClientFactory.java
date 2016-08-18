package de.xn__ho_hia.adapters.http.client.factory;

import de.xn__ho_hia.adapters.http.client.HttpClient;

/**
 *
 */
public interface HttpClientFactory {

    /**
     * @return A newly or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

}
