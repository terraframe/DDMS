package dss.vector.solutions.query;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

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
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.COUNT;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryCSVExporter;
import com.runwaysdk.query.ValueQueryExcelExporter;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.MdssLog;
import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.irs.TeamMemberQuery;
import dss.vector.solutions.irs.TeamMemberView;
import dss.vector.solutions.querybuilder.DistinctOrderedLookupBuilder;
import dss.vector.solutions.querybuilder.OrderedLookupBuilder;
import dss.vector.solutions.querybuilder.TextLookupBuilder;
import dss.vector.solutions.util.QueryUtil;

public class QueryBuilder extends QueryBuilderBase implements Reloadable
{
  private static final long serialVersionUID = 1255379414351L;

  public QueryBuilder()
  {
    super();
  }

  /**
   * Creates a LinkedHashSet of the aliases to preserve order.
   * 
   * @param config
   * @return
   */
  private static LinkedHashSet<String> getAliases(String config)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
      JSONArray aliases = queryConfig.getJSONArray("sortOrder");
      LinkedHashSet<String> set = new LinkedHashSet<String>();
      for (int i = 0; i < aliases.length(); i++)
      {
        set.add(aliases.getString(i));
      }

      return set;
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
  }

  public static ValueQuery getValueQuery(String queryClass, String queryXML, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    Class<?> clazz = null;

    Method xmlToValueQuery = null;

    ValueQuery valueQuery = null;
    try
    {
      // TODO instantiate querybuilder classes directly and remove static calls
      // from domain classes
      clazz = Class.forName(queryClass);
      xmlToValueQuery = clazz.getMethod("xmlToValueQuery", String.class, String.class, Layer.class, Integer.class, Integer.class, Disease.class);
      valueQuery = (ValueQuery) xmlToValueQuery.invoke(clazz, queryXML, config, layer, pageNumber, pageSize, disease);
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
      for (String alias : getAliases(config))
      {
        Selectable sel = valueQuery.getSelectableRef(alias);

        if (sel instanceof SelectablePrimitive)
        {
          valueQuery.ORDER_BY_ASC((SelectablePrimitive) sel);
        }
      }
    }

    // IMPORTANT: Keep this debug here because the generated SQL is
    // required for even the most basic query screen troubleshooting.
    if (MdssLog.isDebugEnabled())
    {
      String sql = valueQuery.getSQL();
      MdssLog.debug(sql);
    }

    return valueQuery;
  }

  /**
   * Queries
   * 
   * @param xml
   * @throws Throwable
   */
  @Authenticate
  public static com.runwaysdk.query.ValueQuery getQueryResults(String queryClass, String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = getValueQuery(queryClass, queryXML, config, null, pageNumber, pageSize, null);

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

    ValueQuery query = getValueQuery(queryClass, queryXML, config, null, null, null, null);

    Set<String> aliases = getAliases(config);
    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName(), aliases);
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

    ValueQuery query = getValueQuery(queryClass, queryXML, config, null, null, null, null);

    DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT, Session.getCurrentLocale());

    Set<String> aliases = getAliases(config);
    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query, dateFormat, null, null, aliases);
    return exporter.exportStream();
  }

  @Authenticate
  public static ValueQuery getTextAttributeSugestions(String match, String klass, String attribute)
  {
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // Searching on Person for IRS has special logic
    if (Person.CLASS.equals(klass) && attribute.endsWith("_" + Person.IDENTIFIER))
    {
      String[] parts = attribute.split("_");
      String prepend = parts[0];
      String attr = parts[1];

      PersonQuery p = new PersonQuery(queryFactory);

      SelectableChar attrSel = p.getIdentifier("attribute");
      COUNT count = F.COUNT(attrSel, "attributeCount");
      valueQuery.SELECT(attrSel, count);

      valueQuery.WHERE(attrSel.LIKEi(match + "%"));

      valueQuery.ORDER_BY_ASC(attrSel);

      valueQuery.restrictRows(20, 1);

      return valueQuery;
    }
    // IRS QB special case for searching on Spray Operator, which doesn't follow
    // the same logic as normal attribute searching. There is also special logic
    // when searching for
    else if (TeamMember.CLASS.equals(klass) && TeamMember.PERSON.equals(attribute))
    {
      PersonQuery personQuery = new PersonQuery(valueQuery);
      TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);

      CONCAT concat = F.CONCAT(F.CONCAT(leaderQuery.getMemberId(TeamMemberView.MEMBERID), " - "), F.CONCAT(F.CONCAT(personQuery.getFirstName(TeamMemberView.FIRSTNAME), " "), personQuery.getLastName(TeamMemberView.LASTNAME)), "attribute", "attribute");
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
      MdEntityDAOIF md = MdEntityDAO.getMdEntityDAO(klass);

      // check if we're searching on MdForm business artifacts or hard-coded
      // class. This condition is a little hacky and might need to be split into
      // different searches at some point, specificaly a separate method just
      // for MdForms.
      if (md.getPackage().equals(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE) && attribute != null && attribute.equals(MdFormUtil.OID))
      {
        MdAttributeDAOIF attr = md.definesAttribute(MdFormUtil.OID);

        Selectable runwayId = valueQuery.aSQLCharacter("runwayId", QueryUtil.getIdColumn());
        SelectableSQLCharacter formId = valueQuery.aSQLCharacter("formId", attr.getColumnName());

        valueQuery.SELECT(runwayId, formId);

        String table = md.getTableName();

        valueQuery.FROM(table, "auto_complete");

        valueQuery.WHERE(formId.LIKEi(match + "%"));

        valueQuery.ORDER_BY_ASC(formId);
      }
      else
      {
        // The attribute may be in the form of attribute1.attribute2.attribute3,
        // etc to represent a chain of attribute dependencies. This needs to be
        // dereferenced.
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
  }

  public static void textLookup(ValueQuery valueQuery, QueryFactory qf, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    new TextLookupBuilder(valueQuery, qf, tokenArray, searchableArray, selectableArray, conditionArray).buildQuery();
  }

  public static void textLookup(ValueQuery valueQuery, QueryFactory qf, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray, Join[] joins)
  {
    new TextLookupBuilder(valueQuery, qf, tokenArray, searchableArray, selectableArray, conditionArray, joins).buildQuery();
  }

  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions)
  {
    new OrderedLookupBuilder(query, orderBy, selectables, conditions).buildQuery();
  }

  public static void orderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    new OrderedLookupBuilder(query, orderBy, selectables, conditions, joins).buildQuery();
  }

  public static void distinctOrderedLookup(ValueQuery query, QueryFactory factory, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    new DistinctOrderedLookupBuilder(query, orderBy, selectables, conditions, joins).buildQuery();
  }
}
