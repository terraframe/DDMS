package dss.vector.solutions.querybuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.RelationshipInfo;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.EXISTS;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedRelationshipQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.util.TermInterceptor;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractQB implements Reloadable
{
  private String                            xml;

  private String                            config;

  private Layer                             layer;
  
  private TermInterceptor termInterceptor;
  
  private List<GeneratedEntityQuery> geoDisplayLabelQueries;

  public AbstractQB(String xml, String config, Layer layer)
  {
    this.xml = xml;
    this.config = config;
    this.layer = layer;
    this.termInterceptor = null;
    geoDisplayLabelQueries = new LinkedList<GeneratedEntityQuery>();
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
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(factory);
    this.termInterceptor = new TermInterceptor(valueQuery);
    
    ValueQueryParser parser = this.getParser(this.xml, valueQuery);
    
    // Query Config
    JSONObject queryConfig = this.constructQueryConfig(this.config);

    // ParseInterceptors
    List<ParseInterceptor> interceptors = this.addParseInterceptors();
    for(ParseInterceptor interceptor : interceptors)
    {
      parser.addParseIntercepter(interceptor);
    }
    
    // Universals, mapping, and geo entity criteria
    Map<String, GeneratedEntityQuery> queryMap = this.joinQueryWithGeoEntities(factory, valueQuery, this.xml, queryConfig, parser);
    
    // Reset the ValueQuery to be returned because the subclass has the option
    // to substitute it with a custom one.
    valueQuery = this.construct(factory, valueQuery, queryMap, this.xml, queryConfig);
    
    this.joinGeoDisplayLabels(valueQuery);
    
    this.setTermCriteria(valueQuery, queryMap);

    return valueQuery;
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
   * Restricts Term attributes according to parent Terms in the AllPaths mapping.
   * 
   * @param valueQuery
   * @param queryMap
   */
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
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

           ValueQuery termVQ = this.termInterceptor.getTermValueQuery(entityAlias);
           termVQ.AND(allPathsQuery.getChildTerm().EQ(termVQ.aSQLCharacter(alias+"_"+attrCol+"_"+entityAlias, alias+"."+attrCol)));

           valueQuery.AND(new EXISTS(termVQ));
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
  private List<ParseInterceptor> addParseInterceptors()
  {
    List<ParseInterceptor> interceptors = new LinkedList<ParseInterceptor>();
    interceptors.add(this.termInterceptor);
    
    return interceptors;
  }
  
  /**
   * Creates a new ValueQueryParser to parse the given XML into the ValueQuery.
   * 
   * @param xml
   * @param valueQuery
   * @return
   */
  protected ValueQueryParser getParser(String xml, ValueQuery valueQuery)
  {
    return new ValueQueryParser(xml, valueQuery);
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
      JSONObject queryConfig, ValueQueryParser parser)
  {
    return QueryUtil.joinQueryWithGeoEntities(factory, valueQuery, xml, queryConfig, layer, valueQuery, parser);
  }

  protected abstract ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig);
}
