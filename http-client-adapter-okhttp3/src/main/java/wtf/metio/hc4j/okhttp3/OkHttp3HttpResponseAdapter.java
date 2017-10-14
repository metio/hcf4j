/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.okhttp3;

import org.eclipse.jdt.annotation.Checks;

import wtf.metio.hc4j.HttpResponse;

final class OkHttp3HttpResponseAdapter implements HttpResponse {

    private final String bodyContent;
    private final int    statusCode;

    OkHttp3HttpResponseAdapter(final String bodyContent, final int statusCode) {
        this.bodyContent = bodyContent;
        this.statusCode = statusCode;
    }

    @Override
    public String getBodyContent() {
        return Checks.requireNonEmpty(bodyContent);
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}
