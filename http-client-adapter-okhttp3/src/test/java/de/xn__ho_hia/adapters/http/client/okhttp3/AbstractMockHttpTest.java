/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client.okhttp3;

import static org.eclipse.jdt.annotation.Checks.requireNonNull;

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
        return requireNonNull(HttpRequest.request());
    }

    @SuppressWarnings("static-method")
    protected HttpResponse response() {
        return requireNonNull(HttpResponse.response());
    }

    protected String path(final String path) {
        return "http://localhost:" + mockServerRule.getPort() + path; //$NON-NLS-1$
    }

}
