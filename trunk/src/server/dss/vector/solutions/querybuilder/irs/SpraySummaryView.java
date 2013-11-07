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
