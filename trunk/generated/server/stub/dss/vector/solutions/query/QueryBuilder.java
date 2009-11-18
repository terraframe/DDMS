package dss.vector.solutions.query;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.COUNT;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.system.metadata.MdBusiness;

public class QueryBuilder extends QueryBuilderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255379414351L;
  
  public QueryBuilder()
  {
    super();
  }
  
  private static ValueQuery getValueQuery(String queryClass, String queryXML, String config, Boolean includeGeometry, ThematicLayer thematicLayer) 
  {
    
    Class<?> clazz = null;

    Method xmlToValueQuery = null;

    ValueQuery valueQuery = null;
    try
    {
      clazz = Class.forName(queryClass);
      xmlToValueQuery = clazz.getMethod("xmlToValueQuery",String.class, String.class, Boolean.class, ThematicLayer.class );
      valueQuery = (ValueQuery) xmlToValueQuery.invoke(clazz, queryXML, config, includeGeometry, thematicLayer);
      System.out.println(valueQuery.getSQL());
      
    }
    catch (ClassNotFoundException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (SecurityException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (NoSuchMethodException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      // TODO Auto-generated catch block
     
     RuntimeException cause = (RuntimeException) e.getTargetException();
     cause.printStackTrace();
      throw cause;
     
    }

    return valueQuery;
    
  }
  
  
  /**
   * Queries 
   *
   * @param xml
   * @throws Throwable 
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery getQueryResults(String queryClass, String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize) 
  {
    ValueQuery valueQuery = getValueQuery(queryClass, queryXML, config, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

  @Transaction
  public static String mapQuery(String queryClass, String xml, String config, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ThematicLayer thematicLayer = search.getThematicLayer();

    if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
    {
      String error = "Cannot create a map for search [" + search.getQueryName()
          + "] without having selected a thematic layer.";
      NoThematicLayerException ex = new NoThematicLayerException(error);
      throw ex;
    }

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
    if (thematicLayer.getGeometryStyle() == null
        || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    ValueQuery query = getValueQuery(queryClass,xml, config, true, thematicLayer);

    System.out.println(query.getSQL());

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

    return layers;
  }


  @Transaction
  public static InputStream exportQueryToExcel(String queryClass,String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = getValueQuery(queryClass, queryXML, config, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryClass, String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = getValueQuery(queryClass, queryXML, config, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }
  
  @Authenticate
  @Transaction
  public static ValueQuery getTextAttributeSugestions(String match, String klass, String attribute)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);
    
    SelectableSQLCharacter attribSelectable = valueQuery.aSQLCharacter("attribute", attribute);
    
    COUNT count = F.COUNT(attribSelectable, "attributeCount");
    
    valueQuery.SELECT(attribSelectable,count);

    String table = MdBusiness.getMdBusiness(klass).getTableName();
    
    valueQuery.FROM(table, "auto_complete");
    
    valueQuery.WHERE(attribSelectable.LIKEi(match+"%"));
    
    valueQuery.ORDER_BY_DESC(count);
    
    valueQuery.restrictRows(20, 1);
    
    System.out.println(valueQuery.getSQL());


    return valueQuery;
  }
  
  
}
