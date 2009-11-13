package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -946928174)
public abstract class LarvacideInstanceViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.LarvacideInstanceView";
  private static final long serialVersionUID = -946928174;
  
  protected LarvacideInstanceViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CONTROLID = "controlId";
  public static java.lang.String CONTROLMETHOD = "controlMethod";
  public static java.lang.String ID = "id";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String UNIT = "unit";
  public static java.lang.String UNITSUSED = "unitsUsed";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public String getControlId()
  {
    return getValue(CONTROLID);
  }
  
  public void setControlId(String value)
  {
    if(value == null)
    {
      setValue(CONTROLID, "");
    }
    else
    {
      setValue(CONTROLID, value);
    }
  }
  
  public boolean isControlIdWritable()
  {
    return isWritable(CONTROLID);
  }
  
  public boolean isControlIdReadable()
  {
    return isReadable(CONTROLID);
  }
  
  public boolean isControlIdModified()
  {
    return isModified(CONTROLID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getControlIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTROLID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getControlMethod()
  {
    if(getValue(CONTROLMETHOD) == null || getValue(CONTROLMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CONTROLMETHOD));
    }
  }
  
  public void setControlMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CONTROLMETHOD, "");
    }
    else
    {
      setValue(CONTROLMETHOD, value.getId());
    }
  }
  
  public boolean isControlMethodWritable()
  {
    return isWritable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodReadable()
  {
    return isReadable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodModified()
  {
    return isModified(CONTROLMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getControlMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CONTROLMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTarget()
  {
    if(getValue(TARGET) == null || getValue(TARGET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TARGET));
    }
  }
  
  public void setTarget(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value.getId());
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public Integer getTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTreatedWritable()
  {
    return isWritable(TREATED);
  }
  
  public boolean isTreatedReadable()
  {
    return isReadable(TREATED);
  }
  
  public boolean isTreatedModified()
  {
    return isModified(TREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUnit()
  {
    if(getValue(UNIT) == null || getValue(UNIT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(UNIT));
    }
  }
  
  public void setUnit(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value.getId());
    }
  }
  
  public boolean isUnitWritable()
  {
    return isWritable(UNIT);
  }
  
  public boolean isUnitReadable()
  {
    return isReadable(UNIT);
  }
  
  public boolean isUnitModified()
  {
    return isModified(UNIT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getUnitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(UNIT).getAttributeMdDTO();
  }
  
  public Integer getUnitsUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSUSED));
  }
  
  public void setUnitsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSUSED, "");
    }
    else
    {
      setValue(UNITSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsUsedWritable()
  {
    return isWritable(UNITSUSED);
  }
  
  public boolean isUnitsUsedReadable()
  {
    return isReadable(UNITSUSED);
  }
  
  public boolean isUnitsUsedModified()
  {
    return isModified(UNITSUSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUnitsUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSUSED).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.LarvacideInstanceView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static LarvacideInstanceViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (LarvacideInstanceViewDTO) dto;
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
