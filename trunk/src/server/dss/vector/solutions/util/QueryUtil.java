package dss.vector.solutions.util;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXParseException;

import com.terraframe.mojo.constants.RelationshipInfo;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.AttributeMoment;
import com.terraframe.mojo.query.AttributeReference;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.Function;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableChar;
import com.terraframe.mojo.query.SelectableMoment;
import com.terraframe.mojo.query.SelectableNumber;
import com.terraframe.mojo.query.SelectableReference;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.SelectableSQLCharacter;
import com.terraframe.mojo.query.SelectableSQLDate;
import com.terraframe.mojo.query.SelectableSingle;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryParser;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;
import com.terraframe.mojo.system.metadata.MdEntity;
import com.terraframe.mojo.system.metadata.MdRelationship;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.NoColumnsAddedException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.query.ThematicVariable;

public class QueryUtil implements Reloadable
{

  private static final String DATEGROUP_EPIWEEK = "dategroup_epiweek";

  private static final String DATEGROUP_MONTH   = "dategroup_month";

  private static final String DATEGROUP_QUARTER = "dategroup_quarter";

  private static final String DATEGROUP_YEAR    = "dategroup_year";

  private static final String DATEGROUP_SEASON  = "dategroup_season";

  private static final String START_DATE_RANGE  = "start_date_range";

  private static final String RATIO             = "ratio_of_this_row_to_total_count";

  private static final String END_DATE_RANGE    = "end_date_range";

  private static final String DATE_ATTRIBUTE    = "date_attribute";

  private static final String DATE_REGEX        = "\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d";

  public static String getRelationshipTermSubSelect(String attribute, String parentClass, String relClass)
  {
    String parentTable = MdBusiness.getMdBusiness(parentClass).getTableName();
    String relTable = MdEntity.getMdEntity(relClass).getTableName();
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();

    return "(select pJoin.id AS id, tJoin." + Term.NAME + " as " + attribute + "_displayLabel from" + " " + parentTable + " AS pJoin LEFT JOIN " + relTable + " rJoin ON rJoin." + RelationshipInfo.PARENT_ID + " = pJoin.id" + " LEFT JOIN " + termTable
        + " tJoin on rJoin." + RelationshipInfo.CHILD_ID + " = tJoin.id)";
  }

  public static ValueQuery joinTermAllpaths(ValueQuery valueQuery, String klass, GeneratedEntityQuery query)
  {
    if (query != null)
    {
      String[] personAttributes = Term.getTermAttributes(klass);
      String sql = "(" + QueryUtil.getTermSubSelect(klass, personAttributes) + ")";
      String subSelect = klass.replace('.', '_') + "TermSubSel";
      String table = MdBusiness.getMdBusiness(klass).getTableName();
      valueQuery.AND(new InnerJoinEq("id", table, query.getTableAlias(), "id", sql, subSelect));
    }
    return valueQuery;

  }

  public static ValueQuery joinGeoDisplayLabels(ValueQuery valueQuery, String klass, GeneratedEntityQuery query)
  {
    if (query != null)
    {
      String[] geoAttributes = GeoEntity.getGeoAttributes(klass);
      String sql = "(" + QueryUtil.getGeoDisplayLabelSubSelect(klass, geoAttributes) + ")";
      String subSelect = klass.replace('.', '_') + "GeoSubSel";
      String table = MdBusiness.getMdBusiness(klass).getTableName();
      valueQuery.AND(new InnerJoinEq("id", table, query.getTableAlias(), "id", sql, subSelect));
    }
    return valueQuery;

  }

  public static ValueQuery getSingleAttribteGridSql(ValueQuery valueQuery, String tableAlias)
  {
    for (Selectable s : Arrays.asList(valueQuery.getSelectables()))
    {
      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      if (s instanceof SelectableSQL)
      {
        String gridAlias = s.getUserDefinedAlias();
        int index1 = gridAlias.indexOf("__");
        int index2 = gridAlias.lastIndexOf("__");
        if (index1 > 0 && index2 > 0)
        {
          String attrib = gridAlias.substring(0, index1);
          String klass = gridAlias.substring(index1 + 2, index2).replace("_", ".");
          String term_id = gridAlias.substring(index2 + 2, gridAlias.length());
          
          String table = MdRelationship.getMdEntity(klass).getTableName();
          
          String sql = "SELECT " + attrib + " FROM " + table + " WHERE child_id = '" + term_id + "' " + "AND parent_id = " + tableAlias + ".id";
          
          ( (SelectableSQL) s ).setSQL(sql);
        }
      }
    }
    return valueQuery;
  }

  

  public static ValueQuery setTermRestrictions(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    for (String entityAlias : queryMap.keySet())
    {
      int index1 = entityAlias.indexOf("__");
      int index2 = entityAlias.lastIndexOf("__");
      if (index1 > 0 && index2 > 0)
      {
        String attrib_name = entityAlias.substring(0, index1);
        String klass = entityAlias.substring(index1 + 2, index2).replace("_", ".");
        String attrib_alias = entityAlias.substring(index2 + 2, entityAlias.length());
        String table = MdRelationship.getMdEntity(klass).getTableName();
        if (queryMap.get(entityAlias) instanceof dss.vector.solutions.ontology.AllPathsQuery)
        {
          dss.vector.solutions.ontology.AllPathsQuery allPathsQuery = (dss.vector.solutions.ontology.AllPathsQuery) queryMap.get(entityAlias);
          String allPathsTable = MdRelationship.getMdEntity(dss.vector.solutions.ontology.AllPaths.CLASS).getTableName();
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);
          valueQuery.AND(new InnerJoinEq(attrib_name, table, attributeQuery.getTableAlias(), dss.vector.solutions.ontology.AllPaths.CHILDTERM, allPathsTable, allPathsQuery.getTableAlias()));
        }
      }

    }
    return null;
  }

  public static ValueQuery setNumericRestrictions(ValueQuery valueQuery, JSONObject queryConfig)
  {
    for (Iterator<String> iter = queryConfig.keys(); iter.hasNext();)
    {
      String key = (String) iter.next();
      Pattern pattern = Pattern.compile("^(\\w+)Criteria$");
      Matcher matcher = pattern.matcher(key);
      String attributeName = null;
      if (matcher.find())
      {
        attributeName = matcher.group(1);

        try
        {
          String value = queryConfig.getString(key);
          Selectable sel = valueQuery.getSelectable(attributeName);

          while (sel instanceof Function)
          {
            Function f = (Function) sel;
            sel = f.getSelectable();
          }

          if (value.contains("-"))
          {
            String[] range = value.split("-");
            if (range.length == 2)
            {
              String range1 = range[0];
              String range2 = range[1];
              if (range1.length() > 0)
              {
                valueQuery.WHERE( ( (SelectableNumber) sel ).GE(range1));
              }

              if (range2.length() > 0)
              {
                valueQuery.WHERE( ( (SelectableNumber) sel ).LE(range2));
              }
            }
            else
            {
              // Just the GE criteria was specified (e.g., "7-")
              valueQuery.WHERE( ( (SelectableNumber) sel ).GE(range[0]));
            }
          }
          else
          {
            // exact value
            if (sel instanceof SelectableNumber && !value.equals("NULL"))
            {
              valueQuery.WHERE(sel.EQ(value));
            }
            if (sel instanceof SelectableChar && !value.equals("NULL"))
            {
              valueQuery.WHERE( ( (SelectableChar) sel ).LIKE(value));
            }

          }

        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          // e.printStackTrace();
        }
      }
    }
    return valueQuery;
  }

  public static String getTermSubSelect(String className, String... attributes)
  {
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
    String tableName = MdBusiness.getMdBusiness(className).getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;

    int count = 0;
    for (String attr : attributes)
    {
      select += " term" + count + "." + Term.NAME + " as " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      from += " LEFT JOIN " + termTable + " as term" + count + " on " + tableName + "." + attr + " = term" + count + ".id";

      count++;
    }

    String sql = select + from;

    return sql;
  }

  public static String getGeoDisplayLabelSubSelect(String className, String... attributes)
  {
    String geoView = "geo_displayLabel";
    String tableName = MdBusiness.getMdBusiness(className).getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;

    int count = 0;
    for (String attr : attributes)
    {
      select += " geo" + count + ".displayLabel as " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      from += " LEFT JOIN " + geoView + " as geo" + count + " on " + tableName + "." + attr + " = geo" + count + ".id";

      count++;
    }

    String sql = select + from;

    return sql;
  }

  /**
   * Joins the ValueQuery with any selected/restricting geo entity information.
   * This method does not perform the final join between the AllPathsQuery and
   * the GeneratedEntityQuery that exists in the calling code.
   * 
   * @param queryFactory
   * @param valueQuery
   * @param xml
   * @param thematicLayer
   * @param includeGeometry
   * @param selectedUniversals
   * @return
   */
  public static Map<String, GeneratedEntityQuery> joinQueryWithGeoEntities(QueryFactory queryFactory, ValueQuery valueQuery, String xml, JSONObject config, ThematicLayer thematicLayer, boolean includeGeometry, String generatedQueryClass,
      String geoEntityAttribute)
  {
    ValueQueryParser valueQueryParser;
    Map<String, GeneratedEntityQuery> queryMap;

    try
    {
      valueQueryParser = new ValueQueryParser(xml, valueQuery);
    }
    catch (QueryException e)
    {
      // Check if the error was because no selectables were added.
      Throwable t = e.getCause();
      if (t != null && t instanceof SAXParseException && t.getMessage().contains("{selectable}"))
      {
        NoColumnsAddedException ex = new NoColumnsAddedException();
        throw ex;
      }
      else
      {
        throw e;
      }
    }

    // include the thematic variable (if applicable).
    if (thematicLayer != null)
    {
      ThematicVariable thematicVariable = thematicLayer.getThematicVariable();
      if (thematicVariable != null)
      {
        String entityAlias = thematicVariable.getEntityAlias();
        String userAlias = thematicVariable.getUserAlias();

        valueQueryParser.setColumnAlias(entityAlias, userAlias, QueryConstants.THEMATIC_DATA_COLUMN);
      }
    }

    // FIXME does not take into account multiple geo attributes
    if (includeGeometry)
    {
      /*
       * Note that the mapping query does not need to perform the complex left
       * join logic. This is because the entity name, geo id selectables on
       * different universal types will not affect the mapping result, so they
       * are omitted.
       */

      MdBusiness geoEntityMd = thematicLayer.getGeoHierarchy().getGeoEntityClass();
      String thematicLayerType = geoEntityMd.definesType();

      MdAttributeGeometry mdAttrGeo = GeoHierarchy.getGeometry(geoEntityMd);
      String attributeName = mdAttrGeo.getAttributeName();

      valueQueryParser.addAttributeSelectable(thematicLayerType, attributeName, attributeName, QueryConstants.GEOMETRY_NAME_COLUMN);
      valueQueryParser.addAttributeSelectable(thematicLayerType, GeoEntity.ENTITYNAME, GeoEntity.ENTITYNAME, QueryConstants.ENTITY_NAME_COLUMN);

      queryMap = valueQueryParser.parse();

      // exclude any entity without spatial data
      Selectable geometrySelectable = valueQuery.getSelectable(attributeName);
      valueQuery.AND(geometrySelectable.NE(null));

      AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(AllPaths.CLASS);
      GeoEntityQuery geoEntityQuery = (GeoEntityQuery) queryMap.get(thematicLayerType);
      GeneratedEntityQuery generatedEntityQuery = queryMap.get(generatedQueryClass);

      if (allPathsQuery == null)
      {
        // this case is for when they have not restricted to a specific
        // geoEntity
        allPathsQuery = new AllPathsQuery(queryFactory);

        // find all the parents that are of the type of the thematicLayer
        valueQuery.WHERE(allPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

        // find all the rows where the children match geoEntities of the
        // children we are looking for.
        valueQuery.AND( ( (AttributeReference) generatedEntityQuery.aAttribute(geoEntityAttribute) ).EQ(allPathsQuery.getChildGeoEntity()));

      }
      else
      {

        // This is the case for where we are restricting by one or more entitys

        // first we make a seond all paths query
        AllPathsQuery allPathsParent = new AllPathsQuery(queryFactory);

        // we narrow down the secoond all paths table to just rows that match
        // entity restrictions
        valueQuery.WHERE(allPathsParent.getParentGeoEntity().EQ(geoEntityQuery));
        // we join the second all paths to the primary all paths
        valueQuery.WHERE(allPathsParent.getChildGeoEntity().EQ(allPathsQuery.getChildGeoEntity()));
        // we join the primary all paths to the md business which is located at
        // a certain geoEntity
        valueQuery.AND( ( (AttributeReference) generatedEntityQuery.aAttribute(geoEntityAttribute) ).EQ(allPathsQuery.getChildGeoEntity()));

      }

    }
    else
    {
      // Normal query (non-mapping)
      Map<String, List<ValueQuery>> attributeKeysAndJoins = new HashMap<String, List<ValueQuery>>();
      try
      {
        JSONObject selectedUniMap = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
        Iterator<?> keys = selectedUniMap.keys();
        while (keys.hasNext())
        {
          String attributeKey = (String) keys.next();

          JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
          if (universals.length() > 0)
          {
            String[] selectedUniversals = new String[universals.length()];
            for (int i = 0; i < universals.length(); i++)
            {
              selectedUniversals[i] = universals.getString(i);
            }

            List<ValueQuery> leftJoinValueQueries = addUniversalsForAttribute(queryFactory, attributeKey, selectedUniversals, valueQueryParser);
            attributeKeysAndJoins.put(attributeKey, leftJoinValueQueries);
          }
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }

      queryMap = valueQueryParser.parse();

      for (String attributeKey : attributeKeysAndJoins.keySet())
      {
        AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(AllPaths.CLASS + "_" + attributeKey);
        List<ValueQuery> leftJoinValueQueries = attributeKeysAndJoins.get(attributeKey);

        restrictEntitiesForAttribute(attributeKey, allPathsQuery, leftJoinValueQueries, valueQuery, queryMap);
      }

    }

    return queryMap;
  }

  private static List<ValueQuery> addUniversalsForAttribute(QueryFactory queryFactory, String attributeKey, String[] selectedUniversals, ValueQueryParser valueQueryParser)
  {
    List<ValueQuery> leftJoinValueQueries = new LinkedList<ValueQuery>();
    for (String selectedGeoEntityType : selectedUniversals)
    {
      GeoEntityQuery geoEntityQuery = new GeoEntityQuery(queryFactory);

      AllPathsQuery subAllPathsQuery = new AllPathsQuery(queryFactory);
      ValueQuery geoEntityVQ = new ValueQuery(queryFactory);
      MdBusinessDAOIF geoEntityMd = MdBusinessDAO.getMdBusinessDAO(selectedGeoEntityType);

      String prepend = attributeKey.replaceAll("\\.", "_") + "__";
      String entityNameAlias = prepend + geoEntityMd.getTypeName().toLowerCase() + "_" + GeoEntityView.ENTITYNAME;
      String geoIdAlias = prepend + geoEntityMd.getTypeName().toLowerCase() + "_" + GeoEntityView.GEOID;

      Selectable selectable1 = geoEntityQuery.getEntityName(entityNameAlias);
      Selectable selectable2 = geoEntityQuery.getGeoId(geoIdAlias);
      SelectableReference selectable3 = subAllPathsQuery.getChildGeoEntity("child_id");

      geoEntityVQ.SELECT(selectable1, selectable2, selectable3);

      List<MdBusinessDAOIF> allClasses = geoEntityMd.getAllSubClasses();
      Condition[] geoConditions = new Condition[allClasses.size()];
      for (int i = 0; i < allClasses.size(); i++)
      {
        geoConditions[i] = subAllPathsQuery.getParentUniversal().EQ(allClasses.get(i));
      }

      geoEntityVQ.WHERE(OR.get(geoConditions));
      geoEntityVQ.AND(subAllPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

      leftJoinValueQueries.add(geoEntityVQ);

      valueQueryParser.setValueQuery(attributeKey + "__" + selectedGeoEntityType, geoEntityVQ);
    }

    return leftJoinValueQueries;
  }

  private static void restrictEntitiesForAttribute(String attributeKey, AllPathsQuery allPathsQuery, List<ValueQuery> leftJoinValueQueries, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    if (allPathsQuery == null && leftJoinValueQueries.size() > 0)
    {
      // This case is for when they have not restricted by any specific
      // geoEntity
      allPathsQuery = new AllPathsQuery(valueQuery.getQueryFactory());
      valueQuery.FROM(allPathsQuery);
      // we use the country universial to restrict the cross product
      valueQuery.AND(allPathsQuery.getParentUniversal().EQ(MdBusiness.getMdBusiness(Country.CLASS).getId()));
    }

    if (allPathsQuery != null)
    {
      // this case is for when they have restricted to a specific geoEntity
      List<SelectableSingle> leftJoinSelectables = new LinkedList<SelectableSingle>();
      for (ValueQuery leftJoinVQ : leftJoinValueQueries)
      {
        leftJoinSelectables.add(leftJoinVQ.aReference("child_id"));
      }

      int size = leftJoinSelectables.size();
      if (size > 0)
      {
        valueQuery.AND(allPathsQuery.getChildGeoEntity().LEFT_JOIN_EQ(leftJoinSelectables.toArray(new SelectableSingle[size])));
      }

      int ind = attributeKey.lastIndexOf(".");
      String className = attributeKey.substring(0, ind);
      String attributeName = attributeKey.substring(ind + 1);

      GeneratedEntityQuery generatedEntityQuery = queryMap.get(className);
      valueQuery.AND( ( (AttributeReference) generatedEntityQuery.aAttribute(attributeName) ).EQ(allPathsQuery.getChildGeoEntity()));
    }
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap)
  {
    String attributeName = null;
    String start = null;
    String end = null;
    String klass = null;
    JSONObject dateObj = null;
    try
    {
      dateObj = queryConfig.getJSONObject(DATE_ATTRIBUTE);
      attributeName = dateObj.getString(DATE_ATTRIBUTE);
      klass = dateObj.getString("klass");
      if (dateObj.has("start") && !dateObj.isNull("start") && !dateObj.getString("start").equals("null"))
      {
        start = dateObj.getString("start");
        if (queryMap.containsKey(klass))
        {
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);
          AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.aAttribute(attributeName);
          valueQuery.AND(dateAttriute.GE(start));
        }

      }
      if (dateObj.has("end") && !dateObj.isNull("end") && !dateObj.getString("start").equals("null"))
      {
        end = dateObj.getString("end");
        if (queryMap.containsKey(klass))
        {
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);
          AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.aAttribute(attributeName);
          valueQuery.AND(dateAttriute.LE(end));
        }
      }
      return setQueryDates(xml, valueQuery, attributeName);
    }
    catch (JSONException e)
    {
      return valueQuery;
    }

  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, SelectableMoment dateAttribute)
  {
    String da = dateAttribute.getQualifiedName();
    return setQueryDates(xml, valueQuery, da);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, String da)
  {
    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_SEASON);
      String table = MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName();
      dateGroup.setSQL("SELECT " + MalariaSeason.SEASONNAME + " FROM " + table + " AS ms " + " WHERE ms." + MalariaSeason.STARTDATE + " < " + da + " AND ms." + MalariaSeason.ENDDATE + " > " + da);
    }

    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_EPIWEEK);
      int startDay = 6 + Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      GregorianCalendar cal = new GregorianCalendar();
      dateGroup.setSQL("to_char(" + da + " - interval '" + startDay + " days','IW')");
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_MONTH);
      dateGroup.setSQL("to_char(" + da + ",'MM')");
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_QUARTER);
      dateGroup.setSQL("to_char(" + da + ",'Q')");
    }

    if (xml.indexOf(DATEGROUP_YEAR) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_YEAR);
      dateGroup.setSQL("to_char(" + da + ",'YYYY')");
    }

    return setQueryDates(xml, valueQuery);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, String sd, String ed)
  {
    String intervalNotValid = "INTERVAL NOT VALID";

    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_SEASON);

      String table = MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName();
      dateGroup.setSQL("SELECT " + MalariaSeason.SEASONNAME + " FROM " + table + " AS ms" + " WHERE ms." + MalariaSeason.STARTDATE + " < " + sd + " AND ms." + MalariaSeason.ENDDATE + " > " + ed);
    }

    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_EPIWEEK);

      String dateGroupSql = "CASE WHEN (" + sd + " + interval '7 days') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(Day FROM " + sd + ") - extract(DOW FROM date_trunc('week'," + ed + "))) > extract(DOW FROM " + ed + ")"
          + "THEN to_char(" + sd + ",'IW')" + "ELSE to_char(" + ed + ",'IW') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_MONTH);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 month') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DAY FROM " + sd + ") - extract(DAY FROM date_trunc('month'," + ed + "))) > extract(DAY FROM " + ed + ")"
          + "THEN to_char(" + sd + ",'MM')" + "ELSE to_char(" + ed + ",'MM') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_QUARTER);

      String dateGroupSql = "CASE WHEN (" + sd + " + interval '3 months') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + " >  (extract(DOY FROM " + ed
          + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + "THEN to_char(" + sd + ",'Q')" + "ELSE to_char(" + ed + ",'Q') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_YEAR) > 0)
    {
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectable(DATEGROUP_YEAR);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 year') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + " >  (extract(DOY FROM " + ed
          + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + "THEN to_char(" + sd + ",'YYYY')" + "ELSE to_char(" + ed + ",'YYYY') END";
      dateGroup.setSQL(dateGroupSql);
    }

    return setQueryDates(xml, valueQuery);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery)
  {
    // NOTE: the regex uses \W{1,2} to match against newlines because \N
    // does not work for all encodings.

    if (xml.indexOf(START_DATE_RANGE) > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectable(START_DATE_RANGE);
      dateGroup.setSQL("''");
      Pattern pattern = Pattern.compile("<operator>GE</operator>\\W{1,2}<value>(" + DATE_REGEX + ")</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'" + matcher.group(1) + "'");
      }
    }

    if (xml.indexOf(END_DATE_RANGE) > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectable(END_DATE_RANGE);
      dateGroup.setSQL("''");
      Pattern pattern = Pattern.compile("<operator>LE</operator>\\W{1,2}<value>(" + DATE_REGEX + ")</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'" + matcher.group(1) + "'");
      }
    }

    return valueQuery;

  }

  public static ValueQuery setQueryRatio(String xml, ValueQuery valueQuery, String countSql)
  {

    if (xml.indexOf(RATIO) > 0)
    {
      SelectableSQL ratio = (SelectableSQL) valueQuery.getSelectable(RATIO);
      Double sum = 0.0;

      // specieRatio.setSQL("");
      ratio.setSQL(countSql);

      for (ValueObject v : valueQuery.getIterator())
      {
        sum += Double.parseDouble(v.getValue(RATIO));
      }

      ratio.setSQL("to_char(" + countSql + "/" + sum + ",'99.99')");
    }

    return valueQuery;

  }
}
