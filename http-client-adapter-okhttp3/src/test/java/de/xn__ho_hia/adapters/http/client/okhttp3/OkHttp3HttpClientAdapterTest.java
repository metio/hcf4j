package de.xn__ho_hia.adapters.http.client.okhttp3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;

import de.xn__ho_hia.adapters.http.client.HttpClient;
import de.xn__ho_hia.adapters.http.client.HttpResponse;
import de.xn__ho_hia.adapters.http.client.builder.HttpGetRequestBuilder;
import de.xn__ho_hia.adapters.http.client.builder.HttpPostRequestBuilder;

/**
 *
 *
 */
@SuppressWarnings("nls")
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
