/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.httpcomponents;

import wtf.metio.hc4j.HttpResponse;
import wtf.metio.hc4j.builder.HttpGetRequestBuilder;

final class HCHttpGetRequestBuilder implements HttpGetRequestBuilder {

    @Override
    public HttpResponse executeOnCallingThread() {
        return new HCHttpResponse("TODO", 0); //$NON-NLS-1$
    }

}
