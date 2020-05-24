/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.apache.httpclient;

import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientBuilder;
import wtf.metio.hcf4j.factory.HttpClientFactory;

final class HCHttpClientFactory implements HttpClientFactory {

    @Override
    public HttpClient build() {
        return client().buildHttpClient();
    }

    @Override
    public HttpClientBuilder client() {
        return new HCHttpClientBuilder();
    }

}
