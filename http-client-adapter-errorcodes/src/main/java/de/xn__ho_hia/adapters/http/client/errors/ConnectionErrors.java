package de.xn__ho_hia.adapters.http.client.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

/**
 * Messages for connection errors.
 */
@BaseName("connections")
@LocaleData({ @Locale("en"), @Locale("de") })
public enum ConnectionErrors {

    /** Used whenever a connection cannot be established. */
    UNABLE_TO_CONNECT;

}
