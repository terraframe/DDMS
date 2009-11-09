package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1193533090)
public abstract class OperatorSprayViewDTOBase extends dss.vector.solutions.irs.ActorSprayViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorSprayView";
  private static final long serialVersionUID = -1193533090;
  
  protected OperatorSprayViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  public Integer getOperatorSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORSPRAYWEEK));
  }
  
  public void setOperatorSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORSPRAYWEEK, "");
    }
    else
    {
      setValue(OPERATORSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorSprayWeekWritable()
  {
    return isWritable(OPERATORSPRAYWEEK);
  }
  
  public boolean isOperatorSprayWeekReadable()
  {
    return isReadable(OPERATORSPRAYWEEK);
  }
  
  public boolean isOperatorSprayWeekModified()
  {
    return isModified(OPERATORSPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORSPRAYWEEK).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayOperatorDTO getSprayOperator()
  {
    if(getValue(SPRAYOPERATOR) == null || getValue(SPRAYOPERATOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperatorDTO.get(getRequest(), getValue(SPRAYOPERATOR));
    }
  }
  
  public void setSprayOperator(dss.vector.solutions.irs.SprayOperatorDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATOR, "");
    }
    else
    {
      setValue(SPRAYOPERATOR, value.getId());
    }
  }
  
  public boolean isSprayOperatorWritable()
  {
    return isWritable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorReadable()
  {
    return isReadable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorModified()
  {
    return isModified(SPRAYOPERATOR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayOperatorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYOPERATOR).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] getStatus()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] getStatus(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayViewDTO searchBySprayData(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethodDTO sprayMethod, dss.vector.solutions.irs.InsecticideBrandDTO brand, java.lang.String operatorId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "dss.vector.solutions.irs.SprayMethod", "dss.vector.solutions.irs.InsecticideBrand", "java.lang.String"};
    Object[] _parameters = new Object[]{geoId, sprayDate, sprayMethod, brand, operatorId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "searchBySprayData", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static OperatorSprayViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorSprayViewDTO) dto;
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
