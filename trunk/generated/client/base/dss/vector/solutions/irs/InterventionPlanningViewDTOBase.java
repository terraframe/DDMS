package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -642658016)
public abstract class InterventionPlanningViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InterventionPlanningView";
  private static final long serialVersionUID = -642658016;
  
  protected InterventionPlanningViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String SEASON = "season";
  public static java.lang.String TARGETS = "targets";
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.MalariaSeasonDTO getSeason()
  {
    if(getValue(SEASON) == null || getValue(SEASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.MalariaSeasonDTO.get(getRequest(), getValue(SEASON));
    }
  }
  
  public void setSeason(dss.vector.solutions.general.MalariaSeasonDTO value)
  {
    if(value == null)
    {
      setValue(SEASON, "");
    }
    else
    {
      setValue(SEASON, value.getId());
    }
  }
  
  public boolean isSeasonWritable()
  {
    return isWritable(SEASON);
  }
  
  public boolean isSeasonReadable()
  {
    return isReadable(SEASON);
  }
  
  public boolean isSeasonModified()
  {
    return isModified(SEASON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSeasonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEASON).getAttributeMdDTO();
  }
  
  public Integer getTargets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGETS));
  }
  
  public void setTargets(Integer value)
  {
    if(value == null)
    {
      setValue(TARGETS, "");
    }
    else
    {
      setValue(TARGETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetsWritable()
  {
    return isWritable(TARGETS);
  }
  
  public boolean isTargetsReadable()
  {
    return isReadable(TARGETS);
  }
  
  public boolean isTargetsModified()
  {
    return isModified(TARGETS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTargetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGETS).getAttributeMdDTO();
  }
  
  public static InterventionPlanningViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (InterventionPlanningViewDTO) dto;
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
