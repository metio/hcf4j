/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.util.Locale;

import ch.qos.cal10n.MessageConveyor;
import de.xn__ho_hia.adapters.http.client.HttpClient;
import de.xn__ho_hia.memoization.map.MapMemoize;
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
    @SuppressWarnings("null")
    public static final HttpClient OkHttp3_HTTP_CLIENT = new OkHttp3HttpClientAdapter(
            new OkHttpClient(), MapMemoize.function(MediaType::parse), new MessageConveyor(Locale.ENGLISH));

    private OkHttp3TestMother() {
        // utility class
    }

}
