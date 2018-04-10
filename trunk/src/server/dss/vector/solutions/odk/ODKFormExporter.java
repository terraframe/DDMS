/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.odk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.CoreException;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdStructDAOIF;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.dataaccess.io.excel.DefaultExcelAttributeFilter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.session.Request;

public class ODKFormExporter
{
  /**
   * The DOM <code>document</code> that is populated with data from the core.
   */
  private Document                        document;

  /**
   * The <code>root</code> element of the DOM document.
   */
  private Element                         root;
  
  private List<ODKAttribute> odkAttrs;
  
  private String formName;

  public ODKFormExporter()
  {
    this.odkAttrs = new ArrayList<ODKAttribute>();
    this.formName = null;
  }

  public static void main(String[] args)
  {
    try
    {
      if (args.length != 1)
      {
        String errMsg = "One argument is required for ODKFormExporter:\n" + "  1) MdClass typename\n";
        throw new CoreException(errMsg);
      }

      String mdcType = args[0];
      
      export(mdcType);
    }
    catch (Throwable t)
    {
      throw new RuntimeException(t);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
  
  @Request
  public static void export(String mdcType)
  {
    MdClassDAOIF mdc = MdClassDAO.getMdClassDAO(mdcType);
    
    ODKFormExporter odkExp = new ODKFormExporter();
    odkExp.addExportable(mdc, null);
    odkExp.doIt();
  }
  
  public void addExportable(MdClassDAOIF exportable, List<ExcelExportListener> listeners)
  {
    prepareColumns(exportable);
    
    if (listeners != null)
    {
      List<ExcelColumn> excels = new ArrayList<ExcelColumn>();
    
      for (ExcelExportListener listener : listeners)
      {
        listener.addColumns(excels);
      }
      
      for (ExcelColumn excel : excels)
      {
        this.addODKAttribute(ODKAttributeFactory.convert(excel));
      }
    }
  }
  
  private void doIt()
  {
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.newDocument();
    }
    catch (ParserConfigurationException e)
    {
      throw new XMLException(e);
    }

    root = document.createElement("h:html");
    root.setAttribute("xmlns", "http://www.w3.org/2002/xforms");
    root.setAttribute("xmlns:ev", "http://www.w3.org/2001/xml-events");
    root.setAttribute("xmlns:h", "http://www.w3.org/1999/xhtml");
    root.setAttribute("xmlns:jr", "http://openrosa.org/javarosa");
    root.setAttribute("xmlns:oc", "http://openclinica.org/xforms");
    root.setAttribute("xmlns:orx", "http://openrosa.org/xforms");
    root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
    document.appendChild(root);
    
    doHead();
    
    doBody();
    
    submit();
  }
  
  private void doHead()
  {
    Element head = document.createElement("h:head");
    
    Element title = document.createElement("h:title");
    title.setTextContent(this.formName);
    head.appendChild(title);
    
    doModel(head);
    
    root.appendChild(head);
  }
  
  private void doModel(Element head)
  {
    Element model = document.createElement("model");
    head.appendChild(model);
    
    Element itext = document.createElement("itext");
    model.appendChild(itext);
    
    Element translation = document.createElement("translation");
    translation.setAttribute("lang", "English");
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeTranslation(translation, document, this.formName);
    }
    itext.appendChild(translation);
    
    Element instance = document.createElement("instance");
    Element instRoot = document.createElement(formName);
    instRoot.setAttribute("id", formName);
    instance.appendChild(instRoot);
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeInstance(instRoot, document, this.formName);
    }
    model.appendChild(instance);
    
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeBind(model, document, this.formName);
    }
  }
  
  private void doBody()
  {
    Element body = document.createElement("h:body");
    root.appendChild(body);
    
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeBody(body, document, this.formName);
    }
  }
  
  // curl --verbose --user ddms:aggregate --header "Content-Type: multipart/form-data" --form "form_def_file=@/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml" http://172.19.0.1:8080/ODKAggregate/formUpload
  private void submit()
  {
    TransformerFactory tfactory = TransformerFactory.newInstance();

    try
    {
      // 1. Write the document to a string
      Transformer serializer = tfactory.newTransformer();
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      serializer.transform(new DOMSource(document), new StreamResult(baos));
      String form = baos.toString("UTF-8");
      baos.close();
      
      // 2. Write the document to a file (for reference)
      FileOutputStream fos = new FileOutputStream(new File("/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml")); // TODO : This shouldn't be hardcoded
      fos.write(form.getBytes());
      fos.close();
      
      // 3. Push the document to ODK
//      HTTPConnector http = new HTTPConnector();
//      http.setCredentials("ddms", "aggregate"); // TODO : This shouldn't be hardcoded
//      http.setServerUrl("http://172.19.0.1:8080/ODKAggregate"); // TODO : This shouldn't be hardcoded
//      HTTPResponse response = http.postAsMultipart("formUpload", new File("/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml"));
//      
//      int statusCode = response.getStatusCode();
//      if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED)
//      {
//        System.out.println(response.getResponse());
//        throw new RuntimeException("Invalid status code [" + statusCode + "].");
//      }
      
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpPost uploadFile = new HttpPost("http://172.19.0.1:8080/ODKAggregate/formUpload"); // TODO : don't hardcode
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      // This attaches the file to the POST:
      File f = new File("/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml"); // TODO : don't hardcode
      builder.addBinaryBody(
          "form_def_file",
          new FileInputStream(f),
          ContentType.APPLICATION_XML,
          f.getName()
      );

      HttpEntity multipart = builder.build();
      uploadFile.setEntity(multipart);
      CloseableHttpResponse response = httpClient.execute(uploadFile);
      HttpEntity responseEntity = response.getEntity();
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED)
      {
        InputStream is = responseEntity.getContent();
        System.out.println(IOUtils.toString(is, "UTF-8"));
        throw new RuntimeException("Invalid status code [" + statusCode + "].");
      }
    }
    catch (Exception e)
    {
      throw new XMLException(e);
    }
  }
  
  protected void setFormName(String input)
  {
    this.formName = input.replaceAll("\\.", "_");
  }
  
  protected void prepareColumns(MdClassDAOIF mdc)
  {
    if (this.formName == null)
    {
      this.setFormName(mdc.definesType()); // TODO : Figure out a real title
    }
    
    List<? extends MdAttributeDAOIF> mdAttributeDAOs = ExcelUtil.getAttributes(mdc, new DefaultExcelAttributeFilter());

    // Store relevant information about all the attributes
    for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
    {
      if (mdAttribute.getMdAttributeConcrete() instanceof MdAttributeStructDAOIF)
      {
        MdAttributeStructDAOIF struct = (MdAttributeStructDAOIF) mdAttribute.getMdAttributeConcrete();
        MdStructDAOIF mdStruct = struct.getMdStructDAOIF();
        List<? extends MdAttributeDAOIF> structAttributes = ExcelUtil.getAttributes(mdStruct, new DefaultExcelAttributeFilter());

        for (MdAttributeDAOIF structAttribute : structAttributes)
        {
          this.addODKAttribute(new StructColumn(struct, structAttribute));
        }
      }
      else
      {
        this.addODKAttribute(new AttributeColumn(mdAttribute));
      }
    }
  }
  
  public void addODKAttribute(ODKAttribute column)
  {
    this.odkAttrs.add(column);
  }
}
