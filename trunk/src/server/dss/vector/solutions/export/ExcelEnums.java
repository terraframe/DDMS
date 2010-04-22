package dss.vector.solutions.export;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.general.Disease;

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

  public static Disease getDisease(String label)
  {
    for (Disease disease : Disease.values())
    {
      if (disease.getEnumName().equalsIgnoreCase(label) ||
          disease.getDisplayLabel().equalsIgnoreCase(label))
      {
        return disease;
      }
    }
    return null;
  }
}
