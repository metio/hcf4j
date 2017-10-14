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

import wtf.metio.hc4j.HttpRequest;
import wtf.metio.hc4j.HttpResponse;

final class HCHttpRequest implements HttpRequest {

  @Override
  public HttpResponse executeOnCallingThread() {
    HttpProcessor httpproc = HttpProcessorBuilder.create()
        .add(new RequestContent())
        .add(new RequestTargetHost())
        .add(new RequestConnControl())
        .add(new RequestUserAgent("hc4j/99999-SNAPSHOT"))
        .add(new RequestExpectContinue(true)).build();

    HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

    HttpCoreContext coreContext = HttpCoreContext.create();
    HttpHost host = new HttpHost("localhost", 8080);
    coreContext.setTargetHost(host);

    try (DefaultBHttpClientConnection connection = new DefaultBHttpClientConnection(8 * 1024)) {
      String target = "";

      if (!connection.isOpen()) {
        Socket socket = new Socket(host.getHostName(), host.getPort());
        connection.bind(socket);
      }
      BasicHttpRequest request = new BasicHttpRequest("GET", target);

      httpexecutor.preProcess(request, httpproc, coreContext);
      org.apache.http.HttpResponse response = httpexecutor.execute(request, connection, coreContext);
      httpexecutor.postProcess(response, httpproc, coreContext);

      HttpEntity entity = response.getEntity();
      EntityUtils.consume(entity);

      return new HCHttpResponse(
          EntityUtils.toString(entity, StandardCharsets.UTF_8),
          response.getStatusLine().getStatusCode());
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException exception) {
      // TODO Auto-generated catch block
      exception.printStackTrace();
    } catch (HttpException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // TODO Auto-generated method stub
    return null;
  }

}
