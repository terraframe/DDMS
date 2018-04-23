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
import java.io.InputStream;
import java.util.ArrayList;
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

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.constants.ElementInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdStructDAOIF;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.dataaccess.io.excel.DefaultExcelAttributeFilter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.MdAttributeFilter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;

public class ODKFormExporter
{
  private static class Filter extends DefaultExcelAttributeFilter implements MdAttributeFilter, Reloadable
  {

    @Override
    public boolean accept(MdAttributeDAOIF mdAttribute)
    {
      if (mdAttribute instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) mdAttribute ).getReferenceMdBusinessDAO();

        String type = referenceMdBusiness.definesType();

        return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS);
      }

      if (mdAttribute.definesAttribute().equals(MosquitoCollectionView.CONCRETEID))
      {
        return false;
      }

      return super.accept(mdAttribute);
    }

  }

  private static final String EXPORT_DIR = "dev/";

  private static final String IP_ADDRESS = "192.168.1.187";

  /**
   * The DOM <code>document</code> that is populated with data from the core.
   */
  private Document            document;

  /**
   * The <code>root</code> element of the DOM document.
   */
  private Element             root;

  private List<ODKForm>       odkForms;

  private List<ODKAttribute>  odkAttrs;

  private String              formName;

  private Logger              logger     = LoggerFactory.getLogger(ODKFormExporter.class);

  public ODKFormExporter()
  {
    this.odkAttrs = new ArrayList<ODKAttribute>();
    this.odkForms = new ArrayList<ODKForm>();
    this.formName = null;
  }

  public static void main(String[] args)
  {
    try
    {
      export();
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
  public static void export()
  {
    // MosquitoCollections is locked to Political plus Sentinel site and
    // Collection Site.
    // TODO : Move this somewhere specific to mosquito collections
    MdClassDAOIF subc = MdClassDAO.getMdClassDAO(SubCollectionView.CLASS);
    MdClassDAOIF mosq = MdClassDAO.getMdClassDAO(MosquitoCollectionView.CLASS);
    GeoFilterCriteria gfc = new GeoFilterCriteria(true, false, false, false, SentinelSite.CLASS, CollectionSite.CLASS);
    ODKForm form = new ODKForm(mosq, gfc, new ODKForm(subc));

    ODKFormExporter odkExp = new ODKFormExporter();
    odkExp.addForm(form, null);
    odkExp.doIt();
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

  public void addForm(ODKForm form, List<ExcelExportListener> listeners)
  {
    this.odkForms.add(form);

    prepareColumns(form);

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

    int maxDepth = generateGeoEntityCSV();

    doHead(maxDepth);

    doBody(maxDepth);

    print();

    submit();
  }

  private int generateGeoEntityCSV()
  {
    // High level : Select lists must be defined for each level in the tree. The
    // GeoEntities must then be exported to the CSV file at the appropriate
    // level.

    // 1. Do a depth-first search of the Universal tree to determine the number
    // of levels. TODO : Do we want Universal or GeoEntity?
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

  private class GeoLoopHandler
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

        for (int i = 0; i < maxDepth - 1; ++i)
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

    public void processGeo(GeoEntity geo, LinkedMap parents)
    {
      ArrayList<String> geoCSV = new ArrayList<String>();

      geoCSV.add(geo.getEntityLabel().getValue());
      geoCSV.add(geo.getGeoId());

      int curDepth = ( parents.size() - 1 );
      geoCSV.add("geoEntity_geolist_" + curDepth); // TODO : This is attribute
                                                   // specific

      for (int listIndex = 0; listIndex < maxDepth - 1; ++listIndex)
      {
        int parentIndex = listIndex + 1;
        if (parentIndex < parents.size())
        {
          geoCSV.add( ( (GeoEntity) parents.getValue(parentIndex) ).getGeoId());
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

  private int geoLoop(GeoLoopHandler handler)
  {
    GeoFilterCriteria[] filters = new GeoFilterCriteria[this.odkForms.size()];
    for (int i = 0; i < this.odkForms.size(); ++i)
    {
      ODKForm form = odkForms.get(i);
      filters[i] = form.getGeoFilterCriteria();
    }

    GeoEntity parent = Earth.getEarthInstance();
    Stack<GeoEntity> allChildrenStack = new Stack<GeoEntity>(); // This stack
                                                                // contains
                                                                // GeoEntitys
                                                                // that have not
                                                                // been
                                                                // processed yet
    LinkedMap parentMap = new LinkedMap(); // Maps the first child geo entity to
                                           // the parent. This is necessary to
                                           // know the parents and also when
                                           // we've gone up in the hierarchy

    int maxDepth = 0;

    do
    {
      List<GeoEntity> children = parent.getImmediateChildren();

      for (int i = 0; i < children.size(); ++i)
      {
        GeoEntity child = children.get(i);

        if (i == 0)
        {
          parentMap.put(child, parent);

          for (GeoFilterCriteria filt : filters)
          {
            if (filt.isPartOfHierarchy(child))
            {
              if (parentMap.size() > maxDepth)
              {
                maxDepth = parentMap.size();
              }
            }
          }
        }

        if (handler != null)
        {
          for (GeoFilterCriteria filt : filters)
          {
            if (filt.isPartOfHierarchy(child))
            {
              handler.processGeo(child, parentMap);
            }
          }
        }

        allChildrenStack.push(child);
      }

      if (!allChildrenStack.empty())
      {
        parent = allChildrenStack.pop();

        if (parent.getId().equals( ( (GeoEntity) parentMap.lastKey() ).getId()))
        {
          parentMap.remove(parentMap.size() - 1);
        }
      }
      else
      {
        parent = null;
      }

    } while (allChildrenStack.size() > 0 || parent != null);

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

  private void doModel(Element head, int maxDepth)
  {
    Element model = document.createElement("model");
    head.appendChild(model);

    Element itext = document.createElement("itext");
    model.appendChild(itext);

    Element translation = document.createElement("translation");
    translation.setAttribute("lang", "English");
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeTranslation(translation, document, this.formName, maxDepth);
    }
    itext.appendChild(translation);

    Element instance = document.createElement("instance");
    Element instRoot = document.createElement(formName);
    instRoot.setAttribute("id", formName);
    instance.appendChild(instRoot);
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeInstance(instRoot, document, this.formName, maxDepth);
    }
    model.appendChild(instance);

    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeBind(model, document, this.formName, maxDepth);
    }
  }

  private void doBody(int maxDepth)
  {
    Element body = document.createElement("h:body");
    root.appendChild(body);
    
    for (ODKAttribute attr : this.odkAttrs)
    {
      attr.writeBody(body, document, this.formName, maxDepth);
    }
  }

  // curl --verbose --user ddms:aggregate --header "Content-Type:
  // multipart/form-data" --form
  // "form_def_file=@/home/rick/Documents/eclipse/workspace/MDSS/dev/mosquitos-test.xml"
  // http://172.19.0.1:8080/ODKAggregate/formUpload
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
      FileOutputStream fos = new FileOutputStream(new File(EXPORT_DIR + "mosquitos-test.xml")); // TODO
                                                                                                // :
                                                                                                // This
                                                                                                // shouldn't
                                                                                                // be
                                                                                                // hardcoded
      fos.write(form.getBytes());
      fos.close();

      // 3. Push the document to ODK
      CredentialsProvider credsProvider = new BasicCredentialsProvider();
      credsProvider.setCredentials(new AuthScope(IP_ADDRESS, 80), new UsernamePasswordCredentials("ddms", "aggregate")); // TODO
                                                                                                                           // :
                                                                                                                           // don't
                                                                                                                           // hardcode

      CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
      HttpPost post = new HttpPost("http://" + IP_ADDRESS + ":8080/ODKAggregate/formUpload"); // TODO
      // :
      // don't
      // hardcode
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();

      // This attaches the file to the POST:
      File fFormDef = new File(EXPORT_DIR + "mosquitos-test.xml"); // TODO :
                                                                   // don't
                                                                   // hardcode
      builder.addBinaryBody("form_def_file", new FileInputStream(fFormDef), ContentType.APPLICATION_XML, fFormDef.getName());
      File itemsets = new File(EXPORT_DIR + "itemsets.csv"); // TODO : don't
                                                             // hardcode
      builder.addBinaryBody("mediaFiles", new FileInputStream(itemsets), ContentType.APPLICATION_XML, itemsets.getName());

      HttpEntity multipart = builder.build();
      post.setEntity(multipart);
      CloseableHttpResponse response = httpClient.execute(post);
      HttpEntity responseEntity = response.getEntity();
      int statusCode = response.getStatusLine().getStatusCode();
      InputStream is = responseEntity.getContent();
      System.out.println(IOUtils.toString(is, "UTF-8"));
      if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED)
      {
        throw new RuntimeException("Invalid status code [" + statusCode + "].");
      }
    }
    catch (Exception e)
    {
      throw new XMLException(e);
    }
  }

  private void print()
  {
    TransformerFactory tfactory = TransformerFactory.newInstance();

    try
    {
      // 1. Write the document to a string
      Transformer serializer = tfactory.newTransformer();
      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
      serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      serializer.transform(new DOMSource(document), new StreamResult(System.out));
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

  protected void prepareColumns(ODKForm form)
  {
    MdClassDAOIF mdc = form.getBase();

    if (this.formName == null)
    {
      this.setFormName(mdc.definesType()); // TODO : Figure out a real title
    }

    List<? extends MdAttributeDAOIF> mdAttributeDAOs = ExcelUtil.getAttributes(mdc, new Filter());

    // Store relevant information about all the attributes
    for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

      if (mdAttributeConcrete instanceof MdAttributeStructDAOIF)
      {
        MdAttributeStructDAOIF struct = (MdAttributeStructDAOIF) mdAttributeConcrete;
        MdStructDAOIF mdStruct = struct.getMdStructDAOIF();
        List<? extends MdAttributeDAOIF> structAttributes = ExcelUtil.getAttributes(mdStruct, new Filter());

        for (MdAttributeDAOIF structAttribute : structAttributes)
        {
          this.addODKAttribute(new StructColumn(struct, structAttribute));
        }
      }
      else if (mdAttributeConcrete instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) mdAttributeConcrete ).getReferenceMdBusinessDAO();
        if (referenceMdBusiness.definesType().equals(GeoEntity.CLASS))
        {
          this.addODKAttribute(new ODKGeoAttribute(mdAttribute));
        }
        else
        {
          this.addODKAttribute(new ODKTermAttribute(mdAttribute));
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
