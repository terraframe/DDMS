package dss.vector.solutions.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.runwaysdk.constants.MdDimensionInfo;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.facade.Facade;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.RequestType;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.TransactionExecuter;

/**
 * This test assumes that there is one locale installed on the system 'en_us'.
 * 
 * @author Justin Smethie
 */
public class QueryUtilTest
{
  public static String           sessionId;

  public static MdDimensionDAOIF dimension;

  @BeforeClass
  public static void classSetup() throws Exception
  {
    final String key = "Dimension.MALARIA";

    sessionId = Facade.login("ddms", "ddms", new Locale[] { Locale.US, Locale.ENGLISH, Locale.GERMAN });

    try
    {
      Facade.setDimension(sessionId, key);

      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          dimension = MdDimensionDAO.get(MdDimensionInfo.CLASS, key);
        }
      }.execute();
    }
    catch (Exception e)
    {
      Facade.logout(sessionId);

      throw e;
    }
  }

  @AfterClass
  public static void classTearDown() throws Exception
  {
    Facade.logout(sessionId);
  }

  //@Test
  public void testGetSupportLocale() throws Exception
  {
    List<String> locales = this.getSupportedSubLocales(sessionId, Locale.US);

    assertEquals(1, locales.size());
    assertEquals(locales.get(0), Locale.US.toString().toLowerCase());
  }

  //@Test
  public void testGetLocaleColumns() throws Exception
  {
    List<String> columns = this.getLocaleColumns(sessionId);

    // Ensure that the columns are in the correct order
    assertEquals(4, columns.size());
    assertEquals(MetadataDAO.convertCamelCaseToUnderscore(dimension.getLocaleAttributeName("en_us")), columns.get(0));
    assertEquals(MetadataDAO.convertCamelCaseToUnderscore(dimension.getDefaultLocaleAttributeName()), columns.get(1));
    assertEquals("en_us", columns.get(2));
    assertEquals(MdAttributeConcrete.getByKey(MetadataDisplayLabel.CLASS + "." + MetadataDisplayLabel.DEFAULTLOCALE).getColumnName(), columns.get(3));
  }

  //@Test
  public void testGetGeoDisplayLabelSQL() throws Exception
  {
    String sql = this.getGeoDisplayLabelSQL(sessionId);
    
    assertNotNull(sql);    
//    System.out.println(sql);
//    System.out.println();
  }

  @Test
  public void testGetGeoDisplayLabel() throws Exception
  {
    ValueQuery vQuery = this.getGeoDisplayLabel(sessionId);
    
    String sql = vQuery.getSQL();

    System.out.println(sql);
  }
  
  @Request(RequestType.SESSION)
  public List<String> getSupportedSubLocales(String sessionId, Locale locale)
  {
    return QueryUtil.getSupportedSubLocales(locale);
  }

  @Request(RequestType.SESSION)
  public String getGeoDisplayLabelSQL(String sessionId)
  {
    return QueryUtil.getGeoDisplayLabelSQL();
  }

  @Request(RequestType.SESSION)
  public ValueQuery getGeoDisplayLabel(String sessionId)
  {
    return QueryUtil.getGeoDisplayLabel();
  }
  
  @Request(RequestType.SESSION)
  public List<String> getLocaleColumns(String sessionId)
  {
    return QueryUtil.getLocaleColumns();
  }
}
