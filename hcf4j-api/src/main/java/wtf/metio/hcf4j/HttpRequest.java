/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.jdt.annotation.Checks;

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
     * Performs a <strong>non-blocking</strong> HTTP request in the common fork/join pool.
     *
     * @return A {@link CompletionStage} for async processing.
     * @see #executeInPool(Executor)
     */
    default CompletionStage<HttpResponse> executeInBackground() {
        return executeInPool(Checks.requireNonNull(ForkJoinPool.commonPool()));
    }

    /**
     * Performs a <strong>non-blocking</strong> HTTP request.
     *
     * @param executor
     *            The {@link Executor} to use.
     * @return A {@link CompletionStage} for async processing.
     * @see #executeInBackground()
     */
    default CompletionStage<HttpResponse> executeInPool(final Executor executor) {
        return Checks.requireNonNull(CompletableFuture
                .supplyAsync(this::executeOnCallingThread, executor));
    }

    /**
     * Performs a <strong>non-blocking</strong> HTTP request using Java 9+ {@link Flow}s.
     *
     * @param consumer
     *            The response consumer to use.
     */
    default void executeAsFlow(final Consumer<HttpResponse> consumer) {
        executeAsFlow(consumer, Checks.requireNonNull(ForkJoinPool.commonPool()));
    }

    /**
     * Performs a <strong>non-blocking</strong> HTTP request using Java 9+ {@link Flow}s.
     *
     * @param consumer
     *            The response consumer to use.
     * @param executor
     *            The thread pool to use.
     */
    default void executeAsFlow(final Consumer<HttpResponse> consumer, final ExecutorService executor) {
        executeAsFlow(executor).subscribe(new HttpSubscriber(consumer));
    }

    /**
     * @return A Java 9+ {@link Flow} based {@link Publisher}.
     */
    default Flow.Publisher<HttpResponse> executeAsFlow() {
        return executeAsFlow(Checks.requireNonNull(ForkJoinPool.commonPool()));
    }

    /**
     * @param executor
     *            The thread pool to use.
     * @return A Java 9+ {@link Flow} based {@link Publisher}.
     */
    default Flow.Publisher<HttpResponse> executeAsFlow(final ExecutorService executor) {
        return subscriber -> subscriber.onSubscribe(new HttpSubscription(
                this::executeOnCallingThread,
                executor,
                subscriber));
    }

    /**
     *
     */
    static class HttpSubscription implements Subscription {

        private final ExecutorService                  executor;
        private final Subscriber<? super HttpResponse> subscriber;
        private final Supplier<HttpResponse>           supplier;

        private Future<?>                              future;    // to allow cancellation

        @SuppressWarnings("null")
        public HttpSubscription(
                final Supplier<HttpResponse> supplier,
                final ExecutorService executor,
                final Subscriber<? super HttpResponse> subscriber) {
            this.executor = executor;
            this.subscriber = subscriber;
            this.supplier = supplier;
        }

        @Override
        public void request(final long n) {
            if (n <= 0) {
                final IllegalArgumentException ex = new IllegalArgumentException();
                executor.execute(() -> subscriber.onError(ex));
            } else {
                future = Checks.requireNonNull(executor.submit(() -> {
                    subscriber.onNext(supplier.get());
                    subscriber.onComplete();
                }));
            }
        }

        @Override
        @SuppressWarnings("null")
        public void cancel() {
            if (future != null) {
                future.cancel(false);
            }
        }

    }

    /**
     *
     *
     *
     */
    static class HttpSubscriber implements Subscriber<HttpResponse> {

        private final Consumer<HttpResponse> consumer;
        private Subscription                 subscription;

        @SuppressWarnings("null")
        public HttpSubscriber(final Consumer<HttpResponse> consumer) {
            this.consumer = consumer;
        }

        @Override
        @SuppressWarnings({ "null", "hiding" })
        public void onSubscribe(final Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(final HttpResponse item) {
            consumer.accept(item);
            subscription.cancel();
        }

        @Override
        @SuppressWarnings("null")
        public void onError(final Throwable throwable) {
            subscription.cancel();
        }

        @Override
        public void onComplete() {
            subscription.cancel();
        }

    }

}
