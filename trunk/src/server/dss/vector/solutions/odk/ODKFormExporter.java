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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
  
  private OutputStream out;
  
  private List<ODKAttribute> odkAttrs;
  
  private String formName;

  public ODKFormExporter(OutputStream out)
  {
    this.out = out;
    this.odkAttrs = new ArrayList<ODKAttribute>();
    this.formName = null;
  }

  public static void main(String[] args)
  {
    try
    {
      if (args.length != 2)
      {
        String errMsg = "Two arguments are required for ODKFormExporter:\n" + "  1) MdClass typename\n" + "  2) path to export file";
        throw new CoreException(errMsg);
      }

      String mdcType = args[0];
      String exportFile = args[1];
      
      export(mdcType, new FileOutputStream(new File(exportFile)));
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
  public static void export(String mdcType, OutputStream os)
  {
    MdClassDAOIF mdc = MdClassDAO.getMdClassDAO(mdcType);
    
    ODKFormExporter odkExp = new ODKFormExporter(os);
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
    
    writeToStream();
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
    
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeBody(body, document, this.formName);
    }
    
    root.appendChild(body);
  }
  
  private void writeToStream()
  {
    TransformerFactory tfactory = TransformerFactory.newInstance();

    try
    {
      Transformer serializer = tfactory.newTransformer();
      
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      
      serializer.transform(new DOMSource(document), new StreamResult(this.out));
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
