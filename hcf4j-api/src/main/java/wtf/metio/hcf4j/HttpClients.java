package wtf.metio.hcf4j;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import wtf.metio.hcf4j.factory.HttpClientFactory;

/**
 * Factory class that uses JDK {@link ServiceLoader}s.
 */
public final class HttpClients {

    /**
     * @return A new HTTP client build by the first loaded client factory.
     */
    public static HttpClient client() {
        return factory().build();
    }

    /**
     * @return The first loaded client factory.
     */
    public static HttpClientFactory factory() {
        return ServiceLoader.load(HttpClientFactory.class)
                .stream()
                .map(Provider::get)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private HttpClients() {
        // factory class
    }

}
