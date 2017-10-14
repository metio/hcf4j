package wtf.metio.hc4j.httpcomponents;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;
import org.eclipse.jdt.annotation.Checks;

import ch.qos.cal10n.IMessageConveyor;
import wtf.metio.hc4j.HttpRequest;
import wtf.metio.hc4j.HttpResponse;
import wtf.metio.hc4j.errors.ConnectionErrors;
import wtf.metio.hc4j.exception.HttpRequestException;

final class HCHttpRequest implements HttpRequest {

    private final IMessageConveyor messages;

    HCHttpRequest(final IMessageConveyor messages) {
        this.messages = messages;
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        final HttpProcessor httpproc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("hc4j/99999-SNAPSHOT")) //$NON-NLS-1$
                .add(new RequestExpectContinue(true)).build();

        final HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

        final HttpCoreContext coreContext = HttpCoreContext.create();
        final HttpHost host = new HttpHost("localhost", 8080); //$NON-NLS-1$
        coreContext.setTargetHost(host);

        try (final DefaultBHttpClientConnection connection = new DefaultBHttpClientConnection(8 * 1024);
                final Socket socket = new Socket(host.getHostName(), host.getPort());) {
            connection.bind(socket);

            final BasicHttpRequest request = new BasicHttpRequest("GET", ""); //$NON-NLS-1$ //$NON-NLS-2$

            httpexecutor.preProcess(request, httpproc, coreContext);
            final org.apache.http.HttpResponse response = httpexecutor.execute(request, connection, coreContext);
            httpexecutor.postProcess(response, httpproc, coreContext);

            final HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);

            return new HCHttpResponse(
                    Checks.requireNonNull(EntityUtils.toString(entity, StandardCharsets.UTF_8)),
                    response.getStatusLine().getStatusCode());
        } catch (final UnknownHostException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.LOOKUP_FAILED)), exception);
        } catch (final IOException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT)), exception);
        } catch (final HttpException exception) {
            throw new HttpRequestException(Checks.requireNonEmpty(
                    messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT)), exception);
        }
    }

}
