package dss.vector.solutions.query;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AttributePrimitive;
import com.terraframe.mojo.query.COUNT;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;
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
  
  private static ValueQuery getValueQuery(String queryClass, String queryXML, String config, Boolean includeGeometry)
  {
    
    Class<?> clazz = null;

    Method xmlToValueQuery = null;

    ValueQuery valueQuery = null;
    try
    {
      clazz = Class.forName(queryClass);
      xmlToValueQuery = clazz.getMethod("xmlToValueQuery",String.class, String.class, Boolean.class);
      valueQuery = (ValueQuery) xmlToValueQuery.invoke(clazz, queryXML, config, includeGeometry);
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
    ValueQuery valueQuery = getValueQuery(queryClass, queryXML, config, false);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
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

    ValueQuery query = getValueQuery(queryClass, queryXML, config, false);

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

    ValueQuery query = getValueQuery(queryClass, queryXML, config, false);

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
  
  public static ValueQuery textLookup(QueryFactory qf, String[] tokenArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    long WEIGHT = 256;

    ValueQuery uQ = qf.valueQuery();

    ValueQuery[] valueQueryArray = new ValueQuery[tokenArray.length];

    if (tokenArray.length > 1)
    {
      for (int i = 0; i < tokenArray.length; i++)
      {
        String token = tokenArray[i].toLowerCase();
        valueQueryArray[i] = buildQueryForToken(qf, token, selectableArray, conditionArray, WEIGHT, i);
      }
      uQ.UNION(valueQueryArray);
    }
    else
    {
      uQ = buildQueryForToken(qf, tokenArray[0].toLowerCase(), selectableArray, conditionArray, WEIGHT, 0);
    }

    // Build outermost select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    ValueQuery resultQuery = qf.valueQuery();
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 2];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = uQ.aAttribute(selectableArray[k].getResultAttributeName());
    }
    selectClauseArray[selectableArray.length] = F.COUNT(uQ.aAttribute("weight"), "weight");
    selectClauseArray[selectableArray.length + 1] = F.SUM(uQ.aAttribute("weight"), "sum");

    resultQuery.SELECT(selectClauseArray);
    resultQuery.ORDER_BY_DESC(F.COUNT(uQ.aAttribute("weight"), "weight"));
    resultQuery.ORDER_BY_DESC(F.SUM(uQ.aAttribute("weight"), "sum"));
    for (SelectablePrimitive selectable : selectableArray)
    {
      resultQuery.ORDER_BY_ASC((AttributePrimitive) uQ.aAttribute(selectable.getResultAttributeName()));
    }
    resultQuery.HAVING(F.COUNT(uQ.aAttribute("weight")).EQ(tokenArray.length));
    System.out.println(resultQuery.getSQL());

    for (ValueObject valueObject : resultQuery.getIterator())
    {
      valueObject.printAttributes();
    }

    return resultQuery;
  }

  private static ValueQuery buildQueryForToken(QueryFactory qf, String token, SelectablePrimitive[] selectableArray, Condition[] conditionArray, long WEIGHT, int i)
  {
    ValueQuery vQ = qf.valueQuery();

    // Build select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 1];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = selectableArray[k];
    }
    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(WEIGHT, i) + " * STRPOS(" + concatenate(selectableArray) + ", ' " + token + "'))");

    vQ.SELECT(selectClauseArray);
    vQ.WHERE(vQ.aSQLCharacter("fields", concatenate(selectableArray)).LIKE("% " + token + "%"));

    for (Condition condition : conditionArray)
    {
      vQ.AND(condition);
    }
    return vQ;
  }

  private static String concatenate(Selectable[] selectableArray)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("LOWER(' ' || ");
    for (int i = 0; i < selectableArray.length; i++)
    {
      if (i > 0)
      {
        sb.append(" || ' ' || ");
      }
      sb.append(selectableArray[i].getQualifiedName());
    }
    sb.append(")");
    return sb.toString();
  }
}
