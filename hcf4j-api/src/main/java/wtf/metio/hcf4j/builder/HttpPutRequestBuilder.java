/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.builder;

/**
 *
 *
 */
public interface HttpPutRequestBuilder extends SupportsEmptyBody {

    /**
     * @param content
     *            The request body to PUT.
     * @return A request builder to further configure the PUT.
     */
    HttpPutWithContentRequestBuilder content(String content);

}
