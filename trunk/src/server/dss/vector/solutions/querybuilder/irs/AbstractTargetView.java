package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractTargetView extends AuxiliaryProvider implements Reloadable
{

  public AbstractTargetView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  public void loadDependencies()
  {
    this.irsQB.addRequiredView(View.DATE_EXTRAPOLATION_VIEW);
  }
  
  
  protected String generateEpiWeekSeriesView(boolean isResource)
  {
    String idCol = this.irsQB.getIdCol();
    String keyName = this.irsQB.getKeyName();
    String targeter = this.irsQB.getTargeter();
    String geoEntity = this.irsQB.getGeoEntity();
    
    String malariaSeasonTable = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS).getTableName();
    String startDate = QueryUtil.getColumnName(MalariaSeason.getStartDateMd());
    String endDate = QueryUtil.getColumnName(MalariaSeason.getEndDateMd());
    String disease = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());

    String diseaseCol;
    String seasonCol;
    if (isResource)
    {
      diseaseCol = QueryUtil.getColumnName(ResourceTarget.getDiseaseMd());
      seasonCol = QueryUtil.getColumnName(ResourceTarget.getSeasonMd());
    }
    else
    {
      diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
      seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    }

    String weeks = "";
    for (int i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT tar." + idCol + " AS " + idCol + ",\n";
    select += "tar." + keyName + " AS " + keyName + ",\n";
    select += "de." + Alias.PLANNED_DATE + " AS " + Alias.PLANNED_DATE + ", \n";
    select += "i AS " + Alias.TARGET_WEEK + ",\n";
    if (isResource)
    {
      select += targeter + " AS " + targeter + ", \n";
    }
    else
    {
      select += geoEntity + " AS " + Alias.GEO_ENTITY + ", \n";
    }

    select += "ms." + idCol + " AS " + IRSQB.MALARIA_SEASON + ", \n";

    select += "tar." + diseaseCol + " AS " + IRSQB.PLANNED_TARGET_DISEASE + ", \n";

    select += "target_array[i] AS " + IRSQB.WEEKLY_TARGET + " \n";

    String from = "FROM ";
    from += "(SELECT id, ";
    from += "key_name, ";
    if (isResource)
    {
      from += targeter + ", ";
    }
    else
    {
      from += geoEntity + ", ";
    }
    from += diseaseCol + ", ";
    from += seasonCol + ", ";

    String sourceTable;
    if (isResource)
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(ResourceTarget.CLASS).getTableName();
    }
    else
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    }

    from += "ARRAY[" + weeks + "] AS target_array FROM " + sourceTable + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS ) + ") AS i, "
        + IRSQB.View.DATE_EXTRAPOLATION_VIEW.getView() + " de, " + malariaSeasonTable + " ms \n";
    from += " WHERE target_array[i] IS NOT NULL \n";
    from += " AND (i-1) = de." + this.irsQB.getPeriodCol() + " \n";
    from += " AND de." + Alias.PLANNED_DATE + " BETWEEN ms." + startDate + " AND ms." + endDate + " \n";
    from += " AND ms." + disease + " = tar." + diseaseCol + "\n";
    from += " AND ms." + idCol + " = tar." + seasonCol + "\n";
    return select + from;
  }

}
