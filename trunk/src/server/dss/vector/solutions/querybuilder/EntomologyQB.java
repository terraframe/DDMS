package dss.vector.solutions.querybuilder;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.BiochemicalAssay;
import dss.vector.solutions.entomology.BiochemicalAssayQuery;
import dss.vector.solutions.entomology.InfectionAssay;
import dss.vector.solutions.entomology.InfectionAssayQuery;
import dss.vector.solutions.entomology.MolecularAssay;
import dss.vector.solutions.entomology.MolecularAssayQuery;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.PooledInfectionAssay;
import dss.vector.solutions.entomology.PooledInfectionAssayQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.entomology.BiochemicalAssaySubSelectQB;
import dss.vector.solutions.querybuilder.entomology.InfectionAssaySubSelectQB;
import dss.vector.solutions.querybuilder.entomology.MolecularAssaySubSelectQB;
import dss.vector.solutions.querybuilder.entomology.PooledInfectionAssaySubSelectQB;
import dss.vector.solutions.util.QueryUtil;

public class EntomologyQB extends AbstractQB implements Reloadable
{

  public EntomologyQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageSize, pageSize);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return InfectionAssay.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    String config = this.getConfig();
    Layer layer = this.getLayer();
    
    ArrayList<ValueQuery>  unionQueries = new ArrayList<ValueQuery>();
    
    Integer pageNumber = this.getPageNumber();
    Integer pageSize = this.getPageSize();
    
    InfectionAssayQuery infectionQuery = (InfectionAssayQuery) queryMap.get(InfectionAssay.CLASS);
    if (infectionQuery != null ||  xml.indexOf(">prevalence<") > 0)
    {
      unionQueries.add(new InfectionAssaySubSelectQB(xml,config,layer, pageNumber, pageSize).construct());
    }

    PooledInfectionAssayQuery pooledInfectionQuery = (PooledInfectionAssayQuery) queryMap.get(PooledInfectionAssay.CLASS);
    if (pooledInfectionQuery != null ||  xml.indexOf(">minPrevalence<") > 0)
    {
      unionQueries.add(new PooledInfectionAssaySubSelectQB(xml, config, layer, pageNumber, pageSize).construct());
    }
    
    MolecularAssayQuery molecularQuery = (MolecularAssayQuery) queryMap.get(MolecularAssay.CLASS);
    if (molecularQuery != null ||  xml.indexOf(">percent") > 0  ||  xml.indexOf(">frequency") > 0)
    {
      unionQueries.add(new MolecularAssaySubSelectQB(xml, config, layer, pageNumber, pageSize).construct());
    }
    
    BiochemicalAssayQuery biochemicalQuery = (BiochemicalAssayQuery) queryMap.get(BiochemicalAssay.CLASS);
    if (biochemicalQuery != null ||  xml.indexOf(">elevated<") > 0)
    {
      unionQueries.add(new BiochemicalAssaySubSelectQB(xml, config, layer, pageNumber, pageSize).construct());
    }
    
    if(unionQueries.size() == 0)
    {
      MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
      this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      this.setNumericRestrictions(valueQuery, queryConfig);
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());
    }

    if(unionQueries.size() == 1)
    {
      valueQuery = unionQueries.get(0);
    }
    
    if(unionQueries.size() > 1)
    {
      valueQuery = new ValueQuery(queryFactory);
      valueQuery.UNION(unionQueries.toArray(new ValueQuery[unionQueries.size()]));
    }  
    
    return valueQuery;
  }

}
