package dss.vector.solutions.irs;

import com.terraframe.mojo.system.metadata.MdBusiness;

public abstract class ActorSpray extends ActorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853373518L;

  public ActorSpray()
  {
    super();
  }

  public void populateView(ActorSprayView view)
  {
    super.populateView(view);

    view.setTarget(this.getTarget());
    view.setReceived(this.getReceived());
    view.setRefills(this.getRefills());
    view.setReturned(this.getReturned());
    view.setUsed(this.getUsed());
    view.setTarget(this.getTarget());
    view.setTeamSprayWeek(this.getTeamSprayWeek());
    view.setTeamLeader(this.getTeamLeader());
  }

/*
  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += ResourceTarget.getTempTableSQL(MdBusiness.getMdBusiness(ResourceTarget.CLASS).getTableName());
    sql += "ORDER BY season_id;\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);

  }
*/

  public static String getUnitTotalsSQL()
  {
    /*
    switch (targetUnit)
    {
      case HOUSEHOLD:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
      case STRUCTURE:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
      case ROOM:
        select += "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n";
        break;
    }
    */

    String select = "SELECT actorspray.id AS id,\n";
    //we use NULLIF before the CAST because float zero != integer zero
    select += "(SELECT CAST(NULLIF(SUM(sprayedhouseholds),0) AS float) FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as household_total,\n";
    select += "(SELECT CAST(NULLIF(SUM(sprayedstructures),0) AS float) FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as structure_total,\n";
    select += "(SELECT CAST(NULLIF(SUM(sprayedrooms),0) AS float)      FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as room_total,\n";

    String from = "FROM ";
    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n";
  }

}
