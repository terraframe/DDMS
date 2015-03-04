package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 23502665)
public abstract class MdFormUtilDTOBase extends com.runwaysdk.business.UtilDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.MdFormUtil";
  private static final long serialVersionUID = 23502665;
  
  protected MdFormUtilDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static final void addToGroup(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String groupId, java.lang.String fieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{groupId, fieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "addToGroup", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO apply(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO mdForm)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm"};
    Object[] _parameters = new Object[]{mdForm};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "apply", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO clone(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO mdForm, com.runwaysdk.system.metadata.MdClassDTO mdClass, com.runwaysdk.system.metadata.MdWebFieldDTO[] mdFields, com.runwaysdk.system.metadata.MdAttributeConcreteDTO[] mdAttrs, java.lang.String oldMdForm)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm", "com.runwaysdk.system.metadata.MdClass", "[Lcom.runwaysdk.system.metadata.MdWebField;", "[Lcom.runwaysdk.system.metadata.MdAttributeConcrete;", "java.lang.String"};
    Object[] _parameters = new Object[]{mdForm, mdClass, mdFields, mdAttrs, oldMdForm};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "clone", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void confirmDeleteCompositeField(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdCompositeFieldId, java.lang.String mdFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{mdCompositeFieldId, mdFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "confirmDeleteCompositeField", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void confirmDeleteForm(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFormId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdFormId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "confirmDeleteForm", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void confirmDeleteMdField(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFormId, java.lang.String mdFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{mdFormId, mdFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "confirmDeleteMdField", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void createCondition(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFieldId, com.runwaysdk.system.metadata.FieldConditionDTO condition)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "com.runwaysdk.system.metadata.FieldCondition"};
    Object[] _parameters = new Object[]{mdFieldId, condition};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "createCondition", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebPrimitiveDTO createFieldForComposite(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebPrimitiveDTO mdField, java.lang.String mdCompositeFieldId)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebPrimitive", "java.lang.String"};
    Object[] _parameters = new Object[]{mdField, mdCompositeFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "createFieldForComposite", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebPrimitiveDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebGeoDTO createGeoField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebGeoDTO mdField, java.lang.String mdFormId, dss.vector.solutions.geo.GeoFieldDTO geoField, java.lang.String[] extraUniversals)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebGeo", "java.lang.String", "dss.vector.solutions.geo.GeoField", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{mdField, mdFormId, geoField, extraUniversals};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "createGeoField", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebGeoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdFieldDTO createMdField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdFieldDTO mdField, java.lang.String mdFormId)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdField", "java.lang.String"};
    Object[] _parameters = new Object[]{mdField, mdFormId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "createMdField", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdFieldDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdAttributeConcreteDTO[] definesAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdRelationshipDTO mdRelationship)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdRelationship"};
    Object[] _parameters = new Object[]{mdRelationship};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "definesAttributes", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdAttributeConcreteDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void delete(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO mdForm)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm"};
    Object[] _parameters = new Object[]{mdForm};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "delete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteAll(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.business.BusinessDTO criteria, java.lang.String type)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.business.Business", "java.lang.String"};
    Object[] _parameters = new Object[]{criteria, type};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "deleteAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteCompositeField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebPrimitiveDTO mdField)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebPrimitive"};
    Object[] _parameters = new Object[]{mdField};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "deleteCompositeField", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteCondition(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFieldId, java.lang.String conditionId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{mdFieldId, conditionId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "deleteCondition", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO mdForm, com.runwaysdk.system.metadata.MdWebFieldDTO mdField)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm", "com.runwaysdk.system.metadata.MdWebField"};
    Object[] _parameters = new Object[]{mdForm, mdField};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "deleteField", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteGroup(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String groupId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{groupId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "deleteGroup", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream excelExport(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String type)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{type};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "excelExport", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream excelImport(com.runwaysdk.constants.ClientRequestIF clientRequest, java.io.InputStream stream, java.lang.String type)
  {
    String[] _declaredTypes = new String[]{"java.io.InputStream", "java.lang.String"};
    Object[] _parameters = new Object[]{stream, type};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "excelImport", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportDefinition(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFormId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdFormId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "exportDefinition", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFieldDTO[] getAllFields(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO form)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm"};
    Object[] _parameters = new Object[]{form};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getAllFields", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO[] getAllForms(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getAllForms", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.form.MdFieldTypeQueryDTO getAvailableCompositeFields(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getAvailableCompositeFields", _declaredTypes);
    return (dss.vector.solutions.form.MdFieldTypeQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.form.MdFieldTypeQueryDTO getAvailableFields(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getAvailableFields", _declaredTypes);
    return (dss.vector.solutions.form.MdFieldTypeQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebPrimitiveDTO[] getCompositeFields(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String compositeFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{compositeFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getCompositeFields", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebPrimitiveDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.FieldConditionDTO[] getConditions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getConditions", _declaredTypes);
    return (com.runwaysdk.system.metadata.FieldConditionDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFieldDTO[] getFields(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFormDTO form)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebForm"};
    Object[] _parameters = new Object[]{form};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFields", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFieldDTO[] getFieldsById(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFieldsById", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getFieldsForComposite(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String compositeFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{compositeFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFieldsForComposite", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdFieldDTO[] getFieldsForConditions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdFieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFieldsForConditions", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFormDTO getForm(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String type)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{type};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getForm", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFormDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getFormByKey(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String formKey)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{formKey};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFormByKey", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getFormTree(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mdFormId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdFormId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getFormTree", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebFieldDTO[] getGroupFields(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String groupId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{groupId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getGroupFields", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebFieldDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdBusinessDTO getMdBusinessByType(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String bizType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{bizType};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getMdBusinessByType", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdBusinessDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdRelationshipDTO getMdRelationship(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebFieldDTO field)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebField"};
    Object[] _parameters = new Object[]{field};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getMdRelationship", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdRelationshipDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getTermsForMultiTermField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebMultipleTermDTO mdWebMultipleTerm, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebMultipleTerm", "java.lang.String"};
    Object[] _parameters = new Object[]{mdWebMultipleTerm, parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "getTermsForMultiTermField", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void importDefinition(com.runwaysdk.constants.ClientRequestIF clientRequest, java.io.InputStream definition)
  {
    String[] _declaredTypes = new String[]{"java.io.InputStream"};
    Object[] _parameters = new Object[]{definition};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "importDefinition", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void isAvailable(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String type, java.lang.String oid)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{type, oid};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "isAvailable", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.BusinessDTO persistObject(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.business.BusinessDTO busObj, java.lang.String multipleTermJSON, java.lang.String singleTermGridJSON)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.business.Business", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{busObj, multipleTermJSON, singleTermGridJSON};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "persistObject", _declaredTypes);
    return (com.runwaysdk.business.BusinessDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void removeFromGroup(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String groupId, java.lang.String fieldId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{groupId, fieldId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "removeFromGroup", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void reorderFields(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] ids)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{ids};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "reorderFields", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ComponentQueryDTO searchObject(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.business.BusinessDTO criteria, java.lang.String type, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.business.Business", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{criteria, type, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "searchObject", _declaredTypes);
    return (com.runwaysdk.business.ComponentQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebPrimitiveDTO updateFieldForComposite(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebPrimitiveDTO mdField)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebPrimitive"};
    Object[] _parameters = new Object[]{mdField};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "updateFieldForComposite", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebPrimitiveDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdWebGeoDTO updateGeoField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdWebGeoDTO mdField, dss.vector.solutions.geo.GeoFieldDTO geoField, java.lang.String[] extraUniversals)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdWebGeo", "dss.vector.solutions.geo.GeoField", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{mdField, geoField, extraUniversals};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "updateGeoField", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdWebGeoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.system.metadata.MdFieldDTO updateMdField(com.runwaysdk.constants.ClientRequestIF clientRequest, com.runwaysdk.system.metadata.MdFieldDTO mdField)
  {
    String[] _declaredTypes = new String[]{"com.runwaysdk.system.metadata.MdField"};
    Object[] _parameters = new Object[]{mdField};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdFormUtilDTO.CLASS, "updateMdField", _declaredTypes);
    return (com.runwaysdk.system.metadata.MdFieldDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
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
