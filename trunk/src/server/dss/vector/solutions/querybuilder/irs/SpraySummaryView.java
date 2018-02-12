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

import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class SpraySummaryView extends AuxiliaryProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.SpraySummaryView";

  public SpraySummaryView(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected View getView()
  {
    return View.SPRAY_SUMMARY_VIEW;
  }

  @Override
  public String getSQL()
  {
    String sprayedRooms = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedRoomsMd());
    String sprayedHouseholds = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedHouseholdsMd());
    String sprayedStructures = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedStructuresMd());
    String householdSprayStatus = MdEntity.getMdEntity(HouseholdSprayStatus.CLASS).getTableName();
    String operatorSpray = MdEntity.getMdEntity(OperatorSpray.CLASS).getTableName();

    String sql = "";
    sql += "SELECT \n";
    sql += " o." + QueryUtil.getIdColumn() + " as " + IRSQB.OPERATOR_SPRAY_ID + ", \n";
    sql += " SUM(" + sprayedRooms + ") as " + IRSQB.SPRAYED_ROOMS_SUM + ", \n";
    sql += " SUM(" + sprayedHouseholds + ") as " + IRSQB.SPRAYED_HOUSEHOLDS_SUM + ", \n";
    sql += " SUM(" + sprayedStructures + ") as " + IRSQB.SPRAYED_STRUCTURES_SUM + " \n";
    sql += "FROM \n";
    sql += " " + householdSprayStatus + " h inner join " + operatorSpray + " o on o."
        + QueryUtil.getIdColumn() + " = h.spray \n";
    sql += "GROUP BY \n";
    sql += " o.id \n";
    return sql;
  }

}
