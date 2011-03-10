package dss.vector.solutions.querybuilder.entomology;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.BiochemicalAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class BiochemicalAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public BiochemicalAssaySubSelectQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);


    BiochemicalAssayQuery biochemicalQuery = (BiochemicalAssayQuery) queryMap.get(BiochemicalAssay.CLASS);
    
    if (biochemicalQuery == null && xml.indexOf(">elevated<") > 0)
    {
      biochemicalQuery = new BiochemicalAssayQuery(queryFactory);
    }
    
    if (biochemicalQuery != null)
    {
      valueQuery.WHERE(biochemicalQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, BiochemicalAssay.CLASS, biochemicalQuery);
      if(xml.indexOf(">elevated<") > 0)
      {
        String numberElevatedCol = QueryUtil.getColumnName(BiochemicalAssay.getNumberElevatedMd());
        String numberTestedCol = QueryUtil.getColumnName(BiochemicalAssay.getNumberTestedMd());
        
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("elevated");
        s.setSQL("100.0 * SUM("+numberElevatedCol+") / SUM("+numberTestedCol+")");
      }
     
    }


    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    
    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    return valueQuery;
  }

}
