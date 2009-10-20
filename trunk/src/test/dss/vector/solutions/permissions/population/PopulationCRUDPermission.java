package dss.vector.solutions.permissions.population;

import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.business.ProblemDTOIF;

import dss.vector.solutions.general.PopulationDataDTO;
import dss.vector.solutions.general.PopulationDataViewDTO;

public abstract class PopulationCRUDPermission extends PopulationPermissionTest
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

      try
      {
        PopulationDataViewDTO edit = PopulationDataDTO.getView(request, view.getConcreteId());
        edit.setPopulation(20L);
        edit.setGrowthRate(14.3F);
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
