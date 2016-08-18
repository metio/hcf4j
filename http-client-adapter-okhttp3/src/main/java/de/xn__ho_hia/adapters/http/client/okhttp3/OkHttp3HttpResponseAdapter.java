package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.io.IOException;

import ch.qos.cal10n.IMessageConveyor;
import de.xn__ho_hia.adapters.http.client.HttpResponse;
import de.xn__ho_hia.adapters.http.client.errors.EncodingErrors;
import de.xn__ho_hia.adapters.http.client.exception.HttpResponseException;
import okhttp3.Response;

final class OkHttp3HttpResponseAdapter implements HttpResponse {

    private final Response         response;
    private final IMessageConveyor messages;

    OkHttp3HttpResponseAdapter(final Response response, final IMessageConveyor messages) {
        this.response = response;
        this.messages = messages;
    }

    @Override
    public String getBodyContent() {
        try {
            return response.body().string();
        } catch (final IOException exception) {
            throw new HttpResponseException(messages.getMessage(EncodingErrors.INVALID_CHARSET), exception);
        }
    }

}
