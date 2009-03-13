package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class LarvaeDiscriminatingDoseAssayDTOBase extends csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236982476633L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssay";
  protected LarvaeDiscriminatingDoseAssayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LarvaeDiscriminatingDoseAssayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGERANGE = "ageRange";
  public csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO getAgeRange()
  {
    if(getValue(AGERANGE) == null || getValue(AGERANGE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO.get(getRequest(), getValue(AGERANGE));
    }
  }
  
  public void setAgeRange(csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeDTO value)
  {
    setValue(AGERANGE, value.getId());
  }
  
  public boolean isAgeRangeWritable()
  {
    return isWritable(AGERANGE);
  }
  
  public boolean isAgeRangeReadable()
  {
    return isReadable(AGERANGE);
  }
  
  public boolean isAgeRangeModified()
  {
    return isModified(AGERANGE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAgeRangeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("ageRange").getAttributeMdDTO();
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO) dto;
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
  
  public static csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssay", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.LarvaeDiscriminatingDoseAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
