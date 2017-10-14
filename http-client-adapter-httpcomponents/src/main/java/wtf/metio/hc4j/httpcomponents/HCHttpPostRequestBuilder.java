package wtf.metio.hc4j.httpcomponents;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hc4j.builder.HttpPostRequestBuilder;
import wtf.metio.hc4j.builder.HttpPostWithContentRequestBuilder;

final class HCHttpPostRequestBuilder implements HttpPostRequestBuilder {

    private final IMessageConveyor messages;

    HCHttpPostRequestBuilder(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public HttpPostWithContentRequestBuilder content(final String content) {
        return new HCHttpPostWithContentRequestBuilder(messages);
    }

}
