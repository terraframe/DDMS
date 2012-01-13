package dss.vector.solutions.entomology;

import java.util.ArrayList;
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

public class PupalCollectionView extends PupalCollectionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -295141057;

  public PupalCollectionView()
  {
    super();
  }

  public void populateView(PupalCollection concrete, PupalPremise premise)
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
  }

  private void populateConcrete(PupalCollection concrete, PupalPremise premise)
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
  }

  private void buildAttributeMap(PupalCollection concrete, PupalPremise premise)
  {
    new AttributeNotificationMap(concrete, PupalCollection.ID, this, PupalCollectionView.CONCRETEID);
    new AttributeNotificationMap(concrete, PupalCollection.GEOENTITY, this, PupalCollectionView.GEOENTITY);
    new AttributeNotificationMap(concrete, PupalCollection.STARTDATE, this, PupalCollectionView.STARTDATE);
    new AttributeNotificationMap(concrete, PupalCollection.ENDDATE, this, PupalCollectionView.ENDDATE);
    new AttributeNotificationMap(concrete, PupalCollection.COLLECTIONID, this, PupalCollectionView.COLLECTIONID);
    new AttributeNotificationMap(concrete, PupalCollection.NOTES, this, PupalCollectionView.NOTES);
    new AttributeNotificationMap(premise, PupalPremise.ID, this, PupalCollectionView.PREMISEID);
    new AttributeNotificationMap(premise, PupalPremise.PREMISETYPE, this, PupalCollectionView.PREMISETYPE);
    new AttributeNotificationMap(premise, PupalPremise.NUMBEREXAMINED, this, PupalCollectionView.NUMBEREXAMINED);
    new AttributeNotificationMap(premise, PupalPremise.PREMISESIZE, this, PupalCollectionView.PREMISESIZE);
    new AttributeNotificationMap(premise, PupalPremise.NUMBERINHABITANTS, this, PupalCollectionView.NUMBERINHABITANTS);
  }

  @Override
  @Transaction
  public void apply()
  {
    PupalCollection concrete = new PupalCollection();
    PupalPremise premise = new PupalPremise();

    if (this.hasConcrete())
    {
      concrete = PupalCollection.lock(this.getConcreteId());
    }

    if (this.hasPremise())
    {
      premise = PupalPremise.lock(this.getPremiseId());
    }

    this.buildAttributeMap(concrete, premise);

    this.populateConcrete(concrete, premise);

    concrete.apply();

    premise.setCollection(concrete);
    premise.apply();

    this.populateView(concrete, premise);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  private boolean hasPremise()
  {
    return this.getPremiseId() != null && !this.getPremiseId().equals("");
  }

  private PupalPremise getPremise()
  {
    if (this.hasPremise())
    {
      return PupalPremise.get(this.getPremiseId());
    }

    return null;
  }

  @Override
  @Transaction
  public PupalContainerView[] applyWithContainers(PupalContainerView[] containers, PupalContainerAmountView[][] amounts)
  {
    this.apply();

    PupalPremise premise = this.getPremise();

    for (int i = 0; i < containers.length; i++)
    {
      PupalContainerView container = containers[i];
      PupalContainerAmountView[] amount = amounts[i];

      container.setPremise(premise);
      container.applyWithAmounts(amount);
    }

    return containers;
  }

  public PupalContainerView[] getContainers()
  {
    PupalPremise premise = this.getPremise();

    if (premise != null)
    {
      List<PupalContainerView> list = new ArrayList<PupalContainerView>();
      PupalContainerQuery query = new PupalContainerQuery(new QueryFactory());

      Condition condition = query.getPremise().EQ(premise);

      query.WHERE(condition);
      query.ORDER_BY_DESC(query.getContainerId());

      OIterator<? extends PupalContainer> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          PupalContainerView view = it.next().getView();

          list.add(view);
        }

        return list.toArray(new PupalContainerView[list.size()]);
      }
      finally
      {
        it.close();
      }
    }

    return new PupalContainerView[0];
  }

  private PupalCollectionView searchClone()
  {
    PupalCollectionView view = new PupalCollectionView();
    view.setGeoEntity(this.getGeoEntity());
    view.setStartDate(this.getStartDate());
    view.setEndDate(this.getEndDate());
    view.setPremiseType(this.getPremiseType());
    view.setCollectionId(this.getCollectionId());

    return view;
  }

  private PupalCollectionView searchCollection()
  {
    QueryFactory factory = new QueryFactory();

    PupalCollectionQuery collectionQuery = new PupalCollectionQuery(factory);
    PupalPremiseQuery premiseQuery = new PupalPremiseQuery(factory);

    Condition collectionCondition = collectionQuery.getGeoEntity().EQ(this.getGeoEntity());
    collectionCondition = AND.get(collectionCondition, collectionQuery.getStartDate().EQ(this.getStartDate()));
    collectionCondition = AND.get(collectionCondition, collectionQuery.getEndDate().EQ(this.getEndDate()));
    collectionQuery.WHERE(collectionCondition);

    Condition premiseCondition = premiseQuery.getCollection().EQ(collectionQuery);
    premiseCondition = AND.get(premiseCondition, premiseQuery.getPremiseType().EQ(this.getPremiseType()));
    premiseQuery.WHERE(premiseCondition);

    OIterator<? extends PupalPremise> premiseIt = premiseQuery.getIterator();

    try
    {
      if (premiseIt.hasNext())
      {
        PupalCollectionView view = premiseIt.next().getView();

        return view;
      }
    }
    finally
    {
      premiseIt.close();
    }

    OIterator<? extends PupalCollection> collectionIt = collectionQuery.getIterator();

    try
    {
      if (collectionIt.hasNext())
      {
        PupalCollectionView view = collectionIt.next().getView();
        view.setPremiseType(this.getPremiseType());

        return view;
      }
    }
    finally
    {
      collectionIt.close();
    }

    return null;
  }

  private boolean validateSearch()
  {
    boolean valid = true;

    if (this.getGeoEntity() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, PupalCollectionView.GEOENTITY);
      p.apply();
      p.throwIt();

      valid = false;
    }

    if (this.getStartDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, PupalCollectionView.STARTDATE);
      p.apply();
      p.throwIt();

      valid = false;
    }

    if (this.getEndDate() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, PupalCollectionView.ENDDATE);
      p.apply();
      p.throwIt();

      valid = false;
    }

    if (this.getPremiseType() == null)
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, PupalCollectionView.PREMISETYPE);
      p.apply();
      p.throwIt();

      valid = false;
    }

    return valid;
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      PupalCollection.get(this.getConcreteId()).deleteAll();
    }
  }

  @Override
  public void deletePremise()
  {
    if (this.hasPremise())
    {
      PupalPremise.get(this.getPremiseId()).delete();
    }
  }

  public static PupalCollectionViewQuery getMostRecent()
  {
    return PupalCollectionViewQuery.searchCollections();
  }

  public static PupalCollectionViewQuery searchCollections(PupalCollectionView collection, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PupalCollectionViewQuery query = PupalCollectionViewQuery.searchCollections(collection);

    if (sortAttribute != null)
    {
      Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

      if (sortAttribute.equalsIgnoreCase(PupalCollectionView.GEOENTITY))
      {
        attribute = query.getGeoEntity().getEntityLabel().localize();
      }
      else if (sortAttribute.equalsIgnoreCase(PupalCollectionView.PREMISETYPE))
      {
        attribute = query.getPremiseType().getTermDisplayLabel().localize();
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
  public static PupalCollectionView getCollection(PupalCollectionView collection)
  {
    boolean valid = collection.validateSearch();

    if (valid)
    {
      PupalCollectionView view = collection.searchCollection();

      if (view != null)
      {
        return view;
      }
    }

    return collection.searchClone();
  }
}
