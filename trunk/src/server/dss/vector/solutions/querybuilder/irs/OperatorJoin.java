package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class OperatorJoin extends TargetJoin implements Reloadable
{

  public OperatorJoin(IRSQB irsQB, boolean hasActual, boolean hasPlanned)
  {
    super(irsQB, hasActual, hasPlanned);
  }
  
  public final String FROM()
  {
    String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_OPERATOR + " " + TargetJoin.PLANNED_ALIAS;

    if (hasPlanned)
    {
      String sql = "";

      sql += a + " FULL OUTER JOIN " + p + " \n";
      
      // NOTE: old code for reference
//      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
//      "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      
      // FIXED: joined based on spray season instead of year + week
      sql += "AND "+TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_SEASON + " \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " \n";
      
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
