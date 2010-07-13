package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.SortedGridComparator;

public class AggregatedPremiseVisitView extends AggregatedPremiseVisitViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1903748524;

  public AggregatedPremiseVisitView()
  {
    super();
  }

  public void populateView(AggregatedPremiseVisit concrete)
  {
    GeoEntity entity = concrete.getGeoEntity();

    this.setConcreteId(concrete.getId());
    this.setPoint(concrete.getPoint());
    this.setGeoEntity(entity);
    this.setVehicleCoverage(concrete.getVehicleCoverage());
    this.setPremises(concrete.getPremises());
    this.setVisited(concrete.getVisited());
    this.setTreated(concrete.getTreated());

    if (entity != null)
    {
      this.setEntityLabel(entity.getLabel());
    }
  }

  private void populateConcrete(AggregatedPremiseVisit concrete)
  {
    concrete.setPoint(this.getPoint());
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setVehicleCoverage(this.getVehicleCoverage());
    concrete.setPremises(this.getPremises());
    concrete.setVisited(this.getVisited());
    concrete.setTreated(this.getTreated());
  }

  private void buildAttributeMap(AggregatedPremiseVisit concrete)
  {
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.ID, this, AggregatedPremiseVisitView.ID);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.POINT, this, AggregatedPremiseVisitView.POINT);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.GEOENTITY, this, AggregatedPremiseVisitView.GEOENTITY);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.GEOENTITY, this, AggregatedPremiseVisitView.ENTITYLABEL);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.VEHICLECOVERAGE, this, AggregatedPremiseVisitView.VEHICLECOVERAGE);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.PREMISES, this, AggregatedPremiseVisitView.PREMISES);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.VISITED, this, AggregatedPremiseVisitView.VISITED);
    new AttributeNotificationMap(concrete, AggregatedPremiseVisit.TREATED, this, AggregatedPremiseVisitView.TREATED);
  }

  @Override
  @Transaction
  public void apply()
  {
    AggregatedPremiseVisit concrete = new AggregatedPremiseVisit();

    if (this.hasConcrete())
    {
      concrete = AggregatedPremiseVisit.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  public AggregatedPremiseMethodView[] applyAll(AggregatedPremiseReasonViewBase[] reasons, AggregatedPremiseMethodView[] methods)
  {
    this.apply();

    AggregatedPremiseVisit concrete = AggregatedPremiseVisit.get(this.getConcreteId());

    for (AggregatedPremiseReasonViewBase reason : reasons)
    {
      reason.setVisit(concrete);
      reason.apply();
    }

    for (AggregatedPremiseMethodView method : methods)
    {
      method.setVisit(concrete);
      method.apply();
    }

    return methods;
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  public AggregatedPremiseReasonView[] getNonTreatmentReasons()
  {
    Term[] terms = Term.getSortedRootChildren(AggregatedPremiseVisitViewBase.getNonTreatmentReasonMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);
    
    return this.getNonTreatmentReasons(terms, comparator);    
  }
  
  public AggregatedPremiseReasonView[] getNonTreatmentReasons(Term[] terms, SortedGridComparator comparator)
  {
    List<AggregatedPremiseReasonView> list = new LinkedList<AggregatedPremiseReasonView>();
    Set<AggregatedPremiseReason> set = new TreeSet<AggregatedPremiseReason>(comparator);

    for (Term d : terms)
    {
      set.add(new AggregatedPremiseReason(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedPremiseVisit c = AggregatedPremiseVisit.get(this.getConcreteId());

      for (AggregatedPremiseReason d : c.getAllNonTreatmentReasonsRel())
      {
        // We will only want grid options Reasons which are active
        // All active Reasons are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    for (AggregatedPremiseReason container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new AggregatedPremiseReasonView[set.size()]);
  }

  @Override
  public AggregatedPremiseMethodView[] getInterventionMethods()
  {
    Term[] terms = Term.getSortedRootChildren(AggregatedPremiseVisitViewBase.getInterventionMethodMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    return this.getInterventionMethods(terms, comparator);
  }
  
  public AggregatedPremiseMethodView[] getInterventionMethods(Term[] terms, SortedGridComparator comparator)
  {
    List<AggregatedPremiseMethodView> list = new LinkedList<AggregatedPremiseMethodView>();
    Set<AggregatedPremiseMethod> set = new TreeSet<AggregatedPremiseMethod>(comparator);

    for (Term d : terms)
    {
      set.add(new AggregatedPremiseMethod(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedPremiseVisit c = AggregatedPremiseVisit.get(this.getConcreteId());

      for (AggregatedPremiseMethod d : c.getAllInterventionMethodsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    for (AggregatedPremiseMethod container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new AggregatedPremiseMethodView[set.size()]);
  }

  public static AggregatedPremiseMethodView[][] getInterventionMethodsForViews(AggregatedPremiseVisitView[] views)
  {
    Term[] terms = Term.getSortedRootChildren(AggregatedPremiseVisitViewBase.getInterventionMethodMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    AggregatedPremiseMethodView[][] methods = new AggregatedPremiseMethodView[views.length][];

    for (int i = 0; i < views.length; i++)
    {
      methods[i] = views[i].getInterventionMethods(terms, comparator);
    }

    return methods;
  }

  public static AggregatedPremiseReasonView[][] getNonTreatmentReasonsForViews(AggregatedPremiseVisitView[] views)
  {
    Term[] terms = Term.getSortedRootChildren(AggregatedPremiseVisitViewBase.getNonTreatmentReasonMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    AggregatedPremiseReasonView[][] reasons = new AggregatedPremiseReasonView[views.length][];
    
    for (int i = 0; i < views.length; i++)
    {
      reasons[i] = views[i].getNonTreatmentReasons(terms, comparator);
    }
    
    return reasons;
  }
  
  @Transaction
  public static AggregatedPremiseVisitView[] applyAll(AggregatedPremiseVisitView[] views, AggregatedPremiseReasonView[][] reasons, AggregatedPremiseMethodView[][] methods)
  {
    for (int i = 0; i < views.length; i++)
    {
      views[i].applyAll(reasons[i], methods[i]);
    }

    return views;
  }
  
  public static AggregatedPremiseVisitView getView(ControlIntervention point, GeoEntity entity)
  {
    AggregatedPremiseVisitQuery query = new AggregatedPremiseVisitQuery(new QueryFactory());

    Condition condition = query.getGeoEntity().EQ(entity);
    
    if(point != null)
    {
      condition = AND.get(condition, query.getPoint().EQ(point));
    }

    query.WHERE(condition);

    OIterator<? extends AggregatedPremiseVisit> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      AggregatedPremiseVisitView view = new AggregatedPremiseVisitView();
      view.setGeoEntity(entity);
      view.setEntityLabel(entity.getLabel());
      view.setPoint(point);

      return view;
    }
    finally
    {
      it.close();
    }
  }

}
