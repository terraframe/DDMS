package dss.vector.solutions.permissions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.DoNotWeave;
import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestConstants;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.general.ThresholdDataViewDTO;

public class ThresholdNoPermission extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(ThresholdNoPermission.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER);
  }

  public void testThresholdData()
  {
    String value = "1";

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
        view.setValue(ThresholdDataViewDTO.IDENTIFICATION + i, value);
        view.setValue(ThresholdDataViewDTO.OUTBREAK + i, value);
      }

      view.apply();

      view.deleteConcrete();

      fail("Able to create Threshold Data without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
    finally
    {
      season.delete();
    }
  }
}
