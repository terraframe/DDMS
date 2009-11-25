package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SettlementSubdivision;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstance;
import dss.vector.solutions.intervention.monitor.LarvacideQuery;
import dss.vector.solutions.irs.SprayLeader;
import dss.vector.solutions.irs.SprayLeaderQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class LarvacideExcelView extends LarvacideExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2093506272;
  
  public LarvacideExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    Larvacide larvacide = getLarvacide();
    
    LarvacideInstance instance = new LarvacideInstance();
    instance.setTarget(Term.validateByDisplayLabel(this.getTarget(), LarvacideInstance.getTargetMd()));
    instance.setTreated(this.getTreated());
    instance.setControlMethod(Term.validateByDisplayLabel(this.getControlMethod(), LarvacideInstance.getControlMethodMd()));
    instance.setUnit(Term.validateByDisplayLabel(this.getUnit(), LarvacideInstance.getUnitMd()));
    instance.setUnitsUsed(this.getUnitsUsed());
    instance.apply();
    
    larvacide.addInstances(instance).apply();
  }

  private Larvacide getLarvacide()
  {
    LarvacideQuery larvacideQuery = new LarvacideQuery(new QueryFactory());
    larvacideQuery.WHERE(larvacideQuery.getStartDate().EQ(this.getStartDate()));
    larvacideQuery.WHERE(larvacideQuery.getCompletionDate().EQ(this.getCompletionDate()));
    larvacideQuery.WHERE(larvacideQuery.getGeoEntity().EQ(this.getGeoEntity()));
    larvacideQuery.WHERE(larvacideQuery.getNatureOfControl().EQ(this.getNatureOfControl()));
    OIterator<? extends Larvacide> larvacideIterator = larvacideQuery.getIterator();
    
    if (larvacideIterator.hasNext())
    {
      Larvacide match = larvacideIterator.next();
      larvacideIterator.close();
      return match;
    }
    
    Larvacide larvacide = new Larvacide();
    larvacide.setStartDate(this.getStartDate());
    larvacide.setCompletionDate(this.getCompletionDate());
    larvacide.setGeoEntity(this.getGeoEntity());
    larvacide.setGeoDescription(this.getGeoDescription());
    larvacide.setNatureOfControl(this.getNatureOfControl());
    larvacide.setPersonCount(this.getPersonCount());
    
    SprayLeaderQuery leaderQuery = new SprayLeaderQuery(new QueryFactory());
    leaderQuery.WHERE(leaderQuery.getLeaderId().EQ(this.getTeamLeaderId()));
    OIterator<? extends SprayLeader> leaderIterator = leaderQuery.getIterator();
    if (leaderIterator.hasNext())
    {
      larvacide.setTeamLeader(leaderIterator.next());
    }
    leaderIterator.close();
    
    return larvacide;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SettlementSubdivision.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
