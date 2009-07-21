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
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.SprayOperator;
import dss.vector.solutions.irs.SprayOperatorQuery;
import dss.vector.solutions.irs.SprayTeamQuery;
import dss.vector.solutions.util.GeoColumnListener;
import dss.vector.solutions.util.SearchableHierarchy;

public class OperatorSprayExcelView extends OperatorSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246588713740L;
  
  public OperatorSprayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity entity = getGeoEntity();
    
    SprayOperator operator = getOperator(entity);
    String operatorId = "";
    if (operator!=null)
    {
      operatorId = operator.getId();
    }
    
    OperatorSprayView osv = OperatorSprayView.searchBySprayData(entity.getGeoId(), this.getSprayDate(), getSprayMethodByLabel(this.getSprayMethod()), InsecticideBrand.getByName(this.getBrandName()), operatorId);
    super.populate(osv);
    osv.setSprayOperator(operator);
    
    osv.apply();
  }

  private SprayOperator getOperator(GeoEntity entity)
  {
    QueryFactory qf = new QueryFactory();
    SprayTeamQuery teamQuery = new SprayTeamQuery(qf);
    teamQuery.WHERE(teamQuery.getSprayZone().EQ(entity));
    teamQuery.WHERE(teamQuery.getTeamId().EQ(this.getSprayTeam()));
    SprayOperatorQuery operatorQuery = new SprayOperatorQuery(qf);
    operatorQuery.WHERE(operatorQuery.sprayTeam(teamQuery));
    operatorQuery.WHERE(operatorQuery.getPerson().getFirstName().EQ(this.getOperatorFirstName()));
    operatorQuery.WHERE(operatorQuery.getPerson().getLastName().EQ(this.getOperatorLastName()));
    OIterator<? extends SprayOperator> iterator = operatorQuery.getIterator();
    
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      return null;
    }
    finally
    {
      iterator.close();
    }
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
