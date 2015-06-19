package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class PlannedSprayTeamTarget extends PlannedResourceTarget implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.PlannedSprayTeamTarget";
  
  // private String teamSprayTable;
  //
  // private String sprayTeamCol;
  //
  // private String targetCol;
  
  private String sprayZone;
  
  public PlannedSprayTeamTarget(IRSQB irsQB)
  {
    super(irsQB);

    // MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    // this.teamSprayTable = teamSprayMd.getTableName();
    // sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    // targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);

    sprayZone = QueryUtil.getColumnName(SprayTeam.getSprayZoneMd());
  }
  
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    this.irsQB.addRequiredView(View.RESOURCE_TARGET_VIEW);
    
    // Load aliases that will be in the JOIN clause
    Alias[] joinAliases = new Alias[]{Alias.TARGET, Alias.SPRAY_SEASON, Alias.SPRAY_TEAM_DEFAULT_LOCALE, Alias.DISEASE};
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_TEAM, joinAliases);
    
    Set<Alias> selected =  this.irsQB.getSelectAliases();
    if(selected.contains(Alias.TEAM_PLANNED_TARGET) || selected.contains(Alias.TEAM_PLANNED_COVERAGE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_TEAM, Alias.SPRAY_TEAM);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_TEAM);
    }
    
    if(selected.contains(Alias.TEAM_TARGET_DIVERGENCE))
    {
      this.irsQB.addRequiredView(View.ALL_ACTUALS);
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_TEAM, Alias.TEAM_ACTUAL_TARGET);
      this.irsQB.addRequiredAlias(View.PLANNED_TEAM, Alias.SPRAY_TEAM, Alias.TEAM_PLANNED_TARGET);
    }
    
    if(selected.contains(Alias.TEAM_PLANNED_COVERAGE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_TEAM, Alias.TEAM_PLANNED_TARGET);
      
      this.irsQB.addRequiredView(View.ALL_ACTUALS);
    }
    
    // There are no geo entities for team targets but selecting a universal will
    // require a column. Although the value will be null, include the column to keep
    // the SQL valid.
    if(this.irsQB.hasUniversal())
    {
      this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
      this.irsQB.addRequiredAlias(View.PLANNED_TEAM, Alias.GEO_ENTITY);
    }
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_TEAM;
  }

  @Override
  public String setTeamPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String setSprayTeam(Alias alias)
  {
    return set(sprayTeamTable, idCol, alias);
  }
  
  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(sprayTeamTable, sprayZone, alias);
  }

  @Override
  public String setTarget(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), this.targeter, alias);
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
    String sql = "--Planned Spray Team Target\n";
    sql += resourceTargetView + " " + resourceTargetView + " INNER JOIN "
        + resourceTargetTable + " " + resourceTargetTable + " ON " + resourceTargetView + "."
        + idCol + " = " + resourceTargetTable + "." + idCol + " \n";
    sql += " INNER JOIN " + sprayTeamTable + " " + sprayTeamTable + " ON " + resourceTargetTable + "."
        + targeter + " = " + sprayTeamTable + "." + idCol + " \n";

    // Restrict by a universal such that only spray teams allowed within the universal type are valid.
    String universal = this.irsQB.getSmallestUniversal();
    if(universal != null)
    {
      String uId = MdBusiness.getMdBusiness(universal).getId();
      MdEntityDAOIF allpaths = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS);
      String parentUniversal = QueryUtil.getColumnName(allpaths, AllPaths.PARENTUNIVERSAL);
      String childGeo = QueryUtil.getColumnName(allpaths, AllPaths.CHILDGEOENTITY);
      
      sql += "INNER JOIN "+allpaths.getTableName()+" apg ON apg."+parentUniversal+" = '"+uId+"' \n";
      sql += "AND apg."+childGeo+" = "+sprayTeamTable+"."+this.sprayZone+" \n";
    }
    
    return sql;
  }
}
