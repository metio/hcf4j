package de.xn__ho_hia.adapters.http.client.errors;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("third-party")
@LocaleData({ @Locale("en"), @Locale("de") })
public enum ThirdPartyErrors {

    /**
     * Used whenever a third-party dependency complains about re-using objects.
     */
    ALREADY_EXECUTED;

}
