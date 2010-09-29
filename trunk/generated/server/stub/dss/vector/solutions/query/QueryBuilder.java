package dss.vector.solutions.query;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.AttributePrimitive;
import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.COUNT;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryCSVExporter;
import com.runwaysdk.query.ValueQueryExcelExporter;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.irs.TeamMemberQuery;
import dss.vector.solutions.irs.TeamMemberView;
import dss.vector.solutions.util.QueryUtil;

public class QueryBuilder extends QueryBuilderBase implements com.runwaysdk.generation.loader.Reloadable
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
      if (target instanceof RunwayException)
      {
        RunwayException fwEx = (RunwayException) target;
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

    // Enforce a sort order if not mapping
    if (layer == null)
    {
      JSONObject queryConfig;
      try
      {
        queryConfig = new JSONObject(config);
        JSONArray sortOrder = queryConfig.getJSONArray("sortOrder");
        for (int i = 0; i < sortOrder.length(); i++)
        {
          String alias = sortOrder.getString(i);
          Selectable sel = valueQuery.getSelectableRef(alias);

          if (sel instanceof SelectablePrimitive)
          {
            valueQuery.ORDER_BY_ASC((SelectablePrimitive) sel);
          }
        }
      }
      catch (JSONException e1)
      {
        throw new ProgrammingErrorException(e1);
      }
    }

    // IMPORTANT: Keep this debug here because the generated SQL is
    // required for even the most basic query screen troubleshooting.
    MdssLog.debug(valueQuery.getSQL());

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
  public static com.runwaysdk.query.ValueQuery getQueryResults(String queryClass, String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
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

    // IRS QB special case for searching on Spray Operator, which doesn't follow
    // the same logic as normal attribute searching.
    if (TeamMember.CLASS.equals(klass) && TeamMember.PERSON.equals(attribute))
    {
      PersonQuery personQuery = new PersonQuery(valueQuery);
      TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);

      CONCAT concat = F.CONCAT(F.CONCAT(F.CONCAT(F.CONCAT(personQuery.getFirstName(TeamMemberView.FIRSTNAME), " "), personQuery.getLastName(TeamMemberView.LASTNAME)), " - "), leaderQuery.getMemberId(TeamMemberView.MEMBERID), "attribute", "attribute");
      COUNT count = F.COUNT(concat, "attributeCount", "attributeCount");

      SelectablePrimitive[] selectables = new SelectablePrimitive[] { concat, count, leaderQuery.getMemberId(TeamMemberView.MEMBERID), personQuery.getFirstName(TeamMemberView.FIRSTNAME), personQuery.getLastName(TeamMemberView.LASTNAME) };

      if (match != null && !match.equals(""))
      {
        valueQuery.SELECT(selectables);

        String[] tokens = match.split(" ");

        Condition condition = null;

        for (String token : tokens)
        {
          String value = token + "%";

          if (condition == null)
          {
            condition = OR.get(leaderQuery.getMemberId(TeamMemberView.MEMBERID).LIKEi(value), personQuery.getFirstName(TeamMemberView.FIRSTNAME).LIKEi(value), personQuery.getLastName(TeamMemberView.LASTNAME).LIKEi(value));
          }
          else
          {
            condition = OR.get(condition, leaderQuery.getMemberId(TeamMemberView.MEMBERID).LIKEi(value), personQuery.getFirstName(TeamMemberView.FIRSTNAME).LIKEi(value), personQuery.getLastName(TeamMemberView.LASTNAME).LIKEi(value));
          }
        }

        valueQuery.WHERE(AND.get(leaderQuery.getIsSprayOperator().EQ(true), personQuery.getTeamMemberDelegate().EQ(leaderQuery), condition));

        valueQuery.ORDER_BY_DESC(count);
      }
      else
      {
        Condition[] conditions = new Condition[] { leaderQuery.getIsSprayOperator().EQ(true), personQuery.getTeamMemberDelegate().EQ(leaderQuery) };
        QueryBuilder.orderedLookup(valueQuery, queryFactory, personQuery.getFirstName(TeamMemberView.FIRSTNAME), selectables, conditions);
      }

      valueQuery.restrictRows(20, 1);

      return valueQuery;
    }
    else
    {
      // The attribute may be in the form of attribute1.attribute2.attribute3,
      // etc to represent a chain of attribute dependencies. This needs to be
      // dereferenced.
      MdEntityDAOIF md = MdEntityDAO.getMdEntityDAO(klass);
      String searchAttribute = attribute;

      if (attribute.contains("."))
      {
        String[] attrs = attribute.split("\\.");
        for (int i = 0; i < attrs.length; i++)
        {
          searchAttribute = attrs[i];

          MdAttributeDAOIF attrMd = md.definesAttribute(searchAttribute);
          if (attrMd instanceof MdAttributeReferenceDAOIF)
          {
            // This should only be valid for MdEntities so this downcast is
            // valid.
            md = (MdEntityDAOIF) ( (MdAttributeReferenceDAOIF) attrMd ).getReferenceMdBusinessDAO();
          }
          else if (i != attrs.length - 1)
          {
            String error = "The attribute [" + attribute + "] on type [" + klass + "] is not valid for chaining.";
            throw new ProgrammingErrorException(error);
          }
        }
      }

      String attrCol = QueryUtil.getColumnName(md.definesType(), searchAttribute);
      SelectableSQLCharacter attribSelectable = valueQuery.aSQLCharacter("attribute", attrCol);

      COUNT count = F.COUNT(attribSelectable, "attributeCount");

      valueQuery.SELECT(attribSelectable, count);

      String table = md.getTableName();

      valueQuery.FROM(table, "auto_complete");

      valueQuery.WHERE(attribSelectable.LIKEi(match + "%"));

      valueQuery.ORDER_BY_DESC(count);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  public static void textLookup(ValueQuery valueQuery, QueryFactory qf, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    QueryBuilder.textLookup(valueQuery, qf, tokenArray, searchableArray, selectableArray, conditionArray, new Join[] {});
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

    // for (ValueObject valueObject : valueQuery.getIterator())
    // {
    // valueObject.printAttributes();
    // }
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

    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(weight, i) + " * NULLIF(STRPOS(" + concatenate(searchableArray) + ", ' " + sql + "'),0))");
    vQ.SELECT_DISTINCT(selectClauseArray);
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

      // IMPORTANT: The selectable may not be required, as such we must COALESCE
      // the selectable with the empty string in order for rows with NULL values
      // to work.
      sb.append("COALESCE(" + selectableArray[i].getDbQualifiedName() + ",'')");
    }

    sb.append(")");
    return sb.toString();
  }

  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions)
  {
    QueryBuilder.orderedLookup(query, factory, orderBy, selectables, conditions, new Join[] {});
  }

  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    Condition condition = null;

    for (Condition cond : conditions)
    {
      condition = ( condition == null ) ? cond : AND.get(condition, cond);
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
