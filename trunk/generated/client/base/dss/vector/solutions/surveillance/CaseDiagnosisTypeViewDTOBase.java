package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 1035646085)
public abstract class CaseDiagnosisTypeViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseDiagnosisTypeView";
  private static final long serialVersionUID = 1035646085;
  
  protected CaseDiagnosisTypeViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGGREGATEDCASE = "aggregatedCase";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DIAGNOSISCATEGORY = "diagnosisCategory";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  public dss.vector.solutions.surveillance.AggregatedCaseDTO getAggregatedCase()
  {
    if(getValue(AGGREGATEDCASE) == null || getValue(AGGREGATEDCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedCaseDTO.get(getRequest(), getValue(AGGREGATEDCASE));
    }
  }
  
  public String getAggregatedCaseId()
  {
    return getValue(AGGREGATEDCASE);
  }
  
  public void setAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO value)
  {
    if(value == null)
    {
      setValue(AGGREGATEDCASE, "");
    }
    else
    {
      setValue(AGGREGATEDCASE, value.getId());
    }
  }
  
  public boolean isAggregatedCaseWritable()
  {
    return isWritable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseReadable()
  {
    return isReadable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseModified()
  {
    return isModified(AGGREGATEDCASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAggregatedCaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGGREGATEDCASE).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.ontology.TermDTO getDiagnosisCategory()
  {
    if(getValue(DIAGNOSISCATEGORY) == null || getValue(DIAGNOSISCATEGORY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DIAGNOSISCATEGORY));
    }
  }
  
  public String getDiagnosisCategoryId()
  {
    return getValue(DIAGNOSISCATEGORY);
  }
  
  public void setDiagnosisCategory(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISCATEGORY, "");
    }
    else
    {
      setValue(DIAGNOSISCATEGORY, value.getId());
    }
  }
  
  public boolean isDiagnosisCategoryWritable()
  {
    return isWritable(DIAGNOSISCATEGORY);
  }
  
  public boolean isDiagnosisCategoryReadable()
  {
    return isReadable(DIAGNOSISCATEGORY);
  }
  
  public boolean isDiagnosisCategoryModified()
  {
    return isModified(DIAGNOSISCATEGORY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiagnosisCategoryMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DIAGNOSISCATEGORY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTerm()
  {
    if(getValue(TERM) == null || getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TERM));
    }
  }
  
  public String getTermId()
  {
    return getValue(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public boolean isTermWritable()
  {
    return isWritable(TERM);
  }
  
  public boolean isTermReadable()
  {
    return isReadable(TERM);
  }
  
  public boolean isTermModified()
  {
    return isModified(TERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TERM).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[] getAmounts()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO.CLASS, "getAmounts", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[] getAmounts(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO.CLASS, "getAmounts", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[][] getAmountsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] ids)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{ids};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiagnosisTypeViewDTO.CLASS, "getAmountsForViews", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static CaseDiagnosisTypeViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CaseDiagnosisTypeViewDTO) dto;
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
