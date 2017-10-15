package wtf.metio.hcf4j.httpclient;

import org.apache.http.impl.client.HttpClients;
import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientBuilder;

final class HCHttpClientBuilder implements HttpClientBuilder {

    private final IMessageConveyor messages;

    HCHttpClientBuilder(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public HttpClient buildHttpClient() {
        return new HCHttpClient(Checks.requireNonNull(HttpClients.createDefault()), messages);
    }

    @Override
    public HttpClientBuilder userAgent(final String userAgent) {
        // TODO: save user agent for later usage
        return this;
    }

}
