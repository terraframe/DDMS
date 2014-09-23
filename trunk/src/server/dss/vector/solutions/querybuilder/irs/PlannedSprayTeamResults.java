package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class PlannedSprayTeamResults extends AbstractSQLProvider implements Reloadable
{

  public PlannedSprayTeamResults(IRSQB irsQB)
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
    return View.PLANNED_TEAM_RESULTS;
  }

  @Override
  public String getSQL()
  {
    String sql = "";
    
    sql += "SELECT * FROM "+IRSQB.View.PLANNED_TEAM+" \n";
    sql += "UNION ALL \n";
    sql += "( \n";
    sql += "  SELECT * FROM "+IRSQB.View.PLANNED_TEAM_ROLLUP+" ptr  \n";
    sql += "  EXCEPT \n";
    sql += "  SELECt ptr.* FROM "+IRSQB.View.PLANNED_TEAM_ROLLUP+" ptr  \n";
    sql += "  INNER JOIN "+IRSQB.View.PLANNED_TEAM+" pt \n";
    sql += "  ON ptr."+Alias.SPRAY_TEAM.getAlias()+" = pt."+Alias.SPRAY_TEAM.getAlias()+"  \n";
    sql += "  AND ptr."+Alias.SPRAY_SEASON.getAlias()+" = pt."+Alias.SPRAY_SEASON.getAlias()+" \n";
    sql += "  AND ptr."+Alias.DISEASE.getAlias()+" = pt."+Alias.DISEASE.getAlias()+" \n";
//    sql += "  AND ptr."+Alias.TARGET_WEEK.getAlias()+" = pt."+Alias.TARGET_WEEK.getAlias()+" \n";
    sql += "  AND ptr."+Alias.SPRAY_TEAM.getAlias()+" = pt."+Alias.TARGET.getAlias()+" \n";
    sql += ") \n";
    
    return sql;
  }
}
