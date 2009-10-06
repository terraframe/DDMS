package dss.vector.solutions.irs;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.ViewArrayExcelExporter;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class InsecticideInterventionPlanningView extends InsecticideInterventionPlanningViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254346304725L;

  public InsecticideInterventionPlanningView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
  }

  private double calculate(Float coverage, BigDecimal ratio, Integer sachets, Integer weight)
  {
    if (validateCalculation())
    {
      double targets = this.getTargets().doubleValue();

      return targets * coverage * ratio.doubleValue() * sachets * weight / 1000;
    }
    
    return 0.0;
  }

  @Transaction
  public static InsecticideInterventionPlanningView[] getViews(String geoId, MalariaSeason season)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<InsecticideInterventionPlanningView> list = new LinkedList<InsecticideInterventionPlanningView>();

    for (GeoEntity child : geoEntity.getImmediateSprayChildren())
    {
      GeoTargetView target = GeoTarget.findByGeoEntityIdAndSeason(child.getId(), season);

      int totalTargets = 0;

      if (target != null)
      {
        totalTargets = target.getTotal();
      }

      InsecticideInterventionPlanningView view = new InsecticideInterventionPlanningView();
      view.setGeoEntity(child);
      view.setSeason(season);
      view.setTargets(totalTargets);
      view.setEntityLabel(child);

      list.add(view);
    }

    return list.toArray(new InsecticideInterventionPlanningView[list.size()]);
  }

  @Transaction
  public static InsecticideInterventionPlanningView[] calculate(InsecticideInterventionPlanningView[] views, String configurationId)
  {
    validateConfiguration(configurationId);
    
    InsecticideNozzleView configuration = InsecticideNozzle.getView(configurationId);

    InsecticideBrand brand = configuration.getBrand();
    Nozzle nozzle = configuration.getNozzle();
    AreaStandardsView area = AreaStandardsView.getMostRecent();

    Float coverage = area.getUnitNozzleAreaCoverage();
    BigDecimal ratio = nozzle.getRatio();
    Integer sachets = brand.getSachetsPerRefill();
    Integer weight = brand.getSachetsPerRefill();

    for (InsecticideInterventionPlanningView view : views)
    {
      double insecticide = view.calculate(coverage, ratio, sachets, weight);

      view.setRequiredInsecticide(insecticide);
    }

    return views;
  }

  private static void validateConfiguration(String configurationId)
  {
    if(configurationId == null || configurationId.equals(""))
    {
      String msg = "Configuration requires a value";
      
      EmptyConfigurationException e = new EmptyConfigurationException(msg);      
      e.apply();
      
      throw e;
    }
  }
  
  public static InputStream exportToExcel(InsecticideInterventionPlanningView[] views)
  {
    List<String> attributes = new LinkedList<String>();
    attributes.add(ENTITYLABEL);
    attributes.add(TARGETS);
    attributes.add(REQUIREDINSECTICIDE);
    
    MdViewDAOIF mdView = MdViewDAO.getMdViewDAO(CLASS);
    
    ViewArrayExcelExporter exporter = new ViewArrayExcelExporter(views, attributes, mdView, mdView.getDisplayLabel(Session.getCurrentLocale()));
    
    return exporter.exportStream();    
  }

}
