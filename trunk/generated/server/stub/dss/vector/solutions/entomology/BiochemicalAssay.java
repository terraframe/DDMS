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
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class BiochemicalAssay extends BiochemicalAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1877238023;
  
  public BiochemicalAssay()
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
    else if(this.getMosquitoId() != null && !this.getMosquitoId().equals(""))
    {
      return this.getMosquitoId();
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
    validateMosquitoId();
    validateNumberElevated();

    super.apply();
  }

  @Override
  public void validateMosquitoId()
  {
    if (this.getNumberTested() != null)
    {
      if (this.getNumberTested() > 1 && ( this.hasMosquitoId() ))
      {
        this.setMosquitoId(null);
      }
      if (this.getNumberTested() == 1 && ( !this.hasMosquitoId() ))
      {
        this.setMosquitoId(LocalProperty.getNextId());
      }
    }
  }
  
  @Override
  public void validateNumberElevated()
  {
    if(this.getNumberElevated() != null && this.getNumberTested() != null)
    {
      if(this.getNumberElevated() > this.getNumberTested())
      {        
        String msg = "Number of tested mosquitos must be GTE to the number of positive mosquitos";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERELEVATED);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberTested());
        p.setInvalidValue(this.getNumberElevated());
        p.apply();
        
        p.throwIt();        
      }
    }
  }
  
  private boolean hasMosquitoId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  @Override
  @Transaction
  public BiochemicalAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public BiochemicalAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public BiochemicalAssayView getView()
  {
    BiochemicalAssayView view = new BiochemicalAssayView();

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
        String numberElevatedCol = QueryUtil.getColumnName(biochemicalQuery.getMdClassIF(), BiochemicalAssay.NUMBERELEVATED);
        String numberTestedCol = QueryUtil.getColumnName(biochemicalQuery.getMdClassIF(), BiochemicalAssay.NUMBERTESTED);
        
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("elevated");
        s.setSQL("100.0 * SUM("+numberElevatedCol+") / SUM("+numberTestedCol+")");
      }
     
    }


    QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
    
    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    return valueQuery;
  }

  
}
