package wtf.metio.hc4j.httpcomponents;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hc4j.HttpRequest;
import wtf.metio.hc4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostWithContentRequestBuilder implements HttpPostWithContentRequestBuilder {

    private final IMessageConveyor messages;

    HCHttpPostWithContentRequestBuilder(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public HttpRequest mediaType(final String mediaType) {
        return new HCHttpRequest(messages);
    }

}
