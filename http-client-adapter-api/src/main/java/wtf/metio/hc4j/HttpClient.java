/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j;

import wtf.metio.hc4j.builder.HttpGetRequestBuilder;
import wtf.metio.hc4j.builder.HttpPostRequestBuilder;

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
     */
    void head(String url);

    /**
     * @param url
     *            The target URL.
     * @return A request builder to further configure the POST request.
     */
    HttpPostRequestBuilder post(String url);

    /**
     * @param url
     *            The target URL.
     */
    void put(String url);

    /**
     * @param url
     *            The target URL.
     */
    void delete(String url);

    /**
     * @param url
     *            The target URL.
     */
    void connect(String url);

    /**
     * @param url
     *            The target URL.
     */
    void trace(String url);

    /**
     * @param url
     *            The target URL.
     */
    void patch(String url);

}
