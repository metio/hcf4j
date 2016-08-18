package com.github.sebhoss.adapters.http.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

/**
 *
 *
 */
public interface HttpRequest {

    /**
     * @return The response once it arrives.
     */
    HttpResponse executeOnCallingThread();

    /**
     * @param executor
     *            The {@link Executor} to use.
     * @return A {@link CompletionStage} for async processing.
     */
    default CompletionStage<HttpResponse> executeInPool(final Executor executor) {
        return CompletableFuture.supplyAsync(this::executeOnCallingThread, executor);
    }

}
