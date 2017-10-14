package wtf.metio.hc4j.httpcomponents;

import wtf.metio.hc4j.HttpResponse;

final class HCHttpResponse implements HttpResponse {

  private String body;
  private int    statusCode;

  public HCHttpResponse(String body, int statusCode) {
    this.body = body;
    this.statusCode = statusCode;
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

}
