package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1815553234)
public abstract class CycleJobViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.CycleJobView";
  private static final long serialVersionUID = 1815553234;
  
  protected CycleJobViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CREATEJOB = "createJob";
  public static java.lang.String ID = "id";
  public static java.lang.String IMAGEHEIGHT = "imageHeight";
  public static java.lang.String IMAGEWIDTH = "imageWidth";
  public static java.lang.String JOBNAME = "jobName";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String SAVEDMAP = "savedMap";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public Boolean getCreateJob()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CREATEJOB));
  }
  
  public void setCreateJob(Boolean value)
  {
    if(value == null)
    {
      setValue(CREATEJOB, "");
    }
    else
    {
      setValue(CREATEJOB, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isCreateJobWritable()
  {
    return isWritable(CREATEJOB);
  }
  
  public boolean isCreateJobReadable()
  {
    return isReadable(CREATEJOB);
  }
  
  public boolean isCreateJobModified()
  {
    return isModified(CREATEJOB);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getCreateJobMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CREATEJOB).getAttributeMdDTO();
  }
  
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
  
  public static CycleJobViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CycleJobViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
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
  
}
