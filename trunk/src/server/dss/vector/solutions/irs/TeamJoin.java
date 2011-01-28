package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public class TeamJoin extends TargetJoin implements Reloadable
{
  
  public TeamJoin(boolean hasActual, boolean hasPlanned)
  {
    super(hasActual, hasPlanned);
  }
  
  public final String from()
  {
    String a = IRSQuery.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQuery.PLANNED_TEAM + " " + TargetJoin.PLANNED_ALIAS;

    if (hasPlanned)
    {
      String sql = "";

      sql += a + " FULL OUTER JOIN " + p + " \n";
      
      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
      "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";
      return sql;
    }
    else
    {
      return a;
    }
  }

}
