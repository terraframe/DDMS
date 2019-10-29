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

import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.InTeam;
import dss.vector.solutions.querybuilder.IRSQB;

public class PlannedSprayTeamRollup extends PlannedSprayTeamTarget implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.PlannedSprayTeamRollup";

  private String inTeamTable;

  public PlannedSprayTeamRollup(IRSQB irsQB)
  {
    super(irsQB);

    this.inTeamTable = MdEntityDAO.getMdEntityDAO(InTeam.CLASS).getTableName();
  }

  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set(OPERATOR_MEMBER + "."+memberIdCol+" || ' - ' || "+OPERATOR_PERSON+"."+firstNameCol+
        " || ' ' || "+OPERATOR_PERSON+"."+lastNameCol, alias);
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
  public String setTeamPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }

  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }

  @Override
  public String setUniquePlannedId(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), idCol, alias);
  }

  // @Override
  // public String setTeamActualTarget(Alias alias)
  // {
  // String sql = "(SELECT SUM(" + this.targetCol + ") FROM " +
  // this.teamSprayTable + " t INNER JOIN "
  // + this.abstractSprayTable + " a on t." + idCol + " = a." + idCol +
  // " WHERE t."
  // + this.sprayTeamCol + " = " + IRSQuery.RESOURCE_TARGET_VIEW + "." +
  // this.q.getTargeter()
  // + " AND " + IRSQuery.RESOURCE_TARGET_VIEW + "." + IRSQuery.TARGET_WEEK
  // + " = get_epiWeek_from_date(a." + this.sprayDateCol + ", " +
  // this.q.getStartDay() + "))";
  // return set(sql, alias);
  // }

  @Override
  public String FROM()
  {
    String resourceTargetView = IRSQB.View.RESOURCE_TARGET_VIEW.getView();
    String sql = "--Planned Spray Operator Target\n";

    sql += resourceTargetView + " " + resourceTargetView + " \n";
    sql += "INNER JOIN " + resourceTargetTable + " " + resourceTargetTable + " \n";
    sql += "ON " + resourceTargetView + "." + idCol + " = " + resourceTargetTable + "." + idCol
        + " \n";
    sql += "INNER JOIN " + teamMemberTable + " " + OPERATOR_MEMBER + " ON " + resourceTargetTable + "."
        + targeter + " = " + OPERATOR_MEMBER + "." + idCol + "  \n";
    sql += "INNER JOIN " + inTeamTable + " " + inTeamTable + " ON " + inTeamTable + "."
        + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + OPERATOR_MEMBER + "." + idCol + " \n";
    sql += "INNER JOIN " + sprayTeamTable + " " + sprayTeamTable + " ON " + sprayTeamTable + "." + idCol
        + " = " + inTeamTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    sql += "INNER JOIN " +personTable+" AS "+OPERATOR_PERSON+" ON "+OPERATOR_PERSON+"."+idCol+" = "+OPERATOR_MEMBER+"."+personCol;

    return sql;
  }
}
