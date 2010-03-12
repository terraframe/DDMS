package dss.vector.solutions.query;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.terraframe.mojo.MojoException;
import com.terraframe.mojo.business.SmartException;
import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.AttributePrimitive;
import com.terraframe.mojo.query.COUNT;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.DISTINCT;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.Join;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.util.QueryUtil;

public class QueryBuilder extends QueryBuilderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255379414351L;

  public QueryBuilder()
  {
    super();
  }

  public static ValueQuery getValueQuery(String queryClass, String queryXML, String config, Layer layer)
  {
    Class<?> clazz = null;

    Method xmlToValueQuery = null;

    ValueQuery valueQuery = null;
    try
    {
      clazz = Class.forName(queryClass);
      xmlToValueQuery = clazz.getMethod("xmlToValueQuery", String.class, String.class, Layer.class);
      valueQuery = (ValueQuery) xmlToValueQuery.invoke(clazz, queryXML, config, layer);
    }
    catch (InvocationTargetException e)
    {
      Throwable target = e.getTargetException();
      if (target instanceof MojoException)
      {
        MojoException fwEx = (MojoException) target;
        throw fwEx;
      }
      else if (target instanceof SmartException)
      {
        throw (SmartException) target;
      }
      else
      {
        ProgrammingErrorException ex = new ProgrammingErrorException(e);
        throw ex;
      }
    }
    catch (Throwable t)
    {
      ProgrammingErrorException ex = new ProgrammingErrorException(t);
      throw ex;
    }
    
    validateDateSelectables(valueQuery);
    
    return valueQuery;
  }
  
  /**
   * Ensures that the ValueQuery contains more than the start and end date criteria.
   * 
   * @param valueQuery
   */
  public static void validateDateSelectables(ValueQuery valueQuery)
  {
    // Start and End date can only be selected if other Selectables added
    // to create a meaninful query.
    List<Selectable> selectables = valueQuery.getSelectableRefs();
    boolean hasOnlyDates = false;
    if(selectables.size() == 1)
    {
      String alias = selectables.get(0).getUserDefinedAlias();
      if(QueryUtil.START_DATE_RANGE.equals(alias) || QueryUtil.END_DATE_RANGE.equals(alias))
      {
        hasOnlyDates = true;
      }
    }
    else if(selectables.size() == 2)
    {
      boolean isStart = false;
      boolean isEnd = false;
      
      for(Selectable sel : selectables)
      {
        String alias = sel.getUserDefinedAlias();
        if(QueryUtil.START_DATE_RANGE.equals(alias))
        {
          isStart = true;
        }
        else if(QueryUtil.END_DATE_RANGE.equals(alias))
        {
          isEnd = true;
        }
      }
      
      hasOnlyDates = isStart && isEnd;
    }
    
    if(hasOnlyDates)
    {
      String error = "The start and end date must be added with other selectables.";
      throw new DatesOnlyException(error);
    }
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
    ValueQuery valueQuery = getValueQuery(queryClass, queryXML, config, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryClass, String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = getValueQuery(queryClass, queryXML, config, null);

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

    ValueQuery query = getValueQuery(queryClass, queryXML, config, null);
    
    DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT, Session.getCurrentLocale());

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query, dateFormat, null, null);
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

    valueQuery.SELECT(attribSelectable, count);

    String table = MdBusiness.getMdBusiness(klass).getTableName();

    valueQuery.FROM(table, "auto_complete");

    valueQuery.WHERE(attribSelectable.LIKEi(match + "%"));

    valueQuery.ORDER_BY_DESC(count);

    valueQuery.restrictRows(20, 1);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

  public static void textLookup(ValueQuery valueQuery, QueryFactory qf, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    QueryBuilder.textLookup(valueQuery, qf, tokenArray, searchableArray, selectableArray, conditionArray, new Join[]{});
  }
  
  public static void textLookup(ValueQuery valueQuery, QueryFactory qf, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray, Join[] joins)
  {
    long WEIGHT = 256;

    ValueQuery uQ = qf.valueQuery();

    ValueQuery[] valueQueryArray = new ValueQuery[tokenArray.length];

    if (tokenArray.length > 1)
    {
      for (int i = 0; i < tokenArray.length; i++)
      {
        String token = tokenArray[i].toLowerCase();
        valueQueryArray[i] = buildQueryForToken(qf, token, searchableArray, selectableArray, conditionArray, joins, WEIGHT, i);
      }
      uQ.UNION(valueQueryArray);
    }
    else
    {
      uQ = buildQueryForToken(qf, tokenArray[0].toLowerCase(), searchableArray, selectableArray, conditionArray, joins, WEIGHT, 0);
    }

    // Build outermost select clause. This would be cleaner if the API supported
    // incrementally adding to the select clause. One day that will be
    // supported.
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 2];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = uQ.get(selectableArray[k].getResultAttributeName());
    }
    selectClauseArray[selectableArray.length] = F.COUNT(uQ.get("weight"), "weight");
    selectClauseArray[selectableArray.length + 1] = F.SUM(uQ.get("weight"), "sum");

    valueQuery.SELECT(selectClauseArray);
    valueQuery.ORDER_BY_DESC(F.COUNT(uQ.get("weight"), "weight"));
    valueQuery.ORDER_BY_DESC(F.SUM(uQ.get("weight"), "sum"));
    
    for (SelectablePrimitive selectable : selectableArray)
    {
      valueQuery.ORDER_BY_ASC((AttributePrimitive) uQ.get(selectable.getResultAttributeName()));
    }
    
    valueQuery.HAVING(F.COUNT(uQ.get("weight")).EQ(tokenArray.length));

//    for (ValueObject valueObject : valueQuery.getIterator())
//    {
//      valueObject.printAttributes();
//    }
  }

  private static ValueQuery buildQueryForToken(QueryFactory qf, String token, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray, Join[] joins, long weight, int i)
  {
    ValueQuery vQ = qf.valueQuery();
    
    token = token.replace("%", "!%");

    // Build select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    SelectablePrimitive[] selectClauseArray = new SelectablePrimitive[selectableArray.length + 1];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = selectableArray[k];
    }
    
    String sql = Database.instance().escapeSQLCharacters(token);
    
    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(weight, i) + " * NULLIF(STRPOS(" + concatenate(searchableArray) + ", ' " + sql  + "'),0))");
    vQ.SELECT(new DISTINCT(selectClauseArray));
    vQ.WHERE(vQ.aSQLCharacter("fields", concatenate(searchableArray)).LIKE("% " + sql + "%"));

    for (Condition condition : conditionArray)
    {
      vQ.AND(condition);
    }
    
    for (Join join : joins)
    {
      vQ.AND(join);
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

  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions)
  {
    QueryBuilder.orderedLookup(query, factory, orderBy, selectables, conditions, new Join[]{});
  }
  
  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    Condition condition = null;
    
    for (Condition cond : conditions)
    {
      condition = (condition == null) ? cond : AND.get(condition, cond);
    }
    
    query.SELECT(selectables);
    query.WHERE(condition);    
    
    for (Join join : joins)
    {
      query.AND(join);
    }
    
    query.ORDER_BY_ASC(orderBy);
  }
  
}
