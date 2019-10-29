/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(LifeStage.CLASS);
    iep.throwIt();
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(ContainerShape.CLASS);
    iep.throwIt();
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(DiagnosisType.CLASS);
    iep.throwIt();
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(PeriodType.CLASS);
    iep.throwIt();
    return null;
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
    
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(SprayMethod.CLASS);
    iep.throwIt();
    return null;
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(MonthOfYear.CLASS);
    iep.throwIt();
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(Response.CLASS);
    iep.throwIt();
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
    
    InvalidEnumProblem iep = new InvalidEnumProblem();
    iep.setEnumName(label);
    iep.setLabelFromQualifiedName(RefusedResponse.CLASS);
    iep.throwIt();
    return null;
  }
}
