package com.github.sebhoss.adapters.http.client.okhttp3;

import org.junit.Rule;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

/**
 *
 *
 */
public abstract class AbstractMockHttpTest {

    /**
     *
     */
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    @SuppressWarnings("static-method")
    protected HttpRequest request() {
        return HttpRequest.request();
    }

    @SuppressWarnings("static-method")
    protected HttpResponse response() {
        return HttpResponse.response();
    }

    protected String path(final String path) {
        return "http://localhost:" + mockServerRule.getPort() + path; //$NON-NLS-1$
    }

}
