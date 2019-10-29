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

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public abstract class PlannedTargetUnion extends AbstractTargetUnion implements Reloadable
{
  public abstract String setId(Alias alias);
  
  public abstract String setTarget(Alias alias);

  public abstract String setUniqueSprayId(Alias alias);
  
  public abstract String setUniquePlannedId(Alias alias);

  public abstract String setPlannedDate(Alias alias);
  
  public abstract String setSpraySeason(Alias alias);
  
  protected String keyName;
  
  public PlannedTargetUnion(IRSQB irsQB)
  {
    super(irsQB);
    
    keyName = QueryUtil.getColumnName(Metadata.getKeyNameMd());
  }
  
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
    for(Alias select : selectAliases)
    {
      this.irsQB.addRequiredAlias(this.getView(), select);
    }
    
    this.irsQB.addRequiredAlias(this.getView(), Alias.ID, Alias.UNIQUE_PLANNED_ID, Alias.PLANNED_DATE);
  }
  
  public String setSprayOperator(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setGeoEntity(Alias alias)
  {
    return setNULL(alias);
  }

  public String setParentGeoEntity(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayOperatorPersonId(Alias alias)
  {
    return setNULL(alias);
  }

  public String setSprayOperatorBirthdate(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayOperatorSex(Alias alias)
  {
    return setNULL(alias);
  }

  public String setSprayOperatorPerson(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayTeam(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setOperatorPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setTeamPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }
  
  public String setDisease(Alias alias)
  {
    return set(IRSQB.PLANNED_TARGET_DISEASE, alias);
  }
  
  public String setAreaPlannedTarget(Alias alias)
  {
    return setNULL(alias);
  }
  
  public final String setTargetWeek(Alias alias)
  {
    return set(alias.getAlias(), alias);    
  }

}
