package wtf.metio.hc4j.httpcomponents;

import wtf.metio.hc4j.HttpResponse;

final class HCHttpResponse implements HttpResponse {

  private String bodyContent;
  private int    statusCode;

  public HCHttpResponse(String bodyContent, int statusCode) {
    this.bodyContent = bodyContent;
    this.statusCode = statusCode;
  }

  @Override
  public String getBodyContent() {
    return bodyContent;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

}
