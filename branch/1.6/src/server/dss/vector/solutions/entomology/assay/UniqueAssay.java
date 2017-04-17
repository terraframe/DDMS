package dss.vector.solutions.entomology.assay;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LocalProperty;

/**
 * Interface for assay classes whose records are uniquely identified
 * by a field that follows the DDMS auto-generated specification (unless 
 * a custom identifier is provided). See {@link LocalProperty#getNextId()}
 * for specification details.
 * 
 * Because the assay classes don't extend a common domain class, this 
 * Interface serves more as a marker than provide anything
 * functional.
 */
public interface UniqueAssay extends Reloadable
{
  
  /**
   * The name of the unique assay id field on all implementing classes.
   */
  public static final String UNIQUE_ASSAY_ID = "uniqueAssayId";
  
  /**
   * 
   * @return
   */
  public boolean isNew();
  
  /**
   * 
   * @return
   */
  public String getUniqueAssayId();
  
  /**
   * 
   * @param value
   */
  public void setUniqueAssayId(String value);
}
