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


public class BrowserFieldView extends BrowserFieldViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713136L;
  
  public BrowserFieldView()
  {
    super();
  }
  
  @Override
  public BrowserRootView[] getRoots()
  {
    BrowserField concrete = this.getConcrete();
    
    if(concrete != null)
    {
      return concrete.getRoots();
    }
    
    return new BrowserRootView[0];
  }

  private BrowserField getConcrete()
  {
    if(this.getBrowserFieldId() != null && this.getBrowserFieldId().length() > 0)
    {
      return BrowserField.get(this.getBrowserFieldId());
    }
    
    return null;
  }
}
