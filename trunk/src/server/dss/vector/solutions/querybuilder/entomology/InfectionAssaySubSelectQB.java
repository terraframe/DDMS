package dss.vector.solutions.querybuilder.entomology;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.InfectionAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class InfectionAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public InfectionAssaySubSelectQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);

    InfectionAssayQuery infectionQuery = (InfectionAssayQuery) queryMap.get(InfectionAssay.CLASS);
    
    
    if (infectionQuery == null && xml.indexOf(">prevalence<") > 0)
    {
      infectionQuery = new InfectionAssayQuery(queryFactory);
    }
    
    
    if (infectionQuery != null)
    {
      valueQuery.WHERE(infectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, InfectionAssay.CLASS, infectionQuery);
      
      if(xml.indexOf(">prevalence<") > 0)
      {
        String numberPositiveCol = QueryUtil.getColumnName(infectionQuery.getMdClassIF(), InfectionAssay.NUMBERPOSITIVE);
        String numberTestedCol = QueryUtil.getColumnName(infectionQuery.getMdClassIF(), InfectionAssay.NUMBERTESTED);
        
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("prevalence");
        s.setSQL("100.0 * SUM("+numberPositiveCol+") / SUM("+numberTestedCol+")");
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
