package de.xn__ho_hia.adapters.http.client.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * Messages for errors in or while working with URLs.
 */
@BaseName("urls")
@LocaleData({ @Locale("en"), @Locale("de") })
public enum UrlErrors {

    /** Used whenever an invalid HTTP/HTTPS URL is encountered. */
    INVALID_URL;

}
