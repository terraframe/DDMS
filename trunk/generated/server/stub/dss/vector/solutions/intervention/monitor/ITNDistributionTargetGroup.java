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
package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNDistributionTargetGroup extends ITNDistributionTargetGroupBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1253142896539L;
  
  public ITNDistributionTargetGroup(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNDistributionTargetGroup(ITNDistribution parent, Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNDistributionTargetGroup clone(ITNDistributionView parent)
  {
    ITNDistributionTargetGroup clone = new ITNDistributionTargetGroup(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
  
}
