package wtf.metio.hcf4j.apache.httpclient;

import ch.qos.cal10n.IMessageConveyor;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.eclipse.jdt.annotation.Checks;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.HttpResponse;
import wtf.metio.hcf4j.errors.ConnectionErrors;
import wtf.metio.hcf4j.exception.HttpRequestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

abstract class AbstractHCHttpRequest<TYPE extends HttpRequestBase> extends AbstractHCAdapter<TYPE>
        implements HttpRequest {

    protected AbstractHCHttpRequest(final AbstractHCAdapter<TYPE> adapter) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), adapter.requestType);
    }

    protected AbstractHCHttpRequest(
            final CloseableHttpClient client,
            final Function<String, ContentType> mediaTypeCreator,
            final IMessageConveyor messages,
            final TYPE requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        try (final var response = client.execute(requestType)) {
            final var entity = response.getEntity();
            final var statusCode = response.getStatusLine().getStatusCode();
            final var body = EntityUtils.toString(entity, StandardCharsets.UTF_8);

            return new HCHttpResponse(Checks.requireNonNull(body), statusCode);
        } catch (final IOException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT)), exception);
        }
    }

}
