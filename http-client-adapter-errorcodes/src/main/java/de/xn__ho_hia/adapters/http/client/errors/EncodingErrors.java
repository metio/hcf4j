package de.xn__ho_hia.adapters.http.client.errors;

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
