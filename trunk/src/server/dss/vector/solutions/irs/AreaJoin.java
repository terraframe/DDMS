package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public class AreaJoin extends TargetJoin implements Reloadable
{

  public AreaJoin(boolean hasActual, boolean hasPlanned)
  {
    super(hasActual, hasPlanned);
  }

  public final String from()
  {
    String a = IRSQuery.ACTUAL_AREA + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQuery.PLANNED_AREA + " " + TargetJoin.PLANNED_ALIAS;

    if (hasActual && hasPlanned)
    {
      String sql = "";

      sql += a + " FULL OUTER JOIN " + p + " \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.GEO_ENTITY + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.GEO_ENTITY + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";
      return sql;
    }
    else if (hasActual)
    {
      return a;
    }
    else
    {
      return p;
    }
  }

}
