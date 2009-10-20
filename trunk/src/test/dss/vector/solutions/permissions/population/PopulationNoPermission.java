package dss.vector.solutions.permissions.population;

import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.general.PopulationDataViewDTO;

public abstract class PopulationNoPermission extends PopulationPermissionTest
{
  public void testPopulationData()
  {
    try
    {
      PopulationDataViewDTO view = new PopulationDataViewDTO(request);
      view.setGeoEntity(geoId);
      view.setYearOfData(2009);
      view.setPopulation(19L);
      view.setGrowthRate(4.3F);
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
