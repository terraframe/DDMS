package dss.vector.solutions.querybuilder;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AmbiguousAttributeException;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class TypeQB extends AbstractQB implements Reloadable
{

  private String auditClass;

  public TypeQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
    auditClass = null;
  }

  /**
   * Not sure if there's a perfect answer for this, so pull the first key the query map
   * 
   * @return
   */
  @Override
  protected String getAuditClassAlias()
  {
    return this.auditClass;
  }
  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    Iterator<GeneratedTableClassQuery> it = queryMap.values().iterator();

    while (it.hasNext())
    {
      GeneratedTableClassQuery query = it.next();

      if (! ( query instanceof AllPathsQuery ) && ! ( query instanceof dss.vector.solutions.ontology.AllPathsQuery ) && !(query.getClassType().equals(MosquitoCollection.CLASS)))
      {
        this.auditClass = query.getMdClassIF().definesType();

        this.addGeoDisplayLabelQuery(query);

        this.setNumericRestrictions(valueQuery, queryConfig);

        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, query.get(MdFormUtil.DISEASE));

        QueryUtil.joinTermAllpaths(valueQuery, query.getClassType(), query, this.getTermRestrictions(), this.getLayer());

        QueryUtil.getSingleAttribteGridSql(valueQuery, query.getTableAlias());

        valueQuery.FROM(query.getMdClassIF().getTableName(), query.getTableAlias());
        
        if (queryMap.containsKey(MosquitoCollection.CLASS))
        {
          GeneratedTableClassQuery mosQ = queryMap.get(MosquitoCollection.CLASS);
          
          Attribute mosQcolId = null;
          Attribute typeQcolId = null;
          try {
            mosQcolId = mosQ.get(MosquitoCollection.COLLECTIONID);
            valueQuery.SELECT(mosQcolId);
          }
          catch (AmbiguousAttributeException e) { }
          try {
            typeQcolId = query.get(MosquitoCollection.COLLECTIONID);
            valueQuery.SELECT(typeQcolId);
          }
          catch (AmbiguousAttributeException e) {}
          
          QueryUtil.joinTermAllpaths(valueQuery, mosQ.getClassType(), mosQ, this.getTermRestrictions(), this.getLayer());
          
          this.addGeoDisplayLabelQuery(mosQ);
          
          valueQuery.WHERE(new InnerJoinEq(mosQcolId, typeQcolId));
        }
        
        return valueQuery;
      }
    }

    throw new RuntimeException("NULL Query");
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new TypeQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }
}
