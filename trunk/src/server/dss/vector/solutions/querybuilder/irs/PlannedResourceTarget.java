package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.querybuilder.IRSQB;
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
  public final String setPlannedDate(Alias alias)
  {
    return set(IRSQB.RESOURCE_TARGET_VIEW,  Alias.PLANNED_DATE.getAlias(), alias);
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
