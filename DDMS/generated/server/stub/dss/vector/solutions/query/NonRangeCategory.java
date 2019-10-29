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
package dss.vector.solutions.query;



public class NonRangeCategory extends NonRangeCategoryBase implements com.runwaysdk.generation.loader.Reloadable, NonRangeCategoryIF
{
  private static final long serialVersionUID = 1241158039274L;

  public NonRangeCategory()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
  
  public String toString()
  {
    return this.getExactValueStr();
  }
  
  @Override
  protected void preValidate()
  {
    this.validateExactValueStr();
  }
  
  @Override
  protected void checkBounds(AbstractCategory category)
  {
    if(category instanceof NonRangeCategory)
    {
      String exact1 = this.getExactValueStr();
      String exact2 = ((NonRangeCategory) category).getExactValueStr();
      
      try
      {
        if(Double.parseDouble(exact1) == Double.parseDouble(exact2))
        {
          this.throwsOverlapException(exact1, exact2);
        }
      }
      catch(NumberFormatException e)
      {
        if(exact1.equals(exact2))
        {
          this.throwsOverlapException(exact1, exact2);
        }
      }
    }
    else
    {
      RangeCategory range = (RangeCategory) category;
      range.checkBounds(this);
    }
  }
}
