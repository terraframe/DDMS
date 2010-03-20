package dss.vector.solutions.permissions;

import junit.framework.Test;

import com.runwaysdk.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.PopulationDataViewDTO;

public class PopulationNoPermission extends PermissionTest
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PopulationNoPermission.class, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.MANAGER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testPopulationData()
  {
    try
    {
      PopulationDataViewDTO view = new PopulationDataViewDTO(request);
      view.setGeoEntity(countryGeoId);
      view.setYearOfData(2009);
      view.setPopulation(19L);
      view.setGrowthRate(4.3D);
      view.apply();

      view.deleteConcrete();

      fail("Able to create PopulationData without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }

  }
}
