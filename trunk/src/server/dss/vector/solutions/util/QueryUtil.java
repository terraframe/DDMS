package dss.vector.solutions.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeVirtualDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.CONCAT;
import com.runwaysdk.query.Coalesce;
import com.runwaysdk.query.F;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeEnumeration;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MdTypeQuery;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.system.metadata.SupportedLocaleQuery;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonSeasonLabel;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityEntityLabel;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermTermDisplayLabel;

public class QueryUtil implements Reloadable
{

  public static final String  GEO_DISPLAY_LABEL            = "geo_displayLabel";

  public static final String  LABEL_COLUMN                 = "label";

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

  public static final String  DATE_ATTRIBUTE               = "date_attribute";

  public static final String  DATE_CLASS                   = "klass";

  public static final String  DATE_START                   = "start";

  public static final String  DATE_END                     = "end";

  private static final String DATE_REGEX                   = "\\d\\d\\d\\d-[0-1]\\d-[0-3]\\d";

  public static final String  DISPLAY_LABEL_SUFFIX         = "_displayLabel";

  public static final String  SUM_FUNCTION                 = "sum_stringified_id_int_pairs";

  public static final String  MIN_FUNCTION                 = "min_stringified_id_int_pairs";

  public static final String  MAX_FUNCTION                 = "max_stringified_id_int_pairs";

  public static final String  AVG_FUNCTION                 = "avg_stringified_id_int_pairs";

  public static final String TABLE_ALIAS = "ms";
  
  public static final String LABEL_ALIAS = "la";
  
  /**
   * Builds a unique DB alias based on the given terms.
   * 
   * @param termIds
   * @return
   */
  public static String aliasTerms(Term... terms)
  {
    String aliases = "";

    for (Term term : terms)
    {
      aliases += "" + term.getId().substring(0, 16);
    }

    return aliases;
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

  public static String getRelationshipTermSubSelect(String attribute, String parentClass, String relClass)
  {
    String parentTable = MdBusiness.getMdBusiness(parentClass).getTableName();
    String relTable = MdEntity.getMdEntity(relClass).getTableName();
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();

    return "(select pJoin.id AS id, tJoin." + Term.NAME + " as " + attribute + "_displayLabel from" + " " + parentTable + " AS pJoin LEFT JOIN " + relTable + " rJoin ON rJoin." + RelationshipDAOIF.PARENT_ID_COLUMN + " = pJoin.id" + " LEFT JOIN " + termTable + " tJoin on rJoin." + RelationshipDAOIF.CHILD_ID_COLUMN + " = tJoin.id)";
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
          if (ind != -1)
          {
            String attr = attributeName.substring(0, ind);
            if (termAttrib.equals(attr))
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

  public static boolean joinTermAllpaths(ValueQuery valueQuery, String klass, GeneratedEntityQuery query)
  {

    String tableAlias = query.getTableAlias();

    return joinTermAllpaths(valueQuery, klass, tableAlias);
  }

  public static boolean joinTermAllpaths(ValueQuery valueQuery, String klass, GeneratedEntityQuery query, Map<String, Restriction> restrictions)
  {
    String tableAlias = query.getTableAlias();

    return joinTermAllpaths(valueQuery, klass, tableAlias, restrictions);
  }

  public static boolean joinTermAllpaths(ValueQuery valueQuery, String klass, String tableAlias)
  {

    String[] termAttributes = filterSelectedAttributes(valueQuery, Term.getTermAttributes(klass));

    // optimization: do nothing if there are no terms selected
    if (termAttributes.length > 0)
    {
      String id = getIdColumn();

      String sql = "(" + QueryUtil.getTermSubSelect(klass, termAttributes) + ")";
      // String subSelect = klass.replace('.', '_') + "TermSubSel";
      String subSelect = tableAlias + "_TermSubSel";
      String table = MdEntity.getMdEntity(klass).getTableName();
      valueQuery.AND(new InnerJoinEq(id, table, tableAlias, id, sql, subSelect));

      return true;
    }
    return false;

  }

  public static boolean joinTermAllpaths(ValueQuery valueQuery, String klass, String tableAlias, Map<String, Restriction> aggregations)
  {
    // This operation is expensive, so only do it once.
    List<String> termAttributes = Arrays.asList(Term.getTermAttributes(klass));

    boolean found = false;

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
        String attributeName = s.getDbColumnName();


        for (String termAttrib : termAttributes)
        {
          int ind = attributeName.lastIndexOf(DISPLAY_LABEL_SUFFIX);
          if (ind != -1)
          {
            String attr = attributeName.substring(0, ind);

            /*
             * Only join for term attributes
             */
            if (termAttrib.equals(attr))
            {
              found = true;

              String id = getIdColumn();

              StringBuffer sql = new StringBuffer("(" + "\n");

              String termTable = MdEntity.getMdEntity(Term.CLASS).getTableName();

              MdEntityDAOIF targetMdBusiness = MdEntityDAO.getMdEntityDAO(klass);
              MdAttributeConcreteDAOIF mdAttribute = targetMdBusiness.definesAttribute(attr);

              String tableName = targetMdBusiness.getTableName();
              String columName = mdAttribute.getColumnName();

              sql.append("       SELECT " + tableName + ".id AS id, term0.name AS " + attr + "_displayLabel" + "\n");
              sql.append("       FROM " + tableName + " AS " + tableName + "\n");

              String columnAlias = s.getUserDefinedAlias();

              if(aggregations.containsKey(columnAlias) && aggregations.get(columnAlias).getRestrictions().size() > 0)
              {
                sql.append("       LEFT JOIN allpaths_ontology AS allpaths_ontology_6 ON " + tableName + "." + columName + "  = allpaths_ontology_6.child_term" + "\n");

                Restriction aggregation = aggregations.get(columnAlias);

                if (aggregation.getAggregate())
                {
                  sql.append("       LEFT JOIN " + termTable + " AS term0 on allpaths_ontology_6.parent_term = term0.id" + "\n");
                }
                else
                {
                  sql.append("       LEFT JOIN " + termTable + " AS term0 on allpaths_ontology_6.child_term = term0.id" + "\n");
                }

                List<String> restrictions = aggregation.getRestrictions();

                if (restrictions.size() > 0)
                {
                  for (int i = 0; i < restrictions.size(); i++)
                  {
                    if (i == 0)
                    {
                      sql.append("       WHERE ");
                    }
                    else
                    {
                      sql.append("       OR ");
                    }

                    sql.append("allpaths_ontology_6.parent_term = '" + restrictions.get(i) + "'" + "\n");
                  }
                }
              }
              else
              {
                sql.append("       LEFT JOIN " + termTable + " AS term0 ON " + tableName + "." + columName + " = term0.id" + "\n");
              }

              // String subSelect = klass.replace('.', '_') + "TermSubSel";
              sql.append("     )");

              String subSelect = tableAlias + "_" + columName;
              String table = MdEntity.getMdEntity(klass).getTableName();
              valueQuery.AND(new InnerJoinEq(id, table, tableAlias, id, sql.toString(), subSelect));
            }
          }
        }
      }
    }

    return found;
  }

  public static ValueQuery leftJoinTermDisplayLabels(ValueQuery valueQuery, GeneratedEntityQuery query, String attributeId)
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

  public static void subselectGeoDisplayLabels(SelectableSQLCharacter geoLabel, String klass, String attributeName, String attributeAlias)
  {
    MdBusiness md = MdBusiness.getMdBusiness(klass);
    String tableName = md.getTableName();
    String attribCol = QueryUtil.getColumnName(klass, attributeName);

    String sql = "SELECT " + LABEL_COLUMN + " FROM " + GEO_DISPLAY_LABEL + " AS gdl JOIN " + tableName + " p ON p." + attribCol + " = gdl.id";

    sql += " WHERE p.id = " + attributeAlias + "";

    geoLabel.setSQL(sql);
  }

  public static ValueQuery joinEnumerationDisplayLabels(ValueQuery valueQuery, String klass, GeneratedEntityQuery query)
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

  public static ValueQuery leftJoinEnumerationDisplayLabels(ValueQuery valueQuery, String klass, GeneratedEntityQuery query, String attributeId)
  {
    MdEntityDAOIF enumMaster = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String tableEnum = enumMaster.getTableName();
    String displayLabelColumn = getColumnName(enumMaster, EnumerationMaster.DISPLAYLABEL);

    SelectableSQL[] enumAttributes = filterSelectedSelectables(valueQuery, QueryUtil.getEnumAttributes(klass));
    String tableName = MdBusiness.getMdBusiness(klass).getTableName();

    MdEntityDAOIF mdDisplayLabel = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String tableDisplay = mdDisplayLabel.getTableName();
    String defaultLocaleColumn = getColumnName(mdDisplayLabel, MetadataDisplayLabel.DEFAULTLOCALE);

    for (SelectableSQL s : Arrays.asList(enumAttributes))
    {
      String attrib = s.getColumnAlias();
      attrib = attrib.substring(0, attrib.length() - DISPLAY_LABEL_SUFFIX.length());
      attrib = getColumnName(klass, attrib);
      String sql = " SELECT " + defaultLocaleColumn + " FROM " + tableEnum + " em JOIN " + tableDisplay + " md on em." + displayLabelColumn + " = md.id  ";
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
  public static String[] getEnumAttributes(String className)
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

          // The default convention is that the child in the relationship is the
          // Term class
          String sql = "SELECT " + attrCol + " FROM " + table + " WHERE " + childColumn + " = '" + term_id + "' " + "AND " + parentColumn + " = " + tableAlias + ".id";

          ( (SelectableSQL) s ).setSQL(sql);
        }
      }
    }
    return foundGrid;
  }

  public static String getTermSubSelect(String className, String... attributes)
  {
    String termTable = MdEntity.getMdEntity(Term.CLASS).getTableName();
    MdEntityDAOIF targetMdBusiness = MdEntityDAO.getMdEntityDAO(className);
    String tableName = targetMdBusiness.getTableName();

    String select = "SELECT " + tableName + ".id ,";
    String from = " FROM " + tableName + " as " + tableName;

    int count = 0;
    Map<String, ? extends MdAttributeConcreteDAOIF> attrMap = targetMdBusiness.getAllDefinedMdAttributeMap();
    for (String attr : attributes)
    {
      select += " term" + count + "." + Term.NAME + " as " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      MdAttributeConcreteDAOIF attrMd = attrMap.get(attr.toLowerCase());
      String attrColumn = attrMd != null ? getColumnName(attrMd) : getColumnName(targetMdBusiness, attr);

      from += " LEFT JOIN " + termTable + " as term" + count + " on " + tableName + "." + attrColumn + " = term" + count + ".id";

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
      String geoDisplayLabelAlias = "geo" + count;
      select += geoDisplayLabelAlias + "." + LABEL_COLUMN + " AS " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      String geoCol = getColumnName(md, attr);
      from += " LEFT JOIN " + GEO_DISPLAY_LABEL + " AS " + geoDisplayLabelAlias + " ON " + tableName + "." + geoCol + " = geo" + count + ".id";

      count++;
    }

    String sql = select + from;

    return sql;
  }

  public static ValueQuery getGeoDisplayLabel()
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery vQuery = new ValueQuery(factory);

    MdTypeQuery mdTypeQuery = new MdTypeQuery(vQuery);
    GeoEntityQuery geoEntityQuery = new GeoEntityQuery(vQuery);
    GeoHierarchyQuery geoHierarchyQuery = new GeoHierarchyQuery(vQuery);
    TermQuery termQuery = new TermQuery(vQuery);

    Coalesce subType = F.COALESCE(F.CONCAT(" : ", termQuery.getTermDisplayLabel().localize()), vQuery.aSQLCharacter("EMPTY", "''"));
    CONCAT universal = F.CONCAT(mdTypeQuery.getDisplayLabel().localize(), subType);
    CONCAT sublabel = F.CONCAT(F.CONCAT(" (", universal), " )");

    vQuery.SELECT(geoEntityQuery.getId(), F.CONCAT(geoEntityQuery.getEntityLabel().localize(), sublabel));
    vQuery.WHERE(geoEntityQuery.getTerm().LEFT_JOIN_EQ(termQuery));
    vQuery.AND(mdTypeQuery.getId().EQ(geoHierarchyQuery.getGeoEntityClass().getId()));
    vQuery.AND(geoEntityQuery.getType().EQ(F.CONCAT(F.CONCAT(mdTypeQuery.getPackageName(), "."), mdTypeQuery.getTypeName())));

    return vQuery;
  }

  public static String getGeoDisplayLabelSQL(boolean withGeoId)
  {
    // Define the aliases
    String GEO_ALIAS = "geo";
    String GEO_LABEL = "geoLabel";
    String TERM_DISPLAY_ALIAS = "termLabel";
    String TERM_ALIAS = "term";
    String MD_TYPE_ALIAS = "md";
    String TYPE_DISPLAY_ALIAS = "typeLabel";

    // Define the tables
    String mdTypeTable = MdEntity.getMdEntity(MdType.CLASS).getTableName();
    String metadataLabelTable = MdEntity.getMdEntity(MetadataDisplayLabel.CLASS).getTableName();
    String geoEntityTable = MdEntity.getMdEntity(GeoEntity.CLASS).getTableName();
    String geoLabelTable = MdEntity.getMdEntity(GeoEntityEntityLabel.CLASS).getTableName();
    String termTable = MdEntity.getMdEntity(Term.CLASS).getTableName();
    String termLabelTable = MdEntity.getMdEntity(TermTermDisplayLabel.CLASS).getTableName();

    // Define the columns
    String idColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.ID);
    String geoId = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.GEOID);
    String termColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.TERM);
    String typeColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.TYPE);
    String entityLabelColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.ENTITYLABEL);
    String entityLabelIdColumn = QueryUtil.getColumnName(GeoEntityEntityLabel.CLASS, GeoEntity.ID);
    String termLabelColumn = QueryUtil.getColumnName(Term.CLASS, Term.TERMDISPLAYLABEL);
    String labelColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.DISPLAYLABEL);
    String packageColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.PACKAGENAME);
    String typeNameColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.TYPENAME);

    StringBuffer buffer = new StringBuffer();

    buffer.append("SELECT " + GEO_ALIAS + "." + idColumn + ", " + geoId + ", " + QueryUtil.getLocaleCoalesce(GEO_LABEL + ".") + " || ' (' || \n");
    buffer.append(QueryUtil.getLocaleCoalesce("" + TYPE_DISPLAY_ALIAS + ".") + " ||\n");
    buffer.append(QueryUtil.getLocaleCoalesce("' : ' || " + TERM_DISPLAY_ALIAS + ".", "''") + " || ')'");

    if (withGeoId)
    {
      buffer.append(" || ' - ' ||  " + GEO_ALIAS + "." + geoId);
    }

    buffer.append(" AS " + QueryUtil.LABEL_COLUMN + "\n");
    buffer.append("FROM  \n");
    buffer.append(geoEntityTable + " " + GEO_ALIAS + " \n");
    buffer.append("INNER JOIN " + geoLabelTable + " " + GEO_LABEL + " ON " + GEO_ALIAS + "." + entityLabelColumn + " = " + GEO_LABEL + "." + entityLabelIdColumn + "\n");
    buffer.append("INNER JOIN " + mdTypeTable + " " + MD_TYPE_ALIAS + " ON " + GEO_ALIAS + "." + typeColumn + " =  (" + MD_TYPE_ALIAS + "." + packageColumn + " || '.' || " + MD_TYPE_ALIAS + "." + typeNameColumn + ")\n");
    buffer.append("INNER JOIN " + metadataLabelTable + " " + TYPE_DISPLAY_ALIAS + " ON " + MD_TYPE_ALIAS + "." + labelColumn + " = " + TYPE_DISPLAY_ALIAS + "." + idColumn + " \n");
    buffer.append("LEFT JOIN " + termTable + " AS " + TERM_ALIAS + " ON " + TERM_ALIAS + "." + idColumn + " = " + GEO_ALIAS + "." + termColumn + " \n");
    buffer.append("LEFT JOIN " + termLabelTable + " AS " + TERM_DISPLAY_ALIAS + " ON " + TERM_DISPLAY_ALIAS + "." + idColumn + " = " + TERM_ALIAS + "." + termLabelColumn + " \n");

    return buffer.toString();
  }

  public static List<String> getSupportedSubLocales(Locale locale)
  {
    List<String> subLocales = new LinkedList<String>();

    String localeString = locale.toString().toLowerCase();

    SupportedLocaleQuery installedLocales = LocalizationFacade.getInstalledLocales();
    OIterator<? extends SupportedLocale> iterator = installedLocales.getIterator();

    List<String> supportedLocales = new LinkedList<String>();

    while (iterator.hasNext())
    {
      SupportedLocale supportedLocale = (SupportedLocale) iterator.next();
      supportedLocales.add(supportedLocale.getEnumName().toLowerCase());
    }

    for (int i = localeString.length(); i > 0; i = localeString.lastIndexOf('_', i - 1))
    {
      String subLocale = localeString.substring(0, i).toLowerCase();

      if (supportedLocales.contains(subLocale))
      {
        subLocales.add(subLocale);
      }
    }

    return subLocales;
  }

  public static List<String> getLocaleColumns()
  {
    List<String> columns = new LinkedList<String>();

    Locale locale = Session.getCurrentLocale();
    List<String> subLocales = QueryUtil.getSupportedSubLocales(locale);

    MdDimensionDAOIF dimension = Session.getCurrentDimension();
    if (dimension != null)
    {
      String dimensionDefault = dimension.getDefaultLocaleAttributeName();

      // First do all of the locales for the current dimension
      for (String subLocale : subLocales)
      {
        columns.add(MetadataDAO.convertCamelCaseToUnderscore(dimension.getLocaleAttributeName(subLocale)));
      }

      // Add the default dimension key
      columns.add(MetadataDAO.convertCamelCaseToUnderscore(dimensionDefault));

      // Add the non dimension locales
      for (String subLocale : subLocales)
      {
        columns.add(subLocale);
      }
    }

    // Add the default locale
    String key = MetadataDisplayLabel.CLASS + "." + MetadataDisplayLabel.DEFAULTLOCALE;
    String defaultLocaleColumn = MdAttributeConcrete.getByKey(key).getColumnName();

    columns.add(defaultLocaleColumn);

    return columns;
  }

  public static String getLocaleCoalesce(String prefix, String... extras)
  {
    StringBuffer buffer = new StringBuffer();
    List<String> columns = QueryUtil.getLocaleColumns();

    for (String column : columns)
    {
      buffer.append(", ");
      buffer.append(prefix);
      buffer.append(column);
    }

    for (String extra : extras)
    {
      buffer.append(", ");
      buffer.append(extra);
    }

    String coalesce = "COALESCE(" + buffer.toString().replaceFirst(",", "") + ")";

    return coalesce;
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
      select += "(SELECT " + localeColumns + " FROM " + tableEnum + " em join " + tableDisplay + " md on em." + displayLabelColumn + " = md.id  WHERE em.id = " + attrCol + "_c) as " + attr + "_displayLabel";

      if (count != attributes.length - 1)
      {
        select += ",";
      }

      count++;
    }

    String sql = select + from;

    return sql;
  }
  
  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap,
      boolean ignoreCriteria, Selectable diseaseSel)
  {
    return setQueryDates(xml, valueQuery, queryConfig, queryMap, ignoreCriteria, diseaseSel, null);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap,
      boolean ignoreCriteria, Selectable diseaseSel, SelectableMoment dateSel)
  {
    String attributeName = null;
    String startValue = null;
    String endValue = null;
    String klass = null;
    JSONObject dateObj = null;
    try
    {
      dateObj = queryConfig.getJSONObject(DATE_ATTRIBUTE);

      if (dateObj.has(DATE_ATTRIBUTE))
      {
        attributeName = dateObj.getString(DATE_ATTRIBUTE);
        klass = dateObj.getString(DATE_CLASS);

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

        if (dateObj.has(DATE_START) && !dateObj.isNull(DATE_START) && !dateObj.getString(DATE_START).equals("null"))
        {
          startValue = dateObj.getString(DATE_START);

          if (!ignoreCriteria)
          {
            AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);

            if (startValue != null && startValue.equals("NOW"))
            {
              valueQuery.AND(dateAttriute.GE(valueQuery.aSQLDate("CURRENT_START_DATE", "now()::date")));
            }
            else
            {
              valueQuery.AND(dateAttriute.GE(startValue));
            }
          }

        }
        if (dateObj.has(DATE_END) && !dateObj.isNull(DATE_END) && !dateObj.getString(DATE_END).equals("null"))
        {
          endValue = dateObj.getString(DATE_END);

          if (!ignoreCriteria)
          {
            AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);

            if (endValue != null && endValue.equals("NOW"))
            {
              valueQuery.AND(dateAttriute.LE(valueQuery.aSQLDate("CURRENT_END_DATE", "now()::date")));
            }
            else
            {
              valueQuery.AND(dateAttriute.LE(endValue));
            }

          }
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

        // If a SelectableMoment is not provided, extract one from the map. While a hack, this allows a QB to
        // override the Selectable that will be plugged into the date groups (eg, DATEGROUP_SEASON).
        if(dateSel != null)
        {
          String dbCol = dateSel.getSQL();
          return setQueryDates(xml, valueQuery, attributeQuery, dbCol, diseaseSel);
        }
        else
        {
          return setQueryDates(xml, valueQuery, attributeQuery, (SelectableMoment) attributeQuery.get(attributeName), diseaseSel);
        }
      }

      return valueQuery;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

  }
  
  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap, 
      boolean ignoreCriteria)
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
      klass = dateObj.getString(DATE_CLASS);

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

      if (dateObj.has(DATE_START) && !dateObj.isNull(DATE_START) && !dateObj.getString(DATE_START).equals("null"))
      {
        startValue = dateObj.getString(DATE_START);

        if (!ignoreCriteria)
        {
          AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);
          valueQuery.AND(dateAttriute.GE(startValue));
        }

      }
      if (dateObj.has(DATE_END) && !dateObj.isNull(DATE_END) && !dateObj.getString(DATE_END).equals("null"))
      {
        endValue = dateObj.getString(DATE_END);

        if (!ignoreCriteria)
        {
          AttributeMoment dateAttriute = (AttributeMoment) attributeQuery.get(attributeName);
          valueQuery.AND(dateAttriute.LE(endValue));
        }
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

      return setQueryDates(xml, valueQuery, attributeQuery, (SelectableMoment) attributeQuery.get(attributeName));
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

  }
  
  
  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap)
  {
    return setQueryDates(xml, valueQuery, queryConfig, queryMap, false);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, JSONObject queryConfig, Map<String, GeneratedEntityQuery> queryMap, Selectable diseaseSel)
  {
    return setQueryDates(xml, valueQuery, queryConfig, queryMap, false, diseaseSel, null);
  }
  
  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target, String da, Selectable diseaseSel)
  {
    Set<String> found = new HashSet<String>();
    
    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      found.add(DATEGROUP_SEASON);
      
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_SEASON);
      
      String sql = QueryUtil.getSeasonNameSQL(diseaseSel, da, da);
      dateGroup.setSQL(sql);
    }
    
    return setQueryDates(xml, valueQuery, target, da, found);
    
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target, SelectableMoment daSel, Selectable diseaseSel)
  {
    String da = daSel.getDbQualifiedName();
    return setQueryDates(xml, valueQuery, target, da, diseaseSel);
  }
  
  public static String getSeasonNameSQL(String diseaseSel, String startDateColumnName, String endDateColumnName)
  {
    StringBuffer buffer = new StringBuffer();
    buffer.append("SELECT " + getSeasonNameSelect());
    buffer.append(" FROM " + getSeasonNameFrom(diseaseSel, startDateColumnName, endDateColumnName, true));
    return buffer.toString();
  }
  
  public static String getSeasonNameSelect()
  {
    return QueryUtil.getLocaleCoalesce(LABEL_ALIAS + ".");
  }
  
  public static String getSeasonNameFrom(String diseaseSel, String startDateColumnName, String endDateColumnName, boolean standalone)
  {
    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    String table = malariaSeasonMd.getTableName();
    String seasonLabelCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.SEASONLABEL);
    String startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    String endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);
    String disease = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.DISEASE);
    
    MdEntityDAOIF seasonLabelMd = MdEntityDAO.getMdEntityDAO(MalariaSeasonSeasonLabel.CLASS);
    String labelTable = seasonLabelMd.getTableName();
    String seasonLabelIdCol = QueryUtil.getColumnName(seasonLabelMd, MalariaSeasonSeasonLabel.ID);
    
    StringBuffer buffer = new StringBuffer();
    

    if(standalone)
    {
      buffer.append(" "+table + " AS " + TABLE_ALIAS);
      buffer.append(" INNER JOIN " + labelTable + " AS " + LABEL_ALIAS + " ON " + TABLE_ALIAS + "." + seasonLabelCol + " = " + LABEL_ALIAS + "." + seasonLabelIdCol + "\n");
      buffer.append(" WHERE " + TABLE_ALIAS + "." + startDateCol + " <= " + startDateColumnName);
      buffer.append(" AND " + TABLE_ALIAS + "." + endDateCol + " >= " + endDateColumnName);
      buffer.append(" AND " + TABLE_ALIAS + "." + disease + " = " + diseaseSel);
    }
    else
    {
      buffer.append(" " + table + " AS " + TABLE_ALIAS +" \n");
      buffer.append(" ON " + TABLE_ALIAS + "." + startDateCol + " <= " + startDateColumnName+" \n");
      buffer.append(" AND " + TABLE_ALIAS + "." + endDateCol + " >= " + endDateColumnName+" \n");
      buffer.append(" AND " + TABLE_ALIAS + "." + disease + " = " + diseaseSel+" \n");
      buffer.append(" INNER JOIN " + labelTable + " AS " + LABEL_ALIAS + " ON " + TABLE_ALIAS + "." + seasonLabelCol + " = " + LABEL_ALIAS + "." + seasonLabelIdCol + "\n");
    }
    
    return buffer.toString();
  }
  
  public static String getSeasonNameSQL(Selectable diseaseSel, String startDateColumnName, String endDateColumnName)
  {
    return getSeasonNameSQL(diseaseSel.getDbQualifiedName(), startDateColumnName, endDateColumnName);
  }

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target, SelectableMoment daSel)
  {
    String da = daSel.getDbQualifiedName();
    Set<String> found = new HashSet<String>();

    return setQueryDates(xml, valueQuery, target, da, found);
  }

  public static String getEpiWeekSQL(String da)
  {
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    return "get_epiWeek_from_date(" + da + "," + startDay + ")";
  }
  
  public static String getMonthSQL(String da)
  {
    return "to_char(" + da + ",'MM')";
  }

  public static String getQuarterSQL(String da)
  {
    return "to_char(" + da + ",'Q')";
  }
  
  public static String getCalendarYearSQL(String da)
  {
    return "to_char(" + da + ",'YYYY')";
  }
  
  public static String getEpiYearSQL(String da)
  {
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    return "get_epiYear_from_date(" + da + "," + startDay + ")";
  }
  
  private static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target, String da, Set<String> found)
  {
    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      found.add(DATEGROUP_EPIWEEK);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_EPIWEEK);
      dateGroup.setSQL(getEpiWeekSQL(da));
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      found.add(DATEGROUP_MONTH);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_MONTH);
      dateGroup.setSQL(getMonthSQL(da));
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      found.add(DATEGROUP_QUARTER);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_QUARTER);
      dateGroup.setSQL(getQuarterSQL(da));
    }

    if (xml.indexOf(DATEGROUP_CALENDARYEAR) > 0)
    {
      found.add(DATEGROUP_CALENDARYEAR);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_CALENDARYEAR);
      dateGroup.setSQL(getCalendarYearSQL(da));
    }

    if (xml.indexOf(DATEGROUP_EPIYEAR) > 0)
    {
      found.add(DATEGROUP_EPIYEAR);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_EPIYEAR);
      dateGroup.setSQL(getEpiYearSQL(da));
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
  private static void ensureEntityInFromClause(Set<String> found, ValueQuery valueQuery, GeneratedEntityQuery target)
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

  public static ValueQuery setQueryDates(String xml, ValueQuery valueQuery, GeneratedEntityQuery target, SelectableMoment sdSel, SelectableMoment edSel, DiseaseQueryReferenceIF diseaseSel)
  {
    String sd = sdSel.getDbQualifiedName();
    String ed = edSel.getDbQualifiedName();

    Set<String> found = new HashSet<String>();

    String intervalNotValid = "INTERVAL NOT VALID";

    if (xml.indexOf(DATEGROUP_SEASON) > 0)
    {
      found.add(DATEGROUP_SEASON);

      String sql = QueryUtil.getSeasonNameSQL(diseaseSel, sd, ed);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_SEASON);
      dateGroup.setSQL(sql);
    }

    if (xml.indexOf(DATEGROUP_EPIWEEK) > 0)
    {
      found.add(DATEGROUP_EPIWEEK);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_EPIWEEK);
      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '7 days') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(Day FROM " + sd + ") - extract(DOW FROM date_trunc('week'," + ed + "))) > extract(DOW FROM " + ed + ")" + "THEN get_epiWeek_from_date(" + sd + "," + startDay + ")::TEXT " + "ELSE get_epiWeek_from_date(" + ed + "," + startDay + ")::TEXT  END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_MONTH) > 0)
    {
      found.add(DATEGROUP_MONTH);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_MONTH);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 month') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DAY FROM " + sd + ") - extract(DAY FROM date_trunc('month'," + ed + "))) > extract(DAY FROM " + ed + ")" + "THEN to_char(" + sd + ",'MM')" + "ELSE to_char(" + ed + ",'MM') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_QUARTER) > 0)
    {
      found.add(DATEGROUP_QUARTER);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_QUARTER);

      String dateGroupSql = "CASE WHEN (" + sd + " + interval '3 months') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + " >  (extract(DOY FROM " + ed + ") - extract(DOY FROM date_trunc('quarter'," + ed + ")))" + "THEN to_char(" + sd + ",'Q')" + "ELSE to_char(" + ed + ",'Q') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_CALENDARYEAR) > 0)
    {
      found.add(DATEGROUP_CALENDARYEAR);

      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_CALENDARYEAR);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 year') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + " >  (extract(DOY FROM " + ed + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + "THEN to_char(" + sd + ",'YYYY')" + "ELSE to_char(" + ed + ",'YYYY') END";
      dateGroup.setSQL(dateGroupSql);
    }

    if (xml.indexOf(DATEGROUP_EPIYEAR) > 0)
    {
      found.add(DATEGROUP_EPIYEAR);

      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
      SelectableSQLCharacter dateGroup = (SelectableSQLCharacter) valueQuery.getSelectableRef(DATEGROUP_EPIYEAR);
      String dateGroupSql = "CASE WHEN (" + sd + " + interval '1 year') < " + ed + "  THEN '" + intervalNotValid + "'" + "WHEN (extract(DOY FROM " + sd + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + " >  (extract(DOY FROM " + ed + ") - extract(DOY FROM date_trunc('year'," + ed + ")))" + "THEN  get_epiYear_from_date(" + sd + "," + startDay + ")::TEXT" + " ELSE  get_epiYear_from_date(" + ed + "," + startDay + ")::TEXT END";
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
      Pattern pattern = Pattern.compile("<operator>GE</operator>\\W{1,2}<value>(" + DATE_REGEX + ")</value>");
      Matcher matcher = pattern.matcher(xml);
      if (matcher.find())
      {
        dateGroup.setSQL("'" + matcher.group(1) + "'");
      }
    }

    if (xml.indexOf(END_DATE_RANGE) > 0)
    {
      SelectableSQLDate dateGroup = (SelectableSQLDate) valueQuery.getSelectableRef(END_DATE_RANGE);
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
      SelectableSQL ratio = (SelectableSQL) valueQuery.getSelectableRef(RATIO);

      ratio.setSQL("(" + countSql + "/(SUM(" + countSql + ") OVER ())::float)::decimal(20,3)");
    }

    return valueQuery;

  }

}
