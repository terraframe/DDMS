package csu.mrc.ivcc.mdss.geo.generated;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdBusiness;

import csu.mrc.ivcc.mdss.geo.ConfirmParentChangeException;
import csu.mrc.ivcc.mdss.geo.GeoHierarchy;
import csu.mrc.ivcc.mdss.geo.LocatedIn;
import csu.mrc.ivcc.mdss.geo.LocatedInException;
import csu.mrc.ivcc.mdss.geo.LocatedInQuery;

public abstract class GeoEntity extends GeoEntityBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;

  public GeoEntity()
  {
    super();
  }

  /**
   * Updates this GeoEntity and its children if its activated
   * attribute has been modified.
   * 
   * @return
   */
  @Override
  @Transaction
  public String[] updateFromTree()
  {
    List<String> ids = new LinkedList<String>();
    
    if(this.isModified(GeoEntity.ACTIVATED))
    {
      ids.addAll(setChildEntityActivated(this.getActivated(), this));
      
      ids.add(this.getId());
    }
    
    this.apply();
    
    return ids.toArray(new String[ids.size()]);
  }

  public static GeoEntity searchByGeoId(java.lang.String geoId)
  {
    GeoEntity geoEntity = null;
    QueryFactory factory = new QueryFactory();
    GeoEntityQuery query = new GeoEntityQuery(factory);

    query.getGeoId().EQ(geoId);

    OIterator<? extends GeoEntity> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        geoEntity = iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }

    if (geoEntity == null)
    {
      String msg = "A GeoEntity with the geoId [" + geoId + "] does not exist";
      throw new InvalidIdException(msg, geoId);
    }

    return geoEntity;
  }
  
  /**
   * Throws an exception to alert the user before they change an entity's parent.
   */
  @Override
  public void confirmChangeParent(String parentId)
  {
    GeoEntity parent = GeoEntity.get(parentId);
    
    ConfirmParentChangeException ex = new ConfirmParentChangeException();
    ex.setEntityName(parent.getEntityName());
    
    throw ex;
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
      while (iter.hasNext())
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
   * Returns all parents of this GeoEntity.
   * 
   * @return
   */
  private List<GeoEntity> getImmediateParents()
  {
    List<GeoEntity> parents = new LinkedList<GeoEntity>();
    OIterator<? extends GeoEntity> iter = this.getAllLocatedInGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        parents.add(iter.next());
      }
    }
    finally
    {
      iter.close();
    }
    
    return parents;
  }

  /**
   * Sets all children of the parent GeoEntity to the given activated status. If
   * a child has more than one parent then nothing is changed for that child.
   * 
   * @param activated
   * @param parent
   * @return A list of ids for each updated child.
   */
  private List<String> setChildEntityActivated(boolean activated, GeoEntity parent)
  {
    List<String> ids = new LinkedList<String>();
    OIterator<? extends GeoEntity> iter = parent.getAllContainsGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        GeoEntity child = iter.next();

        // CHECK: a child with more than one parent set to active
        // cannot be deactivated.
        List<GeoEntity> parents = child.getImmediateParents();
        boolean changeActivated = true;
        if(!activated && parents.size() > 1)
        {
          for(GeoEntity nextParent : parents)
          {
            if(nextParent.getActivated())
            {
              changeActivated = false;
              break;
            }
          }
        }
        
        if (changeActivated)
        {
          child.appLock();
          child.setActivated(activated);
          child.apply();
          
          ids.add(child.getId());

          setChildEntityActivated(activated, child);
        }
      }
    }
    finally
    {
      iter.close();
    }
    
    return ids;
  }

  /**
   * Adds this GeoEntity as a child of the given parent for the {@link LocatedIn} relationship.
   * If this is not for a clone operation then all prior parent relationships will be removed.
   * 
   */
  @Override
  @Transaction
  public String[] applyWithParentGeoEntity(String parentGeoEntityId, Boolean cloneOperation)
  {
    if (this.isNew())
    {
      this.apply();
    }

    if (!cloneOperation)
    {
      OIterator<? extends LocatedIn> iter = this.getAllLocatedInGeoEntityRel();
      try
      {
        while (iter.hasNext())
        {
          iter.next().delete();
        }
      }
      finally
      {
        iter.close();
      }
    }
    
    GeoEntity parent =  GeoEntity.get(parentGeoEntityId);
    this.addLocatedInGeoEntity(parent).apply();
    
    // update activated status on all new children
    List<String> ids = setChildEntityActivated(parent.getActivated(), parent);
    return ids.toArray(new String[ids.size()]);
  }
  
  @Override
  public LocatedIn addContainsGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(geoEntity.getType(), this.getType());
    return super.addContainsGeoEntity(geoEntity);
  }
  
  @Override
  public LocatedIn addLocatedInGeoEntity(GeoEntity geoEntity)
  {
    validateHierarchy(this.getType(), geoEntity.getType());
    return super.addLocatedInGeoEntity(geoEntity);
  }

  /**
   * Validates that this GeoEntity is allowed in the given parent GeoEntity.
   * 
   * @param parentGeoEntityId
   * @return
   */
  private void validateHierarchy(String childType, String parentType)
  {
    MdBusiness childMd = MdBusiness.getMdBusiness(childType);
    MdBusiness parentMd = MdBusiness.getMdBusiness(parentType);

    GeoHierarchy parentGH = GeoHierarchy.getGeoHierarchyFromType(parentMd);
    GeoHierarchy childGH = GeoHierarchy.getGeoHierarchyFromType(childMd);

    OIterator<? extends GeoHierarchy> iter = childGH.getAllAllowedInGeoEntity();
    boolean match = false;
    try
    {
      while (iter.hasNext())
      {
        GeoHierarchy gh = iter.next();
        match = checkHierarchy(parentGH, gh);
        
        if(match)
        {
          break;
        }
      }
    }
    finally
    {
      iter.close();
    }

    if (!match)
    {
      LocatedInException e = new LocatedInException();
      e.setEntityName(this.getEntityName());
      e.setParentDisplayLabel(parentMd.getDisplayLabel());
      throw e;
    }
  }

  /**
   * Recursively checks if the child GeoHierarcy is allowed in the parent.
   * 
   * @param parent
   * @param child
   * @return
   */
  private boolean checkHierarchy(GeoHierarchy parent, GeoHierarchy child)
  {
    if (child.getId().equals(parent.getId()))
    {
      return true;
    }

    // check the parent's parents
    OIterator<? extends GeoHierarchy> iter = child.getAllAllowedInGeoEntity();
    try
    {
      while (iter.hasNext())
      {
        GeoHierarchy next = iter.next();
        if (checkHierarchy(parent, next))
        {
          return true;
        }
      }
    }
    finally
    {
      iter.close();
    }

    return false;
  }

  /**
   * Returns a list of all LocatedIn children for which this GeoEntity is a
   * parent. The list is ordered by the entity name.
   */
  @Override
  public GeoEntityQuery getOrderedChildEntities()
  {
    QueryFactory f = new QueryFactory();
    GeoEntityQuery geoQuery = new GeoEntityQuery(f);
    LocatedInQuery locQuery = new LocatedInQuery(f);

    locQuery.WHERE(locQuery.parentId().EQ(this.getId()));
    geoQuery.WHERE(geoQuery.locatedInGeoEntity(locQuery));
    geoQuery.ORDER_BY_ASC(geoQuery.getEntityName());

    return geoQuery;
  }

}