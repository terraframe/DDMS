package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = -446130801)
public abstract class MdFormUtilDTOBase extends com.runwaysdk.business.UtilDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.MdFormUtil";
  private static final long serialVersionUID = -446130801;
  
  protected MdFormUtilDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static final com.runwaysdk.system.metadata.MdAttributeConcreteDTO[] definesAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdRelationshipDTO mdRelationship)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdRelationship"};
    Object[] _parameters = new Object[]{mdRelationship};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "definesAttributes", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdAttributeConcreteDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO[] getAllForms(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getAllForms", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFieldDTO[] getFields(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO form)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm"};
    Object[] _parameters = new Object[]{form};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFields", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO getForm(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String type)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{type};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getForm", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdRelationshipDTO getMdRelationship(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFieldDTO field)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebField"};
    Object[] _parameters = new Object[]{field};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getMdRelationship", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdRelationshipDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MdFormUtilDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.UtilDTO dto = (com.runwaysdk.business.UtilDTO)clientRequest.get(id);
    
    return (MdFormUtilDTO) dto;
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
