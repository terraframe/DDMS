package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class GeoTargetView extends AbstractTargetView implements Reloadable
{

  public GeoTargetView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  protected View getView()
  {
    return View.GEO_TARGET_VIEW;
  }

  @Override
  public String getSQL()
  {
    return generateEpiWeekSeriesView(false);
  }
  
  protected String generateEpiWeekSeriesView(boolean isResource)
  {
    String idCol = this.irsQB.getIdCol();
    String keyName = this.irsQB.getKeyName();
    String geoEntity = this.irsQB.getGeoEntity();
    
    String malariaSeasonTable = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS).getTableName();
    String disease = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());

    String diseaseCol;
    String seasonCol;
    diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    

    String weeks = "";
    for (int i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT tar." + idCol + " AS " + idCol + ",\n";
    
    Set<Alias> selected = this.irsQB.getSelectAliases();
    if(selected.contains(Alias.AUDIT_CREATE_DATE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.CREATE_DATE);
      select += "tar."+QueryUtil.getColumnName(Metadata.getCreateDateMd())+" "+Alias.AUDIT_CREATE_DATE+" "+", \n";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATE_DATE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.LAST_UPDATE_DATE);
      select += "tar."+QueryUtil.getColumnName(Metadata.getLastUpdateDateMd())+" "+Alias.AUDIT_LAST_UPDATE_DATE+" "+", \n";
    }
    
    if(selected.contains(Alias.AUDIT_CREATED_BY))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.CREATED_BY);
      select += "tar."+QueryUtil.getColumnName(Metadata.getCreatedByMd())+" "+Alias.AUDIT_CREATED_BY+" "+", \n";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATED_BY))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.LAST_UPDATED_BY);
      select += "tar."+QueryUtil.getColumnName(Metadata.getLastUpdatedByMd())+" "+Alias.AUDIT_LAST_UPDATED_BY+" "+", \n";
    }
    
    
    select += "tar." + keyName + " AS " + keyName + ",\n";
    select += "de." + Alias.PLANNED_DATE + " AS " + Alias.PLANNED_DATE + ", \n";
    select += "i AS " + Alias.TARGET_WEEK + ",\n";
    select += geoEntity + " AS " + Alias.GEO_ENTITY + ", \n";

    select += "ms." + idCol + " AS " + IRSQB.MALARIA_SEASON + ", \n";

    select += "tar." + diseaseCol + " AS " + IRSQB.PLANNED_TARGET_DISEASE + ", \n";

    select += "target_array[i] AS " + IRSQB.WEEKLY_TARGET + " \n";

    String from = "FROM ";
    from += "(SELECT id, ";
    
    if(selected.contains(Alias.AUDIT_CREATE_DATE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.CREATE_DATE);
      from += QueryUtil.getColumnName(Metadata.getCreateDateMd())+", ";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATE_DATE))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.LAST_UPDATE_DATE);
      from += QueryUtil.getColumnName(Metadata.getLastUpdateDateMd())+", ";
    }
    
    if(selected.contains(Alias.AUDIT_CREATED_BY))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.CREATED_BY);
      from += QueryUtil.getColumnName(Metadata.getCreatedByMd())+", ";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATED_BY))
    {
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.LAST_UPDATED_BY);
      from += QueryUtil.getColumnName(Metadata.getLastUpdatedByMd())+", ";
    }
    
    from += "key_name, ";
    from += geoEntity + ", ";
    from += diseaseCol + ", ";
    from += seasonCol + ", ";

    String sourceTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();

    from += "ARRAY[" + weeks + "] AS target_array FROM " + sourceTable + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS ) + ") AS i, "
        + IRSQB.View.DATE_EXTRAPOLATION_VIEW.getView() + " de, " + malariaSeasonTable + " ms \n";
    from += " WHERE target_array[i] IS NOT NULL \n";
    from += " AND (i-1) = de." + this.irsQB.getPeriodCol() + " \n"; // substract 1 because
                                                    // arrays in postgres are
                                                    // 1-based
    from += " AND ms." + disease + " = tar." + diseaseCol + "\n";
    from += " AND ms." + idCol + " = tar." + seasonCol + "\n";
    return select + from;
  }

}
