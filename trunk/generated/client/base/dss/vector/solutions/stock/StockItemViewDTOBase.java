package dss.vector.solutions.stock;

@com.terraframe.mojo.business.ClassSignature(hash = 354086859)
public abstract class StockItemViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.StockItemView";
  private static final long serialVersionUID = 354086859;
  
  protected StockItemViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String ITEMID = "itemId";
  public static java.lang.String ITEMNAME = "itemName";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String UNIT = "unit";
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
  
  public String getItemId()
  {
    return getValue(ITEMID);
  }
  
  public void setItemId(String value)
  {
    if(value == null)
    {
      setValue(ITEMID, "");
    }
    else
    {
      setValue(ITEMID, value);
    }
  }
  
  public boolean isItemIdWritable()
  {
    return isWritable(ITEMID);
  }
  
  public boolean isItemIdReadable()
  {
    return isReadable(ITEMID);
  }
  
  public boolean isItemIdModified()
  {
    return isModified(ITEMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getItemIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ITEMID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getItemName()
  {
    if(getValue(ITEMNAME) == null || getValue(ITEMNAME).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ITEMNAME));
    }
  }
  
  public void setItemName(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ITEMNAME, "");
    }
    else
    {
      setValue(ITEMNAME, value.getId());
    }
  }
  
  public boolean isItemNameWritable()
  {
    return isWritable(ITEMNAME);
  }
  
  public boolean isItemNameReadable()
  {
    return isReadable(ITEMNAME);
  }
  
  public boolean isItemNameModified()
  {
    return isModified(ITEMNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getItemNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITEMNAME).getAttributeMdDTO();
  }
  
  public Float getQuantity()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Float value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Float.toString(value));
    }
  }
  
  public boolean isQuantityWritable()
  {
    return isWritable(QUANTITY);
  }
  
  public boolean isQuantityReadable()
  {
    return isReadable(QUANTITY);
  }
  
  public boolean isQuantityModified()
  {
    return isModified(QUANTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getQuantityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
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
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.stock.StockItemViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.stock.StockItemViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockItemViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.stock.StockItemViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.stock.StockItemViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static StockItemViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (StockItemViewDTO) dto;
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
