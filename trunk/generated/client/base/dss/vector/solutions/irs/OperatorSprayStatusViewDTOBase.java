package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -2020136058)
public abstract class OperatorSprayStatusViewDTOBase extends dss.vector.solutions.irs.ActorSprayStatusViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorSprayStatusView";
  private static final long serialVersionUID = -2020136058;
  
  protected OperatorSprayStatusViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OPERATORLABEL = "operatorLabel";
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  public String getOperatorLabel()
  {
    return getValue(OPERATORLABEL);
  }
  
  public void setOperatorLabel(String value)
  {
    if(value == null)
    {
      setValue(OPERATORLABEL, "");
    }
    else
    {
      setValue(OPERATORLABEL, value);
    }
  }
  
  public boolean isOperatorLabelWritable()
  {
    return isWritable(OPERATORLABEL);
  }
  
  public boolean isOperatorLabelReadable()
  {
    return isReadable(OPERATORLABEL);
  }
  
  public boolean isOperatorLabelModified()
  {
    return isModified(OPERATORLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOperatorLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORLABEL).getAttributeMdDTO();
  }
  
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
  
  public static final dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.OperatorSprayStatusView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayStatusViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static OperatorSprayStatusViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorSprayStatusViewDTO) dto;
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
