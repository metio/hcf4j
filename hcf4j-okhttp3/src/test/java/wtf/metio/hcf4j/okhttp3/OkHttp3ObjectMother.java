/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.okhttp3;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import org.eclipse.jdt.annotation.Checks;

import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientFactory;

/**
 * Object Mother for OkHttp 3 based tests.
 *
 * @see <a href="https://martinfowler.com/bliki/ObjectMother.html">Object Mother</a>
 */
public class OkHttp3ObjectMother {

    /**
     * Default OkHttp 3 based {@link HttpClientFactory}.
     */
    public static final HttpClientFactory CLIENT_FACTORY = new OkHttp3HttpClientFactory();

    /**
     * Default OkHttp 3 based {@link HttpClient}.
     */
    public static final HttpClient        CLIENT         = CLIENT_FACTORY.build();

    /**
     * Default thread pool used during tests.
     */
    public static final Executor          THREAD_POOL    = Checks.requireNonNull(ForkJoinPool.commonPool());

    private OkHttp3ObjectMother() {
        // utility class
    }

}
