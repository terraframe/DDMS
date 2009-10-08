package dss.vector.solutions.permissions.irs.planning;

import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO;
import dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO;
import dss.vector.solutions.irs.TimeInterventionPlanningViewDTO;

public abstract class InterventionPlanningCRUDTest extends InterventionPlanningPermissionTest
{
  public void testTimeInterventionPlanning()
  {
    TimeInterventionPlanningViewDTO view = new TimeInterventionPlanningViewDTO(request);
    view.setGeoEntity(GeoEntityDTO.get(request, geoEntityId));
    view.setEntityLabel("Test Label");
    view.setSeason(MalariaSeasonDTO.get(request, seasonId));
    view.setTargets(100);
    view.setOperators(100);
    
    TimeInterventionPlanningViewDTO[] views = new TimeInterventionPlanningViewDTO[]{view};
    
    TimeInterventionPlanningViewDTO[] result = TimeInterventionPlanningViewDTO.calculate(request, views, 1);
    
    assertEquals(1, result.length);
    assertEquals(new Integer(1), result[0].getRequiredDays());    
  }
  
  public void testInsecticideInterventionPlanning()
  {
    InsecticideInterventionPlanningViewDTO view = new InsecticideInterventionPlanningViewDTO(request);
    view.setGeoEntity(GeoEntityDTO.get(request, geoEntityId));
    view.setEntityLabel("Test Label");
    view.setSeason(MalariaSeasonDTO.get(request, seasonId));
    view.setTargets(100);
    
    InsecticideInterventionPlanningViewDTO[] views = new InsecticideInterventionPlanningViewDTO[]{view};
    
    InsecticideInterventionPlanningViewDTO[] result = InsecticideInterventionPlanningViewDTO.calculate(request, views, configurationId);
    
    assertEquals(1, result.length);
//    assertEquals(new Integer(1), result[0].getRequiredDays());   
  }
  
  public void testOperatorInterventionPlanning()
  {
    OperatorInterventionPlanningViewDTO view = new OperatorInterventionPlanningViewDTO(request);
    view.setGeoEntity(GeoEntityDTO.get(request, geoEntityId));
    view.setEntityLabel("Test Label");
    view.setSeason(MalariaSeasonDTO.get(request, seasonId));
    view.setTargets(100);
    view.setUnitsPerDay(100);
    view.setNumberofDays(1);
    
    OperatorInterventionPlanningViewDTO[] views = new OperatorInterventionPlanningViewDTO[]{view};
    
    OperatorInterventionPlanningViewDTO[] result = OperatorInterventionPlanningViewDTO.calculate(request, views);
    
    assertEquals(1, result.length);
    assertEquals(new Integer(1), result[0].getRequiredOperators());  
  }

}
