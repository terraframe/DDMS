package dss.vector.solutions.etl;

public class EndpointUrl
{
  String protocol;
  
  String hostname;
  
  int port;
  
  String webappContextPath;
  
  String resourcePath;
  
  public EndpointUrl(String protocol, String hostname, int port, String webappContextPath)
  {
    setProtocol(protocol);
    setHostname(hostname);
    setPort(port);
    setWebappContextPath(webappContextPath);
  }
  
  public String toString()
  {
    String url = protocol + "://" + hostname + ":" + String.valueOf(port) + "/";
    
    if (webappContextPath.equals(""))
    {
      url += resourcePath;
    }
    else
    {
      url += webappContextPath + "/" + resourcePath;
    }
    
    return url;
  }
  
  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    if (protocol == null) { protocol = ""; }
    
    if (protocol.endsWith("://")) { protocol = protocol.substring(0, protocol.length()-3); }
    
    this.protocol = protocol;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    if (hostname == null) { hostname = ""; }
    if (hostname.endsWith("/")) { hostname = hostname.substring(0, hostname.length()-1); }
    if (hostname.startsWith("/")) { hostname = hostname.substring(1, hostname.length()); }
    
    this.hostname = hostname;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getWebappContextPath() {
    return webappContextPath;
  }

  public void setWebappContextPath(String webappContextPath) {
    if (webappContextPath == null) { webappContextPath = ""; }
    
    if (webappContextPath.endsWith("/")) { webappContextPath = webappContextPath.substring(0, webappContextPath.length()-1); }
    if (webappContextPath.startsWith("/")) { webappContextPath = webappContextPath.substring(1, webappContextPath.length()); }
    
    this.webappContextPath = webappContextPath;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public void setResourcePath(String resourcePath) {
    if (resourcePath == null) { resourcePath = ""; }
    
    if (resourcePath.endsWith("/")) { resourcePath = resourcePath.substring(0, resourcePath.length()-1); }
    if (resourcePath.startsWith("/")) { resourcePath = resourcePath.substring(1, resourcePath.length()); }
    
    this.resourcePath = resourcePath;
  }
}
