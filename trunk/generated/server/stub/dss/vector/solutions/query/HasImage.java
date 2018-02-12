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

public class HasImage extends HasImageBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -608891671;
  
  public HasImage(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasImage(dss.vector.solutions.query.SavedMap parent, dss.vector.solutions.query.MapImage child)
  {
    this(parent.getId(), child.getId());
  }
  
}
