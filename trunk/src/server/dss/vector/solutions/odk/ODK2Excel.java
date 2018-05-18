package dss.vector.solutions.odk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.odk.ODKDataConverter.ODKRow;

public class ODK2Excel implements Reloadable
{
  private static final String NUM_ENTRIES = "5000";

  private ODKForm             form;

  public ODK2Excel(ODKForm form)
  {
    this.form = form;
  }

  public Collection<String> getUUIDs(String cursor)
  {
    List<String> uuids = new LinkedList<String>();

    boolean hasMore = true;

    while (hasMore)
    {

      // 1. Get all of the submission keys
      try (CloseableHttpClient client = ODKFacade.getClient())
      {
        URIBuilder builder = new URIBuilder(ODKFacade.getBaseURL() + "/view/submissionList");
        builder.setParameter("formId", this.form.getFormName());
        builder.setParameter("numEntries", NUM_ENTRIES);

        if (cursor != null)
        {
          builder.setParameter("cursor", cursor);
        }

        HttpGet get = new HttpGet(builder.build());

        try (CloseableHttpResponse response = client.execute(get))
        {
          HttpEntity entity = response.getEntity();

          if (response.getStatusLine().getStatusCode() != 200)
          {
            throw new ProgrammingErrorException("ODK returned an error response: " + EntityUtils.toString(entity));
          }

          try
          {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document document = docBuilder.parse(entity.getContent());

            Collection<String> chunk = this.getUUIDs(document);

            cursor = this.getCursor(document);
            hasMore = ( chunk.size() > 0 );

            uuids.addAll(chunk);
          }
          catch (ParserConfigurationException e)
          {
            throw new XMLException(e);
          }
        }
      }
      catch (Exception e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return uuids;
  }

  public void export(Collection<String> uuids, ExcelExportSheet sheet)
  {
    ODKDataConverter converter = new ODKDataConverter();

    for (String uuid : uuids)
    {
      if (uuid != null && uuid.length() > 0)
      {
        // Get the form instance
        try (CloseableHttpClient client = ODKFacade.getClient())
        {
          String formId = this.form.getFormName() + "[@version=null and @uiVersion=null]/" + this.form.getFormName() + "[@key=" + uuid + "]";

          URIBuilder builder = new URIBuilder(ODKFacade.getBaseURL() + "/view/downloadSubmission");
          builder.setParameter("formId", formId);

          HttpGet get = new HttpGet(builder.build());

          try (CloseableHttpResponse response = client.execute(get))
          {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() != 200)
            {
              throw new ProgrammingErrorException("ODK returned an error response: " + EntityUtils.toString(entity));
            }

            try
            {
              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
              DocumentBuilder docBuilder = factory.newDocumentBuilder();
              Document document = docBuilder.parse(entity.getContent());

              // Print for testing
              // TransformerFactory tfactory = TransformerFactory.newInstance();
              //
              // Transformer serializer = tfactory.newTransformer();
              // serializer.setOutputProperty(OutputKeys.INDENT, "yes");
              // serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
              // "2");
              // serializer.transform(new DOMSource(document), new
              // StreamResult(System.out));

              XPathFactory xPathfactory = XPathFactory.newInstance();
              XPath xpath = xPathfactory.newXPath();
              XPathExpression expr = xpath.compile("//data");

              NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

              for (int i = 0; i < nodeList.getLength(); i++)
              {
                Node node = nodeList.item(i);

                NodeList children = node.getChildNodes();

                for (int j = 0; j < children.getLength(); j++)
                {
                  Node child = children.item(j);

                  List<ODKRow> rows = converter.convert(form, child, sheet.getExtraColumns());

                  for (ODKRow row : rows)
                  {
                    sheet.addRow(row.getMutable(), row.getOverrides());
                  }
                }
              }
            }
            catch (ParserConfigurationException e)
            {
              throw new XMLException(e);
            }
          }
        }
        catch (Exception e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
  }

  private Collection<String> getUUIDs(Document document) throws XPathExpressionException
  {
    List<String> uuids = new LinkedList<String>();

    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile("//idList");

    NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

    for (int i = 0; i < nodeList.getLength(); i++)
    {
      Node node = nodeList.item(i);

      NodeList children = node.getChildNodes();

      for (int j = 0; j < children.getLength(); j++)
      {
        Node child = children.item(j);

        uuids.add(child.getTextContent().trim());
      }
    }

    return uuids;
  }

  private String getCursor(Document document) throws XPathExpressionException
  {
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile("//resumptionCursor");

    NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

    if (nodeList.getLength() > 0)
    {
      Node node = nodeList.item(0);

      return node.getTextContent();
    }

    return null;
  }

  public static void main(String[] args)
  {
    try
    {
      runInRequest();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  private static void runInRequest()
  {
    run();
  }

  public static void run()
  {
    ExcelExporter exporter = new ExcelExporter();
    MosquitoCollectionExcelView.setupExportListener(exporter);
    ExcelExportSheet sheet = exporter.addTemplate(MosquitoCollectionExcelView.CLASS);

    String cursor = "<cursor xmlns=\"http://www.opendatakit.org/cursor\"><attributeName>_LAST_UPDATE_DATE</attributeName><attributeValue>2018-05-08T23:08:33.275+0000</attributeValue><isForwardCursor>true</isForwardCursor></cursor>";

    HashMap<String, String> mapping = new HashMap<String, String>();
    mapping.put(MosquitoCollectionView.ABUNDANCE, MosquitoCollectionExcelView.ABUNDANCE);
    mapping.put(MosquitoCollectionView.COLLECTIONID, MosquitoCollectionExcelView.COLLECTIONID);
    mapping.put(MosquitoCollectionView.COLLECTIONDATE, MosquitoCollectionExcelView.COLLECTIONDATE);
    mapping.put(MosquitoCollectionView.COLLECTIONMETHOD, MosquitoCollectionExcelView.COLLECTIONMETHOD);
    mapping.put(MosquitoCollectionView.COLLECTIONROUND, MosquitoCollectionExcelView.COLLECTIONROUND);
    mapping.put(MosquitoCollectionView.COLLECTIONTYPE, MosquitoCollectionExcelView.COLLECTIONTYPE);
    mapping.put(MosquitoCollectionView.DATELASTSPRAYED, MosquitoCollectionExcelView.DATELASTSPRAYED);
    mapping.put(MosquitoCollectionView.GEOENTITY, MosquitoCollectionExcelView.GEOENTITY);
    mapping.put(MosquitoCollectionView.INSECTICIDEBRAND, MosquitoCollectionExcelView.INSECTICIDEBRAND);
    mapping.put(MosquitoCollectionView.LIFESTAGE, MosquitoCollectionExcelView.LIFESTAGE);
    mapping.put(MosquitoCollectionView.NUMBEROFANIMALOCCUPANTS, MosquitoCollectionExcelView.NUMBEROFANIMALOCCUPANTS);
    mapping.put(MosquitoCollectionView.NUMBEROFHUMANOCCUPANTS, MosquitoCollectionExcelView.NUMBEROFHUMANOCCUPANTS);
    mapping.put(MosquitoCollectionView.NUMBEROFLLINS, MosquitoCollectionExcelView.NUMBEROFLLINS);
    mapping.put(MosquitoCollectionView.WALLTYPE, MosquitoCollectionExcelView.WALLTYPE);

    mapping.put(SubCollectionView.DISECTED, MosquitoCollectionExcelView.DISECTED);
    mapping.put(SubCollectionView.EGGS, MosquitoCollectionExcelView.EGGS);
    mapping.put(SubCollectionView.FEMALESFED, MosquitoCollectionExcelView.FEMALESFED);
    mapping.put(SubCollectionView.FEMALESGRAVID, MosquitoCollectionExcelView.FEMALESGRAVID);
    mapping.put(SubCollectionView.FEMALESHALFGRAVID, MosquitoCollectionExcelView.FEMALESHALFGRAVID);
    mapping.put(SubCollectionView.FEMALESUNFED, MosquitoCollectionExcelView.FEMALESUNFED);
    mapping.put(SubCollectionView.FEMALESUNKNOWN, MosquitoCollectionExcelView.FEMALESUNKNOWN);
    mapping.put(SubCollectionView.IDENTMETHOD, MosquitoCollectionExcelView.IDENTMETHOD);
    mapping.put(SubCollectionView.LARVAE, MosquitoCollectionExcelView.LARVAE);
    mapping.put(SubCollectionView.MALE, MosquitoCollectionExcelView.MALE);
    mapping.put(SubCollectionView.PAROUS, MosquitoCollectionExcelView.PAROUS);
    mapping.put(SubCollectionView.PUPAE, MosquitoCollectionExcelView.PUPAE);
    mapping.put(SubCollectionView.SUBCOLLECTIONID, MosquitoCollectionExcelView.SUBCOLLECTIONID);
    mapping.put(SubCollectionView.TAXON, MosquitoCollectionExcelView.TAXON);
    mapping.put(SubCollectionView.UNKNOWNS, MosquitoCollectionExcelView.UNKNOWNS);

    MdClassDAOIF subc = MdClassDAO.getMdClassDAO(SubCollectionView.CLASS);
    MdClassDAOIF mosq = MdClassDAO.getMdClassDAO(MosquitoCollectionView.CLASS);
    MdClassDAOIF target = MdClassDAO.getMdClassDAO(MosquitoCollectionExcelView.CLASS);
    GeoFilterCriteria gfc = new GeoFilterCriteria(true, false, false, false, SentinelSite.CLASS, CollectionSite.CLASS);

//    ODKForm form = new ODKForm(mosq, target, gfc, new ODKForm(subc));
//
//    ODK2Excel importer = new ODK2Excel(form);
//    Collection<String> uuids = importer.getUUIDs(cursor);
//
//    importer.export(uuids, sheet);
//
//    try
//    {
//      exporter.write(new FileOutputStream("test.xlsx"));
//    }
//    catch (FileNotFoundException e)
//    {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
}
