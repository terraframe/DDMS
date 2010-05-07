package dss.vector.solutions.entomology;

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
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class ImmatureCollectionView extends ImmatureCollectionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1629092049;

  public ImmatureCollectionView()
  {
    super();
  }

  public void populateView(ImmatureCollection concrete, CollectionPremise premise, PremiseTaxon taxon)
  {
    if (concrete != null)
    {
      String cid = concrete.getId();
      if (!cid.equals(this.getConcreteId()))
      {
        this.setConcreteId(cid);
      }
      this.setGeoEntity(concrete.getGeoEntity());
      this.setStartDate(concrete.getStartDate());
      this.setEndDate(concrete.getEndDate());
      this.setCollectionId(concrete.getCollectionId());
      this.setNotes(concrete.getNotes());
    }

    if (premise != null)
    {
      String pid = premise.getId();
      if (!pid.equals(this.getPremiseId()))
      {
        this.setPremiseId(pid);
      }
      this.setPremiseType(premise.getPremiseType());
      this.setNumberExamined(premise.getNumberExamined());
      this.setPremiseSize(premise.getPremiseSize());
      this.setNumberInhabitants(premise.getNumberInhabitants());
    }

    if (taxon != null)
    {
      String tid = taxon.getId();
      if (!tid.equals(this.getTaxonId()))
      {
        this.setTaxonId(tid);
      }
      this.setTaxon(taxon.getTaxon());
    }
  }

  private void populateConcrete(ImmatureCollection concrete, CollectionPremise premise, PremiseTaxon taxon)
  {
    if (concrete.isNew())
    {
      concrete.setGeoEntity(this.getGeoEntity());
      concrete.setStartDate(this.getStartDate());
      concrete.setEndDate(this.getEndDate());
    }

    concrete.setCollectionId(this.getCollectionId());
    concrete.setNotes(this.getNotes());

    if (premise.isNew())
    {
      premise.setPremiseType(this.getPremiseType());
    }

    premise.setNumberExamined(this.getNumberExamined());
    premise.setPremiseSize(this.getPremiseSize());
    premise.setNumberInhabitants(this.getNumberInhabitants());

    if (taxon.isNew())
    {
      taxon.setTaxon(this.getTaxon());
    }
  }

  private void buildAttributeMap(ImmatureCollection concrete, CollectionPremise premise, PremiseTaxon taxon)
  {
    new AttributeNotificationMap(concrete, ImmatureCollection.ID, this, ImmatureCollectionView.CONCRETEID);
    new AttributeNotificationMap(concrete, ImmatureCollection.GEOENTITY, this, ImmatureCollectionView.GEOENTITY);
    new AttributeNotificationMap(concrete, ImmatureCollection.STARTDATE, this, ImmatureCollectionView.STARTDATE);
    new AttributeNotificationMap(concrete, ImmatureCollection.ENDDATE, this, ImmatureCollectionView.ENDDATE);
    new AttributeNotificationMap(concrete, ImmatureCollection.COLLECTIONID, this, ImmatureCollectionView.COLLECTIONID);
    new AttributeNotificationMap(concrete, ImmatureCollection.NOTES, this, ImmatureCollectionView.NOTES);
    new AttributeNotificationMap(premise, CollectionPremise.ID, this, ImmatureCollectionView.PREMISEID);
    new AttributeNotificationMap(premise, CollectionPremise.PREMISETYPE, this, ImmatureCollectionView.PREMISETYPE);
    new AttributeNotificationMap(premise, CollectionPremise.NUMBEREXAMINED, this, ImmatureCollectionView.NUMBEREXAMINED);
    new AttributeNotificationMap(premise, CollectionPremise.PREMISESIZE, this, ImmatureCollectionView.PREMISESIZE);
    new AttributeNotificationMap(premise, CollectionPremise.NUMBERINHABITANTS, this, ImmatureCollectionView.NUMBERINHABITANTS);
    new AttributeNotificationMap(taxon, PremiseTaxon.ID, this, ImmatureCollectionView.TAXONID);
    new AttributeNotificationMap(taxon, PremiseTaxon.TAXON, this, ImmatureCollectionView.TAXON);
  }

  @Override
  @Transaction
  public void apply()
  {
    ImmatureCollection concrete = new ImmatureCollection();
    CollectionPremise premise = new CollectionPremise();
    PremiseTaxon taxon = new PremiseTaxon();

    if (this.hasConcrete())
    {
      concrete = ImmatureCollection.lock(this.getConcreteId());
    }

    if (this.hasPremise())
    {
      premise = CollectionPremise.lock(this.getPremiseId());
    }

    if (this.hasTaxon())
    {
      taxon = PremiseTaxon.lock(this.getTaxonId());
    }

    this.buildAttributeMap(concrete, premise, taxon);

    this.populateConcrete(concrete, premise, taxon);

    concrete.apply();

    premise.setCollection(concrete);
    premise.apply();

    taxon.setPremise(premise);
    taxon.apply();

    this.populateView(concrete, premise, taxon);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  private boolean hasPremise()
  {
    return this.getPremiseId() != null && !this.getPremiseId().equals("");
  }

  private boolean hasTaxon()
  {
    return this.getTaxonId() != null && !this.getTaxonId().equals("");
  }

  @Override
  @Transaction
  public CollectionContainerView[] applyWithContainers(CollectionContainerView[] containers)
  {
    this.apply();

    PremiseTaxon taxon = PremiseTaxon.get(this.getTaxonId());

    for (CollectionContainerView container : containers)
    {
      container.setTaxon(taxon);
      container.apply();
    }

    return containers;
  }

  @Override
  public CollectionContainerView[] getContainers()
  {
    List<CollectionContainerView> list = new LinkedList<CollectionContainerView>();
    Set<CollectionContainer> set = new TreeSet<CollectionContainer>(new GridComparator());

    for (Term d : Term.getRootChildren(ImmatureCollectionView.getContainerGridMd()))
    {
      set.add(new CollectionContainer(this.getId(), d.getId()));
    }

    if (this.hasTaxon())
    {
      PremiseTaxon c = PremiseTaxon.get(this.getTaxonId());

      for (CollectionContainer d : c.getAllContainersRel())
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

    for (CollectionContainer container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new CollectionContainerView[set.size()]);
  }

  private ImmatureCollectionView searchClone()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();
    view.setGeoEntity(this.getGeoEntity());
    view.setStartDate(this.getStartDate());
    view.setEndDate(this.getEndDate());
    view.setPremiseType(this.getPremiseType());
    view.setTaxon(this.getTaxon());
    view.setCollectionId(this.getCollectionId());

    return view;
  }

  private void validateSearch()
  {
    if (this.getGeoEntity() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ImmatureCollectionView.GEOENTITY);
      p.apply();
      p.throwIt();
    }

    if (this.getStartDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ImmatureCollectionView.STARTDATE);
      p.apply();
      p.throwIt();
    }

    if (this.getEndDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ImmatureCollectionView.ENDDATE);
      p.apply();
      p.throwIt();
    }

    if (this.getPremiseType() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ImmatureCollectionView.PREMISETYPE);
      p.apply();
      p.throwIt();
    }

    if (this.getTaxon() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, ImmatureCollectionView.TAXON);
      p.apply();
      p.throwIt();
    }
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ImmatureCollection.get(this.getConcreteId()).deleteAll();
    }
  }

  @Override
  public void deletePremise()
  {
    if (this.hasPremise())
    {
      CollectionPremise.get(this.getPremiseId()).deleteAll();
    }
  }

  @Override
  public void deleteTaxon()
  {
    if (this.hasTaxon())
    {
      PremiseTaxon.get(this.getTaxonId()).delete();
    }
  }

  public static ImmatureCollectionViewQuery getMostRecent()
  {
    return ImmatureCollectionViewQuery.searchCollections();
  }

  public static ImmatureCollectionViewQuery searchCollections(ImmatureCollectionView collection, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ImmatureCollectionViewQuery query = ImmatureCollectionViewQuery.searchCollections(collection);

    if (sortAttribute != null)
    {
      Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

      if (sortAttribute.equalsIgnoreCase(ImmatureCollectionView.GEOENTITY))
      {
        attribute = query.getGeoEntity().getEntityName();
      }
      else if (sortAttribute.equalsIgnoreCase(ImmatureCollectionView.PREMISETYPE))
      {
        attribute = query.getPremiseType().getDisplay();
      }
      else if (sortAttribute.equalsIgnoreCase(ImmatureCollectionView.TAXON))
      {
        attribute = query.getTaxon().getDisplay();
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
  public static ImmatureCollectionView getCollection(ImmatureCollectionView collection)
  {
    collection.validateSearch();

    QueryFactory factory = new QueryFactory();

    ImmatureCollectionQuery collectionQuery = new ImmatureCollectionQuery(factory);
    CollectionPremiseQuery premiseQuery = new CollectionPremiseQuery(factory);
    PremiseTaxonQuery taxonQuery = new PremiseTaxonQuery(factory);

    if (collection.getGeoEntity() != null)
    {
      Condition collectionCondition = collectionQuery.getGeoEntity().EQ(collection.getGeoEntity());
      collectionCondition = AND.get(collectionCondition, collectionQuery.getStartDate().EQ(collection.getStartDate()));
      collectionCondition = AND.get(collectionCondition, collectionQuery.getEndDate().EQ(collection.getEndDate()));
      collectionQuery.WHERE(collectionCondition);

      Condition premiseCondition = premiseQuery.getCollection().EQ(collectionQuery);
      premiseCondition = AND.get(premiseCondition, premiseQuery.getPremiseType().EQ(collection.getPremiseType()));
      premiseQuery.WHERE(premiseCondition);

      Condition taxonCondition = taxonQuery.getPremise().EQ(premiseQuery);
      taxonCondition = AND.get(taxonCondition, taxonQuery.getTaxon().EQ(collection.getTaxon()));
      taxonQuery.WHERE(taxonCondition);

      OIterator<? extends PremiseTaxon> taxonIt = taxonQuery.getIterator();

      try
      {
        if (taxonIt.hasNext())
        {
          return taxonIt.next().getView();
        }
      }
      finally
      {
        taxonIt.close();
      }

      OIterator<? extends CollectionPremise> premiseIt = premiseQuery.getIterator();

      try
      {
        if (premiseIt.hasNext())
        {
          ImmatureCollectionView view = premiseIt.next().getView();
          view.setTaxon(collection.getTaxon());

          return view;
        }
      }
      finally
      {
        premiseIt.close();
      }

      OIterator<? extends ImmatureCollection> collectionIt = collectionQuery.getIterator();

      try
      {
        if (collectionIt.hasNext())
        {
          ImmatureCollectionView view = collectionIt.next().getView();
          view.setPremiseType(collection.getPremiseType());
          view.setTaxon(collection.getTaxon());

          return view;
        }
      }
      finally
      {
        collectionIt.close();
      }
    }
    return collection.searchClone();
  }
}
