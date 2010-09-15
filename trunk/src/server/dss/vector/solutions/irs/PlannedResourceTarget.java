package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.util.QueryUtil;

public abstract class PlannedResourceTarget extends PlannedTargetUnion implements Reloadable
{
  protected String resourceTargetTable;
  protected String disease;
  protected String spraySeason;
  
  public PlannedResourceTarget()
  {
    super();
    
    this.resourceTargetTable = MdEntity.getMdEntity(ResourceTarget.CLASS).getTableName();
    this.disease = QueryUtil.getColumnName(ResourceTarget.getDiseaseMd());
    this.spraySeason = QueryUtil.getColumnName(ResourceTarget.getSeasonMd());
    
  }
  
  @Override
  public final String setSprayDate(Alias alias)
  {
    return set(IRSQuery.RESOURCE_TARGET_VIEW, IRSQuery.PLANNED_DATE, alias);
  }
  
  @Override
  public String setSpraySeason(Alias alias)
  {
    return set(resourceTargetTable, spraySeason, alias);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return set(resourceTargetTable, idCol, alias);
  }
  
  @Override
  public String setDisease(Alias alias)
  {
    return set(resourceTargetTable, disease, alias);
  }
}
