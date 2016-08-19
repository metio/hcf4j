/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client.factory;

import de.xn__ho_hia.adapters.http.client.HttpClient;

/**
 *
 */
public interface HttpClientFactory {

    /**
     * @return A newly or re-used {@link HttpClient}.
     */
    HttpClient buildHttpClient();

}
