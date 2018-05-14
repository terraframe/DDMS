package dss.vector.solutions.odk;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.runwaysdk.business.View;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.scheduler.ExecutionContext;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geo.generated.GeoEntity;

public class MobileDataUploadJob extends MobileDataUploadJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1002885144;
  
  private static Logger logger = LoggerFactory.getLogger(MobileDataUploadJob.class);
  
  private ArrayList<String> ids = new ArrayList<String>();
  
  public MobileDataUploadJob()
  {
    super();
  }
  
  public static void main(String[] args) {
    mainInRequest(args);
  }
  
  @Request
  private static void mainInRequest(String[] args)
  {
    MobileDataUploadJob job = new MobileDataUploadJob();
    job.setFormType(MosquitoCollection.CLASS);
    job.doIt();
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    doIt();
  }
  
  private void doIt()
  {
    fetchIds();
    fetchData();
  }
  
  private void fetchIds()
  {
    HTTPResponse resp = ODKConnector.getFromOdk("view/submissionList?formId=dss_vector_solutions_entomology_MosquitoCollectionView");
    
    String xml = resp.getResponse();
    
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setIgnoringElementContentWhitespace(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
      
      NodeList idChunkNL = doc.getChildNodes();
      Node idChunk = idChunkNL.item(0);
      
      NodeList idListNL = idChunk.getChildNodes();
      Node idList = idListNL.item(0);
      
      NodeList idNL = idList.getChildNodes();
      
      for (int i = 0; i < idNL.getLength(); ++i)
      {
        Node id = idNL.item(i);
        
        if (id.getNodeType() == Node.ELEMENT_NODE)
        {
          String sId = id.getTextContent().substring(5);
          
          ids.add(sId);
        }
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private void fetchData()
  {
    MdBusinessDAOIF mdBiz = MdBusinessDAO.getMdBusinessDAO(this.getFormType());
    
    ArrayList<View> views = new ArrayList<View>();
    
    int j = 0;
    try
    {
      for (String id : ids)
      {
        View view = new MosquitoCollectionView();
        views.add(view);
        
        String mainType = "dss_vector_solutions_entomology_MosquitoCollectionView";
        String query = mainType + "[@version=null and @uiVersion=null]/" + mainType + "[@key=uuid:" + id + "]";
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        
        HTTPResponse resp2 = ODKConnector.getFromOdk("view/downloadSubmission?formId=" + encodedQuery);
        String xml = resp2.getResponse();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
        
        NodeList docNL = doc.getChildNodes();
        Node submission = docNL.item(0);
        
        NodeList submissionNL = submission.getChildNodes();
        Node data = submissionNL.item(0);
        
        NodeList dataNL = data.getChildNodes();
        Element form = (Element) dataNL.item(0);
        
        String submissionDate = form.getAttribute("submissionDate");
        
        NodeList formNL = form.getChildNodes();
        
        for (int i = 0; i < formNL.getLength(); ++i)
        {
          Node child = formNL.item(i);
          
          if (child.getNodeType() == Node.ELEMENT_NODE)
          {
            String name = child.getNodeName();
            String value = child.getTextContent();
            
            if (value != null && value.length() > 0)
            {
              if (name.startsWith("dss_vector_solutions")) // Sub collection
              {
              }
              else if (name.contains("_geolist_")) // Geo Entity attribute
              {
                String attrName = name.substring(0, name.indexOf("_geolist_"));
                try
                {
                  GeoEntity ge = GeoEntity.getByKey(value);
                  
                  view.setValue(attrName, ge.getId());
                  
                  System.out.println(name + " : " + value);
                }
                catch (DataNotFoundException e)
                {
                  // TODO : Create a problem to give to the spreadsheet exporter?
                  logger.error("Problem importing data from ODK. ODK contains a GeoEntity with geoId [" + value + "] but the DDMS does not.");
                }
              }
              else
              {
                MdAttributeConcreteDAOIF mdAttr = mdBiz.definesAttribute(name);
                
                if (mdAttr instanceof MdAttributePrimitiveDAOIF)
                {
                  view.setValue(name, value);
                  
                  if (j == 0)
                  {
                    System.out.println(name + " : " + value);
                  }
                }
              }
            }
          }
        }
        
        ++j;
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
    
    System.out.println(views);
  }
}
