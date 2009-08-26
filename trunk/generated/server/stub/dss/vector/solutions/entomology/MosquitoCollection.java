package dss.vector.solutions.entomology;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.terraframe.mojo.business.Entity;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQuery;
import dss.vector.solutions.entomology.assay.KnockDownAssayQuery;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQuery;
import dss.vector.solutions.geo.generated.CollectionSite;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.mo.CollectionMethod;

public class MosquitoCollection extends MosquitoCollectionBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234285245712L;

  public MosquitoCollection()
  {
    super();
  }
    
  @Override
  protected String buildKey()
  {
    if(this.getGeoEntity() != null && this.getCollectionMethod() != null && this.getDateCollected() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      return this.getGeoEntity().getGeoId() + "." + this.getCollectionMethod().getTermName() + "." + format.format(this.getDateCollected());
    }
    
    return this.getId();
  }
  
  @Override
  public void apply()
  {
    validateGeoEntity();
    validateUniqueness();

    super.apply();
  }

  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();

    if (! ( this.getGeoEntity() instanceof CollectionSite ))
    {
      String msg = "The geoEntity of a mosquito collection must be a (non)sentinel site";

      InvalidMosquitoCollectionGeoEntityException e = new InvalidMosquitoCollectionGeoEntityException(
          msg);
      e.setGeoId(this.getGeoEntity().getGeoId());
      e.apply();

      throw e;
    }
  }

  public void validateUniqueness()
  {
    if (this.getGeoEntity() != null && this.getCollectionMethod() != null)
    {
      MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());

      query.AND(query.getGeoEntity().EQ(this.getGeoEntity()));
      query.AND(query.getDateCollected().EQ(this.getDateCollected()));
      query.AND(query.getCollectionMethod().EQ(this.getCollectionMethod()));

      if (this.getId() != null)
      {
        query.AND(query.getId().NE(this.getId()));
      }

      OIterator<? extends MosquitoCollection> iterator = query.getIterator();

      try
      {
        if (iterator.hasNext())
        {
          MosquitoCollection collection = iterator.next();

          String msg = "This mosquito collection already exists with the id [" + collection.getId()
              + "]";
          MosquitoCollectionAllreadyExistsException e = new MosquitoCollectionAllreadyExistsException(
              msg);
          e.apply();
          throw e;
        }

      }
      finally
      {
        iterator.close();
      }
    }
  }

  public AdultDiscriminatingDoseAssayQuery getAdultDoseAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public LarvaeDiscriminatingDoseAssayQuery getLarvaeDoseAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    LarvaeDiscriminatingDoseAssayQuery query = new LarvaeDiscriminatingDoseAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  @Override
  public KnockDownAssayQuery getKnockDownAssays(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    KnockDownAssayQuery query = new KnockDownAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public static dss.vector.solutions.entomology.MosquitoCollection searchByGeoEntityAndDate(
      dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static MosquitoCollection searchByGeoEntityAndDateAndCollectionMethod(GeoEntity geoEntity, Date collectionDate, CollectionMethod collectionMethod)
  {
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));
    query.AND(query.getCollectionMethod().EQ(collectionMethod));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }
}
