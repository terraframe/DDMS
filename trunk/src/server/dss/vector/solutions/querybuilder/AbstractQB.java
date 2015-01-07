package dss.vector.solutions.querybuilder;

import java.util.ArrayList;
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

import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.AVG;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeCondition;
import com.runwaysdk.query.AttributeDate;
import com.runwaysdk.query.AttributeDateTime;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.AttributeTime;
import com.runwaysdk.query.COUNT;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedRelationshipQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.LeftJoin;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.MIN;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.RawLeftJoinEq;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableDecimal;
import com.runwaysdk.query.SelectableDouble;
import com.runwaysdk.query.SelectableFloat;
import com.runwaysdk.query.SelectableInteger;
import com.runwaysdk.query.SelectableLong;
import com.runwaysdk.query.SelectableNumber;
import com.runwaysdk.query.SelectableReference;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.SelectableSQLDateTime;
import com.runwaysdk.query.SelectableSQLLong;
import com.runwaysdk.query.SelectableSQLTime;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.UsersQuery;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.query.AllRenderTypes;
import dss.vector.solutions.query.CountOrRatioAloneException;
import dss.vector.solutions.query.DatesOnlyException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.NoColumnsAddedException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.irs.Alias;
import dss.vector.solutions.querybuilder.util.QBInterceptor;
import dss.vector.solutions.util.QueryUtil;
import dss.vector.solutions.util.Restriction;

public abstract class AbstractQB implements Reloadable
{
  public static final String WINDOW_COUNT_ALIAS  = "dss_vector_solutions__window_count";

  public static final String IMPORTED_DATETIME   = "imported_datetime";

  public static final String PARENT_UNIVERSAL_ID = "parentUniversalId";

  /**
   * Class to help with the structure of the join criteria for GeoEntity data and mapping.
   */
  private class GeoEntityJoinData implements Reloadable
  {

    private String                        entityNameAlias;

    private String                        idAlias;

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
      idAlias = null;
      attributeKeysAndJoins = new HashMap<String, List<ValueQuery>>();
    }
  }

  protected class AliasPair implements Reloadable
  {
    private String tableAlias;

    private String attributeAlias;

    protected AliasPair(String tableAlias, String attributeAlias)
    {
      this.tableAlias = tableAlias;
      this.attributeAlias = attributeAlias;
    }

    public String getAttributeAlias()
    {
      return attributeAlias;
    }

    public String getTableAlias()
    {
      return tableAlias;
    }
  }

  protected class ClassAttributePair implements Reloadable
  {
    private String klass;

    private String attribute;

    private ClassAttributePair(String klass, String attribute)
    {
      this.klass = klass;
      this.attribute = attribute;
    }

    private String getClazz()
    {
      return klass;
    }

    public String getAttribute()
    {
      return attribute;
    }
  }

  /**
   * Represents a single entry in a WITH clause
   */
  protected class WITHEntry implements Reloadable
  {
    private String name;

    private String sql;

    protected WITHEntry(String name, String sql)
    {
      this.name = name;
      this.sql = sql;
    }
  }

  protected GeoEntityJoinData               geoEntityJoinData;

  private String                            xml;

  private String                            config;

  private Layer                             layer;

  private boolean                           recursiveWithClause;

  private List<GeneratedEntityQuery>        geoDisplayLabelQueries;

  private QueryFactory                      factory;

  private ValueQuery                        valueQuery;

  private ValueQueryParser                  parser;

  private List<WITHEntry>                   withEntries;

  private boolean                           hasUniversal;

  private Integer                           pageNumber;

  private Integer                           pageSize;

  private Map<String, Restriction>          restrictions;

  private JSONObject                        queryConfig;

  private Map<String, GeneratedEntityQuery> queryMap;

  private Disease                           disease;
  
  protected Condition                         geoIncludesCondition;

  public AbstractQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    this.queryConfig = null;
    this.hasUniversal = false;
    this.recursiveWithClause = false;
    this.xml = xml;
    this.config = config;
    this.layer = layer;
    this.geoDisplayLabelQueries = new LinkedList<GeneratedEntityQuery>();

    this.queryMap = null;
    this.factory = null;
    this.valueQuery = null;
    this.parser = null;
    this.withEntries = new LinkedList<WITHEntry>();
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.geoEntityJoinData = new GeoEntityJoinData();
    this.disease = disease;
  }

  public synchronized Disease getDisease()
  {
    if (this.disease == null)
    {
      this.disease = Disease.getCurrent();
    }

    return this.disease;
  }

  public synchronized void setDisease(Disease disease)
  {
    this.disease = disease;
  }

  public boolean hasUniversal()
  {
    return this.hasUniversal;
  }

  public Integer getPageNumber()
  {
    return pageNumber;
  }

  public Integer getPageSize()
  {
    return pageSize;
  }

  protected void setWITHRecursive(boolean recursive)
  {
    this.recursiveWithClause = recursive;
  }

  protected void addWITHEntry(WITHEntry entry)
  {
    withEntries.add(entry);
  }

  protected List<WITHEntry> getWITHEntries()
  {
    return withEntries;
  }

  protected String getXml()
  {
    return xml;
  }

  protected String getConfig()
  {
    return config;
  }

  public JSONObject getQueryConfig()
  {
    return queryConfig;
  }

  protected Layer getLayer()
  {
    return layer;
  }

  protected Map<String, GeneratedEntityQuery> getQueryMap()
  {
    return queryMap;
  }

  protected void initialConstruct()
  {
    // Query Config
    this.queryConfig = this.constructQueryConfig(this.config);

    factory = this.createFactory();
    valueQuery = this.createValueQuery();
    List<ParseInterceptor> interceptors = this.createParseInterceptors(valueQuery);
    parser = this.createParser(valueQuery, interceptors);

    this.setGeoDisplayLabelSQL();
    
    // Universals, mapping, and geo entity criteria
    this.restrictions = this.constructTermRestrictions(queryConfig);

    this.queryMap = this.joinQueryWithGeoEntities(factory, valueQuery, this.xml, queryConfig, layer, parser);
    
    ArrayList<SelectableSQL> terms = this.getTerms(valueQuery);
    if (terms.size() > 0)
    {
      this.addTermDisplayLabelWithEntry();
      this.setTermSelectablesSQL(terms);
    }
  }
  
  protected void setTermSelectablesSQL(ArrayList<SelectableSQL> terms)
  {
    for (SelectableSQL term : terms)
    {
      term.setSQL("(SELECT " + QueryUtil.LABEL_COLUMN + " from " + QueryUtil.TERM_DISPLAY_LABEL + " WHERE " + QueryUtil.TERM_DISPLAY_LABEL + ".id=" + term.getUserDefinedAlias() + ")");
    }
  }
  
  protected void addTermDisplayLabelWithEntry()
  {
    String sql = QueryUtil.getTermDisplayLabelSQL();
    this.addWITHEntry(new WITHEntry(QueryUtil.TERM_DISPLAY_LABEL, sql));
  }
  
  // TODO : Override this method in sub classes.
  protected ArrayList<SelectableSQL> getTerms(ValueQuery valueQuery)
  {
    return new ArrayList<SelectableSQL>();
  }

  protected ValueQuery finishConstruct()
  {
    this.processAuditSelectables(valueQuery, queryMap);

    this.joinGeoDisplayLabels(valueQuery);

    QBInterceptor termInterceptor = this.getQBInterceptor(parser);
    this.setTermCriteria(valueQuery, queryMap, termInterceptor);

    this.setWITHClause(this.withEntries, this.recursiveWithClause, valueQuery);

    this.addCountSelectable(valueQuery);

    ValueQuery finalQuery = this.postProcess(valueQuery);

    return finalQuery;
  }

  /**
   * Helper method for post processing.
   * 
   * We're joining 2 tables, the aggregation and the original, that have the same columns between the two. Some of these rows may only have data in one of the columns in one of the tables, but since the columns are the same we can coalesce the data and select it from any of the columns where the data exists.
   */
  protected void coalesceSelects(ValueQuery finalVQ, ValueQuery aggregate, String originalAlias, String aggregateAlias)
  {
    // 1. Loop over all selectables in the originalVQ
    List<Selectable> finalSels = finalVQ.getSelectableRefs();
    for (Selectable finalSel : finalSels)
    {

      // 2. If the aggregate also contains this selectable
      String selName = finalSel.getDbColumnName();
      if (aggregate.hasSelectableRef(selName))
      {
        // 3. Coalesce it
        ( (SelectableSQL) finalSel ).setSQL("COALESCE(" + originalAlias + "." + selName + ", " + aggregateAlias + "." + selName + ")");
      }
    }
  }

  /**
   * Generates a ValueQuery based on the query this builder represents. Subclasses may override any of the individual steps in the query construction for finer grained control.
   */
  public final ValueQuery construct()
  {
    this.initialConstruct();

    valueQuery = this.construct(factory, valueQuery, queryMap, this.xml, queryConfig);

    return this.finishConstruct();
  }

  /**
   * By default there is no post-processing, but QBs can override this behavior to return whatever they want (I'm looking at you, IRS).
   * 
   * @param valueQuery
   * @return
   */
  protected ValueQuery postProcess(ValueQuery valueQuery)
  {
    return valueQuery;
  }

  /**
   * Copies all selectables and returns them.
   */
  protected Selectable[] copyAll(ValueQuery vq, List<Selectable> sels, String prefix, boolean preserveAggregates, ValueQuery original)
  {
    Selectable[] replacements = new Selectable[sels.size()];

    SelectableSQL newSel;
    int count = 0;
    for (Selectable sel : sels)
    {
      String alias = sel.getUserDefinedAlias();
      // create a new Selectable based off the original type (because they are
      // custom formatted later on)
      String qualifiedCol = prefix != null ? prefix + "." + sel.getColumnAlias() : sel.getColumnAlias();
      if (sel instanceof SelectableInteger)
      {
        if (preserveAggregates && original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateInteger(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLInteger(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableLong)
      {
        if (preserveAggregates && original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateLong(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLLong(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableFloat)
      {
        if (preserveAggregates && original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateFloat(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLFloat(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableDecimal)
      {
        if (preserveAggregates && original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateDecimal(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLDecimal(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableDouble)
      {
        if (preserveAggregates && original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateDouble(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLDouble(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof AttributeDate || sel instanceof SelectableSQLDate)
      {
        newSel = vq.aSQLDate(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }
      else if (sel instanceof AttributeDateTime || sel instanceof SelectableSQLDateTime)
      {
        newSel = vq.aSQLDateTime(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }
      else if (sel instanceof AttributeTime || sel instanceof SelectableSQLTime)
      {
        newSel = vq.aSQLTime(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }
      else
      {
        // use character as the final default because it's flexible
        newSel = vq.aSQLCharacter(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }

      newSel.setColumnAlias(sel.getColumnAlias());

      replacements[count++] = newSel;
    }

    return replacements;
  }

  /**
   * Returns the alias of the class in the query map from which the createdBy and lastUpdatedBy values will be pulled.
   * 
   * @return
   */
  protected abstract String getAuditClassAlias();

  protected void processAuditSelectables(ValueQuery v, Map<String, GeneratedEntityQuery> queryMap)
  {
    GeneratedEntityQuery q = queryMap.get(this.getAuditClassAlias());
    QueryFactory f = valueQuery.getQueryFactory();

    // create date
    if (v.hasSelectableRef(QueryConstants.AUDIT_CREATE_DATE_ALIAS))
    {
      SelectableSQL sel = (SelectableSQL) v.getSelectableRef(QueryConstants.AUDIT_CREATE_DATE_ALIAS);
      sel.setSQL(q.get(Metadata.CREATEDATE).getDbQualifiedName());
    }

    if (v.hasSelectableRef(QueryConstants.AUDIT_LAST_UPDATE_DATE_ALIAS))
    {
      SelectableSQL sel = (SelectableSQL) v.getSelectableRef(QueryConstants.AUDIT_LAST_UPDATE_DATE_ALIAS);
      sel.setSQL(q.get(Metadata.LASTUPDATEDATE).getDbQualifiedName());
    }

    // last update date

    // created by
    if (v.hasSelectableRef(QueryConstants.AUDIT_CREATED_BY_ALIAS))
    {
      UsersQuery uq = new UsersQuery(f);
      SelectableSQLCharacter cb = (SelectableSQLCharacter) v.getSelectableRef(QueryConstants.AUDIT_CREATED_BY_ALIAS);
      cb.setSQL(uq.getUsername().getDbQualifiedName());

      AttributeReference ref = (AttributeReference) q.get(Metadata.CREATEDBY);
      v.WHERE(ref.EQ(uq));
    }

    // last updated by
    if (v.hasSelectableRef(QueryConstants.AUDIT_LAST_UPDATED_BY_ALIAS))
    {
      UsersQuery uq = new UsersQuery(f);
      SelectableSQLCharacter cb = (SelectableSQLCharacter) v.getSelectableRef(QueryConstants.AUDIT_LAST_UPDATED_BY_ALIAS);
      cb.setSQL(uq.getUsername().getDbQualifiedName());

      AttributeReference ref = (AttributeReference) q.get(Metadata.LASTUPDATEDBY);
      v.WHERE(ref.EQ(uq));
    }

    // imported (loosely defines as true if other records have the same created
    // by timestamp)
    if (valueQuery.hasSelectableRef(QueryConstants.AUDIT_IMPORTED_ALIAS))
    {
      MdEntityDAOIF mdClass = q.getMdClassIF();
      ValueQuery withV = new ValueQuery(f);

      Attribute cd = this.getImportedDateTimeSQL(f, mdClass, withV);
      this.addWITHEntry(new WITHEntry(IMPORTED_DATETIME, withV.getSQL()));

      // END - WITH clause

      // fast solution, requires LEFT JOIN on a custom Selectable
      String sql = "CASE WHEN " + IMPORTED_DATETIME + "." + Alias.CREATE_DATE + " IS NOT NULL THEN 1 ELSE 0 END";

      // slow solution, requires sub-select
      // String sql =
      // "EXISTS (SELECT 1 FROM "+IMPORTED_DATETIME+" WHERE "+cd.getColumnAlias()+" = "+q.get(Metadata.CREATEDATE).getDbQualifiedName()+")::integer";

      SelectableSQL imported = (SelectableSQL) v.getSelectableRef(QueryConstants.AUDIT_IMPORTED_ALIAS);
      imported.setSQL(sql);

      this.joinImported(q, f, v, cd);
    }
  }

  protected Attribute getImportedDateTimeSQL(QueryFactory f, MdEntityDAOIF mdClass, ValueQuery withV)
  {
    BusinessQuery withQ = f.businessQuery(mdClass.definesType());
    Attribute cd = withQ.get(Metadata.CREATEDATE);
    cd.setColumnAlias(Alias.CREATE_DATE.getAlias());

    withV.SELECT(cd);

    withV.FROM(mdClass.getTableName(), withQ.getTableAlias());

    withV.GROUP_BY(cd);
    withV.HAVING(F.COUNT(cd).GT(1));

    return cd;
  }

  /**
   * 
   * @param q
   *          The main query class
   * @param f
   *          The query factory used for the entire query
   * @param v
   *          The primary ValueQuery
   * @param importCreateDate
   *          the IMPORT_DATETIME's create date selectable
   */
  protected void joinImported(GeneratedEntityQuery q, QueryFactory f, ValueQuery v, Selectable importCreateDate)
  {
    // RawLeftJoinEq is a horrible hack, but there's no support in Runway for
    // custom left joins
    Selectable createDate = q.get(Metadata.CREATEDATE);
    LeftJoin customJoin = new RawLeftJoinEq(createDate.getDbColumnName(), createDate.getDefiningTableName(), createDate.getDefiningTableAlias(), Alias.CREATE_DATE.getAlias(), IMPORTED_DATETIME, IMPORTED_DATETIME);

    // join on the main query
    v.WHERE(customJoin);
  }

  protected void setWITHClause(List<WITHEntry> entries, boolean recursive, ValueQuery valueQuery, String prepend)
  {
    if (this.withEntries.size() == 0)
    {
      return;
    }

    String with = "WITH ";
    if (recursive)
    {
      with += "RECURSIVE ";
    }

    int count = 0;
    for (WITHEntry entry : entries)
    {
      with += entry.name + " AS (\n" + entry.sql + "\n)";

      if (count < entries.size() - 1)
      {
        with += ",";
      }
      count++;

      with += "\n";
    }

    if (prepend != null)
    {
      with = prepend + " \n" + with;
    }

    valueQuery.setSqlPrefix(with);
  }

  /**
   * Sets the WITH clause on the value query.
   */
  protected void setWITHClause(List<WITHEntry> entries, boolean recursive, ValueQuery valueQuery)
  {
    this.setWITHClause(entries, recursive, valueQuery, null);
  }

  protected final void setGeoDisplayLabelSQL()
  {
    String sql = QueryUtil.getGeoDisplayLabelSQL(false);
    this.addWITHEntry(new WITHEntry(QueryUtil.GEO_DISPLAY_LABEL, sql));
  }

  /**
   * 
   * @param interceptors
   * @return
   */
  protected QBInterceptor getQBInterceptor(ValueQueryParser parser)
  {
    for (ParseInterceptor interceptor : parser.getInterceptors())
    {
      if (interceptor instanceof QBInterceptor)
      {
        return (QBInterceptor) interceptor;
      }
    }
    return null;
  }

  /**
   * Subclasses can use this to add any GeneratedEntityQuery objects whose geo entity attributes need to join on their display labels.
   * 
   * @param query
   */
  protected final void addGeoDisplayLabelQuery(GeneratedEntityQuery query)
  {
    this.geoDisplayLabelQueries.add(query);
  }

  /**
   * This can be used to replicate the alias build process in JavaScript for AllPaths terms. (See: QueryBaseNew.constructQuery()).
   * 
   * @param attr
   * @param klass
   * @param attrAlias
   * @return
   */
  protected String createAllPathsEntityAlias(String attr, String klass, String attrAlias)
  {
    return attr + "__" + klass.replaceAll("\\.", "_") + "__" + attrAlias;
  }

  /**
   * Extracts a class and attribute pair from the entity alias for dereferencing term criteria. If the alias is not a valid string for term criteria then null is returned.
   * 
   * @param entityAlias
   * @return
   */
  protected ClassAttributePair extractClassAndAttributeFromAlias(String entityAlias)
  {
    int index1 = entityAlias.indexOf("__");
    int index2 = entityAlias.lastIndexOf("__");
    if (index1 > 0 && index2 > 0)
    {
      String attrib_name = entityAlias.substring(0, index1);
      String klass = entityAlias.substring(index1 + 2, index2).replace("_", ".");
      return new ClassAttributePair(klass, attrib_name);
    }
    else
    {
      return null;
    }
  }

  /**
   * This allows subclasses to provide a list of aliases for tables and attributes. This is useful when the QueryAPI assumes one table/alias when another should be used (in the case of custom SQL). Ah, who am I kidding? This is only for the IRS QB.
   * 
   * @return
   */
  protected Map<String, AliasPair> getAliasPairs()
  {
    return new HashMap<String, AliasPair>();
  }

  /**
   * Sets the term criteria on the given ValueQuery.
   * 
   * @param valueQuery
   * @param queryMap
   * @param interceptor
   */
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, QBInterceptor interceptor)
  {
    if (interceptor == null)
    {
      return;
    }

    for (String entityAlias : queryMap.keySet())
    {
      ClassAttributePair pair = this.extractClassAndAttributeFromAlias(entityAlias);
      if (pair != null)
      {
        String klass = pair.getClazz();
        String attrib_name = pair.getAttribute();
        if (queryMap.get(entityAlias) instanceof dss.vector.solutions.ontology.AllPathsQuery)
        {
          dss.vector.solutions.ontology.AllPathsQuery allPathsQuery = (dss.vector.solutions.ontology.AllPathsQuery) queryMap.get(entityAlias);
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);

          // IMPORTANT: We cannot always rely on the class table directly
          // because the attribute
          // may have been defined by a parent class, hence it will be on the
          // parent table.
          // Instead, rely on the query and metadata to resolve the
          // class/attribute mapping.
          String alias;
          String attrCol;

          // check for a hard-coded override first (IRS uses this, of course)
          Map<String, AliasPair> aliasPairMap = this.getAliasPairs();
          if (aliasPairMap.containsKey(entityAlias))
          {
            AliasPair aPair = aliasPairMap.get(entityAlias);
            alias = aPair.getTableAlias();
            attrCol = aPair.getAttributeAlias();
          }
          else
          {
            // not using a custom selectable so get the column based on the
            // metadata
            attrCol = QueryUtil.getColumnName(attributeQuery.getMdClassIF(), attrib_name);
            if (attributeQuery instanceof GeneratedRelationshipQuery && ( attrCol.equals(RelationshipInfo.CHILD_ID) || attrCol.equals(RelationshipInfo.PARENT_ID) ))
            {
              // We don't have metadata for childId or parentId so we have to
              // manually get the table and alias
              // IMPORTANT: this does not take inheritance into account (i.e.,
              // if
              // child_id or parent_id are
              // defined by an MdRelationship superclass).
              alias = attributeQuery.getTableAlias();
            }
            else
            {
              com.runwaysdk.query.Attribute attr = attributeQuery.get(attrib_name);

              // first check to see if attributeQuery directly defines the
              // metadata
              // or it's defined on a superclass. If it's on the suberclass then
              // use that table
              // alias instead.
              String originalClass = attr.getMdAttributeIF().definedByClass().definesType();
              if (originalClass.equals(klass))
              {
                alias = attr.getDefiningTableAlias();
              }
              else if (queryMap.containsKey(originalClass))
              {
                alias = queryMap.get(originalClass).getTableAlias();
              }
              else
              {
                alias = attr.getDefiningTableAlias();
              }
            }
          }

          // ValueQuery termVQ = interceptor.getTermValueQuery(entityAlias);
          // termVQ.SELECT(termVQ.aSQLInteger("existsConstant", "1"));
          // termVQ.AND(allPathsQuery.getChildTerm().EQ(termVQ.aSQLCharacter(alias
          // + "_" + attrCol + "_" + entityAlias, alias + "." + attrCol)));
          //
          // // There is no condition passthrough so we have to hack in the
          // EXISTS
          // // operator
          // valueQuery.AND(termVQ.aSQLCharacter("termExistsSQL", "EXISTS (" +
          // termVQ.getSQL() +
          // ") AND true").EQ(termVQ.aSQLCharacter("termExistsSpoof", "true")));
        }
      }
    }
  }

  protected void setNumericRestrictions(ValueQuery valueQuery, JSONObject queryConfig)
  {
    for (@SuppressWarnings("unchecked")
    Iterator<String> iter = queryConfig.keys(); iter.hasNext();)
    {
      String key = (String) iter.next();
      Pattern pattern = Pattern.compile("^(\\w+)Criteria$");
      Matcher matcher = pattern.matcher(key);
      String attributeName = null;
      if (matcher.find())
      {
        attributeName = matcher.group(1);
        if (!valueQuery.hasSelectableRef(attributeName))
        {
          continue;
        }

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

        Selectable original = valueQuery.getSelectableRef(attributeName);
        Selectable sel = original;

        // due to inconsistent and overlapping interfaces, check what operation
        // to perform based on the underlying Selectable type.
        // NOTE: #2717 fix uses the sel object as the underlying criteria
        // Selectable
        // instead of the aggregate because we want to "pre-filter" results.
        while (sel instanceof Function)
        {
          sel = ( (Function) sel ).getSelectable();
        }

        value = value.trim();
        List<AttributeCondition> conditions = new LinkedList<AttributeCondition>();
        if (sel instanceof SelectableChar && !value.equals("NULL"))
        {
          conditions.add((AttributeCondition) ( (SelectableChar) original ).LIKE(value));
        }
        else if (sel instanceof SelectableNumber)
        {
          if (value.contains("-"))
          {
            // range
            String[] range = value.split("-");
            if (range.length == 2)
            {
              String range1 = range[0].trim();
              String range2 = range[1].trim();
              if (range1.length() > 0)
              {
                conditions.add(original instanceof AggregateFunction ? ( (SelectableNumber) sel ).GE(range1) : ( (SelectableNumber) original ).GE(range1));
              }

              if (range2.length() > 0)
              {
                conditions.add(original instanceof AggregateFunction ? ( (SelectableNumber) sel ).LE(range2) : ( (SelectableNumber) original ).LE(range2));
              }
            }
            else
            {
              String lowerBound = range[0].trim();
              // Just the GE criteria was specified (e.g., "7-")
              conditions.add(original instanceof AggregateFunction ? ( (SelectableNumber) sel ).GE(lowerBound) : ( (SelectableNumber) original ).GE(lowerBound));
            }
          }
          else
          {
            // exact value
            conditions.add((AttributeCondition) ( original instanceof AggregateFunction ? sel.EQ(value) : original.EQ(value) ));
          }
        }

        if (conditions.size() > 0)
        {
          Condition cond = AND.get(conditions.toArray(new Condition[conditions.size()]));
          valueQuery.WHERE(cond);
        }
      }
    }
  }

  /**
   * Joins all selected geo entity attributes with their localized display labels.
   * 
   * @param valueQuery
   * @param queryMap
   */
  protected void joinGeoDisplayLabels(ValueQuery valueQuery)
  {
    for (GeneratedEntityQuery query : this.geoDisplayLabelQueries)
    {
      String klass = query.getMdClassIF().definesType();
      String[] geoAttributes = QueryUtil.filterSelectedAttributes(valueQuery, GeoEntity.getGeoAttributes(klass));
      if (geoAttributes.length > 0)
      {
        String id = QueryUtil.getIdColumn();

        String sql = "(" + QueryUtil.getGeoDisplayLabelSubSelect(klass, geoAttributes) + ")";
        String subSelect = klass.replace('.', '_') + "GeoSubSel";
        String table = MdBusiness.getMdBusiness(klass).getTableName();
        valueQuery.AND(new InnerJoinEq(id, table, query.getTableAlias(), id, sql, subSelect));
      }
    }
  }

  /**
   * Returns all ParseInterceptors. Subclasses may override this method to append other interceptors.
   * 
   * @return
   */
  protected List<ParseInterceptor> createParseInterceptors(ValueQuery valueQuery)
  {
    List<ParseInterceptor> interceptors = new LinkedList<ParseInterceptor>();
    interceptors.add(new QBInterceptor(valueQuery));

    return interceptors;
  }

  /**
   * Constructs the JSONObject for the extra query data.
   * 
   * @param config
   * @return
   */
  protected JSONObject constructQueryConfig(String config)
  {
    try
    {
      return new JSONObject(config);
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  protected ValueQuery createValueQuery()
  {
    return new ValueQuery(factory);
  }

  protected QueryFactory createFactory()
  {
    return new QueryFactory();
  }

  protected ValueQueryParser createParser(ValueQuery valueQuery, List<ParseInterceptor> interceptors)
  {
    ValueQueryParser parser = new ValueQueryParser(xml, valueQuery);

    for (ParseInterceptor interceptor : interceptors)
    {
      parser.addParseIntercepter(interceptor);
    }
    return parser;
  }

  /**
   * Joins the ValueQuery with any universal columns and geo entity criteria.
   * 
   * @param factory
   * @param valueQuery
   * @param xml2
   * @param queryConfig
   * @param layer2
   */
  protected Map<String, GeneratedEntityQuery> joinQueryWithGeoEntities(QueryFactory factory, ValueQuery valueQuery, String xml, JSONObject queryConfig, Layer layer, ValueQueryParser parser)
  {
    Map<String, GeneratedEntityQuery> queryMap;

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
      attr = layer.getRenderAs().get(0) == AllRenderTypes.POINT ? GeoEntity.GEOPOINT : GeoEntity.GEOMULTIPOLYGON;
    }

    // Normal query (non-mapping)
    try
    {
      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
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

        addUniversalsForAttribute(factory, attributeKey, selectedUniversals, parser, key, attr, layerGeoEntityType, thematicUserAlias);
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

      parser.addAttributeSelectable(entityAlias, attr, attr, QueryConstants.GEOMETRY_NAME_COLUMN);

      if (geoEntityJoinData.geoThematicAlias != null)
      {
        parser.addAttributeSelectable(geoEntityJoinData.geoThematicEntity, geoEntityJoinData.geoThematicAttr, geoEntityJoinData.geoThematicAlias, "data");
      }
    }

    queryMap = parser.parse();

    // Query validation is done here since all query screens must call this
    // method.
    validateQuery(valueQuery);

    // Set the entity name and geo id columns to something predictable
    if (layer != null)
    {
      valueQuery.getSelectableRef(geoEntityJoinData.entityNameAlias).setColumnAlias(QueryConstants.ENTITY_NAME_COLUMN);
      valueQuery.getSelectableRef(geoEntityJoinData.geoIdAlias).setColumnAlias(QueryConstants.GEO_ID_COLUMN);

      // Name the thematic column if a thematic variable has been selected

      if (layer.hasThematicVariable())
      {
        String alias = geoEntityJoinData.geoThematicAlias != null ? geoEntityJoinData.geoThematicAlias : thematicUserAlias;
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

    QBInterceptor interceptor = this.getQBInterceptor(parser);
    for (String attributeKey : geoEntityJoinData.attributeKeysAndJoins.keySet())
    {
      AllPathsQuery allPathsQuery = (AllPathsQuery) queryMap.get(getGeoAllPathsAlias(attributeKey));
      List<ValueQuery> leftJoinValueQueries = geoEntityJoinData.attributeKeysAndJoins.get(attributeKey);

      setGeoCriteria(interceptor, attributeKey, allPathsQuery, leftJoinValueQueries, valueQuery, queryMap);
    }

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    return queryMap;
  }

  /**
   * Recreates the entity alias for the geo AllPath entries sent in the by the client.
   * 
   * @param attributeKey
   * @return
   */
  private String getGeoAllPathsAlias(String attributeKey)
  {
    return AllPaths.CLASS + "_" + attributeKey;
  }

  public String getUniversalEntityName(String universalName, String attributeKey)
  {
    String prepend = attributeKey.replaceAll("\\.", "_") + "__";
    String entityNameAlias = prepend + universalName.toLowerCase() + "_" + GeoEntityView.ENTITYLABEL;
    return entityNameAlias;
  }

  public String getUniversalGeoId(String universalName, String attributeKey)
  {
    String prepend = attributeKey.replaceAll("\\.", "_") + "__";
    String geoIdAlias = prepend + universalName.toLowerCase() + "_" + GeoEntityView.GEOID;
    return geoIdAlias;
  }

  public String getUniversalId(String universalName, String attributeKey)
  {
    String prepend = attributeKey.replaceAll("\\.", "_") + "__";
    String geoIdAlias = prepend + universalName.toLowerCase() + "_" + GeoEntityView.ID;
    return geoIdAlias;
  }

  private void addUniversalsForAttribute(QueryFactory queryFactory, String attributeKey, String[] selectedUniversals, ValueQueryParser valueQueryParser, String layerKey, String geoAttr, String layerGeoEntityType, String thematicUserAlias)
  {
    List<ValueQuery> leftJoinValueQueries = new LinkedList<ValueQuery>();
    String idCol = QueryUtil.getIdColumn();
    for (String selectedGeoEntityType : selectedUniversals)
    {
      this.hasUniversal = true;
      List<Selectable> selectables = new LinkedList<Selectable>();

      GeoEntityQuery geoEntityQuery = new GeoEntityQuery(queryFactory);

      AllPathsQuery subAllPathsQuery = new AllPathsQuery(queryFactory);
      ValueQuery geoEntityVQ = new ValueQuery(queryFactory);
      MdBusinessDAOIF geoEntityMd = MdBusinessDAO.getMdBusinessDAO(selectedGeoEntityType);

      String entityNameAlias = this.getUniversalEntityName(geoEntityMd.getTypeName(), attributeKey);
      String geoIdAlias = this.getUniversalGeoId(geoEntityMd.getTypeName(), attributeKey);
      String idAlias = this.getUniversalId(geoEntityMd.getTypeName(), attributeKey);

      Selectable selectable1 = geoEntityQuery.getEntityLabel().localize(entityNameAlias);
      Selectable selectable2 = geoEntityQuery.getGeoId(geoIdAlias);
      Selectable selectable4 = geoEntityVQ.aSQLCharacter(entityNameAlias, QueryUtil.GEO_DISPLAY_LABEL + "." + QueryUtil.LABEL_COLUMN, entityNameAlias, selectable1.getUserDefinedDisplayLabel());

      selectables.add(selectable2);
      selectables.add(selectable4);

      SelectableReference selectable3 = subAllPathsQuery.getChildGeoEntity("child_id");
      selectables.add(selectable3);

      SelectableChar selectable6 = geoEntityQuery.getId(idAlias);
      Selectable selectableId = geoEntityVQ.aSQLCharacter(PARENT_UNIVERSAL_ID, selectable6.getDbQualifiedName(), PARENT_UNIVERSAL_ID, selectable6.getUserDefinedAlias());
      selectableId.setColumnAlias(PARENT_UNIVERSAL_ID);
      selectables.add(selectableId);

      String geoVQEntityAlias = attributeKey + "__" + selectedGeoEntityType;
      GeoEntityQuery geoEntityQuery2 = null;
      if (layerKey != null && attributeKey.equals(layerKey) && selectedGeoEntityType.equals(layerGeoEntityType))
      {
        // save the aliases used for mapping the entity name and geo id columns
        geoEntityJoinData.entityNameAlias = entityNameAlias;
        geoEntityJoinData.geoIdAlias = geoIdAlias;
        geoEntityJoinData.idAlias = idAlias;

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

            geoEntityJoinData.geoThematicEntity = geoVQEntityAlias;
            geoEntityJoinData.geoThematicAlias = entityNameAlias + "_entityname_thematic";
            geoEntityJoinData.geoThematicAttr = GeoEntity.ENTITYLABEL;
            thematicSel = geoEntityQuery2.getEntityLabel().localize(geoEntityJoinData.geoThematicAlias);
            selectables.add(thematicSel);
          }
          else if (thematicUserAlias.equals(geoIdAlias))
          {
            geoEntityQuery2 = new GeoEntityQuery(queryFactory);

            geoEntityJoinData.geoThematicEntity = geoVQEntityAlias;
            geoEntityJoinData.geoThematicAlias = geoIdAlias + "_geoid_thematic";
            geoEntityJoinData.geoThematicAttr = GeoEntity.GEOID;
            thematicSel = geoEntityQuery2.getGeoId(geoEntityJoinData.geoThematicAlias);
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

      geoEntityVQ.FROM(QueryUtil.GEO_DISPLAY_LABEL, QueryUtil.GEO_DISPLAY_LABEL);

      geoEntityVQ.WHERE(OR.get(geoConditions));
      geoEntityVQ.AND(subAllPathsQuery.getParentGeoEntity().EQ(geoEntityQuery));

      String prepend = attributeKey.replaceAll("\\.", "_") + "__";
      geoEntityVQ.AND(selectable6.EQ(geoEntityVQ.aSQLCharacter(prepend + "geoDisplayLabel", QueryUtil.GEO_DISPLAY_LABEL + "." + idCol)));

      if (geoEntityQuery2 != null)
      {
        geoEntityVQ.AND(geoEntityQuery.getId().EQ(geoEntityQuery2.getId()));
      }

      leftJoinValueQueries.add(geoEntityVQ);

      valueQueryParser.setValueQuery(geoVQEntityAlias, geoEntityVQ);
    }

    geoEntityJoinData.attributeKeysAndJoins.put(attributeKey, leftJoinValueQueries);
  }

  protected void setGeoCriteria(QBInterceptor interceptor, String attributeKey, AllPathsQuery allPathsQuery, List<ValueQuery> leftJoinValueQueries, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    if (allPathsQuery == null && leftJoinValueQueries.size() > 0)
    {
      // This case is for when they have not restricted by any specific
      // geoEntity
      allPathsQuery = new AllPathsQuery(valueQuery.getQueryFactory());
    }

    if (allPathsQuery != null)
    {
      GeoEntityQuery geQ = new GeoEntityQuery(valueQuery);

      // this case is for when they have restricted to a specific geoEntity
      List<SelectableSingle> leftJoinSelectables = new LinkedList<SelectableSingle>();
      for (ValueQuery leftJoinVQ : leftJoinValueQueries)
      {
        leftJoinSelectables.add(leftJoinVQ.aReference("child_id"));
      }

      int size = leftJoinSelectables.size();
      if (size > 0)
      {
        valueQuery.AND(geQ.getId().getAttribute().LEFT_JOIN_EQ(leftJoinSelectables.toArray(new SelectableSingle[size])));
      }

      int ind = attributeKey.lastIndexOf(".");
      String className = attributeKey.substring(0, ind);
      String attributeName = attributeKey.substring(ind + 1);

      GeneratedEntityQuery generatedEntityQuery = queryMap.get(className);
      AttributeReference sel = (AttributeReference) generatedEntityQuery.get(attributeName);

      // join the domain class that defines the geo entity attribute with the
      // AllPaths table
      valueQuery.AND(sel.EQ(geQ.getId()));

      // Restrict by geo entity if applicable for the current attribute
      geoIncludesCondition = interceptor.getGeoCondition(getGeoAllPathsAlias(attributeKey));
      ValueQuery existsVQ = new ValueQuery(valueQuery.getQueryFactory());
      existsVQ.SELECT(existsVQ.aSQLInteger("geoExistsConstant", "1"));
      existsVQ.FROM(allPathsQuery.getMdClassIF().getTableName(), allPathsQuery.getTableAlias());
      if (geoIncludesCondition != null)
      {
        existsVQ.WHERE(geoIncludesCondition);
      }
      existsVQ.AND(allPathsQuery.getChildGeoEntity().EQ(existsVQ.aSQLCharacter("childId", geQ.getId().getDbQualifiedName())));

      Condition newCond = valueQuery.aSQLCharacter("geoExistsSQL", "EXISTS (" + existsVQ.getSQL() + ") AND true").EQ(valueQuery.aSQLCharacter("geoExistsSpoof", "true"));
      valueQuery.AND(newCond);
    }
  }

  /**
   * Performs basic validation on the ValueQuery to ensure the query is valid.
   * 
   * @param valueQuery
   */
  private void validateQuery(ValueQuery valueQuery)
  {
    validateDateSelectables(valueQuery);

    int size = valueQuery.getSelectableRefs().size();
    if (size == 0)
    {
      NoColumnsAddedException ex = new NoColumnsAddedException();
      throw ex;
    }

    if (size == 1 && ( valueQuery.hasSelectableRef(QueryUtil.RATIO) || valueQuery.getSelectableRefs().get(0) instanceof COUNT ))
    {
      throw new CountOrRatioAloneException();
    }
    else if (size == 2 && valueQuery.hasSelectableRef(QueryUtil.RATIO))
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

  /**
   * Ensures that the ValueQuery contains more than the start and end date criteria.
   * 
   * @param valueQuery
   */
  private void validateDateSelectables(ValueQuery valueQuery)
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

  /**
   * Sets a selectable that represents the total count of the result set with Postgres's window function.
   * 
   * @param v
   */
  protected void addCountSelectable(ValueQuery v)
  {
    // some queries are nested or unioned. In that case the count selectable
    // may have already been added, and if so, skip doing so again
    if (this.enableCountSelectable(v))
    {
      String windowCount = "count(*) over()";
      SelectableSQLLong c = v.isGrouping() ? v.aSQLAggregateLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS) : v.aSQLLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS);
      
      v.SELECT(c);
      v.setCountSelectable(c);
    }
  }

  protected boolean enableCountSelectable(ValueQuery v)
  {
    return !v.hasCountSelectable();
  }

  public Map<String, Restriction> getTermRestrictions()
  {
    return restrictions;
  }

  private Map<String, Restriction> constructTermRestrictions(JSONObject queryConfig)
  {
    HashMap<String, Restriction> map = new HashMap<String, Restriction>();

    if (queryConfig.has("terms"))
    {
      try
      {
        JSONObject attributes = queryConfig.getJSONObject("terms");

        Iterator<?> keys = attributes.keys();

        while (keys.hasNext())
        {
          String key = (String) keys.next();

          JSONObject values = attributes.getJSONObject(key);

          Iterator<?> iterator = values.keys();

          Restriction restriction = new Restriction(key);

          while (iterator.hasNext())
          {
            restriction.getRestrictions().add((String) iterator.next());
          }

          map.put(key, restriction);
        }
      }
      catch (JSONException e)
      {
        // This should only happen if invalid JSON is returned
        throw new ProgrammingErrorException(e);
      }
    }

    if (queryConfig.has("termsAg"))
    {
      try
      {
        JSONObject attributes = queryConfig.getJSONObject("termsAg");

        Iterator<?> keys = attributes.keys();

        while (keys.hasNext())
        {
          String key = (String) keys.next();

          if (map.containsKey(key))
          {
            Restriction restriction = map.get(key);
            restriction.setAggregate(true);
          }
        }
      }
      catch (JSONException e)
      {
        // This should only happen if invalid JSON is returned
        throw new ProgrammingErrorException(e);
      }
    }

    return map;
  }

  /**
   * Exchanges aggregate functions (eg, SUM(sum_column)) as selectable sql aggregates that use custom aggreation logic (eg, SUM(unique_column, sum_column).
   * 
   * @param aliases
   * @param id
   * @param valueQuery
   * @param tableAlias
   * @param allowNonAggregateDefault
   * @param preserveSQL
   * @return The number of aggregates that were swapped out.
   */
  protected int setAttributesAsAggregated(String[] aliases, String id, ValueQuery valueQuery, String tableAlias, boolean allowNonAggregateDefault, boolean preserveSQL)
  {
    int swapped = 0;

    for (String alias : aliases)
    {
      if (valueQuery.hasSelectableRef(alias))
      {
        Selectable sel = valueQuery.getSelectableRef(alias);
        String dislay = sel.getUserDefinedDisplayLabel();

        String sql = null;
        String oldSQL = alias;
        boolean useDefault = false;
        if (sel.isAggregateFunction())
        {
          if (preserveSQL && sel.isAggregateFunction())
          {
            oldSQL = sel.getAggregateFunction().getSelectable().getSQL();
          }

          if (sel instanceof SUM)
          {
            sql = this.sumColumnForId(tableAlias, id, null, oldSQL);
          }
          else if (sel instanceof AVG)
          {
            sql = this.avgColumnForId(tableAlias, id, null, oldSQL);
          }
          else if (sel instanceof MIN)
          {
            sql = this.minColumnForId(tableAlias, id, null, oldSQL);
          }
          else if (sel instanceof MAX)
          {
            sql = this.maxColumnForId(tableAlias, id, null, oldSQL);
          }
          else
          {
            // aggregate function unknown. Could be something custom. We can't
            // make any assumptions so use the default behavior
            useDefault = true;
          }
        }
        else
        {
          // Selectable is not an aggregate function. Use default behavior
          useDefault = true;
        }

        // unwrap the Selectable to get the core type
        while (sel.isAggregateFunction())
        {
          Selectable root = sel.getAggregateFunction().getSelectable();

          if (root.equals(sel))
          {
            break;
          }
          else
          {
            sel = root;
          }
        }

        SelectableSQL newSel;

        if (useDefault)
        {
          // The default behavior is to be used since no recognized
          // aggregate functions were found.

          if (allowNonAggregateDefault)
          {
            sql = sel.getSQL();

            if (sel instanceof SelectableInteger || sel instanceof SelectableLong)
            {
              newSel = valueQuery.aSQLLong(alias, sql, alias, dislay);
            }
            else
            {
              newSel = valueQuery.aSQLDouble(alias, sql, alias, dislay);
            }
          }
          else
          {
            // We have to SUM by default to avoid a cross-product
            sql = this.sumColumnForId(tableAlias, id, null, alias);

            if (sel instanceof SelectableInteger || sel instanceof SelectableLong)
            {
              newSel = valueQuery.aSQLAggregateLong(alias, sql, alias, dislay);
            }
            else
            {
              newSel = valueQuery.aSQLAggregateDouble(alias, sql, alias, dislay);
            }
          }
        }
        else
        {
          // aggregate found. Wrap the SQL in new selectable depending on the
          // type
          if (sel instanceof SelectableInteger || sel instanceof SelectableLong)
          {
            newSel = valueQuery.aSQLAggregateLong(alias, sql, alias, dislay);
          }
          else
          {
            newSel = valueQuery.aSQLAggregateDouble(alias, sql, alias, dislay);
          }
        }

        // swap out the old selectable with the new.
        newSel.setColumnAlias(sel.getColumnAlias());
        valueQuery.replaceSelectable(newSel);

        swapped++;
      }
    }

    return swapped;
  }

  protected int setAttributesAsAggregated(String[] aliases, String id, ValueQuery valueQuery, String tableAlias, boolean allowNonAggregateDefault)
  {
    return setAttributesAsAggregated(aliases, id, valueQuery, tableAlias, allowNonAggregateDefault, false);
  }

  protected String sumColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return QueryUtil.SUM_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String minColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return QueryUtil.MIN_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String maxColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return QueryUtil.MAX_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String avgColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    return QueryUtil.AVG_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected abstract ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig);
}
