package wtf.metio.hcf4j.httpclient;

import java.util.function.Function;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.shared.AbstractHttpClientAdapter;

abstract class AbstractHCAdapter<TYPE extends HttpRequestBase>
        extends AbstractHttpClientAdapter<HttpClient, String> {

    protected final TYPE requestType;

    protected AbstractHCAdapter(final AbstractHCAdapter<TYPE> adapter) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.messages, adapter.requestType);
    }

    protected AbstractHCAdapter(
            final HttpClient client,
            final Function<String, String> mediaTypeCreator,
            final IMessageConveyor messages,
            final TYPE requestType) {
        super(client, mediaTypeCreator, messages);
        this.requestType = requestType;
    }

}
