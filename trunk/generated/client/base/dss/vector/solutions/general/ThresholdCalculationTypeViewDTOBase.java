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
package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 648656042)
public abstract class ThresholdCalculationTypeViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdCalculationTypeView";
  private static final long serialVersionUID = 648656042;
  
  protected ThresholdCalculationTypeViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CALCULATIONINTERVAL = "calculationInterval";
  public static java.lang.String CASETYPES = "caseTypes";
  public static java.lang.String CLINICALPOSITIVEPERCENTAGE = "clinicalPositivePercentage";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String COUNTINGMETHOD = "countingMethod";
  public static java.lang.String EPIDEMICUNIVERSAL = "epidemicUniversal";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMINIMUM = "identificationMinimum";
  public static java.lang.String NOTIFICATIONMINIMUM = "notificationMinimum";
  public static java.lang.String PRIORYEARS = "priorYears";
  public static java.lang.String T1METHOD = "t1Method";
  public static java.lang.String T2METHOD = "t2Method";
  public static java.lang.String WEEKSAFTER = "weeksAfter";
  public static java.lang.String WEEKSBEFORE = "weeksBefore";
  public static java.lang.String WEIGHT0 = "weight0";
  public static java.lang.String WEIGHT1 = "weight1";
  public static java.lang.String WEIGHT2 = "weight2";
  public static java.lang.String WEIGHT3 = "weight3";
  public static java.lang.String WEIGHT4 = "weight4";
  public static java.lang.String WEIGHT5 = "weight5";
  public static java.lang.String WEIGHT6 = "weight6";
  public static java.lang.String WEIGHT7 = "weight7";
  public static java.lang.String WEIGHT8 = "weight8";
  public static java.lang.String WEIGHT9 = "weight9";
  public Boolean getCalculationInterval()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CALCULATIONINTERVAL));
  }
  
  public void setCalculationInterval(Boolean value)
  {
    if(value == null)
    {
      setValue(CALCULATIONINTERVAL, "");
    }
    else
    {
      setValue(CALCULATIONINTERVAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isCalculationIntervalWritable()
  {
    return isWritable(CALCULATIONINTERVAL);
  }
  
  public boolean isCalculationIntervalReadable()
  {
    return isReadable(CALCULATIONINTERVAL);
  }
  
  public boolean isCalculationIntervalModified()
  {
    return isModified(CALCULATIONINTERVAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getCalculationIntervalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CALCULATIONINTERVAL).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO> getCaseTypes()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO.CLASS, getEnumNames(CASETYPES));
  }
  
  public java.util.List<String> getCaseTypesEnumNames()
  {
    return getEnumNames(CASETYPES);
  }
  
  public void addCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO enumDTO)
  {
    addEnumItem(CASETYPES, enumDTO.toString());
  }
  
  public void removeCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO enumDTO)
  {
    removeEnumItem(CASETYPES, enumDTO.toString());
  }
  
  public void clearCaseTypes()
  {
    clearEnum(CASETYPES);
  }
  
  public boolean isCaseTypesWritable()
  {
    return isWritable(CASETYPES);
  }
  
  public boolean isCaseTypesReadable()
  {
    return isReadable(CASETYPES);
  }
  
  public boolean isCaseTypesModified()
  {
    return isModified(CASETYPES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getCaseTypesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(CASETYPES).getAttributeMdDTO();
  }
  
  public Integer getClinicalPositivePercentage()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALPOSITIVEPERCENTAGE));
  }
  
  public void setClinicalPositivePercentage(Integer value)
  {
    if(value == null)
    {
      setValue(CLINICALPOSITIVEPERCENTAGE, "");
    }
    else
    {
      setValue(CLINICALPOSITIVEPERCENTAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isClinicalPositivePercentageWritable()
  {
    return isWritable(CLINICALPOSITIVEPERCENTAGE);
  }
  
  public boolean isClinicalPositivePercentageReadable()
  {
    return isReadable(CLINICALPOSITIVEPERCENTAGE);
  }
  
  public boolean isClinicalPositivePercentageModified()
  {
    return isModified(CLINICALPOSITIVEPERCENTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getClinicalPositivePercentageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CLINICALPOSITIVEPERCENTAGE).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.OutbreakCalculationDTO> getCountingMethod()
  {
    return (java.util.List<dss.vector.solutions.general.OutbreakCalculationDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.OutbreakCalculationDTO.CLASS, getEnumNames(COUNTINGMETHOD));
  }
  
  public java.util.List<String> getCountingMethodEnumNames()
  {
    return getEnumNames(COUNTINGMETHOD);
  }
  
  public void addCountingMethod(dss.vector.solutions.general.OutbreakCalculationDTO enumDTO)
  {
    addEnumItem(COUNTINGMETHOD, enumDTO.toString());
  }
  
  public void removeCountingMethod(dss.vector.solutions.general.OutbreakCalculationDTO enumDTO)
  {
    removeEnumItem(COUNTINGMETHOD, enumDTO.toString());
  }
  
  public void clearCountingMethod()
  {
    clearEnum(COUNTINGMETHOD);
  }
  
  public boolean isCountingMethodWritable()
  {
    return isWritable(COUNTINGMETHOD);
  }
  
  public boolean isCountingMethodReadable()
  {
    return isReadable(COUNTINGMETHOD);
  }
  
  public boolean isCountingMethodModified()
  {
    return isModified(COUNTINGMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getCountingMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(COUNTINGMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.GeoHierarchyDTO getEpidemicUniversal()
  {
    if(getValue(EPIDEMICUNIVERSAL) == null || getValue(EPIDEMICUNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(EPIDEMICUNIVERSAL));
    }
  }
  
  public String getEpidemicUniversalId()
  {
    return getValue(EPIDEMICUNIVERSAL);
  }
  
  public void setEpidemicUniversal(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(EPIDEMICUNIVERSAL, "");
    }
    else
    {
      setValue(EPIDEMICUNIVERSAL, value.getId());
    }
  }
  
  public boolean isEpidemicUniversalWritable()
  {
    return isWritable(EPIDEMICUNIVERSAL);
  }
  
  public boolean isEpidemicUniversalReadable()
  {
    return isReadable(EPIDEMICUNIVERSAL);
  }
  
  public boolean isEpidemicUniversalModified()
  {
    return isModified(EPIDEMICUNIVERSAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEpidemicUniversalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(EPIDEMICUNIVERSAL).getAttributeMdDTO();
  }
  
  public Double getIdentificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATIONMINIMUM));
  }
  
  public void setIdentificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(IDENTIFICATIONMINIMUM, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentificationMinimumWritable()
  {
    return isWritable(IDENTIFICATIONMINIMUM);
  }
  
  public boolean isIdentificationMinimumReadable()
  {
    return isReadable(IDENTIFICATIONMINIMUM);
  }
  
  public boolean isIdentificationMinimumModified()
  {
    return isModified(IDENTIFICATIONMINIMUM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentificationMinimumMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATIONMINIMUM).getAttributeMdDTO();
  }
  
  public Double getNotificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(NOTIFICATIONMINIMUM));
  }
  
  public void setNotificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(NOTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(NOTIFICATIONMINIMUM, java.lang.Double.toString(value));
    }
  }
  
  public boolean isNotificationMinimumWritable()
  {
    return isWritable(NOTIFICATIONMINIMUM);
  }
  
  public boolean isNotificationMinimumReadable()
  {
    return isReadable(NOTIFICATIONMINIMUM);
  }
  
  public boolean isNotificationMinimumModified()
  {
    return isModified(NOTIFICATIONMINIMUM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getNotificationMinimumMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(NOTIFICATIONMINIMUM).getAttributeMdDTO();
  }
  
  public Integer getPriorYears()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORYEARS));
  }
  
  public void setPriorYears(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORYEARS, "");
    }
    else
    {
      setValue(PRIORYEARS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPriorYearsWritable()
  {
    return isWritable(PRIORYEARS);
  }
  
  public boolean isPriorYearsReadable()
  {
    return isReadable(PRIORYEARS);
  }
  
  public boolean isPriorYearsModified()
  {
    return isModified(PRIORYEARS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPriorYearsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PRIORYEARS).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO> getT1Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationMethodDTO.CLASS, getEnumNames(T1METHOD));
  }
  
  public java.util.List<String> getT1MethodEnumNames()
  {
    return getEnumNames(T1METHOD);
  }
  
  public void addT1Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    addEnumItem(T1METHOD, enumDTO.toString());
  }
  
  public void removeT1Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    removeEnumItem(T1METHOD, enumDTO.toString());
  }
  
  public void clearT1Method()
  {
    clearEnum(T1METHOD);
  }
  
  public boolean isT1MethodWritable()
  {
    return isWritable(T1METHOD);
  }
  
  public boolean isT1MethodReadable()
  {
    return isReadable(T1METHOD);
  }
  
  public boolean isT1MethodModified()
  {
    return isModified(T1METHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getT1MethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(T1METHOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO> getT2Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationMethodDTO.CLASS, getEnumNames(T2METHOD));
  }
  
  public java.util.List<String> getT2MethodEnumNames()
  {
    return getEnumNames(T2METHOD);
  }
  
  public void addT2Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    addEnumItem(T2METHOD, enumDTO.toString());
  }
  
  public void removeT2Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    removeEnumItem(T2METHOD, enumDTO.toString());
  }
  
  public void clearT2Method()
  {
    clearEnum(T2METHOD);
  }
  
  public boolean isT2MethodWritable()
  {
    return isWritable(T2METHOD);
  }
  
  public boolean isT2MethodReadable()
  {
    return isReadable(T2METHOD);
  }
  
  public boolean isT2MethodModified()
  {
    return isModified(T2METHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getT2MethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(T2METHOD).getAttributeMdDTO();
  }
  
  public Integer getWeeksAfter()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSAFTER));
  }
  
  public void setWeeksAfter(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSAFTER, "");
    }
    else
    {
      setValue(WEEKSAFTER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWeeksAfterWritable()
  {
    return isWritable(WEEKSAFTER);
  }
  
  public boolean isWeeksAfterReadable()
  {
    return isReadable(WEEKSAFTER);
  }
  
  public boolean isWeeksAfterModified()
  {
    return isModified(WEEKSAFTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWeeksAfterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WEEKSAFTER).getAttributeMdDTO();
  }
  
  public Integer getWeeksBefore()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSBEFORE));
  }
  
  public void setWeeksBefore(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSBEFORE, "");
    }
    else
    {
      setValue(WEEKSBEFORE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWeeksBeforeWritable()
  {
    return isWritable(WEEKSBEFORE);
  }
  
  public boolean isWeeksBeforeReadable()
  {
    return isReadable(WEEKSBEFORE);
  }
  
  public boolean isWeeksBeforeModified()
  {
    return isModified(WEEKSBEFORE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWeeksBeforeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WEEKSBEFORE).getAttributeMdDTO();
  }
  
  public Double getWeight0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT0));
  }
  
  public void setWeight0(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT0, "");
    }
    else
    {
      setValue(WEIGHT0, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight0Writable()
  {
    return isWritable(WEIGHT0);
  }
  
  public boolean isWeight0Readable()
  {
    return isReadable(WEIGHT0);
  }
  
  public boolean isWeight0Modified()
  {
    return isModified(WEIGHT0);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight0Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT0).getAttributeMdDTO();
  }
  
  public Double getWeight1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT1));
  }
  
  public void setWeight1(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT1, "");
    }
    else
    {
      setValue(WEIGHT1, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight1Writable()
  {
    return isWritable(WEIGHT1);
  }
  
  public boolean isWeight1Readable()
  {
    return isReadable(WEIGHT1);
  }
  
  public boolean isWeight1Modified()
  {
    return isModified(WEIGHT1);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight1Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT1).getAttributeMdDTO();
  }
  
  public Double getWeight2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT2));
  }
  
  public void setWeight2(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT2, "");
    }
    else
    {
      setValue(WEIGHT2, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight2Writable()
  {
    return isWritable(WEIGHT2);
  }
  
  public boolean isWeight2Readable()
  {
    return isReadable(WEIGHT2);
  }
  
  public boolean isWeight2Modified()
  {
    return isModified(WEIGHT2);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight2Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT2).getAttributeMdDTO();
  }
  
  public Double getWeight3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT3));
  }
  
  public void setWeight3(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT3, "");
    }
    else
    {
      setValue(WEIGHT3, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight3Writable()
  {
    return isWritable(WEIGHT3);
  }
  
  public boolean isWeight3Readable()
  {
    return isReadable(WEIGHT3);
  }
  
  public boolean isWeight3Modified()
  {
    return isModified(WEIGHT3);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight3Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT3).getAttributeMdDTO();
  }
  
  public Double getWeight4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT4));
  }
  
  public void setWeight4(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT4, "");
    }
    else
    {
      setValue(WEIGHT4, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight4Writable()
  {
    return isWritable(WEIGHT4);
  }
  
  public boolean isWeight4Readable()
  {
    return isReadable(WEIGHT4);
  }
  
  public boolean isWeight4Modified()
  {
    return isModified(WEIGHT4);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight4Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT4).getAttributeMdDTO();
  }
  
  public Double getWeight5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT5));
  }
  
  public void setWeight5(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT5, "");
    }
    else
    {
      setValue(WEIGHT5, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight5Writable()
  {
    return isWritable(WEIGHT5);
  }
  
  public boolean isWeight5Readable()
  {
    return isReadable(WEIGHT5);
  }
  
  public boolean isWeight5Modified()
  {
    return isModified(WEIGHT5);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight5Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT5).getAttributeMdDTO();
  }
  
  public Double getWeight6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT6));
  }
  
  public void setWeight6(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT6, "");
    }
    else
    {
      setValue(WEIGHT6, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight6Writable()
  {
    return isWritable(WEIGHT6);
  }
  
  public boolean isWeight6Readable()
  {
    return isReadable(WEIGHT6);
  }
  
  public boolean isWeight6Modified()
  {
    return isModified(WEIGHT6);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight6Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT6).getAttributeMdDTO();
  }
  
  public Double getWeight7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT7));
  }
  
  public void setWeight7(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT7, "");
    }
    else
    {
      setValue(WEIGHT7, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight7Writable()
  {
    return isWritable(WEIGHT7);
  }
  
  public boolean isWeight7Readable()
  {
    return isReadable(WEIGHT7);
  }
  
  public boolean isWeight7Modified()
  {
    return isModified(WEIGHT7);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight7Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT7).getAttributeMdDTO();
  }
  
  public Double getWeight8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT8));
  }
  
  public void setWeight8(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT8, "");
    }
    else
    {
      setValue(WEIGHT8, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight8Writable()
  {
    return isWritable(WEIGHT8);
  }
  
  public boolean isWeight8Readable()
  {
    return isReadable(WEIGHT8);
  }
  
  public boolean isWeight8Modified()
  {
    return isModified(WEIGHT8);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight8Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT8).getAttributeMdDTO();
  }
  
  public Double getWeight9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT9));
  }
  
  public void setWeight9(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT9, "");
    }
    else
    {
      setValue(WEIGHT9, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight9Writable()
  {
    return isWritable(WEIGHT9);
  }
  
  public boolean isWeight9Readable()
  {
    return isReadable(WEIGHT9);
  }
  
  public boolean isWeight9Modified()
  {
    return isModified(WEIGHT9);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight9Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT9).getAttributeMdDTO();
  }
  
  public static final void calculateFacilityThresholds(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.general.ThresholdCalculationTypeView", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{thresholdCalculation, currentYear};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO.CLASS, "calculateFacilityThresholds", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void calculatePoliticalThresholds(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.general.ThresholdCalculationTypeView", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{thresholdCalculation, currentYear};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO.CLASS, "calculatePoliticalThresholds", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void calculateThresholds(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.ThresholdCalculationTypeViewDTO thresholdCalculation, java.lang.Boolean currentYear)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.general.ThresholdCalculationTypeView", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{thresholdCalculation, currentYear};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO.CLASS, "calculateThresholds", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.ThresholdCalculationTypeViewDTO getCalculationThreshold(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO.CLASS, "getCalculationThreshold", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdCalculationTypeViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.Integer getPercentComplete(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeViewDTO.CLASS, "getPercentComplete", _declaredTypes);
    return (java.lang.Integer) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ThresholdCalculationTypeViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ThresholdCalculationTypeViewDTO) dto;
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
