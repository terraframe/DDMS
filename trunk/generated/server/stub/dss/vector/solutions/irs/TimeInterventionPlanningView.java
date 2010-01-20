package dss.vector.solutions.irs;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.ViewArrayExcelExporter;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class TimeInterventionPlanningView extends TimeInterventionPlanningViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254263185086L;

  public TimeInterventionPlanningView()
  {
    super();
  }

  @Override
  public void apply()
  {
  }

  public Integer calculate(Integer unitsPerDay)
  {
    if (validateCalculation())
    {
      double targets = this.getTargets().doubleValue();
      double operators = this.getOperators().doubleValue();

      return (int) Math.ceil(targets / ( operators * unitsPerDay ));
    }

    return 0;
  }

  protected boolean validateCalculation()
  {
    boolean valid = super.validateCalculation();

    if (this.getOperators() == null || this.getOperators() == 0)
    {
      String msg = "Operators have not been populated";

      RequiredOperatorsProblem p = new RequiredOperatorsProblem(msg);
      p.setNotification(this, OPERATORS);
      p.setEntityLabel(this.getEntityLabel());
      p.apply();
      p.throwIt();

      valid = false;
    }

    return valid;
  }

  @Transaction
  public static TimeInterventionPlanningView[] getViews(String geoId, MalariaSeason season)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<TimeInterventionPlanningView> list = new LinkedList<TimeInterventionPlanningView>();

    for (GeoEntity child : geoEntity.getSprayChildren())
    {
      GeoTargetView target = GeoTarget.findByGeoEntityIdAndSeason(child.getId(), season);
      int totalTargets = 0;
      int operators = TeamMember.getAvailableOperators(child);

      if (target != null)
      {
        totalTargets = target.getTotal();
      }

      TimeInterventionPlanningView view = new TimeInterventionPlanningView();
      view.setGeoEntity(child);
      view.setSeason(season);
      view.setTargets(totalTargets);
      view.setOperators(operators);
      view.setEntityLabel(child);

      list.add(view);
    }

    return list.toArray(new TimeInterventionPlanningView[list.size()]);
  }

  @Transaction
  public static TimeInterventionPlanningView[] calculate(TimeInterventionPlanningView[] views, Integer unitsPerDay)
  {
    validateUnitsPerDay(unitsPerDay);

    for (TimeInterventionPlanningView view : views)
    {
      Integer days = view.calculate(unitsPerDay);

      view.setRequiredDays(days);
    }

    return views;
  }

  private static void validateUnitsPerDay(Integer unitsPerDay)
  {
    if (unitsPerDay == null || ! ( unitsPerDay > 0 ))
    {
      String msg = "Units per day must be a positive whole number";

      UnitsPerDayException e = new UnitsPerDayException(msg);
      e.apply();
      
      throw e;
    }
  }

  @Transaction
  public static TimeInterventionPlanningView[] calculateDefault(TimeInterventionPlanningView[] views)
  {
    Integer unitsPerDay = Property.getInt(PropertyInfo.STANDARDS_PACKAGE, PropertyInfo.DEFAULT_UNITS);

    TimeInterventionPlanningView[] returnViews = TimeInterventionPlanningView.calculate(views, unitsPerDay);

    return returnViews;
  }
  
  public static InputStream exportToExcel(TimeInterventionPlanningView[] views)
  {
    List<String> attributes = new LinkedList<String>();
    attributes.add(ENTITYLABEL);
    attributes.add(TARGETS);
    attributes.add(OPERATORS);
    attributes.add(REQUIREDDAYS);
    
    MdViewDAOIF mdView = MdViewDAO.getMdViewDAO(CLASS);
    
    ViewArrayExcelExporter exporter = new ViewArrayExcelExporter(views, attributes, mdView, mdView.getDisplayLabel(Session.getCurrentLocale()));
    
    return exporter.exportStream();
  }


}
