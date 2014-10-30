package dss.vector.solutions.querybuilder.entomology;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.PooledInfectionAssayQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class PooledInfectionAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public PooledInfectionAssaySubSelectQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    PooledInfectionAssayQuery pooledInfectionQuery = (PooledInfectionAssayQuery) queryMap.get(PooledInfectionAssay.CLASS);

    if (pooledInfectionQuery == null && xml.indexOf(">minPrevalence<") > 0)
    {
      pooledInfectionQuery = new PooledInfectionAssayQuery(queryFactory);
    }

    if (pooledInfectionQuery != null)
    {
      valueQuery.WHERE(pooledInfectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, PooledInfectionAssay.CLASS, pooledInfectionQuery, this.getTermRestrictions());

      if (xml.indexOf(">minPrevalence<") > 0)
      {
        String numberPositiveCol = QueryUtil.getColumnName(pooledInfectionQuery.getMdClassIF(), PooledInfectionAssay.NUMBERPOSITIVE);
        String mosquitosTestedCol = QueryUtil.getColumnName(pooledInfectionQuery.getMdClassIF(), PooledInfectionAssay.MOSQUITOSTESTED);

        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("minPrevalence");
        s.setSQL("100.0 * SUM(" + numberPositiveCol + ") / SUM(" + mosquitosTestedCol + ")");
      }
    }

    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery, this.getTermRestrictions());

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    return valueQuery;
  }

}
