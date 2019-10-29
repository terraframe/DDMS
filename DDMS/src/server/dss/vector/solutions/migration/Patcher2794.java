package dss.vector.solutions.migration;

import com.runwaysdk.constants.MdAttributeConcreteInfo;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttributeDimension;

import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.MenuItemQuery;
import dss.vector.solutions.general.SystemURLQuery;

public class Patcher2794 implements DDMSPatchIF, Reloadable
{
  @Override
  public Integer getPatchVersion()
  {
    return 7154;
  }

  @Override
  public void doIt()
  {
    try
    {
      // The DDMS does not support renaming of attributes via xml!! This is due to a limitation of the way that model merging
      // interacts with MdAttributeDimensions.
      
      MdAttributeDAO mdAttr = (MdAttributeDAO) MdAttributeDAO.getByKey(SubCollection.CLASS + "." + "female");
      
      mdAttr.getAttribute(MdAttributeConcreteInfo.NAME).setValue(SubCollection.FEMALESUNKNOWN);
      
      mdAttr.apply();
    }
    catch (Throwable t)
    {
      t.printStackTrace();
      // do nothing
    }
  }

  @Override
  public void undoIt()
  {
    throw new UnsupportedOperationException();
  }
}
