package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ControlIntervention extends ControlInterventionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1736678503;

  public ControlIntervention()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  @Transaction
  public void delete()
  {
    this.deleteInsecticideIntervention();

    this.deletePersonIntervention();

    this.deleteIndividualPremises();

    this.deleteAggregatedPremises();

    super.delete();
  }

  public void deleteInsecticideIntervention()
  {
    List<InsecticideIntervention> list = this.getInsecticideInterventions();

    for (InsecticideIntervention visit : list)
    {
      visit.delete();
    }
  }

  private List<InsecticideIntervention> getInsecticideInterventions()
  {
    List<InsecticideIntervention> list = new LinkedList<InsecticideIntervention>();
    InsecticideInterventionQuery query = new InsecticideInterventionQuery(new QueryFactory());
    query.WHERE(query.getIntervention().EQ(this));
    OIterator<? extends InsecticideIntervention> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;

  }

  public void deleteAggregatedPremises()
  {
    List<AggregatedPremiseVisit> list = this.getAggregatedPremiseVisits();

    for (AggregatedPremiseVisit visit : list)
    {
      visit.delete();
    }
  }

  private List<AggregatedPremiseVisit> getAggregatedPremiseVisits()
  {
    List<AggregatedPremiseVisit> list = new LinkedList<AggregatedPremiseVisit>();
    AggregatedPremiseVisitQuery query = new AggregatedPremiseVisitQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends AggregatedPremiseVisit> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  public void deleteIndividualPremises()
  {
    List<IndividualPremiseVisit> list = this.getIndividualPremiseVisits();

    for (IndividualPremiseVisit visit : list)
    {
      visit.delete();
    }
  }

  private List<IndividualPremiseVisit> getIndividualPremiseVisits()
  {
    List<IndividualPremiseVisit> list = new LinkedList<IndividualPremiseVisit>();
    IndividualPremiseVisitQuery query = new IndividualPremiseVisitQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends IndividualPremiseVisit> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  public void deletePersonIntervention()
  {
    List<PersonIntervention> list = this.getPersonInterventions();

    for (PersonIntervention visit : list)
    {
      visit.delete();
    }
  }

  private List<PersonIntervention> getPersonInterventions()
  {
    List<PersonIntervention> list = new LinkedList<PersonIntervention>();
    PersonInterventionQuery query = new PersonInterventionQuery(new QueryFactory());
    query.WHERE(query.getPoint().EQ(this));
    OIterator<? extends PersonIntervention> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    return list;
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public ControlInterventionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public ControlInterventionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ControlInterventionView getView()
  {
    ControlInterventionView view = new ControlInterventionView();

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

    
    ControlInterventionQuery controlInterventionQuery  = (ControlInterventionQuery) queryMap.get(ControlIntervention.CLASS);
    
    IndividualPremiseVisitQuery individualPremiseVisitQuery  = (IndividualPremiseVisitQuery) queryMap.get(IndividualPremiseVisit.CLASS);
    IndividualPremiseVisitMethodQuery individualPremiseVisitMethodQuery  = (IndividualPremiseVisitMethodQuery) queryMap.get(IndividualPremiseVisitMethod.CLASS);
    
    AggregatedPremiseReasonQuery aggregatedPremiseReasonQuery  = (AggregatedPremiseReasonQuery) queryMap.get(AggregatedPremiseReason.CLASS);
    AggregatedPremiseVisitQuery aggregatedPremiseVisitQuery  = (AggregatedPremiseVisitQuery) queryMap.get(AggregatedPremiseVisit.CLASS);
    
    PersonInterventionQuery personInterventionQuery  = (PersonInterventionQuery) queryMap.get(PersonIntervention.CLASS);
    PersonInterventionMethodQuery personInterventionMethodQuery  = (PersonInterventionMethodQuery) queryMap.get(PersonInterventionMethod.CLASS);
    
    InsecticideInterventionQuery insecticideInterventionQuery  = (InsecticideInterventionQuery) queryMap.get(InsecticideIntervention.CLASS);
    InsecticideBrandQuery insecticideBrandQuery  = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
    


    QueryUtil.joinGeoDisplayLabels(valueQuery, ControlIntervention.CLASS, controlInterventionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
      

    
    
    return QueryUtil.setQueryDates(xml, valueQuery, controlInterventionQuery, controlInterventionQuery.getStartDate(), controlInterventionQuery.getEndDate());

  }
  
  static boolean getSelectabeTermRelationSQL(ValueQuery valueQuery, String ref, String sql)
  {
    if (valueQuery.hasSelectableRef(ref))
    {
      Selectable s = valueQuery.getSelectableRef(ref);

      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }
      
      ( (SelectableSQL) s ).setSQL(sql);
      return true;
    }
    return false;
  }

}
