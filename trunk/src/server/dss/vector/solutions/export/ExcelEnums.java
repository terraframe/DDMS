package dss.vector.solutions.export;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.ContainerShape;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.DiagnosisType;

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
  
  public static ContainerShape getContainerShape(String label)
  {
    for (ContainerShape containerShape : ContainerShape.values())
    {
      if (containerShape.getEnumName().equalsIgnoreCase(label) ||
          containerShape.getDisplayLabel().equalsIgnoreCase(label))
      {
        return containerShape;
      }
    }
    return null;
  }
  
  public static DiagnosisType getDiagnosisType(String label)
  {
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
    for (DiagnosisType diagnosisType : DiagnosisType.values())
    {
      if (diagnosisType.getEnumName().equalsIgnoreCase(label) ||
          diagnosisType.getDisplayLabel().equalsIgnoreCase(label))
      {
        return diagnosisType;
      }
    }
    return null;
  }
}
