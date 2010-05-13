package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class ControlInterventionView extends ControlInterventionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1672865679;

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
    this.checkPersonInterventionUniversal(concrete);

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
  
  private void checkPersonInterventionUniversal(ControlIntervention concrete)
  {
    GeoHierarchy universal = concrete.getPersonInterventionUniversal();
    GeoHierarchy _universal = this.getPersonInterventionUniversal();
    
    if (universal != null)
    {
      if (_universal == null || !universal.getId().equals(_universal.getId()))
      {
        concrete.deletePersonIntervention();
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
    new AttributeNotificationMap(concrete, ControlIntervention.PERSONINTERVENTIONUNIVERSAL, this, ControlInterventionView.PERSONINTERVENTIONUNIVERSAL);
  }

  public void populateView(ControlIntervention concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setComments(concrete.getComments());
    this.setIndividulPremiseUniversal(concrete.getIndividulPremiseUniversal());
    this.setAggregatedPremiseUniversal(concrete.getAggregatedPremiseUniversal());
    this.setPersonInterventionUniversal(concrete.getPersonInterventionUniversal());
  }

  public void populateConcrete(ControlIntervention concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setComments(this.getComments());
    concrete.setIndividulPremiseUniversal(this.getIndividulPremiseUniversal());
    concrete.setAggregatedPremiseUniversal(this.getAggregatedPremiseUniversal());
    concrete.setPersonInterventionUniversal(this.getPersonInterventionUniversal());
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
    GeoEntity parent = this.getGeoEntity();
    ControlIntervention point = this.getConcrete();
    GeoHierarchy universal = this.getPersonInterventionUniversal();
    
    List<PersonInterventionView> list = new LinkedList<PersonInterventionView>();
    
    QueryFactory factory = new QueryFactory();
    
    AllPathsQuery pathsQuery = new AllPathsQuery(factory);
    Condition pathConditions = pathsQuery.getParentGeoEntity().EQ(parent);
    pathConditions = AND.get(pathConditions, pathsQuery.getChildUniversal().EQ(universal.getGeoEntityClass()));
    pathsQuery.WHERE(pathConditions);
    
    GeoEntityQuery query = new GeoEntityQuery(factory);
    Condition condition = query.getId().EQ(pathsQuery.getChildGeoEntity().getId());
    query.WHERE(condition);
    
    OIterator<? extends GeoEntity> it = query.getIterator();
    
    try
    {
      while (it.hasNext())
      {
        GeoEntity entity = it.next();
        
        PersonInterventionView view = PersonInterventionView.getView(point, entity);
        
        list.add(view);
      }
    }
    finally
    {
      it.close();
    }
    
    return list.toArray(new PersonInterventionView[list.size()]);
  }

  private ControlIntervention getConcrete()
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
