package wtf.metio.hcf4j.httpclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpRequest;
import wtf.metio.hcf4j.HttpResponse;
import wtf.metio.hcf4j.errors.ConnectionErrors;
import wtf.metio.hcf4j.exception.HttpRequestException;

abstract class AbstractHCHttpRequest<TYPE extends HttpRequestBase> extends AbstractHCAdapter<TYPE>
        implements HttpRequest {

    protected AbstractHCHttpRequest(final AbstractHCAdapter<TYPE> adapter) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), adapter.requestType);
    }

    protected AbstractHCHttpRequest(
            final HttpClient client,
            final Function<String, String> mediaTypeCreator,
            final IMessageConveyor messages,
            final TYPE requestType) {
        super(client, mediaTypeCreator, messages, requestType);
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        // see https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html
        try {
            final org.apache.http.HttpResponse response = client.execute(requestType);
            final HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);

            return new HCHttpResponse(
                    Checks.requireNonNull(EntityUtils.toString(entity, StandardCharsets.UTF_8)),
                    response.getStatusLine().getStatusCode());
        } catch (final IOException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT)), exception);
        }
    }

}
