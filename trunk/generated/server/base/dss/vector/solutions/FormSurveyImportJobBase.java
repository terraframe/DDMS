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
package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -418647239)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to FormSurveyImportJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class FormSurveyImportJobBase extends dss.vector.solutions.ExcelImportJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.FormSurveyImportJob";
  private static final long serialVersionUID = -418647239;
  
  public FormSurveyImportJobBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static FormSurveyImportJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    FormSurveyImportJobQuery query = new FormSurveyImportJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static FormSurveyImportJob get(String id)
  {
    return (FormSurveyImportJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static FormSurveyImportJob getByKey(String key)
  {
    return (FormSurveyImportJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static FormSurveyImportJob lock(java.lang.String id)
  {
    FormSurveyImportJob _instance = FormSurveyImportJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static FormSurveyImportJob unlock(java.lang.String id)
  {
    FormSurveyImportJob _instance = FormSurveyImportJob.get(id);
    _instance.unlock();
    
    return _instance;
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
