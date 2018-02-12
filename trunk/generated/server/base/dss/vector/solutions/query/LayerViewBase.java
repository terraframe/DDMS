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
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -757571943)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LayerView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LayerViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.LayerView";
  public static java.lang.String ID = "id";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LAYERPOSITION = "layerPosition";
  private static final long serialVersionUID = -757571943;
  
  public LayerViewBase()
  {
    super();
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.LayerView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.LayerView.CLASS);
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
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void validateLayerName()
  {
    this.validateAttribute(LAYERNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getLayerNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.LayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(LAYERNAME);
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
  
  public Integer getLayerPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LAYERPOSITION));
  }
  
  public void validateLayerPosition()
  {
    this.validateAttribute(LAYERPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getLayerPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.LayerView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(LAYERPOSITION);
  }
  
  public void setLayerPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LAYERPOSITION, "");
    }
    else
    {
      setValue(LAYERPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LayerView get(String id)
  {
    return (LayerView) com.runwaysdk.business.View.get(id);
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
