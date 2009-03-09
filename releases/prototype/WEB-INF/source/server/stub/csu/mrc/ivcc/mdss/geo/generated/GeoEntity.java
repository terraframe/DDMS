package csu.mrc.ivcc.mdss.geo.generated;


import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import csu.mrc.ivcc.mdss.geo.GeoHierarchy;
import csu.mrc.ivcc.mdss.geo.LocatedIn;
import csu.mrc.ivcc.mdss.geo.LocatedInQuery;

public abstract class GeoEntity extends GeoEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;
  
  public GeoEntity()
  {
    super();
  }
  
  public static csu.mrc.ivcc.mdss.geo.generated.GeoEntity searchByGeoId(java.lang.String geoId)
  {
    GeoEntity geoEntity = null;
    QueryFactory factory = new QueryFactory();
    GeoEntityQuery query = new GeoEntityQuery(factory);    

    query.getGeoId().EQ(geoId);
    
    OIterator<? extends GeoEntity> iterator = query.getIterator();
    
    if(iterator.hasNext())
    {
      geoEntity = iterator.next();      
    }
    
    iterator.close();
    
    if(geoEntity == null)
    {
      String msg = "A GeoEntity with the geoId [" + geoId + "] does not exist";
      throw new InvalidIdException(msg, geoId);
    }
    
    return geoEntity;
  }
  
  /**
   * Deletes this GeoEntity and all its children in the LocatedIn relationship.
   */
  @Override
  @Transaction
  public void delete()
  {
    OIterator<? extends GeoEntity> iter = this.getAllContainsGeoEntity();
    
    try
    {
      while(iter.hasNext())
      {
        // FIXME only delete if this entity is the sole parent
        iter.next().delete();
      }
    }
    finally
    {
      iter.close();
    }
    
    super.delete();
  }
  
  /**
   * This method removes any existing parents for {@link LocatedIn} and
   * adds the GeoEntity with the given id as its new parent.
   */
  @Override
  @Transaction
  public void applyWithParentGeoEntity(String parentGeoEntityId)
  {
    GeoEntity parent = validateParentEntity(parentGeoEntityId);
    
    this.apply();
    
    OIterator<? extends LocatedIn> iter = this.getAllLocatedInGeoEntityRel();
    try
    {
      while(iter.hasNext())
      {
        iter.next().delete();
      }

      this.addLocatedInGeoEntity(parent).apply();
    }
    finally
    {
      iter.close();      
    }
  }
  
  /**
   * Validates that this GeoEntity is allowed in the given parent GeoEntity.
   * 
   * @param parentGeoEntityId
   * @return
   */
  private GeoEntity validateParentEntity(String parentGeoEntityId)
  {
    GeoEntity geoEntity = GeoEntity.get(parentGeoEntityId);
    
    MdBusiness childMd = MdBusiness.getMdBusiness(this.getType());
    MdBusiness parentMd = MdBusiness.getMdBusiness(geoEntity.getType());
    
    GeoHierarchy parentGH = GeoHierarchy.getGeoHierarchyFromType(parentMd);
    GeoHierarchy childGH = GeoHierarchy.getGeoHierarchyFromType(childMd);
    
    OIterator<? extends GeoHierarchy> iter = childGH.getAllAllowedInGeoEntity();
    try
    {
      
    }
    finally
    {
      iter.close();
    }
    
    return geoEntity;
  }
  
  /**
   * Returns a list of all LocatedIn children for which this
   * GeoEntity is a parent. The list is ordered by the entity name.
   */
  @Override
  public GeoEntityQuery getOrderedChildEntities()
  {
    QueryFactory f = new QueryFactory();
    GeoEntityQuery geoQuery = new GeoEntityQuery(f);
    LocatedInQuery locQuery = new LocatedInQuery(f);
    
    geoQuery.WHERE(geoQuery.getId().EQ(this.getId()));
    geoQuery.WHERE(geoQuery.containsGeoEntity(locQuery));
    geoQuery.ORDER_BY_ASC(geoQuery.getEntityName());
    
    return geoQuery;
  }
  
}