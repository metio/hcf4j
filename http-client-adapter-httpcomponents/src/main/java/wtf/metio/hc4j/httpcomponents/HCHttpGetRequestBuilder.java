package wtf.metio.hc4j.httpcomponents;

import wtf.metio.hc4j.HttpResponse;
import wtf.metio.hc4j.builder.HttpGetRequestBuilder;

final class HCHttpGetRequestBuilder implements HttpGetRequestBuilder {

    @Override
    public HttpResponse executeOnCallingThread() {
        return new HCHttpResponse("TODO", 0); //$NON-NLS-1$
    }

}
