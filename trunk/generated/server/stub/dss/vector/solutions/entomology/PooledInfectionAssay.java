package dss.vector.solutions.entomology;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class PooledInfectionAssay extends PooledInfectionAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -470295545;

  public PooledInfectionAssay()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getPoolId() != null && !this.getPoolId().equals(""))
    {
      return this.getPoolId();
    }
    else if(this.getSpecies() != null)
    {
      return this.getSpecies().getTermDisplayLabel().getValue();
    }
    
    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public void apply()
  {
    validatePoolId();
    validateNumberPositive();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }

  @Override
  public void validatePoolId()
  {
    if (this.getPoolsTested() != null)
    {
      if (this.getPoolsTested() > 1 && ( this.hasPoolId() ))
      {
        this.setPoolId(null);
      }
      if (this.getPoolsTested() == 1 && ( !this.hasPoolId() ))
      {
        this.setPoolId(LocalProperty.getNextId());
      }
    }
  }

  private boolean hasPoolId()
  {
    return this.getPoolId() != null && !this.getPoolId().equals("");
  }

  @Override
  public void validateNumberPositive()
  {
    if (this.getNumberPositive() != null && this.getPoolsTested() != null)
    {
      if (this.getNumberPositive() > this.getPoolsTested())
      {
        String msg = "Number of tested pools must be GTE to the number of positive pools";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERPOSITIVE);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getPoolsTested());
        p.setInvalidValue(this.getNumberPositive());
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public PooledInfectionAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public PooledInfectionAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public PooledInfectionAssayView getView()
  {
    PooledInfectionAssayView view = new PooledInfectionAssayView();

    view.populateView(this);

    return view;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery getUnionSubQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);


    PooledInfectionAssayQuery pooledInfectionQuery = (PooledInfectionAssayQuery) queryMap.get(PooledInfectionAssay.CLASS);
    
    
    if (pooledInfectionQuery == null && xml.indexOf(">minPrevalence<") > 0)
    {
      pooledInfectionQuery = new PooledInfectionAssayQuery(queryFactory);
    }
    
    
    if (pooledInfectionQuery != null)
    {
      valueQuery.WHERE(pooledInfectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, PooledInfectionAssay.CLASS, pooledInfectionQuery);
      
      if(xml.indexOf(">minPrevalence<") > 0)
      {
        String numberPositiveCol = QueryUtil.getColumnName(pooledInfectionQuery.getMdClassIF(), PooledInfectionAssay.NUMBERPOSITIVE);
        String mosquitosTestedCol = QueryUtil.getColumnName(pooledInfectionQuery.getMdClassIF(), PooledInfectionAssay.MOSQUITOSTESTED);
        
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("minPrevalence");
        s.setSQL("100.0 * SUM("+numberPositiveCol+") / SUM("+mosquitosTestedCol+")");
      }
    }
    

    QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    
    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());


    return valueQuery;
  }
  
}
