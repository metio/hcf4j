package wtf.metio.hcf4j.builder;

/**
 * Interface for request builders that support sending a body.
 *
 * @param <BUILDER>
 *            The type of the builder that continues w/ a body.
 */
public interface SupportsBody<BUILDER extends SupportsMediaType> {

    /**
     * Builds a request with a body.
     * 
     * @param body
     *            The body to send.
     * @return The prepared request.
     */
    BUILDER content(String body);

}
