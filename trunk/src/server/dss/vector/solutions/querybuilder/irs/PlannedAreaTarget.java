package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.system.UsersQuery;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class PlannedAreaTarget extends PlannedTargetUnion implements Reloadable
{
  private static final String GTV_ALIAS = "gt";

  private String              childGeoEntity;

  private String              parentGeoEntity;
  
  private boolean needsImported;
  
  private SelectableChar createdBy;
  
  private SelectableChar lastUpdatedBy;
  
  public PlannedAreaTarget(IRSQB irsQB)
  {
    super(irsQB);

    childGeoEntity = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    parentGeoEntity = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());
    
    this.needsImported = false;
  }
  
  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    // Load aliases that will be in the JOIN clause
    Alias[] joinAliases = new Alias[]{Alias.TARGET, Alias.PARENT_GEO_ENTITY, Alias.TARGET_WEEK, Alias.SPRAY_SEASON, Alias.GEO_ENTITY, Alias.DISEASE};
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, joinAliases);
    this.irsQB.addRequiredAlias(View.PLANNED_AREA, joinAliases);
    
    this.irsQB.addRequiredView(View.GEO_TARGET_VIEW);
    
    Set<Alias> select = this.irsQB.getSelectAliases();
    if(select.contains(Alias.AUDIT_IMPORTED))
    {
      this.needsImported = true;
      this.irsQB.addRequiredAlias(View.PLANNED_AREA, Alias.AUDIT_IMPORTED);
    }
    
    if(select.contains(Alias.AUDIT_CREATED_BY))
    {
      UsersQuery u = new UsersQuery(this.irsQB.getValueQuery());
      this.createdBy = u.getUsername();
    }
    
    if(select.contains(Alias.AUDIT_LAST_UPDATED_BY))
    {
      UsersQuery u = new UsersQuery(this.irsQB.getValueQuery());
      this.lastUpdatedBy = u.getUsername();
    }
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_AREA;
  }
  
  @Override
  public String setSpraySeason(Alias alias)
  {
    return set(IRSQB.MALARIA_SEASON, alias);
  }
  
  public String setAuditImported(Alias alias)
  {
    return set("(CASE WHEN "+IRSQB.IMPORTED_AREA_DATETIME+"."+Alias.CREATE_DATE+" IS NOT NULL THEN 1 ELSE 0 END)", alias);
  }
  
  public String setCreateDate(Alias alias)
  {
    return set(GTV_ALIAS, Alias.AUDIT_CREATE_DATE.getAlias(), Alias.AUDIT_CREATE_DATE);
  }
  
  public String setLastUpdateDate(Alias alias)
  {
    return set(GTV_ALIAS, Alias.AUDIT_LAST_UPDATE_DATE.getAlias(), Alias.AUDIT_LAST_UPDATE_DATE);
  }
  
  public String setCreatedBy(Alias alias)
  {
    return set(this.createdBy.getDbQualifiedName(),  Alias.AUDIT_CREATED_BY);
  }
  
  public String setLastUpdatedBy(Alias alias)
  {
    return set(this.lastUpdatedBy.getDbQualifiedName(), Alias.AUDIT_LAST_UPDATED_BY);
  }
  
  @Override
  public final String setId(Alias alias)
  {
    return set(GTV_ALIAS, idCol, alias);
  }

  @Override
  public String setTarget(Alias alias)
  {
    return null;
  }
  
  @Override
  public String setParentGeoEntity(Alias alias)
  {
    return null;
  }

  @Override
  public final String setUniqueSprayId(Alias alias)
  {
    return setNULL(alias);
  }

  @Override
  public final String setPlannedDate(Alias alias)
  {
    return set(Alias.PLANNED_DATE.getAlias(), alias);
  }

  @Override
  public final String setUniquePlannedId(Alias alias)
  {
//    return set(GTV_ALIAS, keyName, alias);
    return null;
  }

  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(parentGeoEntity, alias);
  }

  @Override
  public String setAreaPlannedTarget(Alias alias)
  {
    // #2865 - don't sum anything here. Do it in the final select
    // String sum =
    // QueryConstants.SUM_AREA_TARGETS+"("+parentGeoEntity+", to_char("+IRSQB.TARGET_WEEK+"-1, 'FM99'), "+Alias.DISEASE.getAlias()+", "+IRSQB.MALARIA_SEASON+")";
    // return set(sum, alias);
    return null;// omit the column entirely
  }

  @Override
  public String FROM()
  {
    String geoTable = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS).getTableName();

    String sql = IRSQB.View.GEO_TARGET_VIEW.getView() + " " + GTV_ALIAS + " INNER JOIN "
        + geoTable + " g ON g." + childGeoEntity + " = " + GTV_ALIAS + "." + this.irsQB.getGeoEntity()
        + " \n";

    if(this.needsImported)
    {
      sql += " LEFT JOIN "+IRSQB.IMPORTED_AREA_DATETIME+" ON "
      +IRSQB.IMPORTED_AREA_DATETIME+"."+Alias.CREATE_DATE+" = "+GTV_ALIAS+"."+Alias.AUDIT_CREATE_DATE + " \n";
    }
    
    if(this.createdBy != null)
    {
      
      sql += " LEFT JOIN "+this.createdBy.getDefiningTableName()+ " "+this.createdBy.getDefiningTableAlias()+ " ON "+
        this.createdBy.getDefiningTableAlias()+"."+idCol+" = "+GTV_ALIAS+"."+Alias.AUDIT_CREATED_BY+" \n";
    }

    if(this.lastUpdatedBy != null)
    {
      sql += " LEFT JOIN "+this.lastUpdatedBy.getDefiningTableName()+ " "+this.lastUpdatedBy.getDefiningTableAlias()+ " ON "+
          this.lastUpdatedBy.getDefiningTableAlias()+"."+idCol+" = "+GTV_ALIAS+"."+Alias.AUDIT_LAST_UPDATED_BY+" \n";
    }
    
    return sql;
  }

  @Override
  public String WHERE()
  {
    String sql = "";

    // Attempt to restrict by the universals. We join based on the ontological
    // structure of the universal
    // tree and not by exact matches on the universal type. For example, if
    // Settlement is located within District
    // and District is chosen as universal criteria, then we gather all
    // Settlements within Districts and Districts as well
    // (as opposed to gathering only Districts).
    String universal = this.irsQB.getSmallestUniversal();

    if (universal != null)
    {
      String parentMd = QueryUtil.getColumnName(AllPaths.getParentUniversalMd());

      sql += parentMd + " = '" + MdEntity.getMdEntity(universal).getId() + "'";
    }
    sql += "\n";
    sql += "GROUP BY ";
    
    if(this.needsImported)
    {
      sql += IRSQB.IMPORTED_AREA_DATETIME+"."+Alias.CREATE_DATE+", ";
    }
    
    if(this.createdBy != null)
    {
      sql += this.createdBy.getDbQualifiedName()+", ";
    }

    if(this.lastUpdatedBy != null)
    {
      sql += this.lastUpdatedBy.getDbQualifiedName()+", ";
    }
    
    Set<Alias> selected = this.irsQB.getSelectAliases();
    if(selected.contains(Alias.AUDIT_CREATE_DATE))
    {
      sql += GTV_ALIAS + "." +Alias.AUDIT_CREATE_DATE + ", ";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATE_DATE))
    {
      sql += GTV_ALIAS + "." +Alias.AUDIT_LAST_UPDATE_DATE + ", ";
    }
    
    if(selected.contains(Alias.AUDIT_CREATED_BY))
    {
      sql += GTV_ALIAS + "." +Alias.AUDIT_CREATED_BY + ", ";
    }
    
    if(selected.contains(Alias.AUDIT_LAST_UPDATED_BY))
    {
      sql += GTV_ALIAS + "." +Alias.AUDIT_LAST_UPDATED_BY + ", ";
    }
    
    sql += GTV_ALIAS + "." + idCol + ", " + parentGeoEntity + ", "
        + Alias.PLANNED_DATE.getAlias() + ", " + Alias.TARGET_WEEK.getAlias() + ", "
        + IRSQB.MALARIA_SEASON + ", " + IRSQB.PLANNED_TARGET_DISEASE + "\n";

    return sql;
  }
}
