package wtf.metio.hc4j.factory;

import wtf.metio.hc4j.HttpClient;

/**
 * Builder that configures & constructs {@link HttpClient}s.
 */
public interface HttpClientBuilder {

    /**
     * @return A newly created or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

}
