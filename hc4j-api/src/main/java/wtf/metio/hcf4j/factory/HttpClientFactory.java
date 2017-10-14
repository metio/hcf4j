/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.factory;

import wtf.metio.hcf4j.HttpClient;

/**
 * Factory that builds {@link HttpClient}s.
 */
public interface HttpClientFactory {

    /**
     * @return A newly created or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

    /**
     * @return A builder to fine-tune a new {@link HttpClient}.
     */
    HttpClientBuilder client();

}
