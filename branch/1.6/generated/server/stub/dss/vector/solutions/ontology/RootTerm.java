package dss.vector.solutions.ontology;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

/**
 * The RootTerm class acts like an immutable singleton. One instance is created upon
 * installation and that instance cannot be deleted, nor can more than one instance be
 * created.
 */
public class RootTerm extends RootTermBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 911811651;
  
  public RootTerm()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    
    // enforce singleton behavior (the user should never be able to
    // get to this point, but the check is enforced regardless).
    if(this.isNew())
    {
      QueryFactory f = new QueryFactory();
      RootTermQuery q = new RootTermQuery(f);
      
      if(q.getCount() > 0)
      {
        String error = "Cannot define more than one type tree root.";
        throw new ProgrammingErrorException(error);
      }
    }
    
    // Cannot mark the root as inactive because it will invalidate
    // the entire tree (the UI should not allow this modification anyway).
    OIterator<? extends InactiveProperty> iter = this.getAllInactiveProperties();
    
    try
    {
      while(iter.hasNext())
      {
        InactiveProperty prop = iter.next();
        
        if(prop.getInactive())
        {
          String error = "Cannot set the root as inactive for any disease.";
          throw new ProgrammingErrorException(error);
        }
      }
    }
    finally
    {
      iter.close();
    }
    
    super.apply();
  }
  
  @Override
  public void delete()
  {
    String error = "Cannot delete the type tree root.";
    throw new ProgrammingErrorException(error);
  }
  
  public static RootTerm getRootInstance()
  {
    QueryFactory f = new QueryFactory();
    RootTermQuery q = new RootTermQuery(f);
    
    OIterator<? extends RootTerm> iter = q.getIterator();
    try
    {
      return iter.next(); // there will always be one and only one
    }
    finally
    {
      iter.close();
    }
  }
}
