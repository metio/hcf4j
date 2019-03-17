package wtf.metio.hcf4j.httpclient;

import static org.eclipse.jdt.annotation.Checks.requireNonNull;

import java.util.Locale;
import java.util.function.Function;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jdt.annotation.Nullable;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientBuilder;

final class HCHttpClientBuilder implements HttpClientBuilder {

    private @Nullable CloseableHttpClient      client;
    private @Nullable Function<String, String> mediaTypeCreator;
    private @Nullable IMessageConveyor         messages;

    HCHttpClientBuilder(final IMessageConveyor messages) {
        this.messages = messages;
    }

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
        mediaTypeCreator = Function.identity();
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
