package wtf.metio.hcf4j.builder;

import wtf.metio.hcf4j.HttpRequest;

/**
 * Interface for request builders that support sending an empty body.
 */
public interface SupportsEmptyBody {

    /**
     * Builds a request without a body.
     *
     * @return The prepared request.
     */
    HttpRequest emptyBody();

}
