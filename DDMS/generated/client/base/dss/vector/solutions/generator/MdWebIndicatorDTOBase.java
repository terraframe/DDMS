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
package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 336872509)
public abstract class MdWebIndicatorDTOBase extends com.runwaysdk.system.metadata.MdWebAttributeDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.MdWebIndicator";
  private static final long serialVersionUID = 336872509;
  
  protected MdWebIndicatorDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MdWebIndicatorDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DENOMINATORAGGREGATION = "denominatorAggregation";
  public static java.lang.String DENOMINATORFIELD = "denominatorField";
  public static java.lang.String NUMERATORAGGREGATION = "numeratorAggregation";
  public static java.lang.String NUMERATORFIELD = "numeratorField";
  public static java.lang.String PERCENTAGE = "percentage";
  @SuppressWarnings("unchecked")
  public java.util.List<com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO> getDenominatorAggregation()
  {
    return (java.util.List<com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO.CLASS, getEnumNames(DENOMINATORAGGREGATION));
  }
  
  public java.util.List<String> getDenominatorAggregationEnumNames()
  {
    return getEnumNames(DENOMINATORAGGREGATION);
  }
  
  public void addDenominatorAggregation(com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO enumDTO)
  {
    addEnumItem(DENOMINATORAGGREGATION, enumDTO.toString());
  }
  
  public void removeDenominatorAggregation(com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO enumDTO)
  {
    removeEnumItem(DENOMINATORAGGREGATION, enumDTO.toString());
  }
  
  public void clearDenominatorAggregation()
  {
    clearEnum(DENOMINATORAGGREGATION);
  }
  
  public boolean isDenominatorAggregationWritable()
  {
    return isWritable(DENOMINATORAGGREGATION);
  }
  
  public boolean isDenominatorAggregationReadable()
  {
    return isReadable(DENOMINATORAGGREGATION);
  }
  
  public boolean isDenominatorAggregationModified()
  {
    return isModified(DENOMINATORAGGREGATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getDenominatorAggregationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(DENOMINATORAGGREGATION).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdWebAttributeDTO getDenominatorField()
  {
    if(getValue(DENOMINATORFIELD) == null || getValue(DENOMINATORFIELD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdWebAttributeDTO.get(getRequest(), getValue(DENOMINATORFIELD));
    }
  }
  
  public String getDenominatorFieldId()
  {
    return getValue(DENOMINATORFIELD);
  }
  
  public void setDenominatorField(com.runwaysdk.system.metadata.MdWebAttributeDTO value)
  {
    if(value == null)
    {
      setValue(DENOMINATORFIELD, "");
    }
    else
    {
      setValue(DENOMINATORFIELD, value.getId());
    }
  }
  
  public boolean isDenominatorFieldWritable()
  {
    return isWritable(DENOMINATORFIELD);
  }
  
  public boolean isDenominatorFieldReadable()
  {
    return isReadable(DENOMINATORFIELD);
  }
  
  public boolean isDenominatorFieldModified()
  {
    return isModified(DENOMINATORFIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDenominatorFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DENOMINATORFIELD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO> getNumeratorAggregation()
  {
    return (java.util.List<com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO.CLASS, getEnumNames(NUMERATORAGGREGATION));
  }
  
  public java.util.List<String> getNumeratorAggregationEnumNames()
  {
    return getEnumNames(NUMERATORAGGREGATION);
  }
  
  public void addNumeratorAggregation(com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO enumDTO)
  {
    addEnumItem(NUMERATORAGGREGATION, enumDTO.toString());
  }
  
  public void removeNumeratorAggregation(com.runwaysdk.system.metadata.IndicatorAggregateFunctionDTO enumDTO)
  {
    removeEnumItem(NUMERATORAGGREGATION, enumDTO.toString());
  }
  
  public void clearNumeratorAggregation()
  {
    clearEnum(NUMERATORAGGREGATION);
  }
  
  public boolean isNumeratorAggregationWritable()
  {
    return isWritable(NUMERATORAGGREGATION);
  }
  
  public boolean isNumeratorAggregationReadable()
  {
    return isReadable(NUMERATORAGGREGATION);
  }
  
  public boolean isNumeratorAggregationModified()
  {
    return isModified(NUMERATORAGGREGATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getNumeratorAggregationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(NUMERATORAGGREGATION).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdWebAttributeDTO getNumeratorField()
  {
    if(getValue(NUMERATORFIELD) == null || getValue(NUMERATORFIELD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdWebAttributeDTO.get(getRequest(), getValue(NUMERATORFIELD));
    }
  }
  
  public String getNumeratorFieldId()
  {
    return getValue(NUMERATORFIELD);
  }
  
  public void setNumeratorField(com.runwaysdk.system.metadata.MdWebAttributeDTO value)
  {
    if(value == null)
    {
      setValue(NUMERATORFIELD, "");
    }
    else
    {
      setValue(NUMERATORFIELD, value.getId());
    }
  }
  
  public boolean isNumeratorFieldWritable()
  {
    return isWritable(NUMERATORFIELD);
  }
  
  public boolean isNumeratorFieldReadable()
  {
    return isReadable(NUMERATORFIELD);
  }
  
  public boolean isNumeratorFieldModified()
  {
    return isModified(NUMERATORFIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getNumeratorFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NUMERATORFIELD).getAttributeMdDTO();
  }
  
  public Boolean getPercentage()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PERCENTAGE));
  }
  
  public void setPercentage(Boolean value)
  {
    if(value == null)
    {
      setValue(PERCENTAGE, "");
    }
    else
    {
      setValue(PERCENTAGE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPercentageWritable()
  {
    return isWritable(PERCENTAGE);
  }
  
  public boolean isPercentageReadable()
  {
    return isReadable(PERCENTAGE);
  }
  
  public boolean isPercentageModified()
  {
    return isModified(PERCENTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPercentageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PERCENTAGE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.generator.MdWebIndicatorDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.generator.MdWebIndicatorDTO) dto;
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
  
  public static dss.vector.solutions.generator.MdWebIndicatorQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.generator.MdWebIndicatorQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.generator.MdWebIndicatorDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.generator.MdWebIndicatorDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdWebIndicatorDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.generator.MdWebIndicatorDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.generator.MdWebIndicatorDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.generator.MdWebIndicatorDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.generator.MdWebIndicatorDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
