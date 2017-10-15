/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j.okhttp3;

import org.junit.Assert;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;

import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.HttpResponse;
import wtf.metio.hcf4j.builder.HttpGetRequestBuilder;
import wtf.metio.hcf4j.builder.HttpPostRequestBuilder;

/**
 *
 *
 */
@SuppressWarnings("nls")
public class OkHttp3HttpClientTest extends AbstractMockHttpTest {

    private final HttpClient client = OkHttp3ObjectMother.CLIENT;

    @SuppressWarnings("null")
    private MockServerClient mockServer;

    /**
     *
     */
    @Test
    public void shouldCreateNonNullGetRequestBuilderForValidHttpUrl() {
        // given
        final String url = "http://localhost";

        // when
        final HttpGetRequestBuilder builder = client.get(url);

        // then
        Assert.assertNotNull(builder);
    }

    /**
     *
     */
    @Test
    public void shouldCreateNonNullPostRequestBuilderForValidHttpUrl() {
        // given
        final String url = "http://localhost";

        // when
        final HttpPostRequestBuilder builder = client.post(url);

        // then
        Assert.assertNotNull(builder);
    }

    /**
     *
     */
    @Test
    public void shouldGetBodyContentAndStatusCode() {
        // given
        mockServer.when(request()
                .withPath("/test")
                .withMethod("GET"))
                .respond(response()
                        .withStatusCode(Integer.valueOf(200))
                        .withBody("test"));

        // when
        final HttpResponse response = client.get(path("/test")).executeOnCallingThread();

        // then
        Assert.assertEquals("test", response.body());
        Assert.assertEquals(200, response.statusCode());
    }

    /**
     *
     */
    @Test
    public void shouldGetBodyContentAndStatusCodeNonBlocking() {
        // given
        mockServer.when(request()
                .withPath("/test")
                .withMethod("GET"))
                .respond(response()
                        .withStatusCode(Integer.valueOf(200))
                        .withBody("test"));

        // when
        client.get(path("/test")).executeInPool(OkHttp3ObjectMother.THREAD_POOL)
                .thenAccept(response -> {
                    // then
                    Assert.assertEquals("test", response.body());
                    Assert.assertEquals(200, response.statusCode());
                });
    }

}
