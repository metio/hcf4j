/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.httpclient;

import wtf.metio.hc4j.HttpResponse;

final class HCHttpResponse implements HttpResponse {

    private final String body;
    private final int    statusCode;

    public HCHttpResponse(final String body, final int statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}
