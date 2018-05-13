package wtf.metio.hcf4j.builder;

/**
 * Interface for request builders that support sending or not sending a body.
 *
 * @param <BUILDER>
 *            The type of the builder that continues w/ or w/o a body.
 */
public interface MightHaveContent<BUILDER extends SupportsMediaType> extends SupportsEmptyBody, SupportsBody<BUILDER> {

    // marker interface

}
