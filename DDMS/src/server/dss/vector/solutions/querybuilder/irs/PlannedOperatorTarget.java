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

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class PlannedOperatorTarget extends PlannedResourceTarget implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.PlannedOperatorTarget";
  
  // private String operSprayTable;
  // private String targetCol;
  // private String sprayOperatorCol;

  public PlannedOperatorTarget(IRSQB irsQB)
  {
    super(irsQB);

    // MdEntityDAOIF operSprayMd =
    // MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
    // this.operSprayTable = operSprayMd.getTableName();
    // this.targetCol = QueryUtil.getColumnName(operSprayMd,
    // OperatorSpray.TARGET);
    // this.sprayOperatorCol = QueryUtil.getColumnName(operSprayMd,
    // OperatorSpray.SPRAYOPERATOR);
  }
  
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    this.irsQB.addRequiredView(View.RESOURCE_TARGET_VIEW);
    
    // Target week is required for all planned + activity joins
    Alias[] joinAliases = new Alias[]{Alias.TARGET, Alias.SPRAY_SEASON, Alias.SPRAY_OPERATOR_DEFAULT_LOCALE, Alias.DISEASE};
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_OPERATOR, joinAliases);
    
    Set<Alias> selected = this.irsQB.getSelectAliases();
    if(selected.contains(Alias.OPERATOR_PLANNED_COVERAGE))
    {
      // coverage requires target
      this.irsQB.addRequiredView(View.ALL_ACTUALS);
      
      this.irsQB.addRequiredAlias(View.PLANNED_OPERATOR, Alias.OPERATOR_PLANNED_TARGET);
    }
    
    if(selected.contains(Alias.OPERATOR_TARGET_DIVERGENCE))
    {
      this.irsQB.addRequiredView(View.ALL_ACTUALS);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.OPERATOR_ACTUAL_TARGET);
      
      this.irsQB.addRequiredAlias(View.PLANNED_OPERATOR, Alias.OPERATOR_PLANNED_TARGET);
    }
    
    // There are no geo entities for operator targets but selecting a universal will
    // require a column. Although the value will be null, include the column to keep
    // the SQL valid.
    if(this.irsQB.hasUniversal())
    {
      this.irsQB.addRequiredView(View.ALL_ACTUALS);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
      this.irsQB.addPlannedAlias(Alias.GEO_ENTITY);
    }
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_OPERATOR;
  }

  @Override
  public String setOperatorPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }

  @Override
  public String setTarget(Alias alias)
  {
    return setNULL(alias);
  }

  // @Override
  // public String setOperatorActualTarget(Alias alias)
  // {
  // String sql =
  // "(SELECT SUM("+this.targetCol+") FROM "+this.operSprayTable+" o INNER JOIN "+this.abstractSprayTable+" a on o."+idCol+" = a."+idCol+" WHERE o."+this.sprayOperatorCol+" = "+IRSQuery.RESOURCE_TARGET_VIEW+"."+this.q.getTargeter()+" AND "+IRSQuery.RESOURCE_TARGET_VIEW+"."+IRSQuery.TARGET_WEEK+" = get_epiWeek_from_date(a."+this.sprayDateCol+", "+this.q.getStartDay()+"))";
  // return set(sql, alias);
  // }

  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set("sprayoperator." + memberIdCol + " || ' - ' || "+OPERATOR_PERSON+"." + firstNameCol + " || ' ' || "
        + OPERATOR_PERSON + "." + lastNameCol, alias);
  }
  
  public String setSprayOperatorPersonId(Alias alias)
  {
    return set(OPERATOR_PERSON, this.identifierCol, alias);
  }

  public String setSprayOperatorBirthdate(Alias alias)
  {
    return set(OPERATOR_PERSON, this.birthdateCol, alias);
  }

  public String setSprayOperatorSex(Alias alias)
  {
    return set(OPERATOR_PERSON, this.sexCol, alias);
  }

  public String setSprayOperatorPerson(Alias alias)
  {
    return set(OPERATOR_PERSON, this.idCol, alias);
  }

  @Override
  public String setUniquePlannedId(Alias alias)
  {
    // Make planned id unique by keyName (spray operator + season) + target_week
    String uniqueId = keyName +"||'.'||"+Alias.TARGET_WEEK;
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), uniqueId, alias);
  }

  @Override
  public String FROM()
  {
    String resourceTargetView = IRSQB.View.RESOURCE_TARGET_VIEW.getView();
    String sql = "--Planned Operator Target\n";
    sql += resourceTargetView + " " + resourceTargetView + " INNER JOIN "
        + resourceTargetTable + " " + resourceTargetTable + " ON " + resourceTargetView + "."
        + idCol + " = " + resourceTargetTable + "." + idCol + " \n";
    sql += " INNER JOIN " + teamMemberTable + " sprayoperator ON " + resourceTargetTable + "."
        + targeter + " = sprayoperator." + idCol + " \n";
    sql += " INNER JOIN " + personTable + " AS " + OPERATOR_PERSON + " ON sprayoperator." + personCol
        + " = " + OPERATOR_PERSON + "."+this.idCol+" \n";

    
    
    return sql;
  }
}
