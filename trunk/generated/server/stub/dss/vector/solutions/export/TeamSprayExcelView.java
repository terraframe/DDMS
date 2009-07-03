package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.SprayTeamQuery;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.SearchableHierarchy;

public class TeamSprayExcelView extends TeamSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246603807054L;
  
  public TeamSprayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity entity = getGeoEntity();
    
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());
    query.WHERE(query.getSprayZone().EQ(query));
    query.WHERE(query.getTeamId().EQ(this.getSprayTeam()));
    OIterator<? extends SprayTeam> iterator = query.getIterator();
    String teamId = "";
    
    if (iterator.hasNext())
    {
      teamId = iterator.next().getId();
    }
    iterator.close();
    
    TeamSprayView tsv = TeamSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.getByName(this.getBrandName()), teamId);
    super.populate(tsv);
    tsv.apply();
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    Map<String, String> map = new HashMap<String, String>();
    List<SearchableHierarchy> hierarchy = GeoColumnListener.getSprayHierarchy();
    List<MdAttributeDAOIF> attributes = AbstractSprayExcelView.getGeoEntityAttributes();

    int size = Math.min(hierarchy.size(), attributes.size());

    for (int i = 0; i < size; i++)
    {
      String key = attributes.get(i).getId();
      String displayLabel = hierarchy.get(i).getDisplayLabel();

      map.put(key, displayLabel);
    }

    exporter.addListener(new GeoColumnListener(map));
  }
  
}
