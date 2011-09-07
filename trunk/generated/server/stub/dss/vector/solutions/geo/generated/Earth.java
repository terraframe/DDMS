package dss.vector.solutions.geo.generated;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.DeleteEarthException;
import dss.vector.solutions.geo.DuplicateEarthException;

public class Earth extends EarthBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236717064787L;

  public Earth()
  {
    super();
  }

  /**
   * Applies the Earth instance, but throws an exception
   * if the instance is new.
   *
   * @throws DuplicateEarthException if the instance is new.
   */
  @Override
  public void apply()
  {
    if(isNew())
    {
      MdBusiness earthMd = MdBusiness.getMdBusiness(Earth.CLASS);

      String error = "Cannot define more than one instance of Earth.";
      DuplicateEarthException ex = new DuplicateEarthException(error);
      ex.setEarthName(earthMd.getDisplayLabel().getValue());
      throw ex;
    }

    super.apply();
  }

  /**
   * Delete method that always throws an exception. Earth cannot be deleted.
   */
  @Override
  public void delete()
  {
    MdBusiness earthMd = MdBusiness.getMdBusiness(Earth.CLASS);

    String error = "Cannot delete the Earth instance.";
    DeleteEarthException ex = new DeleteEarthException(error);
    ex.setEarthName(earthMd.getDisplayLabel().getValue());
    throw ex;
  }

  /**
   * Returns the one and only instance of Earth.
   *
   * @return
   */
  public static Earth getEarthInstance()
  {
    QueryFactory f = new QueryFactory();
    EarthQuery q = new EarthQuery(f);

    OIterator<? extends Earth> iter = q.getIterator();
    try
    {
      while(iter.hasNext())
      {
        return iter.next(); // There will always be one.
      }

      return null;
    }
    finally
    {
      iter.close();
    }
  }

}
