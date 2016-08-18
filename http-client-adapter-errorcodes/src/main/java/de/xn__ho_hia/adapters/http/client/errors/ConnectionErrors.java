package de.xn__ho_hia.adapters.http.client.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("urls")
@LocaleData(defaultCharset = "UTF8", value = { @Locale("en"), @Locale("de") })
public enum ConnectionErrors {

    /** Used whenever a connection cannot be established. */
    UNABLE_TO_CONNECT;

}
