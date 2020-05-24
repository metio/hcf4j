package wtf.metio.hcf4j.apache.httpclient;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jdt.annotation.Nullable;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientBuilder;

import java.util.Locale;
import java.util.function.Function;

import static org.eclipse.jdt.annotation.Checks.requireNonNull;

final class HCHttpClientBuilder implements HttpClientBuilder {

    private @Nullable CloseableHttpClient client;
    private @Nullable Function<String, ContentType> mediaTypeCreator;
    private @Nullable IMessageConveyor messages;

    @Override
    public HttpClient buildHttpClient() {
        if (client == null) {
            rebuildClient();
        }
        if (mediaTypeCreator == null) {
            rebuildMediaTypeCreator();
        }
        if (messages == null) {
            rebuildMessageConveyor();
        }
        return new HCHttpClient(
                requireNonNull(client),
                requireNonNull(mediaTypeCreator),
                requireNonNull(messages));
    }

    private void rebuildClient() {
        client = HttpClients.createDefault();
    }

    private void rebuildMediaTypeCreator() {
        mediaTypeCreator = ContentType::parse;
    }

    private void rebuildMessageConveyor() {
        messages = new MessageConveyor(Locale.ENGLISH);
    }

    @Override
    public HttpClientBuilder userAgent(final String userAgent) {
        // TODO: save user agent for later usage
        return this;
    }

}
