/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
