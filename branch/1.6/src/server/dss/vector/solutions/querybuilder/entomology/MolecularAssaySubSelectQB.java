package dss.vector.solutions.querybuilder.entomology;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.MolecularAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AbstractQB;
import dss.vector.solutions.util.QueryUtil;

public class MolecularAssaySubSelectQB extends AbstractQB implements Reloadable
{

  public MolecularAssaySubSelectQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);


    MolecularAssayQuery molecularQuery = (MolecularAssayQuery) queryMap.get(MolecularAssay.CLASS);
    
    
    if (molecularQuery == null && (xml.indexOf(">percent") > 0  ||  xml.indexOf(">frequency") > 0))
    {
      molecularQuery = new MolecularAssayQuery(queryFactory);
    }
    
    
    
    if (molecularQuery != null)
    {
      valueQuery.WHERE(molecularQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, MolecularAssay.CLASS, molecularQuery, this.getTermRestrictions(), this.getLayer());
      
      String numberrrCol = QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRR);
      String numberrsCol = QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERRS);
      String numberssCol = QueryUtil.getColumnName(molecularQuery.getMdClassIF(), MolecularAssay.NUMBERSS);
      
      if(xml.indexOf(">percentRR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRR");
        s.setSQL("100.0 * SUM("+numberrrCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+")");
      }
      if(xml.indexOf(">percentRS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRS");
        s.setSQL("100.0 * SUM("+numberrsCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+")");
      }
      if(xml.indexOf(">percentSS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentSS");
        s.setSQL("100.0 * SUM("+numberssCol+") / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+")");
      }
      if(xml.indexOf(">frequencyR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyR");
        s.setSQL("(SUM("+numberrrCol+" +(0.5*"+numberrsCol+") ) ) / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+")");
      }
      if(xml.indexOf(">frequencyS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyS");
        s.setSQL("(SUM("+numberssCol+" +(0.5*"+numberrsCol+") ) ) / SUM("+numberrrCol+"+"+numberrsCol+"+"+numberssCol+")");
      }
    }
    

    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery, this.getTermRestrictions(), this.getLayer());

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    
    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());


    return valueQuery;
  }

}
