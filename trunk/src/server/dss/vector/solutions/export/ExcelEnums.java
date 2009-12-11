package dss.vector.solutions.export;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.entomology.LifeStage;

public class ExcelEnums implements Reloadable
{
  public static LifeStage getLifeStage(String label)
  {
    for (LifeStage lifeStage : LifeStage.values())
    {
      if (lifeStage.getEnumName().equalsIgnoreCase(label) ||
          lifeStage.getDisplayLabel().equalsIgnoreCase(label))
      {
        return lifeStage;
      }
    }
    return null;
  }
}
