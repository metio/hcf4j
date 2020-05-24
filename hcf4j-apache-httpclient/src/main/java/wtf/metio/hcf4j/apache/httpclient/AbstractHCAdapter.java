package wtf.metio.hcf4j.apache.httpclient;

import java.util.function.Function;

import org.apache.http.client.methods.HttpRequestBase;

import ch.qos.cal10n.IMessageConveyor;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import wtf.metio.hcf4j.shared.AbstractHttpClientAdapter;

abstract class AbstractHCAdapter<TYPE extends HttpRequestBase>
        extends AbstractHttpClientAdapter<CloseableHttpClient, ContentType> {

    protected final TYPE requestType;

    protected AbstractHCAdapter(final AbstractHCAdapter<TYPE> adapter) {
        this(adapter.client, adapter.mediaTypeCreator, adapter.messages, adapter.requestType);
    }

    protected AbstractHCAdapter(
            final CloseableHttpClient client,
            final Function<String, ContentType> mediaTypeCreator,
            final IMessageConveyor messages,
            final TYPE requestType) {
        super(client, mediaTypeCreator, messages);
        this.requestType = requestType;
    }

}
