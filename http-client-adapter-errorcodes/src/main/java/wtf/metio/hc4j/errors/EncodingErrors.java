/*
 * This file is part of http-client-adapter. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of http-client-adapter,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package wtf.metio.hc4j.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * Messages for errors in or while working with URLs.
 */
@BaseName("encodings")
@LocaleData({ @Locale("en"), @Locale("de") })
public enum EncodingErrors {

    /** Used whenever an invalid charset is encountered. */
    INVALID_CHARSET;

}
