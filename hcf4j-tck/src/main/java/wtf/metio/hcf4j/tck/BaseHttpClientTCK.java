package wtf.metio.hcf4j.tck;

import wtf.metio.hcf4j.HttpClient;
import wtf.metio.hcf4j.factory.HttpClientFactory;

public abstract class BaseHttpClientTCK implements HttpClientTCK {

    private final HttpClientFactory factory;

    protected BaseHttpClientTCK(final HttpClientFactory factory) {
        this.factory = factory;
    }

    @Override
    public final HttpClient createHttpClient() {
        return factory.build();
    }

    protected final boolean ignoreUnsupported() {
        return !Boolean.getBoolean("HCF4J_TESTS_FAIL_UNSUPPORTED");
    }

}
