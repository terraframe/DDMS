package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dss.vector.solutions.manager.properties.ManagerProperties;
import dss.vector.solutions.manager.properties.PropertyReader;
import dss.vector.solutions.manager.properties.PropertyWriter;

public class ServerSettingContextBean
{
  public static final String    ODK_HOSTNAME_PROPERTY = "security.server.hostname";

  public static final String    HOSTNAME_PROPERTY     = "security.server.hostname";

  public static final String    HTTPS_PROPERTY        = "https.enable";

  private static final String   MOBILE                = "Mobile";

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private String                hostname;

  private Boolean               https;

  private String[]              applications;

  public ServerSettingContextBean(String[] applications)
  {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.hostname = ServerSettingContextBean.getHostnameProperty();
    this.https = ServerSettingContextBean.getHttpsEnableProperty();
    this.applications = applications;
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public String getHostname()
  {
    return hostname;
  }

  public void setHostname(String hostname)
  {
    this.propertyChangeSupport.firePropertyChange("hostname", this.hostname, this.hostname = hostname);
  }

  public Boolean getHttps()
  {
    return https;
  }

  public void setHttps(Boolean https)
  {
    this.propertyChangeSupport.firePropertyChange("https", this.https, this.https = https);
  }

  public void save()
  {
    try
    {
      URL resource = this.getClass().getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyWriter writer = new PropertyWriter(file.getAbsolutePath());
      writer.write(HOSTNAME_PROPERTY, this.hostname);
      writer.write(HTTPS_PROPERTY, this.https.toString());

      /*
       * Update tomcat server.conf file
       */
      this.writeServerConf();

      /*
       * Write all of the ODK webapp settings
       */
      for (String application : applications)
      {
        String path = ManagerProperties.getWebappPath() + application + MOBILE + File.separator + "WEB-INF/classes/security.properties";

        File mobileFile = new File(path);

        if (mobileFile.exists())
        {
          new PropertyWriter(mobileFile.getAbsolutePath()).write(ODK_HOSTNAME_PROPERTY, this.hostname);
        }

        this.writeWebXml();
      }
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void writeWebXml()
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setValidating(false);
      dbf.setIgnoringComments(false);
      dbf.setIgnoringElementContentWhitespace(false);

      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(ManagerProperties.getWebXml()));

      XPathFactory xPathfactory = XPathFactory.newInstance();
      XPath xpath = xPathfactory.newXPath();
      XPathExpression expr = xpath.compile("//security-constraint");

      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

      if (!this.https && nodeList.getLength() > 0)
      {

        for (int i = 0; i < nodeList.getLength(); i++)
        {
          Node node = nodeList.item(i);
          node.getParentNode().removeChild(node);
        }

        // Use a Transformer for output
        writeXmlFile(doc, ManagerProperties.getWebXml());
      }
      else if (this.https && nodeList.getLength() == 0)
      {
        expr = xpath.compile("//web-app");

        nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++)
        {
          Element sc = doc.createElement("security-constraint");
          nodeList.item(i).appendChild(sc);

          Element wrc = doc.createElement("web-resource-collection");
          sc.appendChild(wrc);

          Element wrn = doc.createElement("web-resource-name");
          wrn.appendChild(doc.createTextNode("https-only"));
          wrc.appendChild(wrn);

          Element up = doc.createElement("url-pattern");
          up.appendChild(doc.createTextNode("/*"));
          wrc.appendChild(up);

          Element hm1 = doc.createElement("http-method");
          hm1.appendChild(doc.createTextNode("GET"));
          wrc.appendChild(hm1);

          Element hm2 = doc.createElement("http-method");
          hm2.appendChild(doc.createTextNode("POST"));
          wrc.appendChild(hm2);

          Element udc = doc.createElement("user-data-constraint");
          sc.appendChild(udc);

          Element tg = doc.createElement("transport-guarantee");
          tg.appendChild(doc.createTextNode("CONFIDENTIAL"));
          udc.appendChild(tg);
        }

        writeXmlFile(doc, ManagerProperties.getWebXml());
      }
    }
    catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | TransformerException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void writeServerConf()
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setValidating(false);
      dbf.setIgnoringComments(false);
      dbf.setIgnoringElementContentWhitespace(false);

      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(ManagerProperties.getServerXml()));

      XPathFactory xPathfactory = XPathFactory.newInstance();
      XPath xpath = xPathfactory.newXPath();
      XPathExpression expr = xpath.compile("//Connector[@SSLEnabled=\"true\"]");

      NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

      if (!this.https && nodeList.getLength() > 0)
      {
        for (int i = 0; i < nodeList.getLength(); i++)
        {
          Node node = nodeList.item(i);
          node.getParentNode().removeChild(node);
        }

        // Use a Transformer for output
        writeXmlFile(doc, ManagerProperties.getServerXml());
      }
      else if (this.https && nodeList.getLength() == 0)
      {
        expr = xpath.compile("//Service[@name=\"Catalina\"]");

        nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++)
        {
          Element element = doc.createElement("Connector");
          element.setAttribute("port", "8443");
          element.setAttribute("redirectPort", "8443");
          element.setAttribute("protocol", "org.apache.coyote.http11.Http11NioProtocol");
          element.setAttribute("SSLEnabled", "true");
          element.setAttribute("scheme", "https");
          element.setAttribute("secure", "true");
          element.setAttribute("clientAuth", "false");
          element.setAttribute("sslProtocol", "TLS");
          element.setAttribute("keystoreFile", ManagerProperties.getKeystoreLocation());
          element.setAttribute("keyAlias", ManagerProperties.getKeystoreAlias());
          element.setAttribute("keystorePass", ManagerProperties.getKeystorePassword());

          nodeList.item(i).appendChild(element);
        }

        writeXmlFile(doc, ManagerProperties.getServerXml());
      }
    }
    catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | TransformerException e)
    {
      throw new RuntimeException(e);
    }

  }

  private void writeXmlFile(Document doc, String path) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException
  {
    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File(path));
    transformer.transform(source, result);
  }

  public static String getHostnameProperty()
  {
    try
    {
      URL resource = ServerSettingContextBean.class.getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyReader reader = new PropertyReader(file.getAbsolutePath());

      String hostname = reader.getValue(HOSTNAME_PROPERTY);

      return hostname;
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }

  }

  public static Boolean getHttpsEnableProperty()
  {
    try
    {
      URL resource = ServerSettingContextBean.class.getResource("/manager.properties");
      File file = new File(resource.toURI());

      PropertyReader reader = new PropertyReader(file.getAbsolutePath());

      String https = reader.getValue(HTTPS_PROPERTY);

      return new Boolean(https);
    }
    catch (URISyntaxException e)
    {
      throw new RuntimeException(e);
    }
  }
}
