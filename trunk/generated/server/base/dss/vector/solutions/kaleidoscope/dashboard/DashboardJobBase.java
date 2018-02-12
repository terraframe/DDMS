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
package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = 1627285061)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardJobBase extends com.runwaysdk.system.scheduler.ExecutableJob implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardJob";
  public static java.lang.String DASHBOARD = "dashboard";
  public static java.lang.String IMAGEHEIGHT = "imageHeight";
  public static java.lang.String IMAGEWIDTH = "imageWidth";
  public static java.lang.String JOBNAME = "jobName";
  public static java.lang.String LAYER = "layer";
  public static java.lang.String VIEWNAME = "viewName";
  private static final long serialVersionUID = 1627285061;
  
  public DashboardJobBase()
  {
    super();
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.Dashboard getDashboard()
  {
    if (getValue(DASHBOARD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.Dashboard.get(getValue(DASHBOARD));
    }
  }
  
  public String getDashboardId()
  {
    return getValue(DASHBOARD);
  }
  
  public void validateDashboard()
  {
    this.validateAttribute(DASHBOARD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDashboardMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DASHBOARD);
  }
  
  public void setDashboard(dss.vector.solutions.kaleidoscope.dashboard.Dashboard value)
  {
    if(value == null)
    {
      setValue(DASHBOARD, "");
    }
    else
    {
      setValue(DASHBOARD, value.getId());
    }
  }
  
  public Integer getImageHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEHEIGHT));
  }
  
  public void validateImageHeight()
  {
    this.validateAttribute(IMAGEHEIGHT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getImageHeightMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(IMAGEHEIGHT);
  }
  
  public void setImageHeight(Integer value)
  {
    if(value == null)
    {
      setValue(IMAGEHEIGHT, "");
    }
    else
    {
      setValue(IMAGEHEIGHT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getImageWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEWIDTH));
  }
  
  public void validateImageWidth()
  {
    this.validateAttribute(IMAGEWIDTH);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getImageWidthMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(IMAGEWIDTH);
  }
  
  public void setImageWidth(Integer value)
  {
    if(value == null)
    {
      setValue(IMAGEWIDTH, "");
    }
    else
    {
      setValue(IMAGEWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public String getJobName()
  {
    return getValue(JOBNAME);
  }
  
  public void validateJobName()
  {
    this.validateAttribute(JOBNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getJobNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(JOBNAME);
  }
  
  public void setJobName(String value)
  {
    if(value == null)
    {
      setValue(JOBNAME, "");
    }
    else
    {
      setValue(JOBNAME, value);
    }
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer getLayer()
  {
    if (getValue(LAYER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer.get(getValue(LAYER));
    }
  }
  
  public String getLayerId()
  {
    return getValue(LAYER);
  }
  
  public void validateLayer()
  {
    this.validateAttribute(LAYER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLayerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LAYER);
  }
  
  public void setLayer(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer value)
  {
    if(value == null)
    {
      setValue(LAYER, "");
    }
    else
    {
      setValue(LAYER, value.getId());
    }
  }
  
  public String getViewName()
  {
    return getValue(VIEWNAME);
  }
  
  public void validateViewName()
  {
    this.validateAttribute(VIEWNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getViewNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(VIEWNAME);
  }
  
  public void setViewName(String value)
  {
    if(value == null)
    {
      setValue(VIEWNAME, "");
    }
    else
    {
      setValue(VIEWNAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DashboardJobQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    DashboardJobQuery query = new DashboardJobQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static DashboardJob get(String id)
  {
    return (DashboardJob) com.runwaysdk.business.Business.get(id);
  }
  
  public static DashboardJob getByKey(String key)
  {
    return (DashboardJob) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static void applyJSON(java.lang.String json)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getJSON(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.kaleidoscope.dashboard.DashboardJob.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static DashboardJob lock(java.lang.String id)
  {
    DashboardJob _instance = DashboardJob.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static DashboardJob unlock(java.lang.String id)
  {
    DashboardJob _instance = DashboardJob.get(id);
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
