package dss.vector.solutions.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.ZoneSprayView;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.SearchableHierarchy;

public class ZoneSprayExcelView extends ZoneSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246603807084L;
  
  public ZoneSprayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity entity = getGeoEntity();
    
    ZoneSprayView zsv = ZoneSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.getByName(this.getBrandName()));
    super.populate(zsv);
    zsv.setSprayWeek(this.getSprayWeek());
    zsv.setSupervisorName(this.getSupervisorName());
    zsv.setSupervisorSurname(this.getSupervisorSurname());
    zsv.setTarget(this.getTarget());
    
    zsv.apply();
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
