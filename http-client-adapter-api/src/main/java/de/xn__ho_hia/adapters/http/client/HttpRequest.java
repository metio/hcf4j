/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 * Represents a fully configured HTTP request, ready to be executed.
 * <p>
 * There are two ways to execute a request: {@link #executeOnCallingThread() blocking} and
 * {@link #executeInPool(Executor) non-blocking}. Both result in a {@link HttpResponse}.
 *
 * @see HttpResponse
 */
public interface HttpRequest {

    /**
     * Performs a <strong>blocking</strong> HTTP request.
     *
     * @return The response once it arrives.
     */
    HttpResponse executeOnCallingThread();

    /**
     * Performs a <strong>non-blocking</strong> HTTP request.
     *
     * @param executor
     *            The {@link Executor} to use.
     * @return A {@link CompletionStage} for async processing.
     */
    default CompletionStage<HttpResponse> executeInPool(final Executor executor) {
        return CompletableFuture.supplyAsync(this::executeOnCallingThread, executor);
    }

}
