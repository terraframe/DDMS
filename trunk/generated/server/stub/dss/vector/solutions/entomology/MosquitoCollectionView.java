package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.business.Entity;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.KnockDownAssayQuery;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.query.QueryBuilder;

public class MosquitoCollectionView extends MosquitoCollectionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -379422864;

  public MosquitoCollectionView()
  {
    super();
  }

  public void populateView(MosquitoCollection concrete)
  {
    Term method = concrete.getCollectionMethod();
    Term collectionRound = concrete.getCollectionRound();
    GeoEntity entity = concrete.getGeoEntity();

    this.setConcreteId(concrete.getId());
    this.setGeoEntity(entity);
    this.setCollectionDate(concrete.getCollectionDate());
    this.setAbundance(concrete.getAbundance());
    this.setCollectionMethod(method);
    this.setCollectionId(concrete.getCollectionId());
    this.setCollectionRound(collectionRound);
    this.setCollectionType(concrete.getCollectionType());
    this.setInsecticideBrand(concrete.getInsecticideBrand());
    this.setDateLastSprayed(concrete.getDateLastSprayed());
    this.setWallType(concrete.getWallType());
    this.setNumberOfHumanOccupants(concrete.getNumberOfHumanOccupants());
    this.setNumberOfAnimalOccupants(concrete.getNumberOfAnimalOccupants());
    this.setNumberOfLLINs(concrete.getNumberOfLLINs());
    this.setResistanceAssayComments(concrete.getResistanceAssayComments());
    this.clearLifeStage();

    for (LifeStage stage : concrete.getLifeStage())
    {
      this.addLifeStage(stage);
    }
  }

  private void populateConcrete(MosquitoCollection concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setCollectionDate(this.getCollectionDate());
    concrete.setAbundance(this.getAbundance());
    concrete.setCollectionMethod(this.getCollectionMethod());
    concrete.setCollectionId(this.getCollectionId());
    concrete.setCollectionRound(this.getCollectionRound());
    concrete.setCollectionType(this.getCollectionType());
    concrete.setInsecticideBrand(this.getInsecticideBrand());
    concrete.setDateLastSprayed(this.getDateLastSprayed());
    concrete.setWallType(this.getWallType());
    concrete.setNumberOfHumanOccupants(this.getNumberOfHumanOccupants());
    concrete.setNumberOfAnimalOccupants(this.getNumberOfAnimalOccupants());
    concrete.setNumberOfLLINs(this.getNumberOfLLINs());
    concrete.setResistanceAssayComments(this.getResistanceAssayComments());
    concrete.clearLifeStage();

    for (LifeStage stage : this.getLifeStage())
    {
      concrete.addLifeStage(stage);
    }
  }

  private void buildAttributeMap(MosquitoCollection concrete)
  {
    new AttributeNotificationMap(concrete, MosquitoCollection.ID, this, MosquitoCollectionView.CONCRETEID);
    new AttributeNotificationMap(concrete, MosquitoCollection.GEOENTITY, this, MosquitoCollectionView.GEOENTITY);
    new AttributeNotificationMap(concrete, MosquitoCollection.COLLECTIONDATE, this, MosquitoCollectionView.COLLECTIONDATE);
    new AttributeNotificationMap(concrete, MosquitoCollection.COLLECTIONID, this, MosquitoCollectionView.COLLECTIONID);
    new AttributeNotificationMap(concrete, MosquitoCollection.COLLECTIONMETHOD, this, MosquitoCollectionView.COLLECTIONMETHOD);
    new AttributeNotificationMap(concrete, MosquitoCollection.ABUNDANCE, this, MosquitoCollectionView.ABUNDANCE);
    new AttributeNotificationMap(concrete, MosquitoCollection.LIFESTAGE, this, MosquitoCollectionView.LIFESTAGE);
    new AttributeNotificationMap(concrete, MosquitoCollection.RESISTANCEASSAYCOMMENTS, this, MosquitoCollectionView.RESISTANCEASSAYCOMMENTS);
    new AttributeNotificationMap(concrete, MosquitoCollection.COLLECTIONROUND, this, MosquitoCollectionView.COLLECTIONROUND);
    new AttributeNotificationMap(concrete, MosquitoCollection.COLLECTIONTYPE, this, MosquitoCollectionView.COLLECTIONTYPE);
    new AttributeNotificationMap(concrete, MosquitoCollection.DATELASTSPRAYED, this, MosquitoCollectionView.DATELASTSPRAYED);
    new AttributeNotificationMap(concrete, MosquitoCollection.WALLTYPE, this, MosquitoCollectionView.WALLTYPE);
    new AttributeNotificationMap(concrete, MosquitoCollection.INSECTICIDEBRAND, this, MosquitoCollectionView.INSECTICIDEBRAND);
    new AttributeNotificationMap(concrete, MosquitoCollection.NUMBEROFHUMANOCCUPANTS, this, MosquitoCollectionView.NUMBEROFHUMANOCCUPANTS);
    new AttributeNotificationMap(concrete, MosquitoCollection.NUMBEROFANIMALOCCUPANTS, this, MosquitoCollectionView.NUMBEROFANIMALOCCUPANTS);
    new AttributeNotificationMap(concrete, MosquitoCollection.NUMBEROFLLINS, this, MosquitoCollectionView.NUMBEROFLLINS);
  }

  @Override
  public void apply()
  {
    MosquitoCollection concrete = new MosquitoCollection();

    if (this.hasConcrete())
    {
      concrete = MosquitoCollection.lock(this.getConcreteId());
    }

    // Build the attribute map between MosquitoCollection and
    // MosquitoCollectionView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      MosquitoCollection.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Override
  @Transaction
  public SubCollectionView[] applyAll(SubCollectionView[] subCollections)
  {
    this.apply();

    MosquitoCollection collection = MosquitoCollection.get(this.getConcreteId());

    for (SubCollectionView view : subCollections)
    {
      view.setCollection(collection);
      view.apply();
    }

    return subCollections;
  }

  @Override
  public SubCollectionView[] getSubCollections()
  {
    if (this.hasConcrete())
    {
      SubCollectionViewQuery query = new SubCollectionViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));

      OIterator<? extends SubCollectionView> it = query.getIterator();

      try
      {
        List<? extends SubCollectionView> list = it.getAll();

        return list.toArray(new SubCollectionView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new SubCollectionView[0];
  }

  @Override
  public InfectionAssayView[] getInfectionAssays()
  {
    if (this.hasConcrete())
    {
      InfectionAssayViewQuery query = new InfectionAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));
      query.AND(query.getDisease().EQ(Disease.getCurrent()));

      OIterator<? extends InfectionAssayView> it = query.getIterator();

      try
      {
        List<? extends InfectionAssayView> list = it.getAll();

        return list.toArray(new InfectionAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new InfectionAssayView[0];
  }

  @Override
  public PooledInfectionAssayView[] getPooledInfectionAssays()
  {
    if (this.hasConcrete())
    {
      PooledInfectionAssayViewQuery query = new PooledInfectionAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));
      query.AND(query.getDisease().EQ(Disease.getCurrent()));

      OIterator<? extends PooledInfectionAssayView> it = query.getIterator();

      try
      {
        List<? extends PooledInfectionAssayView> list = it.getAll();

        return list.toArray(new PooledInfectionAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new PooledInfectionAssayView[0];
  }

  @Override
  public BiochemicalAssayView[] getBiochemicalAssays()
  {
    if (this.hasConcrete())
    {
      BiochemicalAssayViewQuery query = new BiochemicalAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));

      OIterator<? extends BiochemicalAssayView> it = query.getIterator();

      try
      {
        List<? extends BiochemicalAssayView> list = it.getAll();

        return list.toArray(new BiochemicalAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new BiochemicalAssayView[0];
  }

  @Override
  public MolecularAssayView[] getMolecularAssays()
  {
    if (this.hasConcrete())
    {
      MolecularAssayViewQuery query = new MolecularAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));

      OIterator<? extends MolecularAssayView> it = query.getIterator();

      try
      {
        List<? extends MolecularAssayView> list = it.getAll();

        return list.toArray(new MolecularAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new MolecularAssayView[0];
  }

  @Override
  public DiagnosticAssayView[] getDiagnosticAssays()
  {
    if (this.hasConcrete())
    {
      DiagnosticAssayViewQuery query = new DiagnosticAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));

      OIterator<? extends DiagnosticAssayView> it = query.getIterator();

      try
      {
        List<? extends DiagnosticAssayView> list = it.getAll();

        return list.toArray(new DiagnosticAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new DiagnosticAssayView[0];
  }

  @Override
  public TimeResponseAssayView[] getTimeResponseAssays()
  {
    if (this.hasConcrete())
    {
      TimeResponseAssayViewQuery query = new TimeResponseAssayViewQuery(new QueryFactory());
      query.WHERE(query.getCollection().EQ(this.getConcreteId()));

      OIterator<? extends TimeResponseAssayView> it = query.getIterator();

      try
      {
        List<? extends TimeResponseAssayView> list = it.getAll();

        return list.toArray(new TimeResponseAssayView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new TimeResponseAssayView[0];
  }

  public AdultDiscriminatingDoseAssayQuery getAdultDoseAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this.getConcreteId()));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public LarvaeDiscriminatingDoseAssayQuery getLarvaeDoseAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    LarvaeDiscriminatingDoseAssayQuery query = new LarvaeDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this.getConcreteId()));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public KnockDownAssayQuery getKnockDownAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayQuery query = new KnockDownAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this.getConcreteId()));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public static MosquitoCollectionViewQuery getMostRecent()
  {
    return MosquitoCollectionViewQuery.searchCollections();
  }

  public static MosquitoCollectionViewQuery searchCollections(SearchMosquitoCollectionView collection, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    MosquitoCollectionViewQuery query = MosquitoCollectionViewQuery.searchCollections(collection);

    if (sortAttribute != null)
    {
      SelectablePrimitive attribute;

      if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.GEOENTITY))
      {
        attribute = query.getGeoEntity().getEntityLabel().localize();
      }
      else if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.COLLECTIONMETHOD))
      {
        attribute = query.getCollectionMethod().getTermDisplayLabel().localize();
      }
      else if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.COLLECTIONID))
      {
        attribute = query.getCollectionId();
      }
      else if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.ABUNDANCE))
      {
        attribute = query.getAbundance();
      }
      else if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.LIFESTAGE))
      {
        attribute = query.getLifeStage().getEnumName();
      }
      else
      {
        attribute = query.getCollectionDate();
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

  public static MosquitoCollectionViewQuery searchCollection(String value)
  {
    return MosquitoCollectionViewQuery.searchCollections(value);
  }

  public static MosquitoCollectionView getCollection(MosquitoCollectionView collection)
  {
    if (collection.getGeoEntity() != null && collection.getCollectionDate() != null && collection.getCollectionMethod() != null && collection.getLifeStage().size() > 0)
    {
      List<LifeStage> stage = collection.getLifeStage();
      LifeStage[] array = stage.toArray(new LifeStage[stage.size()]);

      MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());

      Condition condition = query.getGeoEntity().EQ(collection.getGeoEntity());
      condition = AND.get(condition, query.getCollectionDate().EQ(collection.getCollectionDate()));
      condition = AND.get(condition, query.getCollectionMethod().EQ(collection.getCollectionMethod()));
      condition = AND.get(condition, query.getLifeStage().containsAll(array));

      query.WHERE(condition);

      OIterator<? extends MosquitoCollection> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          return it.next().getView();
        }

        collection.setConcreteId(null);

        return collection;
      }
      finally
      {
        it.close();
      }
    }

    return collection;
  }

  public static MosquitoCollectionView getViewByCollectionId(String collectionId)
  {
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());

    Condition condition = query.getCollectionId().EQ(collectionId);

    query.WHERE(condition);

    OIterator<? extends MosquitoCollection> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      String errMsg = "An instance of type [" + MosquitoCollection.CLASS + "] with id [" + collectionId + "] does not exist.";

      throw new DataNotFoundException(errMsg, MdClassDAO.getMdClassDAO(MosquitoCollection.CLASS));
    }
    finally
    {
      it.close();
    }
  }

  public static ValueQuery searchByValueQuery(String value)
  {
    QueryFactory factory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(factory);
    MosquitoCollectionQuery cQ = new MosquitoCollectionQuery(valueQuery);
    MdBusinessQuery mdQ = new MdBusinessQuery(valueQuery);
    GeoEntityQuery q = new GeoEntityQuery(valueQuery);
    TermQuery tq = new TermQuery(valueQuery);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { cQ.getId(MosquitoCollection.ID), cQ.getCollectionId(MosquitoCollectionView.COLLECTIONID), cQ.getCollectionDate(MosquitoCollectionView.COLLECTIONDATE), q.getEntityLabel().localize(GeoEntityView.ENTITYLABEL), q.getGeoId(GeoEntityView.GEOID), q.getType(GeoEntity.TYPE), mdQ.getDisplayLabel().localize(MdBusiness.DISPLAYLABEL), tq.getTermDisplayLabel().localize(GeoEntityView.MOSUBTYPE) };

    Condition[] conditions = new Condition[] { q.getId().EQ(cQ.getGeoEntity().getId()), F.CONCAT(mdQ.getPackageName(), F.CONCAT(".", mdQ.getTypeName())).EQ(q.getType()), q.getActivated().EQ(true) };

    LeftJoinEq[] joins = new LeftJoinEq[] { q.getTerm("geoTermId").LEFT_JOIN_EQ(tq.getId("termId")) };

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { cQ.getCollectionId(MosquitoCollectionView.COLLECTIONID), q.getEntityLabel().localize(GeoEntityView.ENTITYLABEL), q.getGeoId(GeoEntityView.GEOID) };

      QueryBuilder.textLookup(valueQuery, factory, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, cQ.getCollectionId(MosquitoCollectionView.COLLECTIONID), selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

}
