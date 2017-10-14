package wtf.metio.hcf4j.factory;

import wtf.metio.hcf4j.HttpClient;

/**
 * Builder that configures & constructs {@link HttpClient}s.
 */
public interface HttpClientBuilder {

    /**
     * @return A newly created or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

}
