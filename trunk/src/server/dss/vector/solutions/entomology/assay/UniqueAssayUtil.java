package dss.vector.solutions.entomology.assay;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.BusinessQuery;
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
   * Ensures that no duplicate assay ids exist in the given array of UniqueAssay objects.
   * 
   * @param assays
   * @throws DuplicateAssayException Thrown if a duplicate assay id is found.
   */
  public static void validateUniqueAssayIds(UniqueAssay[] assays)
  {
    // make sure that two duplicate assay ids are rejected
    Set<String> assayIds = new HashSet<String>();
    for(UniqueAssay assay : assays)
    {
      String assayId = assay.getUniqueAssayId();
      if(assayId != null && assayIds.contains(assayId))
      {
        DuplicateAssayException ex = new DuplicateAssayException();
        ex.setAssayId(assayId);
        throw ex;
      }
      else if(assayId != null)
      {
        assayIds.add(assayId);
      }
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
