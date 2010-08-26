package dss.vector.solutions.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeVirtualDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.COUNT;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedRelationshipQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableNumber;
import com.runwaysdk.query.SelectableReference;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeEnumeration;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.system.metadata.SupportedLocaleQuery;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.AllRenderTypes;
import dss.vector.solutions.query.CountOrRatioAloneException;
import dss.vector.solutions.query.DatesOnlyException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.NoColumnsAddedException;
import dss.vector.solutions.query.QueryConstants;

public class QueryUtil implements Reloadable
{

  private static final String GEO_DISPLAY_LABEL            = "geo_displayLabel";

  public static final String  DATEGROUP_EPIWEEK            = "dategroup_epiweek";

  public static final String  DATEGROUP_MONTH              = "dategroup_month";

  public static final String  DATEGROUP_QUARTER            = "dategroup_quarter";

  public static final String  DATEGROUP_CALENDARYEAR       = "dategroup_year";

  public static final String  DATEGROUP_EPIYEAR            = "dategroup_epiyear";

  public static final String  DATEGROUP_SEASON             = "dategroup_season";

  public static final String  DUMMY_RELATIONSHIP_VALUE_ONE = "one";

  public static final String  DUMMY_RELATIONSHIP_VALUE_COL = "1";

  public static final String  START_DATE_RANGE             = "start_date_range";

  public static final String  RATIO                        = "ratio_of_this_row_to_total_count";

  public static final String  END_DATE_RANGE               = "end_date_range";

  private static final String DATE_ATTRIBUTE               = "date_attribute";

  private static final String DATE_REGEX                   = "\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d";

  public static final String  DISPLAY_LABEL_SUFFIX         = "_displayLabel";

  public static final String  SHORT_DISPLAY_LABEL          = "short_Display_Label";

  public static String sumColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return "sum_stringified_id_int_pairs(array_agg(DISTINCT "+(sourceTable != null ? sourceTable+".":"")+uniqueId+"|| '~' ||"+(table != null ? table+"." : "")+column+"))";
  }

  public static String minColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return "min_stringified_id_int_pairs(array_agg(DISTINCT "+(sourceTable != null ? sourceTable+".":"")+uniqueId+"|| '~' ||"+(table != null ? table+"." : "")+column+"))";
  }
  
  public static String maxColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return "max_stringified_id_int_pairs(array_agg(DISTINCT "+(sourceTable != null ? sourceTable+".":"")+uniqueId+"|| '~' ||"+(table != null ? table+"." : "")+column+"))";
  }
  
  public static String avgColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return "avg_stringified_id_int_pairs(array_agg(DISTINCT "+(sourceTable != null ? sourceTable+".":"")+uniqueId+"|| '~' ||"+(table != null ? table+"." : "")+column+"))";
  }
  
  /**
   * Performs basic validation on the ValueQuery to ensure the query is valid.
   * 
   * @param valueQuery
   */
  public static void validateQuery(ValueQuery valueQuery)
  {
    validateDateSelectables(valueQuery);

    int size = valueQuery.getSelectableRefs().size();
    if (size == 0)
    {
      NoColumnsAddedException ex = new NoColumnsAddedException();
      throw ex;
    }

    if (size == 1
        && ( valueQuery.hasSelectableRef(RATIO) || valueQuery.getSelectableRefs().get(0) instanceof COUNT ))
    {
      throw new CountOrRatioAloneException();
    }
    else if (size == 2 && valueQuery.hasSelectableRef(RATIO))
    {
      // count and ratio are invalid
      for (Selectable sel : valueQuery.getSelectableRefs())
      {
        if (sel instanceof COUNT)
        {
          throw new CountOrRatioAloneException();
        }
      }
    }
  }

  public static String getColumnName(String klass, String attribute)
  {
    return getColumnName(MdEntityDAO.getMdEntityDAO(klass), attribute);
  }

  /**
   * Returns the column name of the given MdAttribute.
   * 
   * @param md
   * @return
   */
  public static String getColumnName(MdAttributeDAOIF md)
  {
    if (md instanceof MdAttributeVirtualDAOIF)
    {
      return ( (MdAttributeVirtualDAO) md ).getMdAttributeConcrete().getColumnName();
    }
    else
    {
      return ( (MdAttributeConcreteDAOIF) md ).getColumnName();
    }
  }

  public static String getIdColumn()
  {
    return getColumnName(MdClass.getIdMd());
  }

  /**
   * Returns the column name of the given attribute.
   * 
   * @param md
   * @param attribute
   * @return
   */
  public static String getColumnName(MdEntityDAOIF md, String attribute)
  {
    if (attribute.equals("childId"))
    {
      return RelationshipDAOIF.CHILD_ID_COLUMN;
    }
    else if (attribute.equals("parentId"))
    {
      return RelationshipDAOIF.PARENT_ID_COLUMN;
    }
    else
    {
      return getColumnName(md.getAllDefinedMdAttributeMap().get(attribute.toLowerCase()));
    }
  }

  /**
   * Ensures that the ValueQuery contains more than the start and end date
   * criteria.
   * 
   * @param valueQuery
   */
  private static void validateDateSelectables(ValueQuery valueQuery)
  {
    // Start and End date can only be selected if other Selectables added
    // to create a meaninful query.
    List<Selectable> selectables = valueQuery.getSelectableRefs();
    boolean hasOnlyDates = false;
    if (selectables.size() == 1)
    {
      String alias = selectables.get(0).getUserDefinedAlias();
      if (QueryUtil.START_DATE_RANGE.equals(alias) || QueryUtil.END_DATE_RANGE.equals(alias))
      {
        hasOnlyDates = true;
      }
    }
    else if (selectables.size() == 2)
    {
      boolean isStart = false;
      boolean isEnd = false;

      for (Selectable sel : selectables)
      {
        String alias = sel.getUserDefinedAlias();
        if (QueryUtil.START_DATE_RANGE.equals(alias))
        {
          isStart = true;
        }
        else if (QueryUtil.END_DATE_RANGE.equals(alias))
        {
          isEnd = true;
        }
      }

      hasOnlyDates = isStart && isEnd;
    }

    if (hasOnlyDates)
    {
      String error = "The start and end date must be added with other selectables.";
      throw new DatesOnlyException(error);
    }
  }

  public static String getRelationshipTermSubSelect(String attribute, String parentClass, String relClass)
  {
    String parentTable = MdBusiness.getMdBusiness(parentClass).getTableName();
    String relTable = MdEntity.getMdEntity(relClass).getTableName();
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();

    return "(select pJoin.id AS id, tJoin." + Term.NAME + " as " + attribute + "_displayLabel from"
        + " " + parentTable + " AS pJoin LEFT JOIN " + relTable + " rJoin ON rJoin."
        + RelationshipDAOIF.PARENT_ID_COLUMN + " = pJoin.id" + " LEFT JOIN " + termTable
        + " tJoin on rJoin." + RelationshipDAOIF.CHILD_ID_COLUMN + " = tJoin.id)";
  }
  
  public static String[] filterSelectedAttributes(ValueQuery valueQuery, String[] attributes)
  {
    ArrayList<String> selectedTerms = new ArrayList<String>();

    // make a list of terms that are included as selectables
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      if (s instanceof SelectableSQL)
      {
        // String columnAlias = s.getColumnAlias();
        String attributeName = s.getDbColumnName();
        for (String termAttrib : Arrays.asList(attributes))
        {
          int ind = attributeName.lastIndexOf(DISPLAY_LABEL_SUFFIX);
          if(ind != -1)
          {
            String attr = attributeName.substring(0, ind);
            if(termAttrib.equals(attr))
            {
              selectedTerms.add(termAttrib);
            }
          }
        }
      }
    }

    return selectedTerms.toArray(new String[selectedTerms.size()]);

  }

  public static boolean setSelectabeSQL(ValueQuery valueQuery, String ref, String sql)
  {
    if (valueQuery.hasSelectableRef(ref))
    {
      Selectable s = valueQuery.getSelectableRef(ref);

      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      ( (SelectableSQL) s ).setSQL(sql);
      return true;
    }
    return false;
  }

  public static SelectableSQL[] filterSelectedSelectables(ValueQuery valueQuery, String[] attributes)
  {

    ArrayList<SelectableSQL> selectedTerms = new ArrayList<SelectableSQL>();

    // make a list of terms that are included as selectables
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }

      if (s instanceof SelectableSQL)
      {
        String columnAlias = s.getColumnAlias();
        for (String termAttrib : Arrays.asList(attributes))
        {
          if (columnAlias.equals(termAttrib + DISPLAY_LABEL_SUFFIX))
          {
            selectedTerms.add((SelectableSQL) s);
          }
        }
      }
    }

    return selectedTerms.toArray(new SelectableSQL[selectedTerms.size()]);

  }

  public static ValueQuery joinTermAllpaths(ValueQuery valueQuery, String klass,
      GeneratedEntityQuery query)
  {

    String tableAlias = query.getTableAlias();

    return joinTermAllpaths(valueQuery, klass, tableAlias);

  }

  public static ValueQuery joinTermAllpaths(ValueQuery valueQuery, String klass, String tableAlias)
  {

    String[] termAttributes = filterSelectedAttributes(valueQuery, Term.getTermAttributes(klass));

    // optimization: do nothing if there are no terms selected
    if (termAttributes.length > 0)
    {
      String id = getIdColumn();

      String sql = "(" + QueryUtil.getTermSubSelect(klass, termAttributes) + ")";
//      String subSelect = klass.replace('.', '_') + "TermSubSel";
      String subSelect = tableAlias+"_TermSubSel";
      String table = MdEntity.getMdEntity(klass).getTableName();
      valueQuery.AND(new InnerJoinEq(id, table, tableAlias, id, sql, subSelect));
    }
    return valueQuery;

  }

  public static ValueQuery leftJoinTermDisplayLabels(ValueQuery valueQuery, GeneratedEntityQuery query,
      String attributeId)
  {

    MdEntityDAOIF mdEntity = query.getMdClassIF();
    String type = mdEntity.definesType();
    SelectableSQL[] termAttributes = filterSelectedSelectables(valueQuery, Term.getTermAttributes(type));
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
    String tableName = mdEntity.getTableName();
    String idCol = getIdColumn();

    for (SelectableSQL s : Arrays.asList(termAttributes))
    {
      String attrib = s.getColumnAlias();
      attrib = attrib.substring(0, attrib.length() - DISPLAY_LABEL_SUFFIX.length());
      String attrCol = getColumnName(mdEntity, attrib);

      String sql = "SELECT term." + Term.NAME + "  FROM " + tableName + " as t";
      sql += " LEFT JOIN " + termTable + " as term  on t." + attrCol + " = term." + idCol;
      sql += " WHERE t." + idCol + " = " + attributeId + "";

      s.setSQL(sql);
    }
    return valueQuery;

  }

  public static void subselectGeoDisplayLabels(SelectableSQLCharacter geoLabel, String klass,
      String attributeName, String attributeAlias)
  {
    MdBusiness md = MdBusiness.getMdBusiness(klass);
    String tableName = md.getTableName();
    String attribCol = QueryUtil.getColumnName(klass, attributeName);

    String localeCoalesce = getLocaleCoalesce("gdl.");

    String sql = "SELECT " + localeCoalesce + " FROM " + GEO_DISPLAY_LABEL + " AS gdl JOIN " + tableName
        + " p ON p." + attribCol + " = gdl.id";

    sql += " WHERE p.id = " + attributeAlias + "";

    geoLabel.setSQL(sql);
  }

  public static ValueQuery joinGeoDisplayLabels(ValueQuery valueQuery, String klass,
      GeneratedEntityQuery query)
  {
    if (query != null)
    {
      String[] geoAttributes = filterSelectedAttributes(valueQuery, GeoEntity.getGeoAttributes(klass));
      if (geoAttributes.length > 0)
      {
        String id = getIdColumn();

        String sql = "(" + QueryUtil.getGeoDisplayLabelSubSelect(klass, geoAttributes) + ")";
        String subSelect = klass.replace('.', '_') + "GeoSubSel";
        String table = MdBusiness.getMdBusiness(klass).getTableName();
        valueQuery.AND(new InnerJoinEq(id, table, query.getTableAlias(), id, sql, subSelect));
      }
    }
    return valueQuery;

  }

  public static Join forceJoinGeoDisplayLabels(ValueQuery valueQuery, String klass,
      GeneratedEntityQuery query)
  {
    if (query != null)
    {
      String[] geoAttributes = GeoEntity.getGeoAttributes(klass);
      if (geoAttributes.length > 0)
      {
        String id = getIdColumn();

        String sql = "(" + QueryUtil.getGeoDisplayLabelSubSelect(klass, geoAttributes) + ")";
        String subSelect = klass.replace('.', '_') + "GeoSubSel";
        String table = MdBusiness.getMdBusiness(klass).getTableName();

        return new InnerJoinEq(id, table, query.getTableAlias(), id, sql, subSelect);
      }
    }
    return null;

  }

  public static ValueQuery joinEnumerationDisplayLabels(ValueQuery valueQuery, String klass,
      GeneratedEntityQuery query)
  {
    if (query != null)
    {
      String[] enumAttributes = filterSelectedAttributes(valueQuery, QueryUtil.getEnumAttributes(klass));
      if (enumAttributes.length > 0)
      {
        String id = getIdColumn();

        String sql = "(" + QueryUtil.getEnumerationDisplayLabelSubSelect(klass, enumAttributes) + ")";
        String subSelect = klass.replace('.', '_') + "EnumSubSel";
        String table = MdBusiness.getMdBusiness(klass).getTableName();
        valueQuery.AND(new InnerJoinEq(id, table, query.getTableAlias(), id, sql, subSelect));
      }
    }
    return valueQuery;

  }

  public static ValueQuery leftJoinEnumerationDisplayLabels(ValueQuery valueQuery, String klass,
      GeneratedEntityQuery query, String attributeId)
  {
    MdEntityDAOIF enumMaster = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String tableEnum = enumMaster.getTableName();
    String displayLabelColumn = getColumnName(enumMaster, EnumerationMaster.DISPLAYLABEL);

    SelectableSQL[] enumAttributes = filterSelectedSelectables(valueQuery, QueryUtil
        .getEnumAttributes(klass));
    String tableName = MdBusiness.getMdBusiness(klass).getTableName();

    MdEntityDAOIF mdDisplayLabel = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String tableDisplay = mdDisplayLabel.getTableName();
    String defaultLocaleColumn = getColumnName(mdDisplayLabel, MetadataDisplayLabel.DEFAULTLOCALE);

    for (SelectableSQL s : Arrays.asList(enumAttributes))
    {
      String attrib = s.getColumnAlias();
      attrib = attrib.substring(0, attrib.length() - DISPLAY_LABEL_SUFFIX.length());
      attrib = getColumnName(klass, attrib);
      String sql = " SELECT " + defaultLocaleColumn + " FROM " + tableEnum + " em JOIN " + tableDisplay
          + " md on em." + displayLabelColumn + " = md.id  ";
      sql += " JOIN " + tableName + " as t  ON t." + attrib + "_c = em.id";
      sql += " WHERE t.id = " + attributeId + "";

      s.setSQL(sql);
    }
    return valueQuery;

  }

  /**
   * Returns all enumerated attributes
   * 
   * @param className
   * @return
   */
  private static String[] getEnumAttributes(String className)
  {
    MdBusiness md = MdBusiness.getMdBusiness(className);
    List<String> list = new LinkedList<String>();

    for (MdAttribute mdAttr : md.getAllAttribute())
    {
      if (mdAttr instanceof MdAttributeEnumeration)
      {
        list.add( ( (MdAttributeEnumeration) mdAttr ).getAttributeName());
      }
    }

    return list.toArray(new String[list.size()]);
  }
  
  
  public static boolean getSingleAttribteGridSql(ValueQuery valueQuery, String tableAlias)
  {
    return getSingleAttribteGridSql(valueQuery, tableAlias, RelationshipDAOIF.PARENT_ID_COLUMN, RelationshipDAOIF.CHILD_ID_COLUMN);
  }

  public static boolean getSingleAttribteGridSql(ValueQuery valueQuery, String tableAlias, String parentColumn, String childColumn)
  {
    boolean foundGrid = false;

    for (Selectable s : valueQuery.getSelectableRefs())
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
        if (index1 > 0 && index2 > 0 && index1 != index2)
        {
          foundGrid = true;

          String attrib = gridAlias.substring(0, index1);

          // here we make a dummy value when the relationship has no ammount
          if (attrib.equals(DUMMY_RELATIONSHIP_VALUE_ONE))
          {
            attrib = DUMMY_RELATIONSHIP_VALUE_COL;
          }

          String klass = gridAlias.substring(index1 + 2, index2).replace("_", ".");
          String term_id = gridAlias.substring(index2 + 2, gridAlias.length());

          MdEntityDAOIF mdRel = MdEntityDAO.getMdEntityDAO(klass);
          String table = mdRel.getTableName();

          String attrCol;
          if (attrib.equals(DUMMY_RELATIONSHIP_VALUE_COL))
          {
            attrCol = DUMMY_RELATIONSHIP_VALUE_COL;
          }
          else
          {
            attrCol = getColumnName(mdRel, attrib);
          }

          String sql = "SELECT " + attrCol + " FROM " + table + " WHERE "
              + parentColumn + " = '" + term_id + "' " + "AND "
              + childColumn + " = " + tableAlias + ".id";

          ( (SelectableSQL) s ).setSQL(sql);
        }
      }
    }
    return foundGrid;
  }

  public static void setTermRestrictions(ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap)
  {
    for (String entityAlias : queryMap.keySet())
    {
      int index1 = entityAlias.indexOf("__");
      int index2 = entityAlias.lastIndexOf("__");
      if (index1 > 0 && index2 > 0)
      {
        String attrib_name = entityAlias.substring(0, index1);
        String klass = entityAlias.substring(index1 + 2, index2).replace("_", ".");
        if (queryMap.get(entityAlias) instanceof dss.vector.solutions.ontology.AllPathsQuery)
        {
          dss.vector.solutions.ontology.AllPathsQuery allPathsQuery = (dss.vector.solutions.ontology.AllPathsQuery) queryMap
              .get(entityAlias);
          String allPathsTable = MdEntity.getMdEntity(dss.vector.solutions.ontology.AllPaths.CLASS)
              .getTableName();
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);

          String attrCol = getColumnName(attributeQuery.getMdClassIF(), attrib_name);
          String childTermCol = getColumnName(dss.vector.solutions.ontology.AllPaths.getChildTermMd());

          // IMPORTANT: We cannot always rely on the class table directly
          // because the attribute
          // may have been defined by a parent class, hence it will be on the
          // parent table.
          // Instead, rely on the query and metadata to resolve the
          // class/attribute mapping.
          String table;
          String alias;
          if(attributeQuery instanceof GeneratedRelationshipQuery &&
              (attrCol.equals(RelationshipInfo.CHILD_ID) || attrCol.equals(RelationshipInfo.PARENT_ID)))
          {
            // We don't have metadata for childId or parentId so we have to manually get the table and alias
            // IMPORTANT: this does not take inheritance into account (i.e., if child_id or parent_id are
            // defined by an MdRelationship superclass).
            MdRelationshipDAOIF md = (MdRelationshipDAOIF) attributeQuery.getMdClassIF();
            table = md.getTableName();
            alias = attributeQuery.getTableAlias();
          }
          else
          {
            Attribute attr = attributeQuery.get(attrib_name);
            table = attr.getDefiningTableName();
            alias = attr.getDefiningTableAlias();
          }

          valueQuery.AND(new InnerJoinEq(attrCol, table, alias, childTermCol, allPathsTable,
              allPathsQuery.getTableAlias()));
        }
      }

    }
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

        String value;
        try
        {
          value = queryConfig.getString(key);
        }
        catch (JSONException e)
        {
          String error = "Could not extract the key [" + key + "] when setting numeric restrictions.";
          throw new ProgrammingErrorException(error, e);
        }
        Selectable sel = valueQuery.getSelectableRef(attributeName);

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
            String range1 = range[0].trim();
            String range2 = range[1].trim();
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
            valueQuery.WHERE( ( (SelectableNumber) sel ).GE(range[0].trim()));
          }
        }
        else
        {
          value = value.trim();
          
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
    }
    return valueQuery;
  }

  public static String getTermSubSelect(String className, String... attributes)
  {
    String termTable = MdEntity.getMdEntity(Term.CLASS).getTableName();
    MdEntityDAOIF targetMdBusiness = MdEntityDAO.getMdEntityDAO(className);
    String tableName = targetMdBusiness.getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;

    int count = 0;
    Map<String, ? extends MdAttributeConcreteDAOIF> attrMap = targetMdBusiness
        .getAllDefinedMdAttributeMap();
    for (String attr : attributes)
    {
      select += " term" + count + "." + Term.NAME + " as " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      MdAttributeConcreteDAOIF attrMd = attrMap.get(attr.toLowerCase());
      String attrColumn = attrMd != null ? getColumnName(attrMd) : getColumnName(targetMdBusiness, attr);

      from += " LEFT JOIN " + termTable + " as term" + count + " on " + tableName + "." + attrColumn
          + " = term" + count + ".id";

      count++;
    }

    String sql = select + from;

    return sql;
  }

  public static String getGeoDisplayLabelSubSelect(String className, String... attributes)
  {
    MdEntityDAOIF md = MdEntityDAO.getMdEntityDAO(className);
    String tableName = md.getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;

    int count = 0;
    for (String attr : attributes)
    {
      String localeCoalesce = getLocaleCoalesce(" geo" + count + ".");

      select += localeCoalesce + " AS " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      String geoCol = getColumnName(md, attr);
      from += " LEFT JOIN " + GEO_DISPLAY_LABEL + " as geo" + count + " on " + tableName + "." + geoCol
          + " = geo" + count + ".id";

      count++;
    }

    String sql = select + from;

    return sql;
  }

  public static String getLocaleCoalesce(String prefix)
  {
    String localeString = Session.getCurrentLocale().toString().toLowerCase();

    SupportedLocaleQuery installedLocales = LocalizationFacade.getInstalledLocales();
    OIterator<? extends SupportedLocale> iterator = installedLocales.getIterator();
    List<String> list = new LinkedList<String>();

    while (iterator.hasNext())
    {
      SupportedLocale supportedLocale = (SupportedLocale) iterator.next();
      list.add(supportedLocale.getEnumName().toLowerCase());
    }

    String localeColumns = "";

    for (int i = localeString.length(); i > 0; i = localeString.lastIndexOf('_', i - 1))
    {
      String subLocale = localeString.substring(0, i).toLowerCase();

      if (list.contains(subLocale))
      {
        localeColumns += MetadataDAO.convertCamelCaseToUnderscore(Session.getCurrentDimension()
            .getLocaleAttributeName(subLocale))
            + ", ";
        localeColumns += subLocale + ", ";
      }

    }

    String key = MetadataDisplayLabel.CLASS + "." + MetadataDisplayLabel.DEFAULTLOCALE;
    String defaultLocaleColumn = MdAttributeConcrete.getByKey(key).getColumnName();

    String dim_key = Session.getCurrentDimension().getDefaultLocaleAttributeName();
    String defaultDimensionLocaleColumn = MetadataDAO.convertCamelCaseToUnderscore(dim_key);

    localeColumns = "COALESCE(" + localeColumns + prefix + defaultDimensionLocaleColumn + ", " + prefix
        + defaultLocaleColumn + ")";
    return localeColumns;
  }

  public static String getEnumerationDisplayLabelSubSelect(String className, String... attributes)
  {
    MdEntityDAOIF enumMaster = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String tableEnum = enumMaster.getTableName();
    String displayLabelColumn = getColumnName(enumMaster, EnumerationMaster.DISPLAYLABEL);

    MdEntityDAOIF md = MdEntityDAO.getMdEntityDAO(className);
    String tableName = md.getTableName();

    MdEntityDAOIF mdDisplayLabel = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String tableDisplay = mdDisplayLabel.getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;
    String localeColumns = getLocaleCoalesce("");

    int count = 0;
    for (String attr : attributes)
    {
      String attrCol = getColumnName(md, attr);
      select += "(SELECT " + localeColumns + " FROM " + tableEnum + " em join " + tableDisplay
          + " md on em." + displayLabelColumn + " = md.id  WHERE em.id = " + attrCol + "_c) as " + attr
          + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

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
  public static Map<String, GeneratedEntityQuery> joinQueryWithGeoEntities(QueryFactory queryFactory,
      ValueQuery valueQuery, String xml, JSONObject config, Layer layer)
  {
    Map<String, GeneratedEntityQuery> queryMap;

    ValueQueryParser valueQueryParser = new ValueQueryParser(xml, valueQuery);

    // If we're mapping, dereference the MdAttribute that will be joined with
    // the GeoEntity
    // table.
    String key = null;
    String attr = null;
    String layerGeoEntityType = null;
    String thematicUserAlias = null;
    if (layer != null)
    {
      thematicUserAlias = layer.getThematicUserAlias();
      layerGeoEntityType = layer.getGeoHierarchy().getQualifiedType();
      MdAttribute mdAttribute = layer.getMdAttribute();
      key = mdAttribute.getKey();
      attr = layer.getRenderAs().get(0) == AllRenderTypes.POINT ? GeoEntity.GEOPOINT
          : GeoEntity.GEOMULTIPOLYGON;
    }

    // Normal query (non-mapping)
    GeoEntityJoinData joinData = new GeoEntityJoinData();
    try
    {
      JSONObject selectedUniMap = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        String attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        String[] selectedUniversals = new String[universals.length()];
        // we run addUniversalsForAttribute even if length is zero for the case
        // of restricting without selecting
        for (int i = 0; i < universals.length(); i++)
        {
          selectedUniversals[i] = universals.getString(i);
        }

        addUniversalsForAttribute(joinData, queryFactory, attributeKey, selectedUniversals,
            valueQueryParser, key, attr, layerGeoEntityType, thematicUserAlias);
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    // Include the geometry column/attribute in the ValueQuery if we are mapping
    if (layer != null)
    {
      String entityAlias = key + "__" + layerGeoEntityType;

      valueQueryParser.addAttributeSelectable(entityAlias, attr, attr,
          QueryConstants.GEOMETRY_NAME_COLUMN);

      if (joinData.geoThematicAlias != null)
      {
        valueQueryParser.addAttributeSelectable(joinData.geoThematicEntity, joinData.geoThematicAttr,
            joinData.geoThematicAlias, "data");
      }
    }

    queryMap = valueQueryParser.parse();

    // Query validation is done here since all query screens must call this
    // method.
    validateQuery(valueQuery);

    // Set the entity name and geo id columns to something predictable
    if (layer != null)
    {
      valueQuery.getSelectableRef(joinData.entityNameAlias).setColumnAlias(
          QueryConstants.ENTITY_NAME_COLUMN);
      valueQuery.getSelectableRef(joinData.geoIdAlias).setColumnAlias(QueryConstants.GEO_ID_COLUMN);

      // Name the thematic column if a thematic variable has been selected

      if (layer.hasThematicVariable())
      {
        String alias = joinData.geoThematicAlias != null ? joinData.geoThematicAlias : thematicUserAlias;
        Selectable thematic = valueQuery.getSelectableRef(alias);

        // Only lock and apply the layer if it's not new to avoid erroring out
        // on a new
        // instance used for calculations.
        if (!layer.isNew())
        {
          layer.appLock();
        }

        layer.setThematicColumnAlias(thematic.getColumnAlias());

        if (!layer.isNew())
        {
          layer.apply();
        }
      }

      // exclude any rows without geometry data
      valueQuery.AND(valueQuery.getSelectableRef(attr).NE(null));
    }

    for (String attributeKey : joinData.attributeKeysAndJoins.keySet())
    {
      AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(AllPaths.CLASS + "_" + attributeKey);
      List<ValueQuery> leftJoinValueQueries = joinData.attributeKeysAndJoins.get(attributeKey);

      restrictEntitiesForAttribute(attributeKey, allPathsQuery, leftJoinValueQueries, valueQuery,
          queryMap);
    }

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    return queryMap;
  }

  private static void addUniversalsForAttribute(GeoEntityJoinData joinData, QueryFactory queryFactory,
      String attributeKey, String[] selectedUniversals, ValueQueryParser valueQueryParser,
      String layerKey, String geoAttr, String layerGeoEntityType, String thematicUserAlias)
  {
    List<ValueQuery> leftJoinValueQueries = new LinkedList<ValueQuery>();
    for (String selectedGeoEntityType : selectedUniversals)
    {
      List<Selectable> selectables = new LinkedList<Selectable>();

      GeoEntityQuery geoEntityQuery = new GeoEntityQuery(queryFactory);

      AllPathsQuery subAllPathsQuery = new AllPathsQuery(queryFactory);
      ValueQuery geoEntityVQ = new ValueQuery(queryFactory);
      MdBusinessDAOIF geoEntityMd = MdBusinessDAO.getMdBusinessDAO(selectedGeoEntityType);

      String prepend = attributeKey.replaceAll("\\.", "_") + "__";
      String entityNameAlias = prepend + geoEntityMd.getTypeName().toLowerCase() + "_"
          + GeoEntityView.ENTITYNAME;
      String geoIdAlias = prepend + geoEntityMd.getTypeName().toLowerCase() + "_" + GeoEntityView.GEOID;

      Selectable selectable1 = geoEntityQuery.getEntityName(entityNameAlias);
      Selectable selectable2 = geoEntityQuery.getGeoId(geoIdAlias);
      String displaySQL = "SELECT " + SHORT_DISPLAY_LABEL + " FROM " + GEO_DISPLAY_LABEL
          + " gd WHERE gd.id = " + selectable1.getDefiningTableAlias() + ".id";
      Selectable selectable4 = geoEntityVQ.aSQLCharacter(entityNameAlias, displaySQL, entityNameAlias,
          selectable1.getUserDefinedDisplayLabel());

      selectables.add(selectable2);
      selectables.add(selectable4);

      SelectableReference selectable3 = subAllPathsQuery.getChildGeoEntity("child_id");
      selectables.add(selectable3);

      String geoVQEntityAlias = attributeKey + "__" + selectedGeoEntityType;
      GeoEntityQuery geoEntityQuery2 = null;
      if (layerKey != null && attributeKey.equals(layerKey)
          && selectedGeoEntityType.equals(layerGeoEntityType))
      {
        // save the aliases used for mapping the entity name and geo id columns
        joinData.entityNameAlias = entityNameAlias;
        joinData.geoIdAlias = geoIdAlias;

        // If the thematic variable is either the entity name or geo id
        // then create a new selectable because those columns already have
        // a custom name and cannot be renamed to
        // QueryConstants.THEMATIC_DATA_COLUMN.
        Selectable thematicSel = null;
        if (thematicUserAlias != null && thematicUserAlias.length() > 0)
        {
          if (thematicUserAlias.equals(entityNameAlias))
          {
            geoEntityQuery2 = new GeoEntityQuery(queryFactory);

            joinData.geoThematicEntity = geoVQEntityAlias;
            joinData.geoThematicAlias = entityNameAlias + "_entityname_thematic";
            joinData.geoThematicAttr = GeoEntity.ENTITYNAME;
            thematicSel = geoEntityQuery2.getEntityName(joinData.geoThematicAlias);
            selectables.add(thematicSel);
          }
          else if (thematicUserAlias.equals(geoIdAlias))
          {
            geoEntityQuery2 = new GeoEntityQuery(queryFactory);

            joinData.geoThematicEntity = geoVQEntityAlias;
            joinData.geoThematicAlias = geoIdAlias + "_geoid_thematic";
            joinData.geoThematicAttr = GeoEntity.GEOID;
            thematicSel = geoEntityQuery2.getGeoId(joinData.geoThematicAlias);
            selectables.add(thematicSel);
          }
        }

        Selectable geometrySel = geoEntityQuery.get(geoAttr);
        selectables.add(geometrySel);
      }

      geoEntityVQ.SELECT(selectables.toArray(new Selectable[selectables.size()]));

      List<MdBusinessDAOIF> allClasses = geoEntityMd.getAllSubClasses();
      Condition[] geoConditions = new Condition[allClasses.size()];
      for (int i = 0; i < allClasses.size(); i++)
      {
        geoConditions[i] = subAllPathsQuery.getParentUniversal().EQ(allClasses.get(i));
      }

      geoEntityVQ.WHERE(OR.get(geoConditions));
      geoEntityVQ.AND(subAllPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

      if (geoEntityQuery2 != null)
      {
        geoEntityVQ.AND(geoEntityQuery.getId().EQ(geoEntityQuery2.getId()));
      }

      leftJoinValueQueries.add(geoEntityVQ);

      valueQueryParser.setValueQuery(geoVQEntityAlias, geoEntityVQ);
    }

    joinData.attributeKeysAndJoins.put(attributeKey, leftJoinValueQueries);
  }

  private static void restrictEntitiesForAttribute(String attributeKey, AllPathsQuery allPathsQuery,
      List<ValueQuery> leftJoinValueQueries, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap)
  {
    if (allPathsQuery == null && leftJoinValueQueries.size() > 0)
    {
      // This case is for when they have not restricted by any specific
      // geoEntity
      allPathsQuery = new AllPathsQuery(valueQuery.getQueryFactory());
      valueQuery.FROM(allPathsQuery);
      // we use the country universial to restrict the cross product
      valueQuery.AND(allPathsQuery.getParentUniversal().EQ(
          MdBusiness.getMdBusiness(Country.CLASS).getId()));
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
        valueQuery.AND(allPathsQuery.getChildGeoEntity().LEFT_JOIN_EQ(
            leftJoinSelectables.toArray(new SelectableSingle[size])));
      }

      int ind = attributeKey.lastIndexOf(".");
      String className = attributeKey.substring(0, ind);
      String attributeName = attributeKey.substring(ind + 1);

      GeneratedEntityQuery generatedEntityQuery = queryMap.get(className);
      valueQuery.AND( ( (AttributeReference) generatedEntityQuery.get(attributeName) ).EQ(allPathsQuery
          .getChildGeoEntity()));
    }
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig,
      Map<String, GeneratedEntityQuery> queryMap)
  {
    String attributeName = null;
    String startValue = null;
    String endValue = null;
    String klass = null;
    JSONObject dateObj = null;
    try
    {
      dateObj = queryConfig.getJSONObject(DATE_ATTRIBUTE);
      attributeName = dateObj.getString(DATE_ATTRIBUTE);
      klass = dateObj.getString("klass");

      MdBusiness md = MdBusiness.getMdBusiness(klass);
      GeneratedEntityQuery attributeQuery = null;
      while (attributeQuery == null)
      {
        if (md == null)
        {
          // the entity that defines the date attribute is not
          // in the query, so don't do anymore processing.
          return valueQuery;
        }

        attributeQuery = queryMap.get(md.definesType());
        if (attributeQuery != null)
        {
          break;
        }

        md = md.getSuperMdBusiness();
      }

      if (dateObj.has("start") && !dateObj.isNull("start") && !dateObj.getString("start").equals("null"))
      {
        startValue = dateObj.getString("start");
        AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);
        valueQuery.AND(dateAttriute.GE(startValue));

      }
      if (dateObj.has("end") && !dateObj.isNull("end") && !dateObj.getString("start").equals("null"))
      {
        endValue = dateObj.getString("end");
        AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);
        valueQuery.AND(dateAttriute.LE(endValue));
      }

      // now we set the columns that show the restictions
      if (xml.indexOf(START_DATE_RANGE) > 0)
      {
        SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectableRef(START_DATE_RANGE);
        dateGroup.setSQL("''");
        if (startValue != null)
        {
          dateGroup.setSQL("'" + startValue + "'");
        }
      }

      if (xml.indexOf(END_DATE_RANGE) > 0)
      {
        SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectableRef(END_DATE_RANGE);
        dateGroup.setSQL("''");
        if (endValue != null)
        {
          dateGroup.setSQL("'" + endValue + "'");
        }
      }

      return setQueryDates(xml, valueQuery, attributeQuery, (SelectableMoment) attributeQuery
          .get(attributeName));
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target,
      SelectableMoment daSel)
  {
    String da = daSel.getDbQualifiedName();
    Set<String> found = new HashSet<String>();

    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      found.add(DATEGROUP_SEASON);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_SEASON);
      MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
      String table = malariaSeasonMd.getTableName();
      String seasonNameCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.SEASONNAME);
      String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
      String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);

      dateGroup.setSQL("SELECT " + seasonNameCol + " FROM " + table + " AS ms " + " WHERE ms."
          + startDateCol + " <= " + da + " AND ms." + endDateCol + " >= " + da);
    }

    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      found.add(DATEGROUP_EPIWEEK);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_EPIWEEK);
      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      dateGroup.setSQL("get_epiWeek_from_date(" + da + "," + startDay + ")");
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      found.add(DATEGROUP_MONTH);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_MONTH);
      dateGroup.setSQL("to_char(" + da + ",'MM')");
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      found.add(DATEGROUP_QUARTER);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_QUARTER);
      dateGroup.setSQL("to_char(" + da + ",'Q')");
    }

    if (xml.indexOf(DATEGROUP_CALENDARYEAR) > 0)
    {
      found.add(DATEGROUP_CALENDARYEAR);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_CALENDARYEAR);
      dateGroup.setSQL("to_char(" + da + ",'YYYY')");
    }

    if (xml.indexOf(DATEGROUP_EPIYEAR) > 0)
    {
      found.add(DATEGROUP_EPIYEAR);

      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_EPIYEAR);
      dateGroup.setSQL("get_epiYear_from_date(" + da + "," + startDay + ")");
    }

    ensureEntityInFromClause(found, valueQuery, target);

    return setQueryDates(xml, valueQuery);
  }

  /**
   * Some query screens error out when only the date groups are selected because
   * the GeneratedEntityQuery that contains the date attribute is not added to
   * the FROM clause, causing an SQL syntax error. If this method detects that
   * such a problem may occur, a tautological WHERE condition is added to the
   * ValueQuery that will force the GeneratedEntityQuery to be added to the FROM
   * clause.
   * 
   * @param found
   * @param valueQuery
   * @param target
   */
  private static void ensureEntityInFromClause(Set<String> found, ValueQuery valueQuery,
      GeneratedEntityQuery target)
  {
    // Include RATIO, which suffers from the same problem as the date
    // selectables
    if (valueQuery.hasSelectableRef(RATIO))
    {
      found.add(RATIO);
    }

    if (found.size() > 0)
    {
      for (Selectable sel : valueQuery.getSelectableRefs())
      {
        if (!found.contains(sel.getUserDefinedAlias()))
        {
          // A non-date selectable was found, so we must assume that the calling
          // query screen
          // class has already handled this case (as it should).
          return;
        }
      }

      // Only date selectable were found, so force the tautological WHERE
      // condition
      SelectableChar id1 = (SelectableChar) target.id();
      SelectableChar id2 = (SelectableChar) target.id();
      valueQuery.WHERE(id1.EQ(id2));
    }
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target,
      SelectableMoment sdSel, SelectableMoment edSel)
  {
    String sd = sdSel.getDbQualifiedName();
    String ed = edSel.getDbQualifiedName();

    Set<String> found = new HashSet<String>();

    String intervalNotValid = "INTERVAL NOT VALID";

    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      found.add(DATEGROUP_SEASON);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_SEASON);

      MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
      String table = malariaSeasonMd.getTableName();
      String seasonNameCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.SEASONNAME);
      String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
      String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);

      dateGroup.setSQL("SELECT " + seasonNameCol + " FROM " + table + " AS ms" + " WHERE ms."
          + startDateCol + " <= " + sd + " AND ms." + endDateCol + " >= " + ed);
    }

    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      found.add(DATEGROUP_EPIWEEK);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_EPIWEEK);
      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '7 days') < " + ed + "  THEN '"
          + intervalNotValid + "'" + "WHEN (extract(Day FROM " + sd
          + ") - extract(DOW FROM date_trunc('week'," + ed + "))) > extract(DOW FROM " + ed + ")"
          + "THEN get_epiWeek_from_date(" + sd + "," + startDay + ")::TEXT "
          + "ELSE get_epiWeek_from_date(" + ed + "," + startDay + ")::TEXT  END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      found.add(DATEGROUP_MONTH);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_MONTH);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 month') < " + ed + "  THEN '"
          + intervalNotValid + "'" + "WHEN (extract(DAY FROM " + sd
          + ") - extract(DAY FROM date_trunc('month'," + ed + "))) > extract(DAY FROM " + ed + ")"
          + "THEN to_char(" + sd + ",'MM')" + "ELSE to_char(" + ed + ",'MM') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      found.add(DATEGROUP_QUARTER);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_QUARTER);

      String dateGroupSql = "CASE WHEN (" + sd + " + interval '3 months') < " + ed + "  THEN '"
          + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd
          + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + " >  (extract(DOY FROM " + ed
          + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + "THEN to_char(" + sd + ",'Q')"
          + "ELSE to_char(" + ed + ",'Q') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_CALENDARYEAR) > 0)
    {
      found.add(DATEGROUP_CALENDARYEAR);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_CALENDARYEAR);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 year') < " + ed + "  THEN '"
          + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd
          + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + " >  (extract(DOY FROM " + ed
          + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + "THEN to_char(" + sd + ",'YYYY')"
          + "ELSE to_char(" + ed + ",'YYYY') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_EPIYEAR) > 0)
    {
      found.add(DATEGROUP_EPIYEAR);

      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(DATEGROUP_EPIYEAR);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 year') < " + ed + "  THEN '"
          + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd
          + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + " >  (extract(DOY FROM " + ed
          + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + "THEN  get_epiYear_from_date(" + sd
          + "," + startDay + ")::TEXT" + " ELSE  get_epiYear_from_date(" + ed + "," + startDay
          + ")::TEXT END";
      dateGroup.setSQL(dateGroupSql);
    }

    ensureEntityInFromClause(found, valueQuery, target);

    return setQueryDates(xml, valueQuery);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery)
  {
    // NOTE: the regex uses \W{1,2} to match against newlines because \N
    // does not work for all encodings.

    if (xml.indexOf(START_DATE_RANGE) > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectableRef(START_DATE_RANGE);
      Pattern pattern = Pattern.compile("<operator>GE</operator>\\W{1,2}<value>(" + DATE_REGEX
          + ")</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'" + matcher.group(1) + "'");
      }
    }

    if (xml.indexOf(END_DATE_RANGE) > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectableRef(END_DATE_RANGE);
      Pattern pattern = Pattern.compile("<operator>LE</operator>\\W{1,2}<value>(" + DATE_REGEX
          + ")</value>");
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
      SelectableSQL ratio = (SelectableSQL) valueQuery.getSelectableRef(RATIO);

      ratio.setSQL("(" + countSql + "/(SUM(" + countSql + ") OVER ())::float)::decimal(20,3)");
    }

    return valueQuery;

  }

  /**
   * Class to help with the structure of the join criteria for GeoEntity data
   * and mapping.
   */
  private static class GeoEntityJoinData implements Reloadable
  {

    private String                        entityNameAlias;

    private String                        geoIdAlias;

    private String                        geoThematicAlias;

    private String                        geoThematicAttr;

    private String                        geoThematicEntity;

    private Map<String, List<ValueQuery>> attributeKeysAndJoins;

    private GeoEntityJoinData()
    {
      entityNameAlias = null;
      geoIdAlias = null;
      geoThematicAlias = null;
      geoThematicAttr = null;
      geoThematicEntity = null;
      attributeKeysAndJoins = new HashMap<String, List<ValueQuery>>();
    }
  }
}
