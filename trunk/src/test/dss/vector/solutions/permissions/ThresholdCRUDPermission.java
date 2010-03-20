package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.general.ThresholdDataDTO;
import dss.vector.solutions.general.ThresholdDataViewDTO;

public class ThresholdCRUDPermission extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(ThresholdCRUDPermission.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER);
  }

  public void testThresholdData()
  {
    String viewValue = "1";
    String editValue = "2";

    Calendar calendar = Calendar.getInstance();
    Date endDate = calendar.getTime();

    calendar.add(Calendar.MONTH, -1);
    Date startDate = calendar.getTime();

    MalariaSeasonDTO season = new MalariaSeasonDTO(systemRequest);
    season.setStartDate(startDate);
    season.setEndDate(endDate);
    season.setSeasonName(TestConstants.SEASON_NAME);
    season.apply();

    try
    {
      ThresholdDataViewDTO view = new ThresholdDataViewDTO(request);
      view.setGeoEntity(countryGeoId);
      view.setSeason(season);

      for (int i = 0; i < 53; i++)
      {
        view.setValue(ThresholdDataViewDTO.IDENTIFICATION + i, viewValue);
        view.setValue(ThresholdDataViewDTO.OUTBREAK + i, viewValue);
      }

      view.apply();

      try
      {
        ThresholdDataViewDTO edit = ThresholdDataDTO.getView(request, view.getConcreteId());

        for (int i = 0; i < 53; i++)
        {
          edit.setValue(ThresholdDataViewDTO.IDENTIFICATION + i, editValue);
          edit.setValue(ThresholdDataViewDTO.OUTBREAK + i, editValue);
        }

        edit.apply();

        ThresholdDataViewDTO test = ThresholdDataDTO.getView(request, view.getConcreteId());

        assertEquals(edit.getSeason(), test.getSeason());
        assertEquals(edit.getGeoEntity(), test.getGeoEntity());
        assertEquals(edit.getEntityLabel(), test.getEntityLabel());

        for (int i = 0; i < 53; i++)
        {
          assertEquals(edit.getValue(ThresholdDataViewDTO.IDENTIFICATION + i), editValue);
          assertEquals(edit.getValue(ThresholdDataViewDTO.OUTBREAK + i), editValue);
        }

      }
      finally
      {
        view.deleteConcrete();
      }
    }
    catch (ProblemExceptionDTO e)
    {
      for (ProblemDTOIF p : e.getProblems())
      {
        fail(p.getMessage());
      }
    }
    finally
    {
      season.delete();
    }

  }
}
