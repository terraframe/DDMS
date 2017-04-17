package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.GridComparator;

public class PersonInterventionView extends PersonInterventionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 610277344;

  public PersonInterventionView()
  {
    super();
  }

  public void populateView(PersonIntervention concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setPoint(concrete.getPoint());
    this.setVehicleDays(concrete.getVehicleDays());
  }

  private void populateConcrete(PersonIntervention concrete)
  {
    concrete.setPoint(this.getPoint());
    concrete.setVehicleDays(this.getVehicleDays());
  }

  private void buildAttributeMap(PersonIntervention concrete)
  {
    new AttributeNotificationMap(concrete, PersonIntervention.ID, this, PersonInterventionView.ID);
    new AttributeNotificationMap(concrete, PersonIntervention.POINT, this, PersonInterventionView.POINT);
    new AttributeNotificationMap(concrete, PersonIntervention.VEHICLEDAYS, this, PersonInterventionView.VEHICLEDAYS);
  }

  @Override
  @Transaction
  public void apply()
  {
    PersonIntervention concrete = new PersonIntervention();

    if (this.hasConcrete())
    {
      concrete = PersonIntervention.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  public PersonInterventionMethodView[] applyAll(PersonInterventionMethodView[] methods)
  {
    this.apply();

    PersonIntervention concrete = PersonIntervention.get(this.getConcreteId());

    for (PersonInterventionMethodView method : methods)
    {
      method.setPerson(concrete);
      method.apply();
    }

    return methods;
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  public PersonInterventionMethodView[] getInterventionMethods()
  {
    Term[] terms = TermRootCache.getRoots(PersonInterventionViewBase.getInterventionMethodMd());
    
    return this.getInterventionMethods(terms);
  }
  
  public PersonInterventionMethodView[] getInterventionMethods(Term[] terms)
  {
    List<PersonInterventionMethodView> list = new LinkedList<PersonInterventionMethodView>();
    Set<PersonInterventionMethod> set = new TreeSet<PersonInterventionMethod>(new GridComparator());

    for (Term d : terms)
    {
      set.add(new PersonInterventionMethod(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      PersonIntervention c = PersonIntervention.get(this.getConcreteId());

      for (PersonInterventionMethod d : c.getAllInterventionMethodsRel())
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

    for (PersonInterventionMethod container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new PersonInterventionMethodView[set.size()]);
  }

  public static PersonInterventionMethodView[][] getInterventionMethodsForViews(PersonInterventionView[] views)
  {
    Term[] terms = TermRootCache.getRoots(PersonInterventionViewBase.getInterventionMethodMd());

    PersonInterventionMethodView[][] methods = new PersonInterventionMethodView[views.length][];

    for (int i = 0; i < views.length; i++)
    {
      methods[i] = views[i].getInterventionMethods(terms);
    }

    return methods;
  }

  @Transaction
  public static PersonInterventionView[] applyAll(PersonInterventionView[] views, PersonInterventionMethodView[][] methods)
  {
    for (int i = 0; i < views.length; i++)
    {
      views[i].applyAll(methods[i]);
    }

    return views;
  }

  public static PersonInterventionView getView(ControlIntervention point)
  {
    if (point != null)
    {
      PersonInterventionQuery query = new PersonInterventionQuery(new QueryFactory());

      Condition condition = query.getPoint().EQ(point);

      query.WHERE(condition);

      OIterator<? extends PersonIntervention> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          return it.next().getView();
        }
      }
      finally
      {
        it.close();
      }
    }
    
    PersonInterventionView view = new PersonInterventionView();
    view.setPoint(point);

    return view;
  }

}
