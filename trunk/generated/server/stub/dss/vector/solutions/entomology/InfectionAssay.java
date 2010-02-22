package dss.vector.solutions.entomology;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Property;
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class InfectionAssay extends InfectionAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1835211494;

  public InfectionAssay()
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
      return this.getSpecies().getDisplay();
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
    validateNumberPositive();

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
        this.setMosquitoId(Property.getNextId());
      }
    }
  }
  
  @Override
  public void validateNumberPositive()
  {
    if(this.getNumberPositive() != null && this.getNumberTested() != null)
    {
      if(this.getNumberPositive() > this.getNumberTested())
      {        
        String msg = "Number of tested mosquitos must be GTE to the number of positive mosquitos";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERPOSITIVE);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberTested());
        p.setInvalidValue(this.getNumberPositive());
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
  public InfectionAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public InfectionAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public InfectionAssayView getView()
  {
    InfectionAssayView view = new InfectionAssayView();

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
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
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

    ArrayList<ValueQuery>  unionQueries = new ArrayList<ValueQuery>();
    
    InfectionAssayQuery infectionQuery = (InfectionAssayQuery) queryMap.get(InfectionAssay.CLASS);
    if (infectionQuery != null ||  xml.indexOf(">prevalence<") > 0)
    {
      unionQueries.add(InfectionAssay.getUnionSubQuery(xml,config,layer));
    }

    PooledInfectionAssayQuery pooledInfectionQuery = (PooledInfectionAssayQuery) queryMap.get(PooledInfectionAssay.CLASS);
    if (pooledInfectionQuery != null ||  xml.indexOf(">minPrevalence<") > 0)
    {
      unionQueries.add(PooledInfectionAssay.getUnionSubQuery(xml,config,layer));
    }
    
    MolecularAssayQuery molecularQuery = (MolecularAssayQuery) queryMap.get(MolecularAssay.CLASS);
    if (molecularQuery != null ||  xml.indexOf(">percent") > 0  ||  xml.indexOf(">frequency") > 0)
    {
      unionQueries.add(MolecularAssay.getUnionSubQuery(xml,config,layer));
    }
    
    BiochemicalAssayQuery biochemicalQuery = (BiochemicalAssayQuery) queryMap.get(BiochemicalAssay.CLASS);
    if (biochemicalQuery != null ||  xml.indexOf(">elevated<") > 0)
    {
      unionQueries.add(BiochemicalAssay.getUnionSubQuery(xml,config,layer));
    }
    
    if(unionQueries.size() == 0)
    {
      MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
      QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);
      QueryUtil.setTermRestrictions(valueQuery, queryMap);
      QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);
      QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
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
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("prevalence");
        s.setSQL("100.0 * SUM(numberPositive) / SUM(numberTested)");
      }
      
    }

   
    

    QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    return valueQuery;
  }

}
