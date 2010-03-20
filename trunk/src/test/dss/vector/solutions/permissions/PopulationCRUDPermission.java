package dss.vector.solutions.permissions;

import junit.framework.Test;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.general.PopulationDataDTO;
import dss.vector.solutions.general.PopulationDataViewDTO;

public class PopulationCRUDPermission extends PermissionTest
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(PopulationCRUDPermission.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.OPERATIONAL_MANAGER);
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

      try
      {
        PopulationDataViewDTO edit = PopulationDataDTO.getView(request, view.getConcreteId());
        edit.setPopulation(20L);
        edit.setGrowthRate(16D);
        edit.apply();

        PopulationDataViewDTO test = PopulationDataDTO.getView(request, view.getConcreteId());

        assertEquals(edit.getGeoEntity(), test.getGeoEntity());
        assertEquals(edit.getEntityLabel(), test.getEntityLabel());
        assertEquals(edit.getYearOfData(), test.getYearOfData());
        assertEquals(edit.getPopulation(), test.getPopulation());
        assertEquals(edit.getGrowthRate(), test.getGrowthRate());
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

  }
}
