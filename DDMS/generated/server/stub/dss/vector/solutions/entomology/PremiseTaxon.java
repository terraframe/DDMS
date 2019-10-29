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

public class PremiseTaxon extends PremiseTaxonBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -378392539;
  
  public PremiseTaxon()
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
    else if (this.getPremise() != null && this.getTaxon() != null)
    {
      return this.getPremise().toString() + " - " + this.getTaxon().getTermDisplayLabel().getValue();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    if (this.getPremise() != null && this.getTaxon() != null)
    {
      return this.getPremise().getKey() + " - " + this.getTaxon().getTermId();
    }

    return super.buildKey();
  }
  
  @Override
  public void delete()
  {
    super.delete();
    
    CollectionPremise _premise = this.getPremise();
    
    if(!_premise.hasTaxons())
    {
      _premise.delete();
    }
  }
  
  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();
    
    CollectionPremise _premise = this.getPremise();
    ImmatureCollection _collection = _premise.getCollection();
    
    view.populateView(_collection, _premise, this);
    
    return view;
  }
}
