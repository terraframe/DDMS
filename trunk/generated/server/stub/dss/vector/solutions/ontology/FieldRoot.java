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

public class FieldRoot extends FieldRootBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252959713186L;

  public FieldRoot(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public FieldRoot(dss.vector.solutions.ontology.BrowserField parent, dss.vector.solutions.ontology.BrowserRoot child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    // If you're changing this method make sure you're aware of ticket 3214. Its a nasty bug and last time this source was changed it caused that bug.  
    
    if (this.getParentId() != null && this.getChildId() != null)
    {
      BrowserRoot child = BrowserRoot.get(this.getChildId());

      return child.getKey();
    }

    return super.buildKey();
  }

}
