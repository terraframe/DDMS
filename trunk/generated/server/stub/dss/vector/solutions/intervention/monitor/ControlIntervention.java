package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;


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
  @Transaction
  public void delete()
  {
    //FIRST delete all Individual Premises
    this.deletePersonIntervention();
    
    this.deleteIndividualPremises();    
    
    this.deleteAggregatedPremises();
    
    super.delete();
  }
  
  public void deleteAggregatedPremises()
  {
    List<AggregatedPremiseVisit> list = this.getAggregatedPremiseVisits();
    
    for(AggregatedPremiseVisit visit : list)
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
    
    for(IndividualPremiseVisit visit : list)
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
    
    for(PersonIntervention visit : list)
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

}
