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

    /**
     * Sets the <strong>User-Agent</strong> header for all requests.
     *
     * @param userAgent
     *            The user agent to use for all requests.
     * @return The current builder.
     */
    HttpClientBuilder userAgent(String userAgent);

}
