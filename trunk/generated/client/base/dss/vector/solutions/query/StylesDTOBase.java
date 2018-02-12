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

@com.runwaysdk.business.ClassSignature(hash = -1488434945)
public abstract class StylesDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Styles";
  private static final long serialVersionUID = -1488434945;
  
  protected StylesDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected StylesDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ANCHORPOINTX = "anchorPointX";
  public static java.lang.String ANCHORPOINTY = "anchorPointY";
  public static java.lang.String CONFLICTRESOLUTION = "conflictResolution";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISPLACEMENTX = "displacementX";
  public static java.lang.String DISPLACEMENTY = "displacementY";
  public static java.lang.String ENABLE_ANCHORPOINTX = "enable_anchorPointX";
  public static java.lang.String ENABLE_ANCHORPOINTY = "enable_anchorPointY";
  public static java.lang.String ENABLE_CONFLICTRESOLUTION = "enable_conflictResolution";
  public static java.lang.String ENABLE_DISPLACEMENTX = "enable_displacementX";
  public static java.lang.String ENABLE_DISPLACEMENTY = "enable_displacementY";
  public static java.lang.String ENABLE_FILL = "enable_fill";
  public static java.lang.String ENABLE_FONTFAMILY = "enable_fontFamily";
  public static java.lang.String ENABLE_FONTSIZE = "enable_fontSize";
  public static java.lang.String ENABLE_FONTSTYLES = "enable_fontStyles";
  public static java.lang.String ENABLE_GOODNESSOFFIT = "enable_goodnessOfFit";
  public static java.lang.String ENABLE_LABELHALOFILL = "enable_labelHaloFill";
  public static java.lang.String ENABLE_LABELHALOOPACITY = "enable_labelHaloOpacity";
  public static java.lang.String ENABLE_LABELHALORADIUS = "enable_labelHaloRadius";
  public static java.lang.String ENABLE_LABELROTATION = "enable_labelRotation";
  public static java.lang.String ENABLE_POINTMARKER = "enable_pointMarker";
  public static java.lang.String ENABLE_POINTROTATION = "enable_pointRotation";
  public static java.lang.String ENABLE_POINTSIZE = "enable_pointSize";
  public static java.lang.String ENABLE_POINTSTROKE = "enable_pointStroke";
  public static java.lang.String ENABLE_POINTSTROKEOPACITY = "enable_pointStrokeOpacity";
  public static java.lang.String ENABLE_POINTWIDTH = "enable_pointWidth";
  public static java.lang.String ENABLE_POLYGONFILL = "enable_polygonFill";
  public static java.lang.String ENABLE_POLYGONFILLOPACITY = "enable_polygonFillOpacity";
  public static java.lang.String ENABLE_POLYGONSTROKE = "enable_polygonStroke";
  public static java.lang.String ENABLE_POLYGONSTROKEOPACITY = "enable_polygonStrokeOpacity";
  public static java.lang.String ENABLE_POLYGONWIDTH = "enable_polygonWidth";
  public static java.lang.String ENABLE_SPACEAROUND = "enable_spaceAround";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILL = "fill";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLES = "fontStyles";
  public static java.lang.String GOODNESSOFFIT = "goodnessOfFit";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LABELHALOFILL = "labelHaloFill";
  public static java.lang.String LABELHALOOPACITY = "labelHaloOpacity";
  public static java.lang.String LABELHALORADIUS = "labelHaloRadius";
  public static java.lang.String LABELROTATION = "labelRotation";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POINTMARKER = "pointMarker";
  public static java.lang.String POINTROTATION = "pointRotation";
  public static java.lang.String POINTSIZE = "pointSize";
  public static java.lang.String POINTSTROKE = "pointStroke";
  public static java.lang.String POINTSTROKEOPACITY = "pointStrokeOpacity";
  public static java.lang.String POINTWIDTH = "pointWidth";
  public static java.lang.String POLYGONFILL = "polygonFill";
  public static java.lang.String POLYGONFILLOPACITY = "polygonFillOpacity";
  public static java.lang.String POLYGONSTROKE = "polygonStroke";
  public static java.lang.String POLYGONSTROKEOPACITY = "polygonStrokeOpacity";
  public static java.lang.String POLYGONWIDTH = "polygonWidth";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SPACEAROUND = "spaceAround";
  public static java.lang.String TYPE = "type";
  public java.math.BigDecimal getAnchorPointX()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(ANCHORPOINTX));
  }
  
  public void setAnchorPointX(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(ANCHORPOINTX, "");
    }
    else
    {
      setValue(ANCHORPOINTX, value.toString());
    }
  }
  
  public boolean isAnchorPointXWritable()
  {
    return isWritable(ANCHORPOINTX);
  }
  
  public boolean isAnchorPointXReadable()
  {
    return isReadable(ANCHORPOINTX);
  }
  
  public boolean isAnchorPointXModified()
  {
    return isModified(ANCHORPOINTX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getAnchorPointXMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ANCHORPOINTX).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getAnchorPointY()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(ANCHORPOINTY));
  }
  
  public void setAnchorPointY(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(ANCHORPOINTY, "");
    }
    else
    {
      setValue(ANCHORPOINTY, value.toString());
    }
  }
  
  public boolean isAnchorPointYWritable()
  {
    return isWritable(ANCHORPOINTY);
  }
  
  public boolean isAnchorPointYReadable()
  {
    return isReadable(ANCHORPOINTY);
  }
  
  public boolean isAnchorPointYModified()
  {
    return isModified(ANCHORPOINTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getAnchorPointYMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ANCHORPOINTY).getAttributeMdDTO();
  }
  
  public Boolean getConflictResolution()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CONFLICTRESOLUTION));
  }
  
  public void setConflictResolution(Boolean value)
  {
    if(value == null)
    {
      setValue(CONFLICTRESOLUTION, "");
    }
    else
    {
      setValue(CONFLICTRESOLUTION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isConflictResolutionWritable()
  {
    return isWritable(CONFLICTRESOLUTION);
  }
  
  public boolean isConflictResolutionReadable()
  {
    return isReadable(CONFLICTRESOLUTION);
  }
  
  public boolean isConflictResolutionModified()
  {
    return isModified(CONFLICTRESOLUTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getConflictResolutionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CONFLICTRESOLUTION).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public Integer getDisplacementX()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLACEMENTX));
  }
  
  public void setDisplacementX(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLACEMENTX, "");
    }
    else
    {
      setValue(DISPLACEMENTX, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplacementXWritable()
  {
    return isWritable(DISPLACEMENTX);
  }
  
  public boolean isDisplacementXReadable()
  {
    return isReadable(DISPLACEMENTX);
  }
  
  public boolean isDisplacementXModified()
  {
    return isModified(DISPLACEMENTX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDisplacementXMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLACEMENTX).getAttributeMdDTO();
  }
  
  public Integer getDisplacementY()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISPLACEMENTY));
  }
  
  public void setDisplacementY(Integer value)
  {
    if(value == null)
    {
      setValue(DISPLACEMENTY, "");
    }
    else
    {
      setValue(DISPLACEMENTY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisplacementYWritable()
  {
    return isWritable(DISPLACEMENTY);
  }
  
  public boolean isDisplacementYReadable()
  {
    return isReadable(DISPLACEMENTY);
  }
  
  public boolean isDisplacementYModified()
  {
    return isModified(DISPLACEMENTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDisplacementYMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISPLACEMENTY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_anchorPointX()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_ANCHORPOINTX));
  }
  
  public void setEnable_anchorPointX(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_ANCHORPOINTX, "");
    }
    else
    {
      setValue(ENABLE_ANCHORPOINTX, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_anchorPointXWritable()
  {
    return isWritable(ENABLE_ANCHORPOINTX);
  }
  
  public boolean isEnable_anchorPointXReadable()
  {
    return isReadable(ENABLE_ANCHORPOINTX);
  }
  
  public boolean isEnable_anchorPointXModified()
  {
    return isModified(ENABLE_ANCHORPOINTX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_anchorPointXMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_ANCHORPOINTX).getAttributeMdDTO();
  }
  
  public Boolean getEnable_anchorPointY()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_ANCHORPOINTY));
  }
  
  public void setEnable_anchorPointY(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_ANCHORPOINTY, "");
    }
    else
    {
      setValue(ENABLE_ANCHORPOINTY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_anchorPointYWritable()
  {
    return isWritable(ENABLE_ANCHORPOINTY);
  }
  
  public boolean isEnable_anchorPointYReadable()
  {
    return isReadable(ENABLE_ANCHORPOINTY);
  }
  
  public boolean isEnable_anchorPointYModified()
  {
    return isModified(ENABLE_ANCHORPOINTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_anchorPointYMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_ANCHORPOINTY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_conflictResolution()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_CONFLICTRESOLUTION));
  }
  
  public void setEnable_conflictResolution(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_CONFLICTRESOLUTION, "");
    }
    else
    {
      setValue(ENABLE_CONFLICTRESOLUTION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_conflictResolutionWritable()
  {
    return isWritable(ENABLE_CONFLICTRESOLUTION);
  }
  
  public boolean isEnable_conflictResolutionReadable()
  {
    return isReadable(ENABLE_CONFLICTRESOLUTION);
  }
  
  public boolean isEnable_conflictResolutionModified()
  {
    return isModified(ENABLE_CONFLICTRESOLUTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_conflictResolutionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_CONFLICTRESOLUTION).getAttributeMdDTO();
  }
  
  public Boolean getEnable_displacementX()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_DISPLACEMENTX));
  }
  
  public void setEnable_displacementX(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_DISPLACEMENTX, "");
    }
    else
    {
      setValue(ENABLE_DISPLACEMENTX, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_displacementXWritable()
  {
    return isWritable(ENABLE_DISPLACEMENTX);
  }
  
  public boolean isEnable_displacementXReadable()
  {
    return isReadable(ENABLE_DISPLACEMENTX);
  }
  
  public boolean isEnable_displacementXModified()
  {
    return isModified(ENABLE_DISPLACEMENTX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_displacementXMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_DISPLACEMENTX).getAttributeMdDTO();
  }
  
  public Boolean getEnable_displacementY()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_DISPLACEMENTY));
  }
  
  public void setEnable_displacementY(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_DISPLACEMENTY, "");
    }
    else
    {
      setValue(ENABLE_DISPLACEMENTY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_displacementYWritable()
  {
    return isWritable(ENABLE_DISPLACEMENTY);
  }
  
  public boolean isEnable_displacementYReadable()
  {
    return isReadable(ENABLE_DISPLACEMENTY);
  }
  
  public boolean isEnable_displacementYModified()
  {
    return isModified(ENABLE_DISPLACEMENTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_displacementYMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_DISPLACEMENTY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_fill()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_FILL));
  }
  
  public void setEnable_fill(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_FILL, "");
    }
    else
    {
      setValue(ENABLE_FILL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_fillWritable()
  {
    return isWritable(ENABLE_FILL);
  }
  
  public boolean isEnable_fillReadable()
  {
    return isReadable(ENABLE_FILL);
  }
  
  public boolean isEnable_fillModified()
  {
    return isModified(ENABLE_FILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_fillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_FILL).getAttributeMdDTO();
  }
  
  public Boolean getEnable_fontFamily()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_FONTFAMILY));
  }
  
  public void setEnable_fontFamily(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_FONTFAMILY, "");
    }
    else
    {
      setValue(ENABLE_FONTFAMILY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_fontFamilyWritable()
  {
    return isWritable(ENABLE_FONTFAMILY);
  }
  
  public boolean isEnable_fontFamilyReadable()
  {
    return isReadable(ENABLE_FONTFAMILY);
  }
  
  public boolean isEnable_fontFamilyModified()
  {
    return isModified(ENABLE_FONTFAMILY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_fontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_FONTFAMILY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_fontSize()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_FONTSIZE));
  }
  
  public void setEnable_fontSize(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_FONTSIZE, "");
    }
    else
    {
      setValue(ENABLE_FONTSIZE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_fontSizeWritable()
  {
    return isWritable(ENABLE_FONTSIZE);
  }
  
  public boolean isEnable_fontSizeReadable()
  {
    return isReadable(ENABLE_FONTSIZE);
  }
  
  public boolean isEnable_fontSizeModified()
  {
    return isModified(ENABLE_FONTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_fontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_FONTSIZE).getAttributeMdDTO();
  }
  
  public Boolean getEnable_fontStyles()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_FONTSTYLES));
  }
  
  public void setEnable_fontStyles(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_FONTSTYLES, "");
    }
    else
    {
      setValue(ENABLE_FONTSTYLES, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_fontStylesWritable()
  {
    return isWritable(ENABLE_FONTSTYLES);
  }
  
  public boolean isEnable_fontStylesReadable()
  {
    return isReadable(ENABLE_FONTSTYLES);
  }
  
  public boolean isEnable_fontStylesModified()
  {
    return isModified(ENABLE_FONTSTYLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_fontStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_FONTSTYLES).getAttributeMdDTO();
  }
  
  public Boolean getEnable_goodnessOfFit()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_GOODNESSOFFIT));
  }
  
  public void setEnable_goodnessOfFit(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_GOODNESSOFFIT, "");
    }
    else
    {
      setValue(ENABLE_GOODNESSOFFIT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_goodnessOfFitWritable()
  {
    return isWritable(ENABLE_GOODNESSOFFIT);
  }
  
  public boolean isEnable_goodnessOfFitReadable()
  {
    return isReadable(ENABLE_GOODNESSOFFIT);
  }
  
  public boolean isEnable_goodnessOfFitModified()
  {
    return isModified(ENABLE_GOODNESSOFFIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_goodnessOfFitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_GOODNESSOFFIT).getAttributeMdDTO();
  }
  
  public Boolean getEnable_labelHaloFill()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_LABELHALOFILL));
  }
  
  public void setEnable_labelHaloFill(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_LABELHALOFILL, "");
    }
    else
    {
      setValue(ENABLE_LABELHALOFILL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_labelHaloFillWritable()
  {
    return isWritable(ENABLE_LABELHALOFILL);
  }
  
  public boolean isEnable_labelHaloFillReadable()
  {
    return isReadable(ENABLE_LABELHALOFILL);
  }
  
  public boolean isEnable_labelHaloFillModified()
  {
    return isModified(ENABLE_LABELHALOFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_labelHaloFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_LABELHALOFILL).getAttributeMdDTO();
  }
  
  public Boolean getEnable_labelHaloOpacity()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_LABELHALOOPACITY));
  }
  
  public void setEnable_labelHaloOpacity(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_LABELHALOOPACITY, "");
    }
    else
    {
      setValue(ENABLE_LABELHALOOPACITY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_labelHaloOpacityWritable()
  {
    return isWritable(ENABLE_LABELHALOOPACITY);
  }
  
  public boolean isEnable_labelHaloOpacityReadable()
  {
    return isReadable(ENABLE_LABELHALOOPACITY);
  }
  
  public boolean isEnable_labelHaloOpacityModified()
  {
    return isModified(ENABLE_LABELHALOOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_labelHaloOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_LABELHALOOPACITY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_labelHaloRadius()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_LABELHALORADIUS));
  }
  
  public void setEnable_labelHaloRadius(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_LABELHALORADIUS, "");
    }
    else
    {
      setValue(ENABLE_LABELHALORADIUS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_labelHaloRadiusWritable()
  {
    return isWritable(ENABLE_LABELHALORADIUS);
  }
  
  public boolean isEnable_labelHaloRadiusReadable()
  {
    return isReadable(ENABLE_LABELHALORADIUS);
  }
  
  public boolean isEnable_labelHaloRadiusModified()
  {
    return isModified(ENABLE_LABELHALORADIUS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_labelHaloRadiusMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_LABELHALORADIUS).getAttributeMdDTO();
  }
  
  public Boolean getEnable_labelRotation()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_LABELROTATION));
  }
  
  public void setEnable_labelRotation(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_LABELROTATION, "");
    }
    else
    {
      setValue(ENABLE_LABELROTATION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_labelRotationWritable()
  {
    return isWritable(ENABLE_LABELROTATION);
  }
  
  public boolean isEnable_labelRotationReadable()
  {
    return isReadable(ENABLE_LABELROTATION);
  }
  
  public boolean isEnable_labelRotationModified()
  {
    return isModified(ENABLE_LABELROTATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_labelRotationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_LABELROTATION).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointMarker()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTMARKER));
  }
  
  public void setEnable_pointMarker(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTMARKER, "");
    }
    else
    {
      setValue(ENABLE_POINTMARKER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointMarkerWritable()
  {
    return isWritable(ENABLE_POINTMARKER);
  }
  
  public boolean isEnable_pointMarkerReadable()
  {
    return isReadable(ENABLE_POINTMARKER);
  }
  
  public boolean isEnable_pointMarkerModified()
  {
    return isModified(ENABLE_POINTMARKER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointMarkerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTMARKER).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointRotation()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTROTATION));
  }
  
  public void setEnable_pointRotation(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTROTATION, "");
    }
    else
    {
      setValue(ENABLE_POINTROTATION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointRotationWritable()
  {
    return isWritable(ENABLE_POINTROTATION);
  }
  
  public boolean isEnable_pointRotationReadable()
  {
    return isReadable(ENABLE_POINTROTATION);
  }
  
  public boolean isEnable_pointRotationModified()
  {
    return isModified(ENABLE_POINTROTATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointRotationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTROTATION).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointSize()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTSIZE));
  }
  
  public void setEnable_pointSize(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTSIZE, "");
    }
    else
    {
      setValue(ENABLE_POINTSIZE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointSizeWritable()
  {
    return isWritable(ENABLE_POINTSIZE);
  }
  
  public boolean isEnable_pointSizeReadable()
  {
    return isReadable(ENABLE_POINTSIZE);
  }
  
  public boolean isEnable_pointSizeModified()
  {
    return isModified(ENABLE_POINTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTSIZE).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointStroke()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTSTROKE));
  }
  
  public void setEnable_pointStroke(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTSTROKE, "");
    }
    else
    {
      setValue(ENABLE_POINTSTROKE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointStrokeWritable()
  {
    return isWritable(ENABLE_POINTSTROKE);
  }
  
  public boolean isEnable_pointStrokeReadable()
  {
    return isReadable(ENABLE_POINTSTROKE);
  }
  
  public boolean isEnable_pointStrokeModified()
  {
    return isModified(ENABLE_POINTSTROKE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointStrokeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTSTROKE).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointStrokeOpacity()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTSTROKEOPACITY));
  }
  
  public void setEnable_pointStrokeOpacity(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTSTROKEOPACITY, "");
    }
    else
    {
      setValue(ENABLE_POINTSTROKEOPACITY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointStrokeOpacityWritable()
  {
    return isWritable(ENABLE_POINTSTROKEOPACITY);
  }
  
  public boolean isEnable_pointStrokeOpacityReadable()
  {
    return isReadable(ENABLE_POINTSTROKEOPACITY);
  }
  
  public boolean isEnable_pointStrokeOpacityModified()
  {
    return isModified(ENABLE_POINTSTROKEOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointStrokeOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTSTROKEOPACITY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_pointWidth()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POINTWIDTH));
  }
  
  public void setEnable_pointWidth(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POINTWIDTH, "");
    }
    else
    {
      setValue(ENABLE_POINTWIDTH, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_pointWidthWritable()
  {
    return isWritable(ENABLE_POINTWIDTH);
  }
  
  public boolean isEnable_pointWidthReadable()
  {
    return isReadable(ENABLE_POINTWIDTH);
  }
  
  public boolean isEnable_pointWidthModified()
  {
    return isModified(ENABLE_POINTWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_pointWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POINTWIDTH).getAttributeMdDTO();
  }
  
  public Boolean getEnable_polygonFill()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POLYGONFILL));
  }
  
  public void setEnable_polygonFill(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POLYGONFILL, "");
    }
    else
    {
      setValue(ENABLE_POLYGONFILL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_polygonFillWritable()
  {
    return isWritable(ENABLE_POLYGONFILL);
  }
  
  public boolean isEnable_polygonFillReadable()
  {
    return isReadable(ENABLE_POLYGONFILL);
  }
  
  public boolean isEnable_polygonFillModified()
  {
    return isModified(ENABLE_POLYGONFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_polygonFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POLYGONFILL).getAttributeMdDTO();
  }
  
  public Boolean getEnable_polygonFillOpacity()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POLYGONFILLOPACITY));
  }
  
  public void setEnable_polygonFillOpacity(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POLYGONFILLOPACITY, "");
    }
    else
    {
      setValue(ENABLE_POLYGONFILLOPACITY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_polygonFillOpacityWritable()
  {
    return isWritable(ENABLE_POLYGONFILLOPACITY);
  }
  
  public boolean isEnable_polygonFillOpacityReadable()
  {
    return isReadable(ENABLE_POLYGONFILLOPACITY);
  }
  
  public boolean isEnable_polygonFillOpacityModified()
  {
    return isModified(ENABLE_POLYGONFILLOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_polygonFillOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POLYGONFILLOPACITY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_polygonStroke()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POLYGONSTROKE));
  }
  
  public void setEnable_polygonStroke(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POLYGONSTROKE, "");
    }
    else
    {
      setValue(ENABLE_POLYGONSTROKE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_polygonStrokeWritable()
  {
    return isWritable(ENABLE_POLYGONSTROKE);
  }
  
  public boolean isEnable_polygonStrokeReadable()
  {
    return isReadable(ENABLE_POLYGONSTROKE);
  }
  
  public boolean isEnable_polygonStrokeModified()
  {
    return isModified(ENABLE_POLYGONSTROKE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_polygonStrokeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POLYGONSTROKE).getAttributeMdDTO();
  }
  
  public Boolean getEnable_polygonStrokeOpacity()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POLYGONSTROKEOPACITY));
  }
  
  public void setEnable_polygonStrokeOpacity(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POLYGONSTROKEOPACITY, "");
    }
    else
    {
      setValue(ENABLE_POLYGONSTROKEOPACITY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_polygonStrokeOpacityWritable()
  {
    return isWritable(ENABLE_POLYGONSTROKEOPACITY);
  }
  
  public boolean isEnable_polygonStrokeOpacityReadable()
  {
    return isReadable(ENABLE_POLYGONSTROKEOPACITY);
  }
  
  public boolean isEnable_polygonStrokeOpacityModified()
  {
    return isModified(ENABLE_POLYGONSTROKEOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_polygonStrokeOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POLYGONSTROKEOPACITY).getAttributeMdDTO();
  }
  
  public Boolean getEnable_polygonWidth()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_POLYGONWIDTH));
  }
  
  public void setEnable_polygonWidth(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_POLYGONWIDTH, "");
    }
    else
    {
      setValue(ENABLE_POLYGONWIDTH, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_polygonWidthWritable()
  {
    return isWritable(ENABLE_POLYGONWIDTH);
  }
  
  public boolean isEnable_polygonWidthReadable()
  {
    return isReadable(ENABLE_POLYGONWIDTH);
  }
  
  public boolean isEnable_polygonWidthModified()
  {
    return isModified(ENABLE_POLYGONWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_polygonWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_POLYGONWIDTH).getAttributeMdDTO();
  }
  
  public Boolean getEnable_spaceAround()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLE_SPACEAROUND));
  }
  
  public void setEnable_spaceAround(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLE_SPACEAROUND, "");
    }
    else
    {
      setValue(ENABLE_SPACEAROUND, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnable_spaceAroundWritable()
  {
    return isWritable(ENABLE_SPACEAROUND);
  }
  
  public boolean isEnable_spaceAroundReadable()
  {
    return isReadable(ENABLE_SPACEAROUND);
  }
  
  public boolean isEnable_spaceAroundModified()
  {
    return isModified(ENABLE_SPACEAROUND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnable_spaceAroundMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLE_SPACEAROUND).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public String getFill()
  {
    return getValue(FILL);
  }
  
  public void setFill(String value)
  {
    if(value == null)
    {
      setValue(FILL, "");
    }
    else
    {
      setValue(FILL, value);
    }
  }
  
  public boolean isFillWritable()
  {
    return isWritable(FILL);
  }
  
  public boolean isFillReadable()
  {
    return isReadable(FILL);
  }
  
  public boolean isFillModified()
  {
    return isModified(FILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILL).getAttributeMdDTO();
  }
  
  public String getFontFamily()
  {
    return getValue(FONTFAMILY);
  }
  
  public void setFontFamily(String value)
  {
    if(value == null)
    {
      setValue(FONTFAMILY, "");
    }
    else
    {
      setValue(FONTFAMILY, value);
    }
  }
  
  public boolean isFontFamilyWritable()
  {
    return isWritable(FONTFAMILY);
  }
  
  public boolean isFontFamilyReadable()
  {
    return isReadable(FONTFAMILY);
  }
  
  public boolean isFontFamilyModified()
  {
    return isModified(FONTFAMILY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFAMILY).getAttributeMdDTO();
  }
  
  public Integer getFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FONTSIZE));
  }
  
  public void setFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(FONTSIZE, "");
    }
    else
    {
      setValue(FONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFontSizeWritable()
  {
    return isWritable(FONTSIZE);
  }
  
  public boolean isFontSizeReadable()
  {
    return isReadable(FONTSIZE);
  }
  
  public boolean isFontSizeModified()
  {
    return isModified(FONTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FONTSIZE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.FontStylesDTO> getFontStyles()
  {
    return (java.util.List<dss.vector.solutions.query.FontStylesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.FontStylesDTO.CLASS, getEnumNames(FONTSTYLES));
  }
  
  public java.util.List<String> getFontStylesEnumNames()
  {
    return getEnumNames(FONTSTYLES);
  }
  
  public void addFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    addEnumItem(FONTSTYLES, enumDTO.toString());
  }
  
  public void removeFontStyles(dss.vector.solutions.query.FontStylesDTO enumDTO)
  {
    removeEnumItem(FONTSTYLES, enumDTO.toString());
  }
  
  public void clearFontStyles()
  {
    clearEnum(FONTSTYLES);
  }
  
  public boolean isFontStylesWritable()
  {
    return isWritable(FONTSTYLES);
  }
  
  public boolean isFontStylesReadable()
  {
    return isReadable(FONTSTYLES);
  }
  
  public boolean isFontStylesModified()
  {
    return isModified(FONTSTYLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getFontStylesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(FONTSTYLES).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getGoodnessOfFit()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(GOODNESSOFFIT));
  }
  
  public void setGoodnessOfFit(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(GOODNESSOFFIT, "");
    }
    else
    {
      setValue(GOODNESSOFFIT, value.toString());
    }
  }
  
  public boolean isGoodnessOfFitWritable()
  {
    return isWritable(GOODNESSOFFIT);
  }
  
  public boolean isGoodnessOfFitReadable()
  {
    return isReadable(GOODNESSOFFIT);
  }
  
  public boolean isGoodnessOfFitModified()
  {
    return isModified(GOODNESSOFFIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getGoodnessOfFitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(GOODNESSOFFIT).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public String getLabelHaloFill()
  {
    return getValue(LABELHALOFILL);
  }
  
  public void setLabelHaloFill(String value)
  {
    if(value == null)
    {
      setValue(LABELHALOFILL, "");
    }
    else
    {
      setValue(LABELHALOFILL, value);
    }
  }
  
  public boolean isLabelHaloFillWritable()
  {
    return isWritable(LABELHALOFILL);
  }
  
  public boolean isLabelHaloFillReadable()
  {
    return isReadable(LABELHALOFILL);
  }
  
  public boolean isLabelHaloFillModified()
  {
    return isModified(LABELHALOFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLabelHaloFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABELHALOFILL).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getLabelHaloOpacity()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(LABELHALOOPACITY));
  }
  
  public void setLabelHaloOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(LABELHALOOPACITY, "");
    }
    else
    {
      setValue(LABELHALOOPACITY, value.toString());
    }
  }
  
  public boolean isLabelHaloOpacityWritable()
  {
    return isWritable(LABELHALOOPACITY);
  }
  
  public boolean isLabelHaloOpacityReadable()
  {
    return isReadable(LABELHALOOPACITY);
  }
  
  public boolean isLabelHaloOpacityModified()
  {
    return isModified(LABELHALOOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getLabelHaloOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(LABELHALOOPACITY).getAttributeMdDTO();
  }
  
  public Integer getLabelHaloRadius()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LABELHALORADIUS));
  }
  
  public void setLabelHaloRadius(Integer value)
  {
    if(value == null)
    {
      setValue(LABELHALORADIUS, "");
    }
    else
    {
      setValue(LABELHALORADIUS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLabelHaloRadiusWritable()
  {
    return isWritable(LABELHALORADIUS);
  }
  
  public boolean isLabelHaloRadiusReadable()
  {
    return isReadable(LABELHALORADIUS);
  }
  
  public boolean isLabelHaloRadiusModified()
  {
    return isModified(LABELHALORADIUS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLabelHaloRadiusMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LABELHALORADIUS).getAttributeMdDTO();
  }
  
  public Integer getLabelRotation()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LABELROTATION));
  }
  
  public void setLabelRotation(Integer value)
  {
    if(value == null)
    {
      setValue(LABELROTATION, "");
    }
    else
    {
      setValue(LABELROTATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLabelRotationWritable()
  {
    return isWritable(LABELROTATION);
  }
  
  public boolean isLabelRotationReadable()
  {
    return isReadable(LABELROTATION);
  }
  
  public boolean isLabelRotationModified()
  {
    return isModified(LABELROTATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLabelRotationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LABELROTATION).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.WellKnownNamesDTO> getPointMarker()
  {
    return (java.util.List<dss.vector.solutions.query.WellKnownNamesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.query.WellKnownNamesDTO.CLASS, getEnumNames(POINTMARKER));
  }
  
  public java.util.List<String> getPointMarkerEnumNames()
  {
    return getEnumNames(POINTMARKER);
  }
  
  public void addPointMarker(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    addEnumItem(POINTMARKER, enumDTO.toString());
  }
  
  public void removePointMarker(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    removeEnumItem(POINTMARKER, enumDTO.toString());
  }
  
  public void clearPointMarker()
  {
    clearEnum(POINTMARKER);
  }
  
  public boolean isPointMarkerWritable()
  {
    return isWritable(POINTMARKER);
  }
  
  public boolean isPointMarkerReadable()
  {
    return isReadable(POINTMARKER);
  }
  
  public boolean isPointMarkerModified()
  {
    return isModified(POINTMARKER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getPointMarkerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(POINTMARKER).getAttributeMdDTO();
  }
  
  public Integer getPointRotation()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTROTATION));
  }
  
  public void setPointRotation(Integer value)
  {
    if(value == null)
    {
      setValue(POINTROTATION, "");
    }
    else
    {
      setValue(POINTROTATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointRotationWritable()
  {
    return isWritable(POINTROTATION);
  }
  
  public boolean isPointRotationReadable()
  {
    return isReadable(POINTROTATION);
  }
  
  public boolean isPointRotationModified()
  {
    return isModified(POINTROTATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPointRotationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTROTATION).getAttributeMdDTO();
  }
  
  public Integer getPointSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTSIZE));
  }
  
  public void setPointSize(Integer value)
  {
    if(value == null)
    {
      setValue(POINTSIZE, "");
    }
    else
    {
      setValue(POINTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointSizeWritable()
  {
    return isWritable(POINTSIZE);
  }
  
  public boolean isPointSizeReadable()
  {
    return isReadable(POINTSIZE);
  }
  
  public boolean isPointSizeModified()
  {
    return isModified(POINTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPointSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTSIZE).getAttributeMdDTO();
  }
  
  public String getPointStroke()
  {
    return getValue(POINTSTROKE);
  }
  
  public void setPointStroke(String value)
  {
    if(value == null)
    {
      setValue(POINTSTROKE, "");
    }
    else
    {
      setValue(POINTSTROKE, value);
    }
  }
  
  public boolean isPointStrokeWritable()
  {
    return isWritable(POINTSTROKE);
  }
  
  public boolean isPointStrokeReadable()
  {
    return isReadable(POINTSTROKE);
  }
  
  public boolean isPointStrokeModified()
  {
    return isModified(POINTSTROKE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPointStrokeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POINTSTROKE).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPointStrokeOpacity()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POINTSTROKEOPACITY));
  }
  
  public void setPointStrokeOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POINTSTROKEOPACITY, "");
    }
    else
    {
      setValue(POINTSTROKEOPACITY, value.toString());
    }
  }
  
  public boolean isPointStrokeOpacityWritable()
  {
    return isWritable(POINTSTROKEOPACITY);
  }
  
  public boolean isPointStrokeOpacityReadable()
  {
    return isReadable(POINTSTROKEOPACITY);
  }
  
  public boolean isPointStrokeOpacityModified()
  {
    return isModified(POINTSTROKEOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPointStrokeOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POINTSTROKEOPACITY).getAttributeMdDTO();
  }
  
  public Integer getPointWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POINTWIDTH));
  }
  
  public void setPointWidth(Integer value)
  {
    if(value == null)
    {
      setValue(POINTWIDTH, "");
    }
    else
    {
      setValue(POINTWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPointWidthWritable()
  {
    return isWritable(POINTWIDTH);
  }
  
  public boolean isPointWidthReadable()
  {
    return isReadable(POINTWIDTH);
  }
  
  public boolean isPointWidthModified()
  {
    return isModified(POINTWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPointWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POINTWIDTH).getAttributeMdDTO();
  }
  
  public String getPolygonFill()
  {
    return getValue(POLYGONFILL);
  }
  
  public void setPolygonFill(String value)
  {
    if(value == null)
    {
      setValue(POLYGONFILL, "");
    }
    else
    {
      setValue(POLYGONFILL, value);
    }
  }
  
  public boolean isPolygonFillWritable()
  {
    return isWritable(POLYGONFILL);
  }
  
  public boolean isPolygonFillReadable()
  {
    return isReadable(POLYGONFILL);
  }
  
  public boolean isPolygonFillModified()
  {
    return isModified(POLYGONFILL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPolygonFillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONFILL).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPolygonFillOpacity()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POLYGONFILLOPACITY));
  }
  
  public void setPolygonFillOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POLYGONFILLOPACITY, "");
    }
    else
    {
      setValue(POLYGONFILLOPACITY, value.toString());
    }
  }
  
  public boolean isPolygonFillOpacityWritable()
  {
    return isWritable(POLYGONFILLOPACITY);
  }
  
  public boolean isPolygonFillOpacityReadable()
  {
    return isReadable(POLYGONFILLOPACITY);
  }
  
  public boolean isPolygonFillOpacityModified()
  {
    return isModified(POLYGONFILLOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPolygonFillOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POLYGONFILLOPACITY).getAttributeMdDTO();
  }
  
  public String getPolygonStroke()
  {
    return getValue(POLYGONSTROKE);
  }
  
  public void setPolygonStroke(String value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKE, "");
    }
    else
    {
      setValue(POLYGONSTROKE, value);
    }
  }
  
  public boolean isPolygonStrokeWritable()
  {
    return isWritable(POLYGONSTROKE);
  }
  
  public boolean isPolygonStrokeReadable()
  {
    return isReadable(POLYGONSTROKE);
  }
  
  public boolean isPolygonStrokeModified()
  {
    return isModified(POLYGONSTROKE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPolygonStrokeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POLYGONSTROKE).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPolygonStrokeOpacity()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(POLYGONSTROKEOPACITY));
  }
  
  public void setPolygonStrokeOpacity(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(POLYGONSTROKEOPACITY, "");
    }
    else
    {
      setValue(POLYGONSTROKEOPACITY, value.toString());
    }
  }
  
  public boolean isPolygonStrokeOpacityWritable()
  {
    return isWritable(POLYGONSTROKEOPACITY);
  }
  
  public boolean isPolygonStrokeOpacityReadable()
  {
    return isReadable(POLYGONSTROKEOPACITY);
  }
  
  public boolean isPolygonStrokeOpacityModified()
  {
    return isModified(POLYGONSTROKEOPACITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPolygonStrokeOpacityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(POLYGONSTROKEOPACITY).getAttributeMdDTO();
  }
  
  public Integer getPolygonWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POLYGONWIDTH));
  }
  
  public void setPolygonWidth(Integer value)
  {
    if(value == null)
    {
      setValue(POLYGONWIDTH, "");
    }
    else
    {
      setValue(POLYGONWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPolygonWidthWritable()
  {
    return isWritable(POLYGONWIDTH);
  }
  
  public boolean isPolygonWidthReadable()
  {
    return isReadable(POLYGONWIDTH);
  }
  
  public boolean isPolygonWidthModified()
  {
    return isModified(POLYGONWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPolygonWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POLYGONWIDTH).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public Integer getSpaceAround()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPACEAROUND));
  }
  
  public void setSpaceAround(Integer value)
  {
    if(value == null)
    {
      setValue(SPACEAROUND, "");
    }
    else
    {
      setValue(SPACEAROUND, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSpaceAroundWritable()
  {
    return isWritable(SPACEAROUND);
  }
  
  public boolean isSpaceAroundReadable()
  {
    return isReadable(SPACEAROUND);
  }
  
  public boolean isSpaceAroundModified()
  {
    return isModified(SPACEAROUND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSpaceAroundMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPACEAROUND).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.StylesDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.StylesDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
  public static dss.vector.solutions.query.StylesQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.StylesQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.StylesDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.StylesDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.StylesDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.StylesDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.StylesDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.StylesDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.StylesDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
