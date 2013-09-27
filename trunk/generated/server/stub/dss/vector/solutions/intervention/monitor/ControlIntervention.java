package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.InterventionControlQB;

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
    this.validateStartDate();
    this.validateEndDate();
    
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }
  
  @Override
  public void validateEndDate()
  {
    Date end = this.getEndDate();
    
    if (end!=null && end.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(end);
      p.setCurrentDate(new Date());
      p.setNotification(this, ENDDATE);
      p.apply();
      p.throwIt();
    }
  }
  
  @Override
  public void validateStartDate()
  {
    Date start = this.getStartDate();
    Date end = this.getEndDate();
    
    if (start!=null && start.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(start);
      p.setCurrentDate(new Date());
      p.setNotification(this, STARTDATE);
      p.apply();
      p.throwIt();
    }

    if(start != null && end != null)
    {
      if(start.after(end))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();
        
        p.throwIt();
      }
    }
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
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    return new InterventionControlQB(xml, config, layer, pageSize, pageSize).construct();
  }



}
