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
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1766019550)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedITNExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AggregatedITNExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.AggregatedITNExcelView";
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERDISTRIBUTED = "numberDistributed";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String RECEIVEDFORCOMMUNITYRESPONSE = "receivedForCommunityResponse";
  public static java.lang.String RECEIVEDFORTARGETGROUPS = "receivedForTargetGroups";
  private static final long serialVersionUID = -1766019550;
  
  public AggregatedITNExcelViewBase()
  {
    super();
  }
  
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void validateBatchNumber()
  {
    this.validateAttribute(BATCHNUMBER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getBatchNumberMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(BATCHNUMBER);
  }
  
  public void setBatchNumber(String value)
  {
    if(value == null)
    {
      setValue(BATCHNUMBER, "");
    }
    else
    {
      setValue(BATCHNUMBER, value);
    }
  }
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void validateCurrencyReceived()
  {
    this.validateAttribute(CURRENCYRECEIVED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getCurrencyReceivedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(CURRENCYRECEIVED);
  }
  
  public void setCurrencyReceived(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, value.toString());
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Integer getNumberDistributed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDISTRIBUTED));
  }
  
  public void validateNumberDistributed()
  {
    this.validateAttribute(NUMBERDISTRIBUTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberDistributedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERDISTRIBUTED);
  }
  
  public void setNumberDistributed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDISTRIBUTED, "");
    }
    else
    {
      setValue(NUMBERDISTRIBUTED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberSold()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void validateNumberSold()
  {
    this.validateAttribute(NUMBERSOLD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberSoldMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERSOLD);
  }
  
  public void setNumberSold(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSOLD, "");
    }
    else
    {
      setValue(NUMBERSOLD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getPeriodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(PERIOD);
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public String getPeriodType()
  {
    return getValue(PERIODTYPE);
  }
  
  public void validatePeriodType()
  {
    this.validateAttribute(PERIODTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getPeriodTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PERIODTYPE);
  }
  
  public void setPeriodType(String value)
  {
    if(value == null)
    {
      setValue(PERIODTYPE, "");
    }
    else
    {
      setValue(PERIODTYPE, value);
    }
  }
  
  public Integer getPeriodYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void validatePeriodYear()
  {
    this.validateAttribute(PERIODYEAR);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getPeriodYearMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(PERIODYEAR);
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getReceivedForCommunityResponse()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORCOMMUNITYRESPONSE));
  }
  
  public void validateReceivedForCommunityResponse()
  {
    this.validateAttribute(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getReceivedForCommunityResponseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(RECEIVEDFORCOMMUNITYRESPONSE);
  }
  
  public void setReceivedForCommunityResponse(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, "");
    }
    else
    {
      setValue(RECEIVEDFORCOMMUNITYRESPONSE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getReceivedForTargetGroups()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVEDFORTARGETGROUPS));
  }
  
  public void validateReceivedForTargetGroups()
  {
    this.validateAttribute(RECEIVEDFORTARGETGROUPS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getReceivedForTargetGroupsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.AggregatedITNExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(RECEIVEDFORTARGETGROUPS);
  }
  
  public void setReceivedForTargetGroups(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVEDFORTARGETGROUPS, "");
    }
    else
    {
      setValue(RECEIVEDFORTARGETGROUPS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AggregatedITNExcelView get(String id)
  {
    return (AggregatedITNExcelView) com.runwaysdk.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
