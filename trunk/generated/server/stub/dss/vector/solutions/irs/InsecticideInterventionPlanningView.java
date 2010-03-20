package dss.vector.solutions.irs;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdViewDAOIF;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.ViewArrayExcelExporter;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.GeoEntitySprayProblem;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class InsecticideInterventionPlanningView extends InsecticideInterventionPlanningViewBase implements com.runwaysdk.generation.loader.Reloadable
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

  private double calculate(Float coverage, Float area, Double weight, Integer sachets)
  {
    Integer _targets = this.getTargets();

    if (_targets != null)
    {
      // Formula : #Targets x target unit area (MDSS106) * sachet weight in gr
      // (MDSS106) * sachets per refill (MDSS106) / (unit nozzle can area
      // coverage (MDSS106) x 1000)

      double targets = _targets.doubleValue();

      return ( targets * area * weight * sachets ) / ( coverage * 1000 );
    }

    return 0.0;
  }

  @Transaction
  public static InsecticideInterventionPlanningView[] getViews(String geoId, MalariaSeason season)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);

    // Validate the Geo Entity: it must be part of the spray hierarchy
    GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromType(entity.getType());

    if (!geoHierarchy.getSprayTargetAllowed())
    {
      String label = entity.getLabel();

      String msg = "The Geo Entity [" + label + "] does not allow spray targets";

      GeoEntitySprayProblem p = new GeoEntitySprayProblem(msg);
      p.setEntityLabel(label);
      p.apply();
      p.throwIt();
    }

    List<InsecticideInterventionPlanningView> list = new LinkedList<InsecticideInterventionPlanningView>();

    for (GeoEntity child : entity.getSprayChildren())
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
    AreaStandardsView area = AreaStandardsView.getMostRecent();

    Float coverage = area.getUnitNozzleAreaCoverage();
    Integer sachets = brand.getSachetsPerRefill();
    BigDecimal weight = brand.getWeight();
    Float targetArea = area.getTargetArea();

    for (InsecticideInterventionPlanningView view : views)
    {
      double insecticide = view.calculate(coverage, targetArea, weight.doubleValue(), sachets);

      view.setRequiredInsecticide(insecticide);
    }

    return views;
  }

  private static void validateConfiguration(String configurationId)
  {
    if (configurationId == null || configurationId.equals(""))
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
