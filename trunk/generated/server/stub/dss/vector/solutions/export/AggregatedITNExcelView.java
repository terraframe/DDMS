package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.surveillance.PeriodType;

public class AggregatedITNExcelView extends AggregatedITNExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1914412224;
  
  public AggregatedITNExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity geoEntity = getGeoEntity();

    PeriodType periodType = AggregatedCaseExcelView.getPeriodTypeByLabel(this.getPeriodType());
    
    ITNDataView data = ITNData.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getPeriodYear());
    data.setBatchNumber(this.getBatchNumber());
    data.setReceivedForTargetGroups(this.getReceivedForTargetGroups());
    data.setReceivedForCommunityResponse(this.getReceivedForCommunityResponse());
    data.setNumberDistributed(this.getNumberDistributed());
    data.setNumberSold(this.getNumberSold());
    data.setCurrencyReceived(this.getCurrencyReceived());
    data.applyAll(new ITNNet[0], new ITNTargetGroup[0], new ITNService[0]);
  }
}
