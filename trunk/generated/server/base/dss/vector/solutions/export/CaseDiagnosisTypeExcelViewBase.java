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

@com.runwaysdk.business.ClassSignature(hash = -1505841673)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiagnosisTypeExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CaseDiagnosisTypeExcelViewBase extends dss.vector.solutions.export.AggregatedCaseExcelView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.CaseDiagnosisTypeExcelView";
  public static java.lang.String DIAGNOSISTYPE = "diagnosisType";
  private static final long serialVersionUID = -1505841673;
  
  public CaseDiagnosisTypeExcelViewBase()
  {
    super();
  }
  
  public String getDiagnosisType()
  {
    return getValue(DIAGNOSISTYPE);
  }
  
  public void validateDiagnosisType()
  {
    this.validateAttribute(DIAGNOSISTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDiagnosisTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.CaseDiagnosisTypeExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DIAGNOSISTYPE);
  }
  
  public void setDiagnosisType(String value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISTYPE, "");
    }
    else
    {
      setValue(DIAGNOSISTYPE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CaseDiagnosisTypeExcelView get(String id)
  {
    return (CaseDiagnosisTypeExcelView) com.runwaysdk.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
