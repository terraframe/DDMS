package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -674885199)
public abstract class CollectionContainerViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.CollectionContainerView";
  private static final long serialVersionUID = -674885199;
  
  protected CollectionContainerViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERCONTAINERS = "numberContainers";
  public static java.lang.String NUMBERDESTROYED = "numberDestroyed";
  public static java.lang.String NUMBERIMMATURES = "numberImmatures";
  public static java.lang.String NUMBERLARVAE = "numberLarvae";
  public static java.lang.String NUMBERLARVAECOLLECTED = "numberLarvaeCollected";
  public static java.lang.String NUMBERPUPAE = "numberPupae";
  public static java.lang.String NUMBERPUPAECOLLECTED = "numberPupaeCollected";
  public static java.lang.String NUMBERWITHLARVICIDE = "numberWithLarvicide";
  public static java.lang.String NUMBERWITHWATER = "numberWithWater";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TERM = "term";
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
  
  public Integer getNumberContainers()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCONTAINERS));
  }
  
  public void setNumberContainers(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCONTAINERS, "");
    }
    else
    {
      setValue(NUMBERCONTAINERS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberContainersWritable()
  {
    return isWritable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersReadable()
  {
    return isReadable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersModified()
  {
    return isModified(NUMBERCONTAINERS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberContainersMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCONTAINERS).getAttributeMdDTO();
  }
  
  public Integer getNumberDestroyed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDESTROYED));
  }
  
  public void setNumberDestroyed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDESTROYED, "");
    }
    else
    {
      setValue(NUMBERDESTROYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberDestroyedWritable()
  {
    return isWritable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedReadable()
  {
    return isReadable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedModified()
  {
    return isModified(NUMBERDESTROYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberDestroyedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERDESTROYED).getAttributeMdDTO();
  }
  
  public Integer getNumberImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERIMMATURES));
  }
  
  public void setNumberImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERIMMATURES, "");
    }
    else
    {
      setValue(NUMBERIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberImmaturesWritable()
  {
    return isWritable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesReadable()
  {
    return isReadable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesModified()
  {
    return isModified(NUMBERIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAE));
  }
  
  public void setNumberLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAE, "");
    }
    else
    {
      setValue(NUMBERLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeWritable()
  {
    return isWritable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeReadable()
  {
    return isReadable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeModified()
  {
    return isModified(NUMBERLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAECOLLECTED));
  }
  
  public void setNumberLarvaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERLARVAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeCollectedWritable()
  {
    return isWritable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedReadable()
  {
    return isReadable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedModified()
  {
    return isModified(NUMBERLARVAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAE));
  }
  
  public void setNumberPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAE, "");
    }
    else
    {
      setValue(NUMBERPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeWritable()
  {
    return isWritable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeReadable()
  {
    return isReadable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeModified()
  {
    return isModified(NUMBERPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAE).getAttributeMdDTO();
  }
  
  public Integer getNumberPupaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAECOLLECTED));
  }
  
  public void setNumberPupaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERPUPAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeCollectedWritable()
  {
    return isWritable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedReadable()
  {
    return isReadable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedModified()
  {
    return isModified(NUMBERPUPAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvicide()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVICIDE));
  }
  
  public void setNumberWithLarvicide(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVICIDE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVICIDE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvicideWritable()
  {
    return isWritable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideReadable()
  {
    return isReadable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideModified()
  {
    return isModified(NUMBERWITHLARVICIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvicideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVICIDE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithWater()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHWATER));
  }
  
  public void setNumberWithWater(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHWATER, "");
    }
    else
    {
      setValue(NUMBERWITHWATER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithWaterWritable()
  {
    return isWritable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterReadable()
  {
    return isReadable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterModified()
  {
    return isModified(NUMBERWITHWATER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithWaterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHWATER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.PremiseTaxonDTO getTaxon()
  {
    if(getValue(TAXON) == null || getValue(TAXON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.PremiseTaxonDTO.get(getRequest(), getValue(TAXON));
    }
  }
  
  public String getTaxonId()
  {
    return getValue(TAXON);
  }
  
  public void setTaxon(dss.vector.solutions.entomology.PremiseTaxonDTO value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value.getId());
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
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
  
  public static CollectionContainerViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CollectionContainerViewDTO) dto;
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
