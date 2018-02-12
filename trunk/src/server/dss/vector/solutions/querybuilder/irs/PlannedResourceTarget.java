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
package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public abstract class PlannedResourceTarget extends PlannedTargetUnion implements Reloadable
{
  protected String resourceTargetTable;
  protected String disease;
  protected String spraySeason;
  
  public PlannedResourceTarget(IRSQB irsQB)
  {
    super(irsQB);
    
    this.resourceTargetTable = MdEntity.getMdEntity(ResourceTarget.CLASS).getTableName();
    this.disease = QueryUtil.getColumnName(ResourceTarget.getDiseaseMd());
    this.spraySeason = QueryUtil.getColumnName(ResourceTarget.getSeasonMd());
    
  }
  
  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(),  Alias.PLANNED_DATE.getAlias(), alias);
  }
  
  @Override
  public String setSpraySeason(Alias alias)
  {
    return set(resourceTargetTable, spraySeason, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return set(resourceTargetTable, idCol, alias);
  }
  
  @Override
  public final String setUniqueSprayId(Alias alias)
  {
    return this.setNULL(alias);
  }
  
  @Override
  public String setDisease(Alias alias)
  {
    return set(resourceTargetTable, disease, alias);
  }
}
