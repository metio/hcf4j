/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j;

import wtf.metio.hcf4j.builder.HttpDeleteRequestBuilder;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;
import wtf.metio.hcf4j.builder.HttpHeadRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPatchRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPutRequestBuilder;

/**
 * Starting interface for HTTP client actions.
 */
public interface HttpClient {

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the GET request.
     */
    HttpGetRequestBuilder get(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the HEAD request.
     */
    HttpHeadRequestBuilder head(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the POST request.
     */
    HttpPostRequestBuilder post(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the PUT request.
     */
    HttpPutRequestBuilder put(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the DELETE request.
     */
    HttpDeleteRequestBuilder delete(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the PATCH request.
     */
    HttpPatchRequestBuilder patch(String url);

}
