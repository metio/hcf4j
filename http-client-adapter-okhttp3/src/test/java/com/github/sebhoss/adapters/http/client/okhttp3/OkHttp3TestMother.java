package com.github.sebhoss.adapters.http.client.okhttp3;

import java.util.Locale;

import com.github.sebhoss.adapters.http.client.HttpClient;
import com.github.sebhoss.utils.memoization.map.MapMemoization;

import ch.qos.cal10n.MessageConveyor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 *
 *
 */
public class OkHttp3TestMother {

    /**
     *
     */
    public static final HttpClient OkHttp3_HTTP_CLIENT = new OkHttp3HttpClientAdapter(
            new OkHttpClient(), MapMemoization.memoize(MediaType::parse), new MessageConveyor(Locale.ENGLISH));

    private OkHttp3TestMother() {
        // utility class
    }

}
