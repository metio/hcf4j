package wtf.metio.hcf4j.apache.httpclient;

import org.junit.jupiter.api.DisplayName;
import org.mockserver.junit.jupiter.MockServerSettings;
import wtf.metio.hcf4j.apache.httpclient.HCHttpClientFactory;
import wtf.metio.hcf4j.tck.BaseHttpClientTCK;
import wtf.metio.hcf4j.tck.get.GetUrlTCK;
import wtf.metio.hcf4j.tck.post.PostBodyTCK;
import wtf.metio.hcf4j.tck.urls.InvalidUrlTCK;

import static wtf.metio.hcf4j.tck.HttpClientTCK.DEFAULT_PORT;

@DisplayName("Apache HttpClient TCK")
@MockServerSettings(ports = DEFAULT_PORT)
class HttpClientTckTest extends BaseHttpClientTCK
        implements InvalidUrlTCK, GetUrlTCK, PostBodyTCK {

    protected HttpClientTckTest() {
        super(new HCHttpClientFactory());
    }

    @Override
    public boolean supportsGetUrlTestCase(final GetUrlTestCase testCase) {
        if (ignoreUnsupported()) {
            return testCase.body() != null && !testCase.body().isBlank();
        }
        return true;
    }

    @Override
    public boolean supportsPostBodyTestCase(final PostBodyTestCase testCase) {
        if (ignoreUnsupported()) {
            return testCase.body() != null && !testCase.body().isBlank();
        }
        return true;
    }

}
