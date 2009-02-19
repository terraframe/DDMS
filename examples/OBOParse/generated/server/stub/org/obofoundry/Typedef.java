package org.obofoundry;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdRelationship;

public class Typedef extends TypedefBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public Typedef()
  {
    super();
  }  

  public void delete()
  {
    MdRelationship mdRelationship = this.getMdRelationship();
    if (mdRelationship != null)
    {
      this.getMdRelationship().delete();
    }
    super.delete();
  }
  
  public static Typedef getByName(String name)
  {
    TypedefQuery q = new TypedefQuery(new QueryFactory());
    q.WHERE(q.getComponentName().EQ(name));
    
    OIterator<? extends Typedef> iterator = q.getIterator();
    
    if (iterator.hasNext())
    {
      return iterator.next();
    }
    else
    {
      return null;
    }
  }
}