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
package dss.vector.solutions.kaleidoscope.dashboard.layer;

@com.runwaysdk.business.ClassSignature(hash = 1124079407)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DashboardReferenceLayerView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DashboardReferenceLayerViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView";
  public static java.lang.String ACTIVEBYDEFAULT = "activeByDefault";
  public static java.lang.String DISPLAYINLEGEND = "displayInLegend";
  public static java.lang.String FEATURESTRATEGY = "featureStrategy";
  public static java.lang.String GROUPEDINLEGEND = "groupedInLegend";
  public static java.lang.String ID = "id";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String LAYERISACTIVE = "layerIsActive";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LEGENDXPOSITION = "legendXPosition";
  public static java.lang.String LEGENDYPOSITION = "legendYPosition";
  public static java.lang.String SLDNAME = "sldName";
  public static java.lang.String VIEWNAME = "viewName";
  private static final long serialVersionUID = 1124079407;
  
  public DashboardReferenceLayerViewBase()
  {
    super();
  }
  
  public Boolean getActiveByDefault()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVEBYDEFAULT));
  }
  
  public void validateActiveByDefault()
  {
    this.validateAttribute(ACTIVEBYDEFAULT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getActiveByDefaultMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(ACTIVEBYDEFAULT);
  }
  
  public void setActiveByDefault(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVEBYDEFAULT, "");
    }
    else
    {
      setValue(ACTIVEBYDEFAULT, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getDisplayInLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(DISPLAYINLEGEND));
  }
  
  public void validateDisplayInLegend()
  {
    this.validateAttribute(DISPLAYINLEGEND);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getDisplayInLegendMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(DISPLAYINLEGEND);
  }
  
  public void setDisplayInLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(DISPLAYINLEGEND, "");
    }
    else
    {
      setValue(DISPLAYINLEGEND, java.lang.Boolean.toString(value));
    }
  }
  
  public String getFeatureStrategy()
  {
    return getValue(FEATURESTRATEGY);
  }
  
  public void validateFeatureStrategy()
  {
    this.validateAttribute(FEATURESTRATEGY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFeatureStrategyMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FEATURESTRATEGY);
  }
  
  public void setFeatureStrategy(String value)
  {
    if(value == null)
    {
      setValue(FEATURESTRATEGY, "");
    }
    else
    {
      setValue(FEATURESTRATEGY, value);
    }
  }
  
  public Boolean getGroupedInLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(GROUPEDINLEGEND));
  }
  
  public void validateGroupedInLegend()
  {
    this.validateAttribute(GROUPEDINLEGEND);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getGroupedInLegendMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(GROUPEDINLEGEND);
  }
  
  public void setGroupedInLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(GROUPEDINLEGEND, "");
    }
    else
    {
      setValue(GROUPEDINLEGEND, java.lang.Boolean.toString(value));
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getLayerId()
  {
    return getValue(LAYERID);
  }
  
  public void validateLayerId()
  {
    this.validateAttribute(LAYERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getLayerIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(LAYERID);
  }
  
  public void setLayerId(String value)
  {
    if(value == null)
    {
      setValue(LAYERID, "");
    }
    else
    {
      setValue(LAYERID, value);
    }
  }
  
  public Boolean getLayerIsActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(LAYERISACTIVE));
  }
  
  public void validateLayerIsActive()
  {
    this.validateAttribute(LAYERISACTIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getLayerIsActiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(LAYERISACTIVE);
  }
  
  public void setLayerIsActive(Boolean value)
  {
    if(value == null)
    {
      setValue(LAYERISACTIVE, "");
    }
    else
    {
      setValue(LAYERISACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void validateLayerName()
  {
    this.validateAttribute(LAYERNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getLayerNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public Integer getLegendXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDXPOSITION));
  }
  
  public void validateLegendXPosition()
  {
    this.validateAttribute(LEGENDXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getLegendXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(LEGENDXPOSITION);
  }
  
  public void setLegendXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDXPOSITION, "");
    }
    else
    {
      setValue(LEGENDXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getLegendYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LEGENDYPOSITION));
  }
  
  public void validateLegendYPosition()
  {
    this.validateAttribute(LEGENDYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getLegendYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(LEGENDYPOSITION);
  }
  
  public void setLegendYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LEGENDYPOSITION, "");
    }
    else
    {
      setValue(LEGENDYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public String getSldName()
  {
    return getValue(SLDNAME);
  }
  
  public void validateSldName()
  {
    this.validateAttribute(SLDNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSldNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SLDNAME);
  }
  
  public void setSldName(String value)
  {
    if(value == null)
    {
      setValue(SLDNAME, "");
    }
    else
    {
      setValue(SLDNAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerView.CLASS);
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
  
  public static DashboardReferenceLayerView get(String id)
  {
    return (DashboardReferenceLayerView) com.runwaysdk.business.View.get(id);
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
