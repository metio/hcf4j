package wtf.metio.hcf4j.builder;

import wtf.metio.hcf4j.HttpRequest;

/**
 * Interface for request builders that support sending with a specific media type.
 */
public interface SupportsMediaType {

    /**
     * @param mediaType
     *            The media type to use.
     * @return The configured {@link HttpRequest}.
     */
    HttpRequest mediaType(String mediaType);

}
