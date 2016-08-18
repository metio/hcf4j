package de.xn__ho_hia.adapters.http.client.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("urls")
@LocaleData(defaultCharset = "UTF8", value = { @Locale("en"), @Locale("de") })
public enum UrlErrors {

    /** Used whenever an invalid HTTP/HTTPS URL is encountered. */
    INVALID_URL;

}
