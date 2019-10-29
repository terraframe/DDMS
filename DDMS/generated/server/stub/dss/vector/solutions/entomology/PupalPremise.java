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
package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class PupalPremise extends PupalPremiseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 705461057;
  
  public PupalPremise()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getCollection() != null && this.getPremiseType() != null)
    {
      return this.getCollection().getKey() + " - " + this.getPremiseType().getTermDisplayLabel().getValue();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    if (this.getCollection() != null && this.getPremiseType() != null)
    {
      return this.getCollection().getKey() + " - " + this.getPremiseType().getTermId();
    }

    return super.buildKey();
  }

  @Override
  @Transaction
  public void delete()
  { 
    // First delete all of the containers
    PupalContainerQuery query = new PupalContainerQuery(new QueryFactory());

    query.WHERE(query.getPremise().EQ(this));

    OIterator<? extends PupalContainer> it = query.getIterator();

    try
    {
      List<? extends PupalContainer> containers = it.getAll();

      for(PupalContainer container : containers)
      {
        container.delete();
      }
    }
    finally
    {
      it.close();
    }
    
    super.delete();
    
    PupalCollection _collection = this.getCollection();
    
    if(!_collection.hasPremises())
    {
      _collection.delete();
    }
  }
  
  public PupalCollectionView getView()
  {
    PupalCollectionView view = new PupalCollectionView();
    
    PupalCollection _collection = this.getCollection();
    
    view.populateView(_collection, this);
    
    return view;
  }

}
