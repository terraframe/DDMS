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

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class IndividualPremiseVisitView extends IndividualPremiseVisitViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1903748524;

  public IndividualPremiseVisitView()
  {
    super();
  }

  public void populateView(IndividualPremiseVisit concrete)
  {
    GeoEntity entity = concrete.getGeoEntity();
    
    this.setConcreteId(concrete.getId());
    this.setGeoEntity(entity);
    this.setVisited(concrete.getVisited());
    this.setTreated(concrete.getTreated());
    this.setReasonsForNotTreated(concrete.getReasonsForNotTreated());
    
    if(entity != null)
    {
      this.setEntityLabel(entity.getLabel());
    }
  }

  private void populateConcrete(IndividualPremiseVisit concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setVisited(this.getVisited());
    concrete.setTreated(this.getTreated());
    concrete.setReasonsForNotTreated(this.getReasonsForNotTreated());
  }

  private void buildAttributeMap(IndividualPremiseVisit concrete)
  {
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.ID, this, IndividualPremiseVisitView.CONCRETEID);
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.GEOENTITY, this, IndividualPremiseVisitView.GEOENTITY);
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.GEOENTITY, this, IndividualPremiseVisitView.ENTITYLABEL);
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.VISITED, this, IndividualPremiseVisitView.VISITED);
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.TREATED, this, IndividualPremiseVisitView.TREATED);
    new AttributeNotificationMap(concrete, IndividualPremiseVisit.REASONSFORNOTTREATED, this, IndividualPremiseVisitView.REASONSFORNOTTREATED);
  }

  @Override
  @Transaction
  public void apply()
  {
    IndividualPremiseVisit concrete = new IndividualPremiseVisit();

    if (this.hasConcrete())
    {
      concrete = IndividualPremiseVisit.lock(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  private IndividualPremiseVisitMethodView[] applyWithMethods(IndividualPremiseVisitMethodView[] methods)
  {
    this.apply();

    IndividualPremiseVisit concrete = IndividualPremiseVisit.get(this.getConcreteId());

    for (IndividualPremiseVisitMethodView method : methods)
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
  public IndividualPremiseVisitMethodView[] getInterventionMethods()
  {
    List<IndividualPremiseVisitMethodView> list = new LinkedList<IndividualPremiseVisitMethodView>();
    Set<IndividualPremiseVisitMethod> set = new TreeSet<IndividualPremiseVisitMethod>(new GridComparator());

    for (Term d : Term.getRootChildren(IndividualPremiseVisitView.getInterventionMethodMd()))
    {
      set.add(new IndividualPremiseVisitMethod(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      IndividualPremiseVisit c = IndividualPremiseVisit.get(this.getConcreteId());

      for (IndividualPremiseVisitMethod d : c.getAllInterventionMethodsRel())
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

    for (IndividualPremiseVisitMethod container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new IndividualPremiseVisitMethodView[set.size()]);
  }

  public static IndividualPremiseVisitMethodView[][] getInterventionMethodsForViews(IndividualPremiseVisitView[] views)
  {
    IndividualPremiseVisitMethodView[][] methods = new IndividualPremiseVisitMethodView[views.length][];

    for (int i = 0; i < views.length; i++)
    {
      methods[i] = views[i].getInterventionMethods();
    }

    return methods;
  }

  @Transaction
  public static IndividualPremiseVisitView[] applyAll(IndividualPremiseVisitView[] views, IndividualPremiseVisitMethodView[][] methods)
  {
    for (int i = 0; i < views.length; i++)
    {
      views[i].applyWithMethods(methods[i]);
    }

    return views;
  }

  public static IndividualPremiseVisitView[] getViews()
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId("100101001020");

    List<IndividualPremiseVisitView> list = new LinkedList<IndividualPremiseVisitView>();

    for (GeoEntity entity : geoEntity.getImmediateChildren())
    {
      IndividualPremiseVisitView view = IndividualPremiseVisitView.getView(entity);

      list.add(view);
    }

    return list.toArray(new IndividualPremiseVisitView[list.size()]);
  }

  private static IndividualPremiseVisitView getView(GeoEntity entity)
  {
    IndividualPremiseVisitQuery query = new IndividualPremiseVisitQuery(new QueryFactory());

    Condition condition = query.getGeoEntity().EQ(entity);


    query.WHERE(condition);

    OIterator<? extends IndividualPremiseVisit> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      IndividualPremiseVisitView view = new IndividualPremiseVisitView();
      view.setGeoEntity(entity);
      view.setEntityLabel(entity.getLabel());

      return view;
    }
    finally
    {
      it.close();
    }
  }

}
