/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.serializer.OutputPropertiesFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CatalogBuilder
{
  /**
   * Name of the namespace tag
   */
  private static final String NAMESPACE  = "namespace";

  /**
   * Name of the datastore tag
   */
  private static final String DATASTORE  = "datastore";

  /**
   * Name of the datastores tag
   */
  private static final String DATASTORES = "datastores";

  /**
   * Name of the namespaces tag
   */
  private static final String NAMESPACES = "namespaces";

  private String              file;

  private Document            document;

  public CatalogBuilder(String file) throws ParserConfigurationException, SAXException, IOException
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);

    DocumentBuilder builder = factory.newDocumentBuilder();

    this.document = builder.parse(file);
    this.file = file;
  }

  public void addApplication(String appName, String dbName)
  {
    // Ensure that the datastore and namespace don't already exist
    boolean hasDatastore = this.hasDatastore(appName);
    boolean hasNamespace = this.hasNamespace(appName);

    if (!hasDatastore)
    {
      String datastoreId = this.getDatastoreId(appName);
      
      Element connectionParams = document.createElement("connectionParams");
      connectionParams.appendChild(this.createParameter(document, "port", "5444"));
      connectionParams.appendChild(this.createParameter(document, "passwd", "mdssdeploy"));
      connectionParams.appendChild(this.createParameter(document, "dbtype", "postgis"));
      connectionParams.appendChild(this.createParameter(document, "host", "localhost"));
      connectionParams.appendChild(this.createParameter(document, "validate connections", "false"));
      connectionParams.appendChild(this.createParameter(document, "max connections", "10"));
      connectionParams.appendChild(this.createParameter(document, "database", dbName));
      connectionParams.appendChild(this.createParameter(document, "wkb enabled", "true"));
      connectionParams.appendChild(this.createParameter(document, "namespace", "http://" + appName + ".terraframe.com"));
      connectionParams.appendChild(this.createParameter(document, "schema", "public"));
      connectionParams.appendChild(this.createParameter(document, "estimated extent", "false"));
      connectionParams.appendChild(this.createParameter(document, "loose bbox", "true"));
      connectionParams.appendChild(this.createParameter(document, "user", "mdssdeploy"));
      connectionParams.appendChild(this.createParameter(document, "min connections", "4"));

      Element datastore = document.createElement(DATASTORE);
      datastore.setAttribute("id", datastoreId);
      datastore.setAttribute("enabled", "true");
      datastore.setAttribute("namespace", appName);
      datastore.appendChild(connectionParams);

      Node datastores = document.getElementsByTagName(DATASTORES).item(0);
      datastores.insertBefore(datastore, datastores.getFirstChild());
    }

    if (!hasNamespace)
    {
      Element namespace = document.createElement(NAMESPACE);
      namespace.setAttribute("prefix", appName);
      namespace.setAttribute("uri", "http://" + appName + ".terraframe.com");

      Node namespaces = document.getElementsByTagName(NAMESPACES).item(0);
      namespaces.insertBefore(namespace, namespaces.getFirstChild());
    }
  }

  public void write() throws TransformerFactoryConfigurationError, TransformerException, IOException
  {
    FileWriter out = new FileWriter(this.file);

    try
    {
      this.write(out);
    }
    finally
    {
      out.flush();
      out.close();
    }
  }

  public void write(Writer out) throws TransformerFactoryConfigurationError, TransformerException
  {
    Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
    serializer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    serializer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");
    serializer.transform(new DOMSource(document), new StreamResult(out));
  }

  private boolean hasNamespace(String appName)
  {
    NodeList namespaces = document.getElementsByTagName(NAMESPACE);

    for (int i = 0; i < namespaces.getLength(); i++)
    {
      Node namespace = namespaces.item(i);

      NamedNodeMap attributes = namespace.getAttributes();
      String prefix = attributes.getNamedItem("prefix").getNodeValue();

      if (prefix.equals(appName))
      {
        return true;
      }
    }

    return false;
  }

  private boolean hasDatastore(String appName)
  {
    String datastoreId = this.getDatastoreId(appName);
    
    NodeList datastores = document.getElementsByTagName(DATASTORE);

    for (int i = 0; i < datastores.getLength(); i++)
    {
      Node datastore = datastores.item(i);

      NamedNodeMap attributes = datastore.getAttributes();
      String id = attributes.getNamedItem("id").getNodeValue();

      if (id.equals(datastoreId))
      {
        return true;
      }
    }

    return false;
  }

  private String getDatastoreId(String appName)
  {
    return "MDSS_maps_" + appName;
  }

  private Node createParameter(Document document, String name, String value)
  {
    Element parameter = document.createElement("parameter");
    parameter.setAttribute("name", name);
    parameter.setAttribute("value", value);

    return parameter;
  }

  public static void main(String[] args) throws Exception
  {
    Writer out = new StringWriter();

    try
    {
      CatalogBuilder builder = new CatalogBuilder("/home/jsmethie/apache-tomcat-6.0.26/webapps/geoserver/data/catalog.xml");
      builder.addApplication("kajima_dep1", "mdss_kajima_dep1");
      builder.addApplication("zambia_dep1", "mdss_zambia_dep1");
      builder.write(out);
    }
    finally
    {
      out.flush();
      out.close();
    }

    // print xml
    System.out.println("Here's the xml:\n\n" + out.toString());
  }
}
