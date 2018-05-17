/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Runway SDK(tm). If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.odk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;

public class ODKFormExporter implements Reloadable
{
  private static final String EXPORT_DIR = DeployProperties.getDeployPath() + "/ODK/";

  /**
   * The DOM <code>document</code> that is populated with data from the core.
   */
  private Document            document;

  /**
   * The <code>root</code> element of the DOM document.
   */
  private Element             root;

  private List<ODKForm>       odkForms;

  private List<ODKAttribute>  baseAttrs;

  private String              formName;

  private Logger              logger     = LoggerFactory.getLogger(ODKFormExporter.class);

  public ODKFormExporter()
  {
    this.baseAttrs = new ArrayList<ODKAttribute>();
    this.odkForms = new ArrayList<ODKForm>();
    this.formName = null;
  }

  // This code is for a different kind of exportable, an MdWebForm.
  // We will need this code when we support types other than MosquitoCollection
  // (in section 8 of the spec).
  //
  // public void addExportable(MdWebForm exportable, List<ExcelExportListener>
  // listeners)
  // {
  //
  // for (MdWebField field : exportable.getAllMdFields())
  // {
  // field.setDefiningMdForm(mdForm);
  //
  // if (field instanceof MdWebGeo)
  // {
  // String fieldName = field.getFieldName();
  //
  // MdWebGeo oldWebGeo = (MdWebGeo) oldWebForm.getField(fieldName);
  // GeoField oldGeoField = GeoField.getGeoFieldForMdWebGeo(oldWebGeo.getId());
  // }
  // }
  //
  // if (this.formName == null)
  // {
  // this.setFormName(mdc.definesType()); // TODO : Figure out a real title
  // }
  //
  // List<? extends MdAttributeDAOIF> mdAttributeDAOs =
  // ExcelUtil.getAttributes(mdc, new DefaultExcelAttributeFilter());
  //
  // // Store relevant information about all the attributes
  // for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
  // {
  // if (mdAttribute.getMdAttributeConcrete() instanceof MdAttributeStructDAOIF)
  // {
  // MdAttributeStructDAOIF struct = (MdAttributeStructDAOIF)
  // mdAttribute.getMdAttributeConcrete();
  // MdStructDAOIF mdStruct = struct.getMdStructDAOIF();
  // List<? extends MdAttributeDAOIF> structAttributes =
  // ExcelUtil.getAttributes(mdStruct, new DefaultExcelAttributeFilter());
  //
  // for (MdAttributeDAOIF structAttribute : structAttributes)
  // {
  // this.addODKAttribute(new StructColumn(struct, structAttribute));
  // }
  // }
  // else
  // {
  // this.addODKAttribute(new AttributeColumn(mdAttribute));
  // }
  // }
  //
  // if (listeners != null)
  // {
  // List<ExcelColumn> excels = new ArrayList<ExcelColumn>();
  //
  // for (ExcelExportListener listener : listeners)
  // {
  // listener.addColumns(excels);
  // }
  //
  // for (ExcelColumn excel : excels)
  // {
  // this.addODKAttribute(ODKAttributeFactory.convert(excel));
  // }
  // }
  // }

  public void addForm(ODKForm form, ExcelExportListener... listeners)
  {
    this.odkForms.add(form);
    
    if (this.formName == null)
    {
      this.setFormName(form.getFormName());
    }

    this.baseAttrs.addAll(form.getBaseAttrs());

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

  public String doIt()
  {
    new File(EXPORT_DIR).mkdirs();
    
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

    int maxDepth = generateGeoEntityCSV();

    doHead(maxDepth);

    doBody(maxDepth);

//    print();

    return submit();
  }

  private int generateGeoEntityCSV()
  {
    // TODO : versioning of odk submissions
    
    // High level : Select lists must be defined for each level in the tree. The GeoEntities must then be exported to the CSV file at the appropriate level.
    
    // 1. Do a depth-first search of the Universal tree to determine the number of levels. TODO : Do we want Universal or GeoEntity?
    // 2. Create select lists for each level and for each attribute
    // 3. Export the GeoEntities to a CSV file
    // 4. Geo filter criteria
    // 5. Fix bug in form generator

    int maxDepth = geoLoop(null);

    GeoLoopHandler csvPrinter = new GeoLoopHandler(maxDepth);

    try
    {
      geoLoop(csvPrinter);
    }
    finally
    {
      csvPrinter.close();
    }

    return maxDepth;
  }

  private class GeoLoopHandler implements Reloadable
  {
    private int        maxDepth;

    private CSVPrinter csvp;

    private FileWriter writer;

    public GeoLoopHandler(int maxDepth)
    {
      this.maxDepth = maxDepth;

      FileWriter writer = null;
      csvp = null;

      try
      {
        writer = new FileWriter(EXPORT_DIR + "itemsets.csv");
        csvp = new CSVPrinter(writer, CSVFormat.DEFAULT.withRecordSeparator(System.getProperty("line.separator")));

        ArrayList<String> header = new ArrayList<String>();
        header.add("label::English");
        header.add("name");
        header.add("list_name");

        for (int i = 0; i <= maxDepth; ++i)
        {
          header.add("geoEntity_geolist_" + i); // TODO : This is attribute
                                                // specific
        }

        csvp.printRecord(header);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

    public void processGeo(GeoEntity geo, LinkedList<String> parents)
    {
      ArrayList<String> geoCSV = new ArrayList<String>();

      geoCSV.add(geo.getEntityLabel().getValue());
      geoCSV.add(geo.getGeoId());

      geoCSV.add("geoEntity_geolist_" + parents.size()); // TODO : This is attribute specific

      for (int listIndex = 0; listIndex < maxDepth; ++listIndex)
      {
        if (listIndex < parents.size())
        {
          geoCSV.add(parents.get(listIndex));
        }
        else
        {
          geoCSV.add("");
        }
      }

      try
      {
        csvp.printRecord(geoCSV);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

    public void close()
    {
      try
      {
        if (writer != null)
        {
          writer.close();
        }
        if (csvp != null)
        {
          csvp.close();
        }
      }
      catch (IOException e)
      {
        logger.error("Error occurred while closing resources.", e);
      }
    }
  }
  
  private class GeoStackElement implements Reloadable
  {
    private String geoId;
    private LinkedList<String> parentIds;
    
    public GeoStackElement(String geoId, LinkedList<String> parentIds)
    {
      this.geoId = geoId;
      this.parentIds = parentIds;
    }
  }

  @SuppressWarnings("unchecked")
  private int geoLoop(GeoLoopHandler handler)
  {
    ArrayList<GeoFilterCriteria> filters = new ArrayList<GeoFilterCriteria>(this.odkForms.size());
    for (int i = 0; i < this.odkForms.size(); ++i)
    {
      ODKForm form = odkForms.get(i);
      filters.add(form.getGeoFilterCriteria());
    }

    LinkedList<String> emptyParents = new LinkedList<String>();
    GeoEntity earth = Earth.getEarthInstance();
    GeoStackElement current = new GeoStackElement(earth.getGeoId(), emptyParents);
    Stack<GeoStackElement> allChildrenStack = new Stack<GeoStackElement>();
    int maxDepth = 0;
    List<GeoEntity> earthChildren = earth.getImmediateChildren();
    if (earthChildren.size() > 0)
    {
      for (int i = 0; i < earthChildren.size(); ++i)
      {
        allChildrenStack.push(new GeoStackElement(earthChildren.get(i).getGeoId(), emptyParents));
      }
      current = allChildrenStack.pop();
  
      while (current != null)
      {
        GeoEntity curGeo = GeoEntity.getByKey(current.geoId);
        
        boolean isPartOfHierarchies = true;
        for (GeoFilterCriteria filt : filters)
        {
          if (!filt.isPartOfHierarchy(curGeo))
          {
            isPartOfHierarchies = false;
          }
        }
        if (isPartOfHierarchies)
        {
          if (current.parentIds.size() > maxDepth)
          {
            maxDepth = current.parentIds.size();
          }
          
          if (handler != null)
          {
            handler.processGeo(curGeo, current.parentIds);
          }
        }
        
        List<GeoEntity> children = curGeo.getImmediateChildren();
        LinkedList<String> parents = ((LinkedList<String>)current.parentIds.clone());
        parents.add(curGeo.getGeoId());
        for (int i = 0; i < children.size(); ++i)
        {
          GeoEntity child = children.get(i);
          
          GeoStackElement childGeoStack = new GeoStackElement(child.getGeoId(), parents);
          allChildrenStack.push(childGeoStack);
        }
  
        if (!allChildrenStack.empty())
        {
          current = allChildrenStack.pop();
        }
        else
        {
          current = null;
        }
      }
    }

    return maxDepth;
  }

  private void doHead(int maxDepth)
  {
    Element head = document.createElement("h:head");

    Element title = document.createElement("h:title");
    title.setTextContent(this.formName);
    head.appendChild(title);

    doModel(head, maxDepth);

    root.appendChild(head);
  }
  
  protected String getFormVersion()
  {
    ODKFormMappingQuery q = new ODKFormMappingQuery(new QueryFactory());
    q.WHERE(q.getFormName().EQ(this.formName));
    OIterator<? extends ODKFormMapping> it = q.getIterator();
    try
    {
      String version = new SimpleDateFormat("yyyyMMdd").format(new Date());
      
      if (it.hasNext())
      {
        ODKFormMapping mapping = it.next();
        mapping.lock();
        mapping.setRevision(mapping.getRevision()+1);
        mapping.apply();
        
        version = version + String.format("%02d", mapping.getRevision());
      }
      else
      {
        ODKFormMapping mapping = new ODKFormMapping();
        mapping.setFormName(this.formName);
        mapping.setDisease(Disease.getCurrent());
        mapping.setRevision(0);
        mapping.apply();
        
        version = version + String.format("%02d", mapping.getRevision());
      }
      
      return version;
    }
    finally
    {
      it.close();
    }
  }

  private void doModel(Element head, int maxDepth)
  {
    Element model = document.createElement("model");
    head.appendChild(model);

    Element itext = document.createElement("itext");
    model.appendChild(itext);

    Element translation = document.createElement("translation");
    translation.setAttribute("lang", "English");
    
    for (ODKForm form : this.odkForms)
    {
      form.writeTranslation(translation, document, this.formName, maxDepth);
    }
    itext.appendChild(translation);

    Element instance = document.createElement("instance");
    model.appendChild(instance);
    Element instRoot = document.createElement(formName);
    instRoot.setAttribute("id", formName);
    
    instRoot.setAttribute("orx:version", getFormVersion());
    
    instance.appendChild(instRoot);
    
    for (ODKForm form : this.odkForms)
    {
      form.writeInstance(instRoot, document, this.formName, maxDepth);
    }

    for (ODKForm form : this.odkForms)
    {
      form.writeBind(model, document, this.formName, maxDepth);
    }
  }

  private void doBody(int maxDepth)
  {
    Element body = document.createElement("h:body");
    root.appendChild(body);

    for (ODKForm form : this.odkForms)
    {
      form.writeBody(body, document, this.formName, maxDepth);
    }
  }

  // curl --verbose --user ddms:aggregate --header "Content-Type:
  // multipart/form-data" --form
  // "form_def_file=@/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml"
  // http://172.19.0.1:8080/ODKAggregate/formUpload
  @AbortIfProblem
  private String submit()
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
      FileOutputStream fos = new FileOutputStream(new File(EXPORT_DIR + "mosquitos-test.xml")); // TODO
                                                                                                // :
                                                                                                // This
                                                                                                // shouldn't
                                                                                                // be
                                                                                                // hardcoded
      fos.write(form.getBytes());
      fos.close();

      // 3. Push the document to ODK
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      File fFormDef = new File(EXPORT_DIR + "mosquitos-test.xml");
      builder.addBinaryBody("form_def_file", new FileInputStream(fFormDef), ContentType.APPLICATION_XML, fFormDef.getName());
      File itemsets = new File(EXPORT_DIR + "itemsets.csv");
      builder.addBinaryBody("mediaFiles", new FileInputStream(itemsets), ContentType.APPLICATION_XML, itemsets.getName());
      HttpEntity multipart = builder.build();
      
      HTTPResponse resp = ODKConnector.postToOdk("formUpload", multipart);
      
      if (resp.getResponse().length() == 0)
      {
        throw new RuntimeException("Expected a response from ODK.");
      }
      
      return resp.getResponse();
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  protected void setFormName(String input)
  {
    this.formName = input;
  }

  public void addODKAttribute(ODKAttribute column)
  {
    this.baseAttrs.add(column);
  }
}
