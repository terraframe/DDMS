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

@com.runwaysdk.business.ClassSignature(hash = -47311192)
public abstract class CycleJobDTOBase extends com.runwaysdk.system.scheduler.ExecutableJobDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.CycleJob";
  private static final long serialVersionUID = -47311192;
  
  protected CycleJobDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CycleJobDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String IMAGEHEIGHT = "imageHeight";
  public static java.lang.String IMAGEWIDTH = "imageWidth";
  public static java.lang.String JOBNAME = "jobName";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String SAVEDMAP = "savedMap";
  public Integer getImageHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEHEIGHT));
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
  
  public boolean isImageHeightWritable()
  {
    return isWritable(IMAGEHEIGHT);
  }
  
  public boolean isImageHeightReadable()
  {
    return isReadable(IMAGEHEIGHT);
  }
  
  public boolean isImageHeightModified()
  {
    return isModified(IMAGEHEIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getImageHeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(IMAGEHEIGHT).getAttributeMdDTO();
  }
  
  public Integer getImageWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMAGEWIDTH));
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
  
  public boolean isImageWidthWritable()
  {
    return isWritable(IMAGEWIDTH);
  }
  
  public boolean isImageWidthReadable()
  {
    return isReadable(IMAGEWIDTH);
  }
  
  public boolean isImageWidthModified()
  {
    return isModified(IMAGEWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getImageWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(IMAGEWIDTH).getAttributeMdDTO();
  }
  
  public String getJobName()
  {
    return getValue(JOBNAME);
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
  
  public boolean isJobNameWritable()
  {
    return isWritable(JOBNAME);
  }
  
  public boolean isJobNameReadable()
  {
    return isReadable(JOBNAME);
  }
  
  public boolean isJobNameModified()
  {
    return isModified(JOBNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getJobNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(JOBNAME).getAttributeMdDTO();
  }
  
  public String getLayerId()
  {
    return getValue(LAYERID);
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
  
  public boolean isLayerIdWritable()
  {
    return isWritable(LAYERID);
  }
  
  public boolean isLayerIdReadable()
  {
    return isReadable(LAYERID);
  }
  
  public boolean isLayerIdModified()
  {
    return isModified(LAYERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLayerIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.SavedMapDTO getSavedMap()
  {
    if(getValue(SAVEDMAP) == null || getValue(SAVEDMAP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedMapDTO.get(getRequest(), getValue(SAVEDMAP));
    }
  }
  
  public String getSavedMapId()
  {
    return getValue(SAVEDMAP);
  }
  
  public void setSavedMap(dss.vector.solutions.query.SavedMapDTO value)
  {
    if(value == null)
    {
      setValue(SAVEDMAP, "");
    }
    else
    {
      setValue(SAVEDMAP, value.getId());
    }
  }
  
  public boolean isSavedMapWritable()
  {
    return isWritable(SAVEDMAP);
  }
  
  public boolean isSavedMapReadable()
  {
    return isReadable(SAVEDMAP);
  }
  
  public boolean isSavedMapModified()
  {
    return isModified(SAVEDMAP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSavedMapMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SAVEDMAP).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.CycleJobDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.CycleJobDTO) dto;
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
  
  public static dss.vector.solutions.query.CycleJobQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.CycleJobQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.CycleJobDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.CycleJobDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.CycleJobDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.CycleJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.CycleJobDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.CycleJobDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.CycleJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
