package dss.vector.solutions.irs;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.MdViewDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdViewDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.ViewArrayExcelExporter;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorInterventionPlanningView extends OperatorInterventionPlanningViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254348689271L;

  public OperatorInterventionPlanningView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
  }
  
  public Integer calculate()
  {
    if (validateCalculation())
    {
      double targets = this.getTargets().doubleValue();
      int days = this.getNumberofDays();
      int unitsPerDay = this.getUnitsPerDay();

      return (int) Math.ceil(targets / ( days * unitsPerDay ));
    }

    return 0;
  }

  protected boolean validateCalculation()
  {
    boolean valid = super.validateCalculation();
    
    if (this.getNumberofDays() == null || this.getNumberofDays() == 0)
    {
      String msg = "Number of days have not been populated";

      EmptyValueProblem p = new EmptyValueProblem(msg);
      p.setNotification(this, NUMBEROFDAYS);
      p.setEntityLabel(this.getEntityLabel());
      p.apply();
      p.throwIt();

      valid = false;
    }

    if (this.getUnitsPerDay() == null || this.getUnitsPerDay() == 0)
    {
      String msg = "Sprayed units per operator per day have not been populated";

      EmptyValueProblem p = new EmptyValueProblem(msg);
      p.setNotification(this, UNITSPERDAY);
      p.setEntityLabel(this.getEntityLabel());
      p.apply();
      p.throwIt();

      valid = false;
    }

    return valid;
  }

  @Transaction
  public static OperatorInterventionPlanningView[] getViews(String geoId, MalariaSeason season)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    List<OperatorInterventionPlanningView> list = new LinkedList<OperatorInterventionPlanningView>();

    for (GeoEntity child : geoEntity.getSprayChildren())
    {
      GeoTargetView target = GeoTarget.findByGeoEntityIdAndSeason(child.getId(), season);
      int totalTargets = 0;

      if (target != null)
      {
        totalTargets = target.getTotal();
      }

      OperatorInterventionPlanningView view = new OperatorInterventionPlanningView();
      view.setGeoEntity(child);
      view.setSeason(season);
      view.setTargets(totalTargets);
      view.setEntityLabel(child);

      list.add(view);
    }

    return list.toArray(new OperatorInterventionPlanningView[list.size()]);
  }

  @Transaction
  public static OperatorInterventionPlanningView[] calculate(OperatorInterventionPlanningView[] views)
  {
    for (OperatorInterventionPlanningView view : views)
    {
      Integer operators = view.calculate();

      view.setRequiredOperators(operators);
    }

    return views;
  }
  
  public static InputStream exportToExcel(OperatorInterventionPlanningView[] views)
  {
    List<String> attributes = new LinkedList<String>();
    attributes.add(ENTITYLABEL);
    attributes.add(TARGETS);
    attributes.add(NUMBEROFDAYS);
    attributes.add(UNITSPERDAY);    
    attributes.add(REQUIREDOPERATORS);
    
    MdViewDAOIF mdView = MdViewDAO.getMdViewDAO(CLASS);
    
    ViewArrayExcelExporter exporter = new ViewArrayExcelExporter(views, attributes, mdView, mdView.getDisplayLabel(Session.getCurrentLocale()));
    
    return exporter.exportStream();    
  }
}
