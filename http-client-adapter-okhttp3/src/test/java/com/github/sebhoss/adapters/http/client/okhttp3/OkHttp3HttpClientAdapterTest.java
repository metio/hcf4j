package com.github.sebhoss.adapters.http.client.okhttp3;

import com.github.sebhoss.adapters.http.client.HttpClient;
import com.github.sebhoss.adapters.http.client.HttpResponse;
import com.github.sebhoss.adapters.http.client.builder.HttpGetRequestBuilder;
import com.github.sebhoss.adapters.http.client.builder.HttpPostRequestBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;

/**
 *
 *
 */
public class OkHttp3HttpClientAdapterTest extends AbstractMockHttpTest {

    private HttpClient       client;
    private MockServerClient mockServer;

    /**
     *
     */
    @Before
    public void setUp() {
        client = OkHttp3TestMother.OkHttp3_HTTP_CLIENT;
    }

    /**
     *
     */
    @SuppressWarnings("nls")
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
    @SuppressWarnings("nls")
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
    @SuppressWarnings("nls")
    @Test
    public void shouldGetBodyContent() {
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
        Assert.assertEquals("test", response.getBodyContent());
    }

}
