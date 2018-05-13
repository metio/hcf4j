/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hcf4j;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link HttpStatusCodes}.
 */
@SuppressWarnings("static-method")
public class HttpStatusCodesTest {

    /**
     * Ensures that a HTTP OK (200) is considered a successful response code.
     */
    @Test
    public void shouldDetectHttpOk() {
        // given
        final var statusCode = 200;

        // when
        final var success = HttpStatusCodes.isSuccess(statusCode);

        // then
        Assert.assertTrue(success);
    }

}
