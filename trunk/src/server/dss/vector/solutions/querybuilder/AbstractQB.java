package dss.vector.solutions.querybuilder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeCondition;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedRelationshipQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableNumber;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermTermDisplayLabel;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.util.TermInterceptor;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractQB implements Reloadable
{
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
  
  private String                            xml;

  private String                            config;

  private Layer                             layer;
  
  private boolean recursiveWithClause;
  
  private List<GeneratedEntityQuery> geoDisplayLabelQueries;
  
  private QueryFactory factory;
  private ValueQuery valueQuery;
  private ValueQueryParser parser;
  
  private List<WITHEntry> withEntries;
  
  public AbstractQB(String xml, String config, Layer layer)
  {
    this.recursiveWithClause = false;
    this.xml = xml;
    this.config = config;
    this.layer = layer;
    geoDisplayLabelQueries = new LinkedList<GeneratedEntityQuery>();
    
    this.factory = null;
    this.valueQuery = null;
    this.parser = null;
    this.withEntries = new LinkedList<WITHEntry>();
  }
  
  protected void setWITHRecursive(boolean recursive)
  {
    this.recursiveWithClause = recursive;
  }
  
  protected void addWITHEntry(WITHEntry entry)
  {
    withEntries.add(entry);
  }
  
  protected String getXml()
  {
    return xml;
  }
  
  protected String getConfig()
  {
    return config;
  }
  
  protected Layer getLayer()
  {
    return layer;
  }

  /**
   * Generates a ValueQuery based on the query this builder represents.
   * Subclasses may override any of the individual steps in the query
   * construction for finer grained control.
   */
  public final ValueQuery construct()
  {
    // Query Config
    JSONObject queryConfig = this.constructQueryConfig(this.config);

    factory = this.createFactory();
    valueQuery = this.createValueQuery();
    List<ParseInterceptor> interceptors = this.createParseInterceptors(valueQuery);
    parser = this.createParser(valueQuery, interceptors);
    
    this.setGeoDisplayLabelSQL();

    // Universals, mapping, and geo entity criteria
    Map<String, GeneratedEntityQuery> queryMap = this.joinQueryWithGeoEntities(factory, valueQuery, this.xml, queryConfig, layer, parser);
    
    // Reset the ValueQuery to be returned because the subclass has the option
    // to substitute it with a custom one.
    valueQuery = this.construct(factory, valueQuery, queryMap, this.xml, queryConfig);
    
    this.joinGeoDisplayLabels(valueQuery);
    
    TermInterceptor termInterceptor = this.getTermInterceptor(parser);
    this.setTermCriteria(valueQuery, queryMap, termInterceptor);

    this.setWITHClause();
    
    return valueQuery;
  }
  
  /**
   * Sets the WITH clause on the value query.
   */
  private void setWITHClause()
  {
    if(this.withEntries.size() == 0)
    {
      return;
    }
    
    String with = "WITH ";
    if(this.recursiveWithClause)
    {
      with += "RECURSIVE ";
    }
    
    int count = 0;
    for(WITHEntry entry : this.withEntries)
    {
      with += entry.name + " AS (\n"+entry.sql+"\n)";
      
      if(count < this.withEntries.size()-1)
      {
        with += ",";
      }
      count++;
      
      with += "\n";
    }
    
    valueQuery.setSqlPrefix(with);
  }
  
  private void setGeoDisplayLabelSQL()
  {    
    // Define the aliases
    String GEO_ALIAS = "geo";
    String TERM_DISPLAY_ALIAS = "termLabel";
    String TERM_ALIAS = "term";
    String MD_TYPE_ALIAS = "md";
    String TYPE_DISPLAY_ALIAS = "typeLabel";
    
    // Define the tables
    String mdTypeTable = MdEntity.getMdEntity(MdType.CLASS).getTableName();
    String metadataLabelTable = MdEntity.getMdEntity(MetadataDisplayLabel.CLASS).getTableName();
    String geoEntityTable = MdEntity.getMdEntity(GeoEntity.CLASS).getTableName();
    String termTable = MdEntity.getMdEntity(Term.CLASS).getTableName();
    String termLabelTable = MdEntity.getMdEntity(TermTermDisplayLabel.CLASS).getTableName();
    
    // Define the columns
    String entityNameColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.ENTITYNAME);
//    String geoIdColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.GEOID);
    String idColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.ID);
    String termColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.TERM);
    String typeColumn = QueryUtil.getColumnName(GeoEntity.CLASS, GeoEntity.TYPE);
    String termLabelColumn = QueryUtil.getColumnName(Term.CLASS, Term.TERMDISPLAYLABEL);
    String labelColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.DISPLAYLABEL);
    String packageColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.PACKAGENAME);
    String typeNameColumn = QueryUtil.getColumnName(MdType.CLASS, MdType.TYPENAME);
    
    StringBuffer buffer = new StringBuffer();
    
    buffer.append("SELECT " + GEO_ALIAS + "." + idColumn + ", " + GEO_ALIAS + "." + entityNameColumn + " || ' (' || \n");
    buffer.append(QueryUtil.getLocaleCoalesce("" + TYPE_DISPLAY_ALIAS + ".") + " ||\n");
    buffer.append(QueryUtil.getLocaleCoalesce("' : ' || " + TERM_DISPLAY_ALIAS + ".", "' '") + " || ')' AS "+QueryUtil.LABEL_COLUMN+"\n");
    buffer.append("FROM  \n");
    buffer.append(geoEntityTable + " " + GEO_ALIAS + " \n");
    buffer.append("INNER JOIN "+mdTypeTable+" "+MD_TYPE_ALIAS+" ON "+ GEO_ALIAS + "." + typeColumn 
        + " =  (" + MD_TYPE_ALIAS + "." + packageColumn + " || '.' || " + MD_TYPE_ALIAS + "." + typeNameColumn + ")\n");
    buffer.append("INNER JOIN "+metadataLabelTable+" "+TYPE_DISPLAY_ALIAS + " ON "+ MD_TYPE_ALIAS + "." + labelColumn + " = " + TYPE_DISPLAY_ALIAS + "." + idColumn + " \n");
    buffer.append("LEFT JOIN " + termTable + " AS " + TERM_ALIAS + " ON " + TERM_ALIAS + "." + idColumn + " = " + GEO_ALIAS + "." + termColumn + " \n");
    buffer.append("LEFT JOIN " + termLabelTable + " AS " + TERM_DISPLAY_ALIAS + " ON " + TERM_DISPLAY_ALIAS + "." + idColumn + " = " + TERM_ALIAS + "." + termLabelColumn + " \n");
    
    this.addWITHEntry(new WITHEntry(QueryUtil.GEO_DISPLAY_LABEL, buffer.toString()));
  }

  /**
   * 
   * @param interceptors
   * @return
   */
  protected TermInterceptor getTermInterceptor(ValueQueryParser parser)
  {
    for(ParseInterceptor interceptor : parser.getInterceptors())
    {
      if(interceptor instanceof TermInterceptor)
      {
        return (TermInterceptor) interceptor;
      }
    }
    return null;
  }

  /**
   * Subclasses can use this to add any GeneratedEntityQuery objects whose
   * geo entity attributes need to join on their display labels.
   * 
   * @param query
   */
  protected final void addGeoDisplayLabelQuery(GeneratedEntityQuery query)
  {
    this.geoDisplayLabelQueries.add(query);
  }
  
  /**
   * Sets the term criteria on the given ValueQuery.
   * 
   * @param valueQuery
   * @param queryMap
   * @param interceptor
   */
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, TermInterceptor interceptor)
  {
    if(interceptor == null)
    {
      return;
    }
    
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
          dss.vector.solutions.ontology.AllPathsQuery allPathsQuery = (dss.vector.solutions.ontology.AllPathsQuery) queryMap.get(entityAlias);
          GeneratedEntityQuery attributeQuery = queryMap.get(klass);

          String attrCol = QueryUtil.getColumnName(attributeQuery.getMdClassIF(), attrib_name);

          // IMPORTANT: We cannot always rely on the class table directly
          // because the attribute
          // may have been defined by a parent class, hence it will be on the
          // parent table.
          // Instead, rely on the query and metadata to resolve the
          // class/attribute mapping.
          String alias;
          if (attributeQuery instanceof GeneratedRelationshipQuery && ( attrCol.equals(RelationshipInfo.CHILD_ID) || attrCol.equals(RelationshipInfo.PARENT_ID) ))
          {
            // We don't have metadata for childId or parentId so we have to
            // manually get the table and alias
            // IMPORTANT: this does not take inheritance into account (i.e., if
            // child_id or parent_id are
            // defined by an MdRelationship superclass).
            alias = attributeQuery.getTableAlias();
          }
          else
          {
            Attribute attr = attributeQuery.get(attrib_name);
            alias = attr.getDefiningTableAlias();
          }

          ValueQuery termVQ = interceptor.getTermValueQuery(entityAlias);
          termVQ.SELECT(termVQ.aSQLInteger("existsConstant", "1"));
          termVQ.AND(allPathsQuery.getChildTerm().EQ(termVQ.aSQLCharacter(alias+"_"+attrCol+"_"+entityAlias, alias+"."+attrCol)));
          
          // There is no condition passthrough so we have to hack in the EXISTS operator
          valueQuery.AND(termVQ.aSQLCharacter("termExistsSQL", "EXISTS ("+termVQ.getSQL()+") AND true").EQ(termVQ.aSQLCharacter("termExistsSpoof", "true")));
        }
      }
    }
  }
  
  
  protected void setNumericRestrictions(ValueQuery valueQuery, JSONObject queryConfig)
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
        boolean isAgg = original.isAggregateFunction();
        Selectable sel = original;
        
        // due to inconsistent and overlapping interfaces, check what operation
        // to perform based on the underlying Selectable type.
        while(sel instanceof Function)
        {
          sel = ((Function)sel).getSelectable();
        }
        

        AttributeCondition cond = null;
        value = value.trim();
        if(sel instanceof SelectableChar && !value.equals("NULL"))
        {
          cond = (AttributeCondition) ((SelectableChar) original ).LIKE(value);
        }
        else if (sel instanceof SelectableNumber)
        {
          if(value.contains("-"))
          {
            // range
            String[] range = value.split("-");
            if (range.length == 2)
            {
              String range1 = range[0].trim();
              String range2 = range[1].trim();
              if (range1.length() > 0)
              {
                cond = isAgg ? ((AggregateFunction)original).GE(range1) : ((SelectableNumber) original ).GE(range1);
              }
              
              if (range2.length() > 0)
              {
                cond = isAgg ? ((AggregateFunction)original).LE(range2) : ((SelectableNumber) original ).LE(range2);
              }
            }
            else
            {
              // Just the GE criteria was specified (e.g., "7-")
              cond = isAgg ? ((AggregateFunction)original).GE(range[0].trim()) : ((SelectableNumber) original ).GE(range[0].trim());
            }
          }
          else
          {
            // exact value
            cond = (AttributeCondition) original.EQ(value);
          }
        }
        
        if(cond != null)
        {
          if(isAgg)
          {
            valueQuery.HAVING(cond);
          }
          else
          {
            valueQuery.WHERE(cond);
          }
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
  private void joinGeoDisplayLabels(ValueQuery valueQuery)
  {
    for(GeneratedEntityQuery query : this.geoDisplayLabelQueries)
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
    interceptors.add(new TermInterceptor(valueQuery));
    
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
    
    for(ParseInterceptor interceptor : interceptors)
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
  protected Map<String, GeneratedEntityQuery> joinQueryWithGeoEntities(QueryFactory factory, ValueQuery valueQuery, String xml2,
      JSONObject queryConfig, Layer layer, ValueQueryParser parser)
  {
    return QueryUtil.joinQueryWithGeoEntities(factory, valueQuery, xml, queryConfig, layer, valueQuery, parser);
  }

  protected abstract ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig);
}
