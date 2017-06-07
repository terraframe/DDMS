package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = 1359980485)
public abstract class DashboardJobDTOBase extends com.runwaysdk.system.scheduler.ExecutableJobDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardJob";
  private static final long serialVersionUID = 1359980485;
  
  protected DashboardJobDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardJobDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DASHBOARD = "dashboard";
  public static java.lang.String IMAGEHEIGHT = "imageHeight";
  public static java.lang.String IMAGEWIDTH = "imageWidth";
  public static java.lang.String JOBNAME = "jobName";
  public static java.lang.String LAYER = "layer";
  public static java.lang.String VIEWNAME = "viewName";
  public dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO getDashboard()
  {
    if(getValue(DASHBOARD) == null || getValue(DASHBOARD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.get(getRequest(), getValue(DASHBOARD));
    }
  }
  
  public String getDashboardId()
  {
    return getValue(DASHBOARD);
  }
  
  public void setDashboard(dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO value)
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
  
  public boolean isDashboardWritable()
  {
    return isWritable(DASHBOARD);
  }
  
  public boolean isDashboardReadable()
  {
    return isReadable(DASHBOARD);
  }
  
  public boolean isDashboardModified()
  {
    return isModified(DASHBOARD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDashboardMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DASHBOARD).getAttributeMdDTO();
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
  
  public dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO getLayer()
  {
    if(getValue(LAYER) == null || getValue(LAYER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.get(getRequest(), getValue(LAYER));
    }
  }
  
  public String getLayerId()
  {
    return getValue(LAYER);
  }
  
  public void setLayer(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO value)
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
  
  public boolean isLayerWritable()
  {
    return isWritable(LAYER);
  }
  
  public boolean isLayerReadable()
  {
    return isReadable(LAYER);
  }
  
  public boolean isLayerModified()
  {
    return isModified(LAYER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLayerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LAYER).getAttributeMdDTO();
  }
  
  public String getViewName()
  {
    return getValue(VIEWNAME);
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
  
  public boolean isViewNameWritable()
  {
    return isWritable(VIEWNAME);
  }
  
  public boolean isViewNameReadable()
  {
    return isReadable(VIEWNAME);
  }
  
  public boolean isViewNameModified()
  {
    return isModified(VIEWNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getViewNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VIEWNAME).getAttributeMdDTO();
  }
  
  public static final void applyJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String json)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{json};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO.CLASS, "applyJSON", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO.CLASS, "getJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardJobQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardJobQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
