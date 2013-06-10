package dss.vector.solutions.entomology.assay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Component;
import com.runwaysdk.business.View;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.LocalProperty;

/**
 * Utility class to manage UniqueAssay behavior, specifically
 * setting the unique identifier.
 */
public class UniqueAssayUtil implements Reloadable
{

  private static Log log = LogFactory.getLog(UniqueAssayUtil.class);
  
  /**
   * Checks if the attribute on the given Business object has a value ready
   * @param bus
   * @param name
   * @return
   */
  public static boolean allowAttributeUpdate(View src, Business dest, String name)
  {
    if(dest.isNew())
    {
      // a new instance should have every field validated
      return true;
    }
    else if(src.isModified(name))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Checks if the attribute on the given Business object has a value ready
   * @param bus
   * @param name
   * @return
   */
  public static boolean allowAttributeUpdate(View src, View dest, String name)
  {
    if(src.isModified(name))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Sets a unique id on the given UniqueAssay if a valid value
   * is not already set.
   * 
   * @param assay
   */
  public static void setUniqueAssayId(UniqueAssay assay)
  {
    // Generate an assay id if one is not provided. 
    String uai = assay.getUniqueAssayId();
    if(uai == null || uai.trim().length() == 0)
    {
      String genId = LocalProperty.getNextId();
      assay.setUniqueAssayId(genId);
      
      log.debug(String.format("Set the id [%s] on assay [%s].", genId, assay.toString()));
    }
    else
    {
      log.debug(String.format("Assay [%s] already exists with id [%s].", assay.toString(), uai)); 
    }
  }
  
  /**
   * Gets the assay with the given id or if one is not found a new instance with that id
   * is returned. The returned instance is not persisted to the database.
   * 
   * @param clazz
   * @param uniqueAssayId
   * @return
   */
  public static <T extends UniqueAssay> T getOrCreateAssay(Class<T> clazz, String uniqueAssayId)
  {
    String type = clazz.getName();
    
    if(uniqueAssayId != null && uniqueAssayId.trim().length() > 0)
    {
      QueryFactory f = new QueryFactory();
      BusinessQuery q = f.businessQuery(type);
      
      q.WHERE(q.get(UniqueAssay.UNIQUE_ASSAY_ID).EQ(uniqueAssayId));
      
      OIterator<Business> iterator = q.getIterator();
      
      try
      {
        if(iterator.hasNext())
        {
          T assay = clazz.cast(iterator.next());

          String msg =  "Assay ["+assay+"] of type [%s] and id [%s] was found.";
          log.debug(String.format(msg, type, uniqueAssayId));
          
          return assay;
        }
      }
      finally
      {
        iterator.close();
      }
    }
    
    // could not find assay by id, so create a new instance with the given id
    Business obj = BusinessFacade.newBusiness(clazz.getName());
    T assay = clazz.cast(obj);
    
    assay.setUniqueAssayId(uniqueAssayId);
    
    String msg =  "Assay of type [%s] not found with id [%s].";
    log.debug(String.format(msg, type, uniqueAssayId));
    
    return assay;
  }
  
}
