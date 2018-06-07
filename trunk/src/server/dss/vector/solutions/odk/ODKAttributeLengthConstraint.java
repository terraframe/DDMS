package dss.vector.solutions.odk;

import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class ODKAttributeLengthConstraint extends ODKAttributeConstraint implements Reloadable
{
  private ODKAttribute attr;
  
  private String size;
  
  private MdAttributeCharacterDAOIF mdAttr;
  
  public ODKAttributeLengthConstraint(MdAttributeCharacterDAOIF mdAttr, ODKAttribute attr, String size, String localized)
  {
    super(localized);
    
    this.attr = attr;
    this.size = size;
    this.mdAttr = mdAttr;
  }
  
  @Override
  public String getBindConstraint()
  {
    return "string-length(.) <= " + size;
  }

}
