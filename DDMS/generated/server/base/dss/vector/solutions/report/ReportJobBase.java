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
package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 38615935)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReportJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ReportJobBase extends com.runwaysdk.system.scheduler.ExecutableJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.ReportJob";
  public static java.lang.String REPORTITEM = "reportItem";
  private static final long serialVersionUID = 38615935;
  
  public ReportJobBase()
  {
    super();
  }
  
  public dss.vector.solutions.report.ReportItem getReportItem()
  {
    if (getValue(REPORTITEM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.report.ReportItem.get(getValue(REPORTITEM));
    }
  }
  
  public String getReportItemId()
  {
    return getValue(REPORTITEM);
  }
  
  public void validateReportItem()
  {
    this.validateAttribute(REPORTITEM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getReportItemMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.ReportJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(REPORTITEM);
  }
  
  public void setReportItem(dss.vector.solutions.report.ReportItem value)
  {
    if(value == null)
    {
      setValue(REPORTITEM, "");
    }
    else
    {
      setValue(REPORTITEM, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ReportJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ReportJobQuery query = new ReportJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ReportJob get(String id)
  {
    return (ReportJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static ReportJob getByKey(String key)
  {
    return (ReportJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static ReportJob lock(java.lang.String id)
  {
    ReportJob _instance = ReportJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ReportJob unlock(java.lang.String id)
  {
    ReportJob _instance = ReportJob.get(id);
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
