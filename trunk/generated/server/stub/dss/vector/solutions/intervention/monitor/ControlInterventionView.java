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
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.TooManyRowsException;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class ControlInterventionView extends ControlInterventionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1672865679;
  
  private static final long MAXIMUM_COUNT = 500L;

  public ControlInterventionView()
  {
    super();
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      this.getConcrete().delete();
    }
  }

  @Transaction
  public void apply()
  {
    ControlIntervention concrete = new ControlIntervention();

    if (this.hasConcrete())
    {
      concrete = ControlIntervention.lock(this.getConcreteId());
    }

    this.checkIndividulPremiseUniversal(concrete);
    this.checkAggregatedPremiseUniversal(concrete);

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void checkIndividulPremiseUniversal(ControlIntervention concrete)
  {
    GeoHierarchy universal = concrete.getIndividulPremiseUniversal();
    GeoHierarchy _universal = this.getIndividulPremiseUniversal();

    if (universal != null)
    {
      if (_universal == null || !universal.getId().equals(_universal.getId()))
      {
        concrete.deleteIndividualPremises();
      }
    }
  }

  private void checkAggregatedPremiseUniversal(ControlIntervention concrete)
  {
    GeoHierarchy universal = concrete.getAggregatedPremiseUniversal();
    GeoHierarchy _universal = this.getAggregatedPremiseUniversal();

    if (universal != null)
    {
      if (_universal == null || !universal.getId().equals(_universal.getId()))
      {
        concrete.deleteAggregatedPremises();
      }
    }
  }

  private void populateMapping(ControlIntervention concrete)
  {
    new AttributeNotificationMap(concrete, ControlIntervention.ID, this, ControlInterventionView.CONCRETEID);
    new AttributeNotificationMap(concrete, ControlIntervention.GEOENTITY, this, ControlInterventionView.GEOENTITY);
    new AttributeNotificationMap(concrete, ControlIntervention.STARTDATE, this, ControlInterventionView.STARTDATE);
    new AttributeNotificationMap(concrete, ControlIntervention.ENDDATE, this, ControlInterventionView.ENDDATE);
    new AttributeNotificationMap(concrete, ControlIntervention.COMMENTS, this, ControlInterventionView.COMMENTS);
    new AttributeNotificationMap(concrete, ControlIntervention.INDIVIDULPREMISEUNIVERSAL, this, ControlInterventionView.INDIVIDULPREMISEUNIVERSAL);
    new AttributeNotificationMap(concrete, ControlIntervention.AGGREGATEDPREMISEUNIVERSAL, this, ControlInterventionView.AGGREGATEDPREMISEUNIVERSAL);
  }

  public void populateView(ControlIntervention concrete)
  {
    String otherId = concrete.getId();
    if (!this.getConcreteId().equals(otherId))
    {
      this.setConcreteId(otherId);
    }
    this.setGeoEntity(concrete.getGeoEntity());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setComments(concrete.getComments());
    this.setIndividulPremiseUniversal(concrete.getIndividulPremiseUniversal());
    this.setAggregatedPremiseUniversal(concrete.getAggregatedPremiseUniversal());
  }

  public void populateConcrete(ControlIntervention concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setComments(this.getComments());
    concrete.setIndividulPremiseUniversal(this.getIndividulPremiseUniversal());
    concrete.setAggregatedPremiseUniversal(this.getAggregatedPremiseUniversal());
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  @Transaction
  public IndividualPremiseVisitView[] applyWithIndividualPremiseViews(IndividualPremiseVisitView[] premises, IndividualPremiseVisitMethodView[][] methods)
  {
    this.apply();

    ControlIntervention point = this.getConcrete();

    for (int i = 0; i < premises.length; i++)
    {
      IndividualPremiseVisitView premise = premises[i];
      IndividualPremiseVisitMethodView[] _methods = methods[i];

      premise.setPoint(point);
      premise.applyWithMethods(_methods);
    }

    return premises;
  }

  @Override
  @Transaction
  public AggregatedPremiseVisitView[] applyWithAggregatedPremiseViews(AggregatedPremiseVisitView[] premises, AggregatedPremiseReasonView[][] reasons, AggregatedPremiseMethodView[][] methods)
  {
    this.apply();

    ControlIntervention point = this.getConcrete();

    for (int i = 0; i < premises.length; i++)
    {
      AggregatedPremiseVisitView premise = premises[i];
      AggregatedPremiseReasonView[] _reasons = reasons[i];
      AggregatedPremiseMethodView[] _methods = methods[i];

      premise.setPoint(point);
      premise.applyAll(_reasons, _methods);
    }

    return premises;
  }

  @Override
  @Transaction
  public PersonInterventionView[] applyWithPersonInterventionViews(PersonInterventionView[] premises, PersonInterventionMethodView[][] methods)
  {
    this.apply();

    ControlIntervention point = this.getConcrete();

    for (int i = 0; i < premises.length; i++)
    {
      PersonInterventionView premise = premises[i];
      PersonInterventionMethodView[] _methods = methods[i];

      premise.setPoint(point);
      premise.applyAll(_methods);
    }

    return premises;
  }

  @Override
  @Transaction
  public IndividualPremiseVisitView[] getIndividualPremiseViews()
  {
    GeoEntity parent = this.getGeoEntity();
    ControlIntervention point = this.getConcrete();
    GeoHierarchy universal = this.getIndividulPremiseUniversal();

    List<IndividualPremiseVisitView> list = new LinkedList<IndividualPremiseVisitView>();

    QueryFactory factory = new QueryFactory();

    AllPathsQuery pathsQuery = new AllPathsQuery(factory);
    Condition pathConditions = pathsQuery.getParentGeoEntity().EQ(parent);
    pathConditions = AND.get(pathConditions, pathsQuery.getChildUniversal().EQ(universal.getGeoEntityClass()));
    pathsQuery.WHERE(pathConditions);

    GeoEntityQuery query = new GeoEntityQuery(factory);
    Condition condition = query.getId().EQ(pathsQuery.getChildGeoEntity().getId());
    query.WHERE(condition);
    
    long count = query.getCount();
    
    if(count > MAXIMUM_COUNT)
    {
      TooManyRowsException exception = new TooManyRowsException();
      exception.apply();
      
      throw exception;      
    }

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        GeoEntity entity = it.next();

        IndividualPremiseVisitView view = IndividualPremiseVisitView.getView(point, entity);

        list.add(view);
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new IndividualPremiseVisitView[list.size()]);
  }

  @Override
  public AggregatedPremiseVisitView[] getAggregatedPremiseViews()
  {
    GeoEntity parent = this.getGeoEntity();
    ControlIntervention point = this.getConcrete();
    GeoHierarchy universal = this.getAggregatedPremiseUniversal();

    List<AggregatedPremiseVisitView> list = new LinkedList<AggregatedPremiseVisitView>();

    QueryFactory factory = new QueryFactory();

    AllPathsQuery pathsQuery = new AllPathsQuery(factory);
    Condition pathConditions = pathsQuery.getParentGeoEntity().EQ(parent);
    pathConditions = AND.get(pathConditions, pathsQuery.getChildUniversal().EQ(universal.getGeoEntityClass()));
    pathsQuery.WHERE(pathConditions);

    GeoEntityQuery query = new GeoEntityQuery(factory);
    Condition condition = query.getId().EQ(pathsQuery.getChildGeoEntity().getId());
    query.WHERE(condition);
        
    long count = query.getCount();
    
    if(count > MAXIMUM_COUNT)
    {
      TooManyRowsException exception = new TooManyRowsException();
      exception.apply();
      
      throw exception;      
    }

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        GeoEntity entity = it.next();

        AggregatedPremiseVisitView view = AggregatedPremiseVisitView.getView(point, entity);

        list.add(view);
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new AggregatedPremiseVisitView[list.size()]);
  }

  @Override
  public PersonInterventionView[] getPersonInterventionViews()
  {
    List<PersonInterventionView> list = new LinkedList<PersonInterventionView>();
    ControlIntervention point = this.getConcrete();

    PersonInterventionView view = PersonInterventionView.getView(point);
    list.add(view);

    return list.toArray(new PersonInterventionView[list.size()]);
  }

  @Override
  public InsecticideInterventionView[] getInsecticideInterventionViews()
  {
    Set<InsecticideInterventionView> set = new TreeSet<InsecticideInterventionView>(new GridComparator());

    for (Term d : Term.getRootChildren(ControlInterventionView.getInsecticideInterventionMd()))
    {
      InsecticideInterventionView view = new InsecticideInterventionView();
      view.setInterventionMethod(d);

      set.add(view);
    }

    if (this.hasConcrete())
    {
      ControlIntervention concrete = this.getConcrete();

      InsecticideInterventionViewQuery query = new InsecticideInterventionViewQuery(new QueryFactory());
      query.WHERE(query.getIntervention().EQ(concrete));

      OIterator<? extends InsecticideInterventionView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          InsecticideInterventionView view = it.next().clone();
          
          // We will only want grid options methods which are active
          // All active methods are already in the set. Thus, if
          // the set already contains an entry for the Grid Option
          // replace the default relationship with the actaul
          // relationship
          if (set.contains(view))
          {
            set.remove(view);
            set.add(view);
          }
        }
      }
      finally
      {
        it.close();
      }
    }

    return set.toArray(new InsecticideInterventionView[set.size()]);
  }
  
  @Override
  @Transaction
  public InsecticideInterventionView[] applyWithInsecticideInterventionViews(InsecticideInterventionView[] views)
  {
    this.apply();

    ControlIntervention point = this.getConcrete();

    for (InsecticideInterventionView view : views)
    {
      view.setIntervention(point);
      view.apply();
    }

    return views;
  }

  public ControlIntervention getConcrete()
  {
    if (this.hasConcreteId())
    {
      return ControlIntervention.get(this.getConcreteId());
    }

    return null;
  }

  private boolean hasConcreteId()
  {
    return this.getConcreteId() != null && this.getConcreteId().length() > 0;
  }

  private void validateSearch()
  {
    if (this.getGeoEntity() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ControlInterventionView.GEOENTITY);
      p.apply();
      p.throwIt();
    }

    if (this.getStartDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ControlInterventionView.STARTDATE);
      p.apply();
      p.throwIt();
    }

    if (this.getEndDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ControlInterventionView.ENDDATE);
      p.apply();
      p.throwIt();
    }
  }

  private ControlInterventionView searchClone()
  {
    ControlInterventionView view = new ControlInterventionView();
    view.setGeoEntity(this.getGeoEntity());
    view.setStartDate(this.getStartDate());
    view.setEndDate(this.getEndDate());

    return view;
  }

  public static ControlInterventionViewQuery getMostRecent()
  {
    return ControlInterventionViewQuery.searchCollections();
  }

  public static ControlInterventionViewQuery search(ControlInterventionView criteria, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ControlInterventionViewQuery query = ControlInterventionViewQuery.searchCollections(criteria);

    if (sortAttribute != null)
    {
      Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

      if (sortAttribute.equalsIgnoreCase(ControlInterventionView.GEOENTITY))
      {
        attribute = query.getGeoEntity().getEntityName();
      }

      if (isAscending)
      {
        query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
      }
      else
      {
        query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
      }
    }

    query.restrictRows(pageSize, pageNumber);

    return query;
  }

  @Transaction
  public static ControlInterventionView getIntervention(ControlInterventionView intervention)
  {
    intervention.validateSearch();

    QueryFactory factory = new QueryFactory();

    ControlInterventionQuery query = new ControlInterventionQuery(factory);

    Condition collectionCondition = query.getGeoEntity().EQ(intervention.getGeoEntity());
    collectionCondition = AND.get(collectionCondition, query.getStartDate().EQ(intervention.getStartDate()));
    collectionCondition = AND.get(collectionCondition, query.getEndDate().EQ(intervention.getEndDate()));
    collectionCondition = AND.get(collectionCondition, query.getDisease().EQ(Disease.getCurrent()));
    query.WHERE(collectionCondition);

    OIterator<? extends ControlIntervention> it = query.getIterator();

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

    return intervention.searchClone();
  }

}
