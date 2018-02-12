/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = 1262163271)
public abstract class StockEventViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.StockEventView";
  private static final long serialVersionUID = 1262163271;
  
  protected StockEventViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AVAILABLESTOCK = "availableStock";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String COST = "cost";
  public static java.lang.String EVENTDATE = "eventDate";
  public static java.lang.String ID = "id";
  public static java.lang.String ITEM = "item";
  public static java.lang.String ITEMLABEL = "itemLabel";
  public static java.lang.String OTHERPARTY = "otherParty";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String STAFF = "staff";
  public static java.lang.String STAFFLABEL = "staffLabel";
  public static java.lang.String STOCKDEPOT = "stockDepot";
  public static java.lang.String TRANSACTIONTYPE = "transactionType";
  public Integer getAvailableStock()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AVAILABLESTOCK));
  }
  
  public void setAvailableStock(Integer value)
  {
    if(value == null)
    {
      setValue(AVAILABLESTOCK, "");
    }
    else
    {
      setValue(AVAILABLESTOCK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAvailableStockWritable()
  {
    return isWritable(AVAILABLESTOCK);
  }
  
  public boolean isAvailableStockReadable()
  {
    return isReadable(AVAILABLESTOCK);
  }
  
  public boolean isAvailableStockModified()
  {
    return isModified(AVAILABLESTOCK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAvailableStockMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AVAILABLESTOCK).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getCost()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(COST));
  }
  
  public void setCost(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(COST, "");
    }
    else
    {
      setValue(COST, value.toString());
    }
  }
  
  public boolean isCostWritable()
  {
    return isWritable(COST);
  }
  
  public boolean isCostReadable()
  {
    return isReadable(COST);
  }
  
  public boolean isCostModified()
  {
    return isModified(COST);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getCostMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(COST).getAttributeMdDTO();
  }
  
  public java.util.Date getEventDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(EVENTDATE));
  }
  
  public void setEventDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(EVENTDATE, "");
    }
    else
    {
      setValue(EVENTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEventDateWritable()
  {
    return isWritable(EVENTDATE);
  }
  
  public boolean isEventDateReadable()
  {
    return isReadable(EVENTDATE);
  }
  
  public boolean isEventDateModified()
  {
    return isModified(EVENTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEventDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(EVENTDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.stock.StockItemDTO getItem()
  {
    if(getValue(ITEM) == null || getValue(ITEM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockItemDTO.get(getRequest(), getValue(ITEM));
    }
  }
  
  public String getItemId()
  {
    return getValue(ITEM);
  }
  
  public void setItem(dss.vector.solutions.stock.StockItemDTO value)
  {
    if(value == null)
    {
      setValue(ITEM, "");
    }
    else
    {
      setValue(ITEM, value.getId());
    }
  }
  
  public boolean isItemWritable()
  {
    return isWritable(ITEM);
  }
  
  public boolean isItemReadable()
  {
    return isReadable(ITEM);
  }
  
  public boolean isItemModified()
  {
    return isModified(ITEM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getItemMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITEM).getAttributeMdDTO();
  }
  
  public String getItemLabel()
  {
    return getValue(ITEMLABEL);
  }
  
  public void setItemLabel(String value)
  {
    if(value == null)
    {
      setValue(ITEMLABEL, "");
    }
    else
    {
      setValue(ITEMLABEL, value);
    }
  }
  
  public boolean isItemLabelWritable()
  {
    return isWritable(ITEMLABEL);
  }
  
  public boolean isItemLabelReadable()
  {
    return isReadable(ITEMLABEL);
  }
  
  public boolean isItemLabelModified()
  {
    return isModified(ITEMLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getItemLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ITEMLABEL).getAttributeMdDTO();
  }
  
  public String getOtherParty()
  {
    return getValue(OTHERPARTY);
  }
  
  public void setOtherParty(String value)
  {
    if(value == null)
    {
      setValue(OTHERPARTY, "");
    }
    else
    {
      setValue(OTHERPARTY, value);
    }
  }
  
  public boolean isOtherPartyWritable()
  {
    return isWritable(OTHERPARTY);
  }
  
  public boolean isOtherPartyReadable()
  {
    return isReadable(OTHERPARTY);
  }
  
  public boolean isOtherPartyModified()
  {
    return isModified(OTHERPARTY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOtherPartyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OTHERPARTY).getAttributeMdDTO();
  }
  
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.stock.StockStaffDTO getStaff()
  {
    if(getValue(STAFF) == null || getValue(STAFF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockStaffDTO.get(getRequest(), getValue(STAFF));
    }
  }
  
  public String getStaffId()
  {
    return getValue(STAFF);
  }
  
  public void setStaff(dss.vector.solutions.stock.StockStaffDTO value)
  {
    if(value == null)
    {
      setValue(STAFF, "");
    }
    else
    {
      setValue(STAFF, value.getId());
    }
  }
  
  public boolean isStaffWritable()
  {
    return isWritable(STAFF);
  }
  
  public boolean isStaffReadable()
  {
    return isReadable(STAFF);
  }
  
  public boolean isStaffModified()
  {
    return isModified(STAFF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStaffMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STAFF).getAttributeMdDTO();
  }
  
  public String getStaffLabel()
  {
    return getValue(STAFFLABEL);
  }
  
  public void setStaffLabel(String value)
  {
    if(value == null)
    {
      setValue(STAFFLABEL, "");
    }
    else
    {
      setValue(STAFFLABEL, value);
    }
  }
  
  public boolean isStaffLabelWritable()
  {
    return isWritable(STAFFLABEL);
  }
  
  public boolean isStaffLabelReadable()
  {
    return isReadable(STAFFLABEL);
  }
  
  public boolean isStaffLabelModified()
  {
    return isModified(STAFFLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getStaffLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(STAFFLABEL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getStockDepot()
  {
    if(getValue(STOCKDEPOT) == null || getValue(STOCKDEPOT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(STOCKDEPOT));
    }
  }
  
  public String getStockDepotId()
  {
    return getValue(STOCKDEPOT);
  }
  
  public void setStockDepot(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(STOCKDEPOT, "");
    }
    else
    {
      setValue(STOCKDEPOT, value.getId());
    }
  }
  
  public boolean isStockDepotWritable()
  {
    return isWritable(STOCKDEPOT);
  }
  
  public boolean isStockDepotReadable()
  {
    return isReadable(STOCKDEPOT);
  }
  
  public boolean isStockDepotModified()
  {
    return isModified(STOCKDEPOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStockDepotMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKDEPOT).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.stock.EventOptionDTO> getTransactionType()
  {
    return (java.util.List<dss.vector.solutions.stock.EventOptionDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.stock.EventOptionDTO.CLASS, getEnumNames(TRANSACTIONTYPE));
  }
  
  public java.util.List<String> getTransactionTypeEnumNames()
  {
    return getEnumNames(TRANSACTIONTYPE);
  }
  
  public void addTransactionType(dss.vector.solutions.stock.EventOptionDTO enumDTO)
  {
    addEnumItem(TRANSACTIONTYPE, enumDTO.toString());
  }
  
  public void removeTransactionType(dss.vector.solutions.stock.EventOptionDTO enumDTO)
  {
    removeEnumItem(TRANSACTIONTYPE, enumDTO.toString());
  }
  
  public void clearTransactionType()
  {
    clearEnum(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeWritable()
  {
    return isWritable(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeReadable()
  {
    return isReadable(TRANSACTIONTYPE);
  }
  
  public boolean isTransactionTypeModified()
  {
    return isModified(TRANSACTIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getTransactionTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(TRANSACTIONTYPE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.stock.StockEventViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.stock.StockEventViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.stock.StockEventView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.lang.String item, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{geoId, item, startDate, endDate};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "deleteAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockEventViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber, java.lang.String geoId, java.lang.String item, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String", "java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber, geoId, item, startDate, endDate};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.stock.StockEventViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.ontology.TermDTO item, java.util.Date date, dss.vector.solutions.stock.EventOptionDTO transactionType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.ontology.Term", "java.util.Date", "dss.vector.solutions.stock.EventOption"};
    Object[] _parameters = new Object[]{geoId, item, date, transactionType};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.stock.StockEventViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.stock.StockEventViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static StockEventViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (StockEventViewDTO) dto;
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
