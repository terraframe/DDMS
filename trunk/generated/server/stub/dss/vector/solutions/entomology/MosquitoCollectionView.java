package dss.vector.solutions.entomology;

import java.util.List;

import com.terraframe.mojo.business.Entity;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.KnockDownAssayQuery;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public class MosquitoCollectionView extends MosquitoCollectionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -379422864;

  public MosquitoCollectionView()
  {
    super();
  }

  public MosquitoCollection findMatch()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public void populateView(MosquitoCollection concrete)
  {
    Term method = concrete.getCollectionMethod();
    GeoEntity entity = concrete.getGeoEntity();

    this.setConcreteId(concrete.getId());
    this.setGeoEntity(entity);
    this.setCollectionDate(concrete.getCollectionDate());
    this.setAbundance(concrete.getAbundance());
    this.setCollectionMethod(method);
    this.setCollectionId(concrete.getCollectionId());
    this.clearLifeStage();

    for (LifeStage stage : concrete.getLifeStage())
    {
      this.addLifeStage(stage);
    }

    if (entity != null)
    {
      this.setGeoEntityLabel(entity.getLabel());
    }

    if (method != null)
    {
      this.setCollectionMethodLabel(method.getDisplay());
    }
  }

  private void populateConcrete(MosquitoCollection concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setCollectionDate(this.getCollectionDate());
    concrete.setAbundance(this.getAbundance());
    concrete.setCollectionMethod(this.getCollectionMethod());
    concrete.setCollectionId(this.getCollectionId());
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
        attribute = query.getGeoEntity().getEntityName();
      }
      else if (sortAttribute.equalsIgnoreCase(MosquitoCollectionView.COLLECTIONMETHOD))
      {
        attribute = query.getCollectionMethodLabel();
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
        query.ORDER_BY_ASC((SelectablePrimitive) attribute);
      }
      else
      {
        query.ORDER_BY_DESC((SelectablePrimitive) attribute);
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
}
