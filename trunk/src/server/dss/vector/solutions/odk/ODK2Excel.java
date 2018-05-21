package dss.vector.solutions.odk;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.odk.ODKDataConverter.ODKRow;

public class ODK2Excel implements Reloadable
{
  private static final String NUM_ENTRIES = "5000";

  private ODKForm             form;

  private String              cursor;

  private Date                exportDateTime;

  public ODK2Excel(ODKForm form, String cursor)
  {
    this.form = form;
    this.cursor = cursor;
    this.exportDateTime = null;
  }

  public ODKForm getForm()
  {
    return form;
  }

  public String getCursor()
  {
    return cursor;
  }

  public Date getExportDateTime()
  {
    return exportDateTime;
  }

  public Collection<String> getUUIDs()
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

        if (this.cursor != null)
        {
          builder.setParameter("cursor", this.cursor);
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

            // Print for testing
            TransformerFactory tfactory = TransformerFactory.newInstance();

            Transformer serializer = tfactory.newTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            serializer.transform(new DOMSource(document), new StreamResult(System.out));

            Collection<String> chunk = this.getUUIDs(document);

            this.cursor = this.getCursor(document);
            this.exportDateTime = new Date();

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
              TransformerFactory tfactory = TransformerFactory.newInstance();

              Transformer serializer = tfactory.newTransformer();
              serializer.setOutputProperty(OutputKeys.INDENT, "yes");
              serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
              serializer.transform(new DOMSource(document), new StreamResult(System.out));

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
}
