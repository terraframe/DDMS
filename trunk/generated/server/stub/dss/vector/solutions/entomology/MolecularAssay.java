package dss.vector.solutions.entomology;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.MolecularSumProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class MolecularAssay extends MolecularAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1142002498;

  public MolecularAssay()
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
    validateSum();

    super.apply();
  }

  @Override
  public void validateMosquitoId()
  {
    Integer sum = this.getSum();

    if (sum > 1 && ( this.hasMosquitoId() ))
    {
      this.setMosquitoId(null);
    }
    if (sum == 1 && ( !this.hasMosquitoId() ))
    {
      this.setMosquitoId(LocalProperty.getNextId());
    }
  }

  private Integer getSum()
  {
    int sum = 0;

    sum += ( this.getNumberRR() != null ? this.getNumberRR() : 0 );
    sum += ( this.getNumberRS() != null ? this.getNumberRS() : 0 );
    sum += ( this.getNumberSS() != null ? this.getNumberSS() : 0 );

    return sum;
  }

  public void validateSum()
  {
    Integer sum = this.getSum();

    if (!(sum > 0))
    {
      String msg = "The sum of RR, RS and SS must be GT 0";
      MolecularSumProblem p = new MolecularSumProblem(msg);
      p.apply();

      p.throwIt();
    }
  }

  private boolean hasMosquitoId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  @Override
  @Transaction
  public MolecularAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MolecularAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MolecularAssayView getView()
  {
    MolecularAssayView view = new MolecularAssayView();

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


    MolecularAssayQuery molecularQuery = (MolecularAssayQuery) queryMap.get(MolecularAssay.CLASS);
    
    
    if (molecularQuery == null && (xml.indexOf(">percent") > 0  ||  xml.indexOf(">frequency") > 0))
    {
      molecularQuery = new MolecularAssayQuery(queryFactory);
    }
    
    
    
    if (molecularQuery != null)
    {
      valueQuery.WHERE(molecularQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));
      QueryUtil.joinTermAllpaths(valueQuery, MolecularAssay.CLASS, molecularQuery);
      
      if(xml.indexOf(">percentRR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRR");
        s.setSQL("100.0 * SUM(numberrr) / SUM(numberrr+numberrs+numberss)");
      }
      if(xml.indexOf(">percentRS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentRS");
        s.setSQL("100.0 * SUM(numberrs) / SUM(numberrr+numberrs+numberss)");
      }
      if(xml.indexOf(">percentSS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("percentSS");
        s.setSQL("100.0 * SUM(numberss) / SUM(numberrr+numberrs+numberss)");
      }
      if(xml.indexOf(">frequencyR<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyR");
        s.setSQL("100.0 * (SUM(numberrr +(0.5*numberrs) ) ) / SUM(numberrr+numberrs+numberss)");
      }
      if(xml.indexOf(">frequencyS<") > 0)
      {
        SelectableSQL s = (SelectableSQL) valueQuery.getSelectableRef("frequencyS");
        s.setSQL("100.0 * (SUM(numberss +(0.5*numberrs) ) ) / SUM(numberrr+numberrs+numberss)");
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
