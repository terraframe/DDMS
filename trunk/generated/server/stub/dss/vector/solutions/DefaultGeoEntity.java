package dss.vector.solutions;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

/**
 * A singleton that represents the default geo entity of the system.
 */
public class DefaultGeoEntity extends DefaultGeoEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 648964684;
  
  public DefaultGeoEntity()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    if(isNew())
    {
      String error = "Only one instance of DefaultGeoEntity can exist."; 
      throw new ProgrammingErrorException(error);
    }
    
    super.apply();
  }
  
  @Override
  public String toString()
  {
    return this.getClassDisplayLabel();
  }
  
  @Override
  public void delete()
  {
    String error = "The singleton object of DefaultGeoEntity cannot be deleted.";
    throw new ProgrammingErrorException(error);
  }
  
  public static DefaultGeoEntity getDefaultGeoEntity()
  {
    QueryFactory f= new QueryFactory();
    DefaultGeoEntityQuery q = new DefaultGeoEntityQuery(f);
    
    OIterator<? extends DefaultGeoEntity> iter = q.getIterator();
    
    try
    {
      return iter.next();
    }
    finally
    {
      iter.close();
    }
    
  }
  
}
