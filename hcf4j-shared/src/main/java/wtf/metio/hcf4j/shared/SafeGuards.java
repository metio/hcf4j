package wtf.metio.hcf4j.shared;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.errors.ConnectionErrors;
import wtf.metio.hcf4j.errors.UrlErrors;
import wtf.metio.hcf4j.exception.HttpRequestException;

import java.io.IOException;
import java.net.*;

public final class SafeGuards {

    public static void invalidUrl(final String input, final IMessageConveyor messages) {
        try {
            if (input == null) {
                throw new HttpRequestException(messages.getMessage(UrlErrors.INVALID_URL, input));
            }
            final var uri = new URI(input).parseServerAuthority();
            if (uri.getHost() == null || uri.getHost().isBlank()) {
                throw new HttpRequestException(messages.getMessage(UrlErrors.INVALID_URL, input));
            }
            final var url = new URL(input);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (final MalformedURLException | URISyntaxException exception) {
            throw new HttpRequestException(messages.getMessage(UrlErrors.INVALID_URL, input));
        } catch (final IOException exception) {
            throw new HttpRequestException(messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT));
        }
    }

    private SafeGuards() {
        // utility class
    }

}
