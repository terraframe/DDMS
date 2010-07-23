package dss.vector.solutions.export;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MonthOfYear;
import dss.vector.solutions.RefusedResponse;
import dss.vector.solutions.Response;
import dss.vector.solutions.entomology.ContainerShape;
import dss.vector.solutions.entomology.LifeStage;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.irs.SprayMethod;
import dss.vector.solutions.surveillance.PeriodType;

public class ExcelEnums implements Reloadable
{
  public static LifeStage getLifeStage(String label)
  {
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
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
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
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

  public static PeriodType getPeriodType(String label)
  {
    if (label == null || label.equals(""))
    {
      return null;
    }

    for (PeriodType e : PeriodType.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + PeriodType.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(PeriodType.CLASS));
  }

//  public static SurfacePosition getSurfacePosition(String label)
//  {
//    if(label == null || label.equals(""))
//    {
//      return null;
//    }
//    
//    for (SurfacePosition e : SurfacePosition.values())
//    {
//      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
//          e.getEnumName().equalsIgnoreCase(label))
//      {
//        return e;
//      }
//    }
//    String message = "[" + label + "] is not a valid display label for [" + SurfacePosition.CLASS + "]";
//    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SurfacePosition.CLASS));
//  }
//
  public static SprayMethod getSprayMethod(String label)
  {
    if(label == null || label.equals(""))
    {
      return null;
    }
    
    for (SprayMethod e : SprayMethod.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    
    String message = "[" + label + "] is not a valid display label for [" + SprayMethod.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(SprayMethod.CLASS));
  }
  
  public static MonthOfYear getMonthOfYear(String label)
  {
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
    for (MonthOfYear e : MonthOfYear.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
  
  public static Response getResponse(String label)
  {
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
    for (Response e : Response.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
  
  public static RefusedResponse getRefusedResponse(String label)
  {
    if (label==null)
    {
      return null;
    }
    
    if (label.length()==0)
    {
      return null;
    }
    
    for (RefusedResponse e : RefusedResponse.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    return null;
  }
}
