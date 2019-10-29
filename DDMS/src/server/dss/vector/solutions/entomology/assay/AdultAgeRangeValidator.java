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
package dss.vector.solutions.entomology.assay;

import com.runwaysdk.generation.loader.Reloadable;

public class AdultAgeRangeValidator implements Reloadable
{
  private boolean modified;
  
  private AdultAgeRange ageRange;
  
  public AdultAgeRangeValidator(AdultAssay assay)
  {
    this(assay.getAgeRange());    
  }
  
  public AdultAgeRangeValidator(EfficacyAssay assay)
  {
    this(assay.getAgeRange());
  }
  
  public AdultAgeRangeValidator(AdultAgeRange ageRange)
  {
    super();
    this.ageRange = ageRange;
    this.modified = (ageRange.isModified(AdultAgeRange.STARTPOINT) ||ageRange.isModified(AdultAgeRange.ENDPOINT));
  }

  public void validate()
  {
    if (modified)
    {
      ageRange.validate();
    }
  }
}
