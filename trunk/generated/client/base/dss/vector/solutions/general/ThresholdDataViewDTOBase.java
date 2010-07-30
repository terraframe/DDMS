package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1448751357)
public abstract class ThresholdDataViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdDataView";
  private static final long serialVersionUID = 1448751357;
  
  protected ThresholdDataViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATION_0 = "identification_0";
  public static java.lang.String IDENTIFICATION_1 = "identification_1";
  public static java.lang.String IDENTIFICATION_10 = "identification_10";
  public static java.lang.String IDENTIFICATION_11 = "identification_11";
  public static java.lang.String IDENTIFICATION_12 = "identification_12";
  public static java.lang.String IDENTIFICATION_13 = "identification_13";
  public static java.lang.String IDENTIFICATION_14 = "identification_14";
  public static java.lang.String IDENTIFICATION_15 = "identification_15";
  public static java.lang.String IDENTIFICATION_16 = "identification_16";
  public static java.lang.String IDENTIFICATION_17 = "identification_17";
  public static java.lang.String IDENTIFICATION_18 = "identification_18";
  public static java.lang.String IDENTIFICATION_19 = "identification_19";
  public static java.lang.String IDENTIFICATION_2 = "identification_2";
  public static java.lang.String IDENTIFICATION_20 = "identification_20";
  public static java.lang.String IDENTIFICATION_21 = "identification_21";
  public static java.lang.String IDENTIFICATION_22 = "identification_22";
  public static java.lang.String IDENTIFICATION_23 = "identification_23";
  public static java.lang.String IDENTIFICATION_24 = "identification_24";
  public static java.lang.String IDENTIFICATION_25 = "identification_25";
  public static java.lang.String IDENTIFICATION_26 = "identification_26";
  public static java.lang.String IDENTIFICATION_27 = "identification_27";
  public static java.lang.String IDENTIFICATION_28 = "identification_28";
  public static java.lang.String IDENTIFICATION_29 = "identification_29";
  public static java.lang.String IDENTIFICATION_3 = "identification_3";
  public static java.lang.String IDENTIFICATION_30 = "identification_30";
  public static java.lang.String IDENTIFICATION_31 = "identification_31";
  public static java.lang.String IDENTIFICATION_32 = "identification_32";
  public static java.lang.String IDENTIFICATION_33 = "identification_33";
  public static java.lang.String IDENTIFICATION_34 = "identification_34";
  public static java.lang.String IDENTIFICATION_35 = "identification_35";
  public static java.lang.String IDENTIFICATION_36 = "identification_36";
  public static java.lang.String IDENTIFICATION_37 = "identification_37";
  public static java.lang.String IDENTIFICATION_38 = "identification_38";
  public static java.lang.String IDENTIFICATION_39 = "identification_39";
  public static java.lang.String IDENTIFICATION_4 = "identification_4";
  public static java.lang.String IDENTIFICATION_40 = "identification_40";
  public static java.lang.String IDENTIFICATION_41 = "identification_41";
  public static java.lang.String IDENTIFICATION_42 = "identification_42";
  public static java.lang.String IDENTIFICATION_43 = "identification_43";
  public static java.lang.String IDENTIFICATION_44 = "identification_44";
  public static java.lang.String IDENTIFICATION_45 = "identification_45";
  public static java.lang.String IDENTIFICATION_46 = "identification_46";
  public static java.lang.String IDENTIFICATION_47 = "identification_47";
  public static java.lang.String IDENTIFICATION_48 = "identification_48";
  public static java.lang.String IDENTIFICATION_49 = "identification_49";
  public static java.lang.String IDENTIFICATION_5 = "identification_5";
  public static java.lang.String IDENTIFICATION_50 = "identification_50";
  public static java.lang.String IDENTIFICATION_51 = "identification_51";
  public static java.lang.String IDENTIFICATION_52 = "identification_52";
  public static java.lang.String IDENTIFICATION_6 = "identification_6";
  public static java.lang.String IDENTIFICATION_7 = "identification_7";
  public static java.lang.String IDENTIFICATION_8 = "identification_8";
  public static java.lang.String IDENTIFICATION_9 = "identification_9";
  public static java.lang.String OUTBREAK_0 = "outbreak_0";
  public static java.lang.String OUTBREAK_1 = "outbreak_1";
  public static java.lang.String OUTBREAK_10 = "outbreak_10";
  public static java.lang.String OUTBREAK_11 = "outbreak_11";
  public static java.lang.String OUTBREAK_12 = "outbreak_12";
  public static java.lang.String OUTBREAK_13 = "outbreak_13";
  public static java.lang.String OUTBREAK_14 = "outbreak_14";
  public static java.lang.String OUTBREAK_15 = "outbreak_15";
  public static java.lang.String OUTBREAK_16 = "outbreak_16";
  public static java.lang.String OUTBREAK_17 = "outbreak_17";
  public static java.lang.String OUTBREAK_18 = "outbreak_18";
  public static java.lang.String OUTBREAK_19 = "outbreak_19";
  public static java.lang.String OUTBREAK_2 = "outbreak_2";
  public static java.lang.String OUTBREAK_20 = "outbreak_20";
  public static java.lang.String OUTBREAK_21 = "outbreak_21";
  public static java.lang.String OUTBREAK_22 = "outbreak_22";
  public static java.lang.String OUTBREAK_23 = "outbreak_23";
  public static java.lang.String OUTBREAK_24 = "outbreak_24";
  public static java.lang.String OUTBREAK_25 = "outbreak_25";
  public static java.lang.String OUTBREAK_26 = "outbreak_26";
  public static java.lang.String OUTBREAK_27 = "outbreak_27";
  public static java.lang.String OUTBREAK_28 = "outbreak_28";
  public static java.lang.String OUTBREAK_29 = "outbreak_29";
  public static java.lang.String OUTBREAK_3 = "outbreak_3";
  public static java.lang.String OUTBREAK_30 = "outbreak_30";
  public static java.lang.String OUTBREAK_31 = "outbreak_31";
  public static java.lang.String OUTBREAK_32 = "outbreak_32";
  public static java.lang.String OUTBREAK_33 = "outbreak_33";
  public static java.lang.String OUTBREAK_34 = "outbreak_34";
  public static java.lang.String OUTBREAK_35 = "outbreak_35";
  public static java.lang.String OUTBREAK_36 = "outbreak_36";
  public static java.lang.String OUTBREAK_37 = "outbreak_37";
  public static java.lang.String OUTBREAK_38 = "outbreak_38";
  public static java.lang.String OUTBREAK_39 = "outbreak_39";
  public static java.lang.String OUTBREAK_4 = "outbreak_4";
  public static java.lang.String OUTBREAK_40 = "outbreak_40";
  public static java.lang.String OUTBREAK_41 = "outbreak_41";
  public static java.lang.String OUTBREAK_42 = "outbreak_42";
  public static java.lang.String OUTBREAK_43 = "outbreak_43";
  public static java.lang.String OUTBREAK_44 = "outbreak_44";
  public static java.lang.String OUTBREAK_45 = "outbreak_45";
  public static java.lang.String OUTBREAK_46 = "outbreak_46";
  public static java.lang.String OUTBREAK_47 = "outbreak_47";
  public static java.lang.String OUTBREAK_48 = "outbreak_48";
  public static java.lang.String OUTBREAK_49 = "outbreak_49";
  public static java.lang.String OUTBREAK_5 = "outbreak_5";
  public static java.lang.String OUTBREAK_50 = "outbreak_50";
  public static java.lang.String OUTBREAK_51 = "outbreak_51";
  public static java.lang.String OUTBREAK_52 = "outbreak_52";
  public static java.lang.String OUTBREAK_6 = "outbreak_6";
  public static java.lang.String OUTBREAK_7 = "outbreak_7";
  public static java.lang.String OUTBREAK_8 = "outbreak_8";
  public static java.lang.String OUTBREAK_9 = "outbreak_9";
  public static java.lang.String SEASON = "season";
  public static java.lang.String THRESHOLDTYPE = "thresholdType";
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
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Double getIdentification_0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_0));
  }
  
  public void setIdentification_0(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_0, "");
    }
    else
    {
      setValue(IDENTIFICATION_0, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_0Writable()
  {
    return isWritable(IDENTIFICATION_0);
  }
  
  public boolean isIdentification_0Readable()
  {
    return isReadable(IDENTIFICATION_0);
  }
  
  public boolean isIdentification_0Modified()
  {
    return isModified(IDENTIFICATION_0);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_0Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_0).getAttributeMdDTO();
  }
  
  public Double getIdentification_1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_1));
  }
  
  public void setIdentification_1(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_1, "");
    }
    else
    {
      setValue(IDENTIFICATION_1, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_1Writable()
  {
    return isWritable(IDENTIFICATION_1);
  }
  
  public boolean isIdentification_1Readable()
  {
    return isReadable(IDENTIFICATION_1);
  }
  
  public boolean isIdentification_1Modified()
  {
    return isModified(IDENTIFICATION_1);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_1Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_1).getAttributeMdDTO();
  }
  
  public Double getIdentification_10()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_10));
  }
  
  public void setIdentification_10(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_10, "");
    }
    else
    {
      setValue(IDENTIFICATION_10, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_10Writable()
  {
    return isWritable(IDENTIFICATION_10);
  }
  
  public boolean isIdentification_10Readable()
  {
    return isReadable(IDENTIFICATION_10);
  }
  
  public boolean isIdentification_10Modified()
  {
    return isModified(IDENTIFICATION_10);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_10Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_10).getAttributeMdDTO();
  }
  
  public Double getIdentification_11()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_11));
  }
  
  public void setIdentification_11(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_11, "");
    }
    else
    {
      setValue(IDENTIFICATION_11, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_11Writable()
  {
    return isWritable(IDENTIFICATION_11);
  }
  
  public boolean isIdentification_11Readable()
  {
    return isReadable(IDENTIFICATION_11);
  }
  
  public boolean isIdentification_11Modified()
  {
    return isModified(IDENTIFICATION_11);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_11Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_11).getAttributeMdDTO();
  }
  
  public Double getIdentification_12()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_12));
  }
  
  public void setIdentification_12(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_12, "");
    }
    else
    {
      setValue(IDENTIFICATION_12, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_12Writable()
  {
    return isWritable(IDENTIFICATION_12);
  }
  
  public boolean isIdentification_12Readable()
  {
    return isReadable(IDENTIFICATION_12);
  }
  
  public boolean isIdentification_12Modified()
  {
    return isModified(IDENTIFICATION_12);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_12Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_12).getAttributeMdDTO();
  }
  
  public Double getIdentification_13()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_13));
  }
  
  public void setIdentification_13(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_13, "");
    }
    else
    {
      setValue(IDENTIFICATION_13, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_13Writable()
  {
    return isWritable(IDENTIFICATION_13);
  }
  
  public boolean isIdentification_13Readable()
  {
    return isReadable(IDENTIFICATION_13);
  }
  
  public boolean isIdentification_13Modified()
  {
    return isModified(IDENTIFICATION_13);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_13Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_13).getAttributeMdDTO();
  }
  
  public Double getIdentification_14()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_14));
  }
  
  public void setIdentification_14(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_14, "");
    }
    else
    {
      setValue(IDENTIFICATION_14, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_14Writable()
  {
    return isWritable(IDENTIFICATION_14);
  }
  
  public boolean isIdentification_14Readable()
  {
    return isReadable(IDENTIFICATION_14);
  }
  
  public boolean isIdentification_14Modified()
  {
    return isModified(IDENTIFICATION_14);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_14Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_14).getAttributeMdDTO();
  }
  
  public Double getIdentification_15()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_15));
  }
  
  public void setIdentification_15(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_15, "");
    }
    else
    {
      setValue(IDENTIFICATION_15, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_15Writable()
  {
    return isWritable(IDENTIFICATION_15);
  }
  
  public boolean isIdentification_15Readable()
  {
    return isReadable(IDENTIFICATION_15);
  }
  
  public boolean isIdentification_15Modified()
  {
    return isModified(IDENTIFICATION_15);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_15Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_15).getAttributeMdDTO();
  }
  
  public Double getIdentification_16()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_16));
  }
  
  public void setIdentification_16(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_16, "");
    }
    else
    {
      setValue(IDENTIFICATION_16, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_16Writable()
  {
    return isWritable(IDENTIFICATION_16);
  }
  
  public boolean isIdentification_16Readable()
  {
    return isReadable(IDENTIFICATION_16);
  }
  
  public boolean isIdentification_16Modified()
  {
    return isModified(IDENTIFICATION_16);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_16Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_16).getAttributeMdDTO();
  }
  
  public Double getIdentification_17()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_17));
  }
  
  public void setIdentification_17(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_17, "");
    }
    else
    {
      setValue(IDENTIFICATION_17, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_17Writable()
  {
    return isWritable(IDENTIFICATION_17);
  }
  
  public boolean isIdentification_17Readable()
  {
    return isReadable(IDENTIFICATION_17);
  }
  
  public boolean isIdentification_17Modified()
  {
    return isModified(IDENTIFICATION_17);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_17Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_17).getAttributeMdDTO();
  }
  
  public Double getIdentification_18()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_18));
  }
  
  public void setIdentification_18(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_18, "");
    }
    else
    {
      setValue(IDENTIFICATION_18, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_18Writable()
  {
    return isWritable(IDENTIFICATION_18);
  }
  
  public boolean isIdentification_18Readable()
  {
    return isReadable(IDENTIFICATION_18);
  }
  
  public boolean isIdentification_18Modified()
  {
    return isModified(IDENTIFICATION_18);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_18Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_18).getAttributeMdDTO();
  }
  
  public Double getIdentification_19()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_19));
  }
  
  public void setIdentification_19(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_19, "");
    }
    else
    {
      setValue(IDENTIFICATION_19, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_19Writable()
  {
    return isWritable(IDENTIFICATION_19);
  }
  
  public boolean isIdentification_19Readable()
  {
    return isReadable(IDENTIFICATION_19);
  }
  
  public boolean isIdentification_19Modified()
  {
    return isModified(IDENTIFICATION_19);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_19Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_19).getAttributeMdDTO();
  }
  
  public Double getIdentification_2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_2));
  }
  
  public void setIdentification_2(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_2, "");
    }
    else
    {
      setValue(IDENTIFICATION_2, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_2Writable()
  {
    return isWritable(IDENTIFICATION_2);
  }
  
  public boolean isIdentification_2Readable()
  {
    return isReadable(IDENTIFICATION_2);
  }
  
  public boolean isIdentification_2Modified()
  {
    return isModified(IDENTIFICATION_2);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_2Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_2).getAttributeMdDTO();
  }
  
  public Double getIdentification_20()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_20));
  }
  
  public void setIdentification_20(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_20, "");
    }
    else
    {
      setValue(IDENTIFICATION_20, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_20Writable()
  {
    return isWritable(IDENTIFICATION_20);
  }
  
  public boolean isIdentification_20Readable()
  {
    return isReadable(IDENTIFICATION_20);
  }
  
  public boolean isIdentification_20Modified()
  {
    return isModified(IDENTIFICATION_20);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_20Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_20).getAttributeMdDTO();
  }
  
  public Double getIdentification_21()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_21));
  }
  
  public void setIdentification_21(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_21, "");
    }
    else
    {
      setValue(IDENTIFICATION_21, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_21Writable()
  {
    return isWritable(IDENTIFICATION_21);
  }
  
  public boolean isIdentification_21Readable()
  {
    return isReadable(IDENTIFICATION_21);
  }
  
  public boolean isIdentification_21Modified()
  {
    return isModified(IDENTIFICATION_21);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_21Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_21).getAttributeMdDTO();
  }
  
  public Double getIdentification_22()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_22));
  }
  
  public void setIdentification_22(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_22, "");
    }
    else
    {
      setValue(IDENTIFICATION_22, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_22Writable()
  {
    return isWritable(IDENTIFICATION_22);
  }
  
  public boolean isIdentification_22Readable()
  {
    return isReadable(IDENTIFICATION_22);
  }
  
  public boolean isIdentification_22Modified()
  {
    return isModified(IDENTIFICATION_22);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_22Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_22).getAttributeMdDTO();
  }
  
  public Double getIdentification_23()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_23));
  }
  
  public void setIdentification_23(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_23, "");
    }
    else
    {
      setValue(IDENTIFICATION_23, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_23Writable()
  {
    return isWritable(IDENTIFICATION_23);
  }
  
  public boolean isIdentification_23Readable()
  {
    return isReadable(IDENTIFICATION_23);
  }
  
  public boolean isIdentification_23Modified()
  {
    return isModified(IDENTIFICATION_23);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_23Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_23).getAttributeMdDTO();
  }
  
  public Double getIdentification_24()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_24));
  }
  
  public void setIdentification_24(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_24, "");
    }
    else
    {
      setValue(IDENTIFICATION_24, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_24Writable()
  {
    return isWritable(IDENTIFICATION_24);
  }
  
  public boolean isIdentification_24Readable()
  {
    return isReadable(IDENTIFICATION_24);
  }
  
  public boolean isIdentification_24Modified()
  {
    return isModified(IDENTIFICATION_24);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_24Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_24).getAttributeMdDTO();
  }
  
  public Double getIdentification_25()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_25));
  }
  
  public void setIdentification_25(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_25, "");
    }
    else
    {
      setValue(IDENTIFICATION_25, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_25Writable()
  {
    return isWritable(IDENTIFICATION_25);
  }
  
  public boolean isIdentification_25Readable()
  {
    return isReadable(IDENTIFICATION_25);
  }
  
  public boolean isIdentification_25Modified()
  {
    return isModified(IDENTIFICATION_25);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_25Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_25).getAttributeMdDTO();
  }
  
  public Double getIdentification_26()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_26));
  }
  
  public void setIdentification_26(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_26, "");
    }
    else
    {
      setValue(IDENTIFICATION_26, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_26Writable()
  {
    return isWritable(IDENTIFICATION_26);
  }
  
  public boolean isIdentification_26Readable()
  {
    return isReadable(IDENTIFICATION_26);
  }
  
  public boolean isIdentification_26Modified()
  {
    return isModified(IDENTIFICATION_26);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_26Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_26).getAttributeMdDTO();
  }
  
  public Double getIdentification_27()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_27));
  }
  
  public void setIdentification_27(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_27, "");
    }
    else
    {
      setValue(IDENTIFICATION_27, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_27Writable()
  {
    return isWritable(IDENTIFICATION_27);
  }
  
  public boolean isIdentification_27Readable()
  {
    return isReadable(IDENTIFICATION_27);
  }
  
  public boolean isIdentification_27Modified()
  {
    return isModified(IDENTIFICATION_27);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_27Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_27).getAttributeMdDTO();
  }
  
  public Double getIdentification_28()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_28));
  }
  
  public void setIdentification_28(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_28, "");
    }
    else
    {
      setValue(IDENTIFICATION_28, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_28Writable()
  {
    return isWritable(IDENTIFICATION_28);
  }
  
  public boolean isIdentification_28Readable()
  {
    return isReadable(IDENTIFICATION_28);
  }
  
  public boolean isIdentification_28Modified()
  {
    return isModified(IDENTIFICATION_28);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_28Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_28).getAttributeMdDTO();
  }
  
  public Double getIdentification_29()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_29));
  }
  
  public void setIdentification_29(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_29, "");
    }
    else
    {
      setValue(IDENTIFICATION_29, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_29Writable()
  {
    return isWritable(IDENTIFICATION_29);
  }
  
  public boolean isIdentification_29Readable()
  {
    return isReadable(IDENTIFICATION_29);
  }
  
  public boolean isIdentification_29Modified()
  {
    return isModified(IDENTIFICATION_29);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_29Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_29).getAttributeMdDTO();
  }
  
  public Double getIdentification_3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_3));
  }
  
  public void setIdentification_3(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_3, "");
    }
    else
    {
      setValue(IDENTIFICATION_3, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_3Writable()
  {
    return isWritable(IDENTIFICATION_3);
  }
  
  public boolean isIdentification_3Readable()
  {
    return isReadable(IDENTIFICATION_3);
  }
  
  public boolean isIdentification_3Modified()
  {
    return isModified(IDENTIFICATION_3);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_3Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_3).getAttributeMdDTO();
  }
  
  public Double getIdentification_30()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_30));
  }
  
  public void setIdentification_30(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_30, "");
    }
    else
    {
      setValue(IDENTIFICATION_30, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_30Writable()
  {
    return isWritable(IDENTIFICATION_30);
  }
  
  public boolean isIdentification_30Readable()
  {
    return isReadable(IDENTIFICATION_30);
  }
  
  public boolean isIdentification_30Modified()
  {
    return isModified(IDENTIFICATION_30);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_30Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_30).getAttributeMdDTO();
  }
  
  public Double getIdentification_31()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_31));
  }
  
  public void setIdentification_31(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_31, "");
    }
    else
    {
      setValue(IDENTIFICATION_31, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_31Writable()
  {
    return isWritable(IDENTIFICATION_31);
  }
  
  public boolean isIdentification_31Readable()
  {
    return isReadable(IDENTIFICATION_31);
  }
  
  public boolean isIdentification_31Modified()
  {
    return isModified(IDENTIFICATION_31);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_31Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_31).getAttributeMdDTO();
  }
  
  public Double getIdentification_32()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_32));
  }
  
  public void setIdentification_32(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_32, "");
    }
    else
    {
      setValue(IDENTIFICATION_32, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_32Writable()
  {
    return isWritable(IDENTIFICATION_32);
  }
  
  public boolean isIdentification_32Readable()
  {
    return isReadable(IDENTIFICATION_32);
  }
  
  public boolean isIdentification_32Modified()
  {
    return isModified(IDENTIFICATION_32);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_32Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_32).getAttributeMdDTO();
  }
  
  public Double getIdentification_33()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_33));
  }
  
  public void setIdentification_33(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_33, "");
    }
    else
    {
      setValue(IDENTIFICATION_33, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_33Writable()
  {
    return isWritable(IDENTIFICATION_33);
  }
  
  public boolean isIdentification_33Readable()
  {
    return isReadable(IDENTIFICATION_33);
  }
  
  public boolean isIdentification_33Modified()
  {
    return isModified(IDENTIFICATION_33);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_33Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_33).getAttributeMdDTO();
  }
  
  public Double getIdentification_34()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_34));
  }
  
  public void setIdentification_34(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_34, "");
    }
    else
    {
      setValue(IDENTIFICATION_34, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_34Writable()
  {
    return isWritable(IDENTIFICATION_34);
  }
  
  public boolean isIdentification_34Readable()
  {
    return isReadable(IDENTIFICATION_34);
  }
  
  public boolean isIdentification_34Modified()
  {
    return isModified(IDENTIFICATION_34);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_34Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_34).getAttributeMdDTO();
  }
  
  public Double getIdentification_35()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_35));
  }
  
  public void setIdentification_35(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_35, "");
    }
    else
    {
      setValue(IDENTIFICATION_35, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_35Writable()
  {
    return isWritable(IDENTIFICATION_35);
  }
  
  public boolean isIdentification_35Readable()
  {
    return isReadable(IDENTIFICATION_35);
  }
  
  public boolean isIdentification_35Modified()
  {
    return isModified(IDENTIFICATION_35);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_35Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_35).getAttributeMdDTO();
  }
  
  public Double getIdentification_36()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_36));
  }
  
  public void setIdentification_36(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_36, "");
    }
    else
    {
      setValue(IDENTIFICATION_36, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_36Writable()
  {
    return isWritable(IDENTIFICATION_36);
  }
  
  public boolean isIdentification_36Readable()
  {
    return isReadable(IDENTIFICATION_36);
  }
  
  public boolean isIdentification_36Modified()
  {
    return isModified(IDENTIFICATION_36);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_36Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_36).getAttributeMdDTO();
  }
  
  public Double getIdentification_37()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_37));
  }
  
  public void setIdentification_37(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_37, "");
    }
    else
    {
      setValue(IDENTIFICATION_37, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_37Writable()
  {
    return isWritable(IDENTIFICATION_37);
  }
  
  public boolean isIdentification_37Readable()
  {
    return isReadable(IDENTIFICATION_37);
  }
  
  public boolean isIdentification_37Modified()
  {
    return isModified(IDENTIFICATION_37);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_37Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_37).getAttributeMdDTO();
  }
  
  public Double getIdentification_38()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_38));
  }
  
  public void setIdentification_38(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_38, "");
    }
    else
    {
      setValue(IDENTIFICATION_38, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_38Writable()
  {
    return isWritable(IDENTIFICATION_38);
  }
  
  public boolean isIdentification_38Readable()
  {
    return isReadable(IDENTIFICATION_38);
  }
  
  public boolean isIdentification_38Modified()
  {
    return isModified(IDENTIFICATION_38);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_38Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_38).getAttributeMdDTO();
  }
  
  public Double getIdentification_39()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_39));
  }
  
  public void setIdentification_39(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_39, "");
    }
    else
    {
      setValue(IDENTIFICATION_39, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_39Writable()
  {
    return isWritable(IDENTIFICATION_39);
  }
  
  public boolean isIdentification_39Readable()
  {
    return isReadable(IDENTIFICATION_39);
  }
  
  public boolean isIdentification_39Modified()
  {
    return isModified(IDENTIFICATION_39);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_39Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_39).getAttributeMdDTO();
  }
  
  public Double getIdentification_4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_4));
  }
  
  public void setIdentification_4(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_4, "");
    }
    else
    {
      setValue(IDENTIFICATION_4, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_4Writable()
  {
    return isWritable(IDENTIFICATION_4);
  }
  
  public boolean isIdentification_4Readable()
  {
    return isReadable(IDENTIFICATION_4);
  }
  
  public boolean isIdentification_4Modified()
  {
    return isModified(IDENTIFICATION_4);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_4Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_4).getAttributeMdDTO();
  }
  
  public Double getIdentification_40()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_40));
  }
  
  public void setIdentification_40(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_40, "");
    }
    else
    {
      setValue(IDENTIFICATION_40, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_40Writable()
  {
    return isWritable(IDENTIFICATION_40);
  }
  
  public boolean isIdentification_40Readable()
  {
    return isReadable(IDENTIFICATION_40);
  }
  
  public boolean isIdentification_40Modified()
  {
    return isModified(IDENTIFICATION_40);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_40Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_40).getAttributeMdDTO();
  }
  
  public Double getIdentification_41()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_41));
  }
  
  public void setIdentification_41(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_41, "");
    }
    else
    {
      setValue(IDENTIFICATION_41, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_41Writable()
  {
    return isWritable(IDENTIFICATION_41);
  }
  
  public boolean isIdentification_41Readable()
  {
    return isReadable(IDENTIFICATION_41);
  }
  
  public boolean isIdentification_41Modified()
  {
    return isModified(IDENTIFICATION_41);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_41Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_41).getAttributeMdDTO();
  }
  
  public Double getIdentification_42()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_42));
  }
  
  public void setIdentification_42(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_42, "");
    }
    else
    {
      setValue(IDENTIFICATION_42, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_42Writable()
  {
    return isWritable(IDENTIFICATION_42);
  }
  
  public boolean isIdentification_42Readable()
  {
    return isReadable(IDENTIFICATION_42);
  }
  
  public boolean isIdentification_42Modified()
  {
    return isModified(IDENTIFICATION_42);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_42Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_42).getAttributeMdDTO();
  }
  
  public Double getIdentification_43()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_43));
  }
  
  public void setIdentification_43(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_43, "");
    }
    else
    {
      setValue(IDENTIFICATION_43, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_43Writable()
  {
    return isWritable(IDENTIFICATION_43);
  }
  
  public boolean isIdentification_43Readable()
  {
    return isReadable(IDENTIFICATION_43);
  }
  
  public boolean isIdentification_43Modified()
  {
    return isModified(IDENTIFICATION_43);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_43Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_43).getAttributeMdDTO();
  }
  
  public Double getIdentification_44()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_44));
  }
  
  public void setIdentification_44(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_44, "");
    }
    else
    {
      setValue(IDENTIFICATION_44, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_44Writable()
  {
    return isWritable(IDENTIFICATION_44);
  }
  
  public boolean isIdentification_44Readable()
  {
    return isReadable(IDENTIFICATION_44);
  }
  
  public boolean isIdentification_44Modified()
  {
    return isModified(IDENTIFICATION_44);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_44Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_44).getAttributeMdDTO();
  }
  
  public Double getIdentification_45()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_45));
  }
  
  public void setIdentification_45(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_45, "");
    }
    else
    {
      setValue(IDENTIFICATION_45, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_45Writable()
  {
    return isWritable(IDENTIFICATION_45);
  }
  
  public boolean isIdentification_45Readable()
  {
    return isReadable(IDENTIFICATION_45);
  }
  
  public boolean isIdentification_45Modified()
  {
    return isModified(IDENTIFICATION_45);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_45Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_45).getAttributeMdDTO();
  }
  
  public Double getIdentification_46()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_46));
  }
  
  public void setIdentification_46(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_46, "");
    }
    else
    {
      setValue(IDENTIFICATION_46, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_46Writable()
  {
    return isWritable(IDENTIFICATION_46);
  }
  
  public boolean isIdentification_46Readable()
  {
    return isReadable(IDENTIFICATION_46);
  }
  
  public boolean isIdentification_46Modified()
  {
    return isModified(IDENTIFICATION_46);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_46Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_46).getAttributeMdDTO();
  }
  
  public Double getIdentification_47()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_47));
  }
  
  public void setIdentification_47(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_47, "");
    }
    else
    {
      setValue(IDENTIFICATION_47, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_47Writable()
  {
    return isWritable(IDENTIFICATION_47);
  }
  
  public boolean isIdentification_47Readable()
  {
    return isReadable(IDENTIFICATION_47);
  }
  
  public boolean isIdentification_47Modified()
  {
    return isModified(IDENTIFICATION_47);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_47Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_47).getAttributeMdDTO();
  }
  
  public Double getIdentification_48()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_48));
  }
  
  public void setIdentification_48(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_48, "");
    }
    else
    {
      setValue(IDENTIFICATION_48, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_48Writable()
  {
    return isWritable(IDENTIFICATION_48);
  }
  
  public boolean isIdentification_48Readable()
  {
    return isReadable(IDENTIFICATION_48);
  }
  
  public boolean isIdentification_48Modified()
  {
    return isModified(IDENTIFICATION_48);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_48Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_48).getAttributeMdDTO();
  }
  
  public Double getIdentification_49()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_49));
  }
  
  public void setIdentification_49(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_49, "");
    }
    else
    {
      setValue(IDENTIFICATION_49, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_49Writable()
  {
    return isWritable(IDENTIFICATION_49);
  }
  
  public boolean isIdentification_49Readable()
  {
    return isReadable(IDENTIFICATION_49);
  }
  
  public boolean isIdentification_49Modified()
  {
    return isModified(IDENTIFICATION_49);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_49Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_49).getAttributeMdDTO();
  }
  
  public Double getIdentification_5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_5));
  }
  
  public void setIdentification_5(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_5, "");
    }
    else
    {
      setValue(IDENTIFICATION_5, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_5Writable()
  {
    return isWritable(IDENTIFICATION_5);
  }
  
  public boolean isIdentification_5Readable()
  {
    return isReadable(IDENTIFICATION_5);
  }
  
  public boolean isIdentification_5Modified()
  {
    return isModified(IDENTIFICATION_5);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_5Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_5).getAttributeMdDTO();
  }
  
  public Double getIdentification_50()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_50));
  }
  
  public void setIdentification_50(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_50, "");
    }
    else
    {
      setValue(IDENTIFICATION_50, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_50Writable()
  {
    return isWritable(IDENTIFICATION_50);
  }
  
  public boolean isIdentification_50Readable()
  {
    return isReadable(IDENTIFICATION_50);
  }
  
  public boolean isIdentification_50Modified()
  {
    return isModified(IDENTIFICATION_50);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_50Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_50).getAttributeMdDTO();
  }
  
  public Double getIdentification_51()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_51));
  }
  
  public void setIdentification_51(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_51, "");
    }
    else
    {
      setValue(IDENTIFICATION_51, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_51Writable()
  {
    return isWritable(IDENTIFICATION_51);
  }
  
  public boolean isIdentification_51Readable()
  {
    return isReadable(IDENTIFICATION_51);
  }
  
  public boolean isIdentification_51Modified()
  {
    return isModified(IDENTIFICATION_51);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_51Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_51).getAttributeMdDTO();
  }
  
  public Double getIdentification_52()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_52));
  }
  
  public void setIdentification_52(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_52, "");
    }
    else
    {
      setValue(IDENTIFICATION_52, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_52Writable()
  {
    return isWritable(IDENTIFICATION_52);
  }
  
  public boolean isIdentification_52Readable()
  {
    return isReadable(IDENTIFICATION_52);
  }
  
  public boolean isIdentification_52Modified()
  {
    return isModified(IDENTIFICATION_52);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_52Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_52).getAttributeMdDTO();
  }
  
  public Double getIdentification_6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_6));
  }
  
  public void setIdentification_6(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_6, "");
    }
    else
    {
      setValue(IDENTIFICATION_6, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_6Writable()
  {
    return isWritable(IDENTIFICATION_6);
  }
  
  public boolean isIdentification_6Readable()
  {
    return isReadable(IDENTIFICATION_6);
  }
  
  public boolean isIdentification_6Modified()
  {
    return isModified(IDENTIFICATION_6);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_6Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_6).getAttributeMdDTO();
  }
  
  public Double getIdentification_7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_7));
  }
  
  public void setIdentification_7(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_7, "");
    }
    else
    {
      setValue(IDENTIFICATION_7, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_7Writable()
  {
    return isWritable(IDENTIFICATION_7);
  }
  
  public boolean isIdentification_7Readable()
  {
    return isReadable(IDENTIFICATION_7);
  }
  
  public boolean isIdentification_7Modified()
  {
    return isModified(IDENTIFICATION_7);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_7Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_7).getAttributeMdDTO();
  }
  
  public Double getIdentification_8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_8));
  }
  
  public void setIdentification_8(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_8, "");
    }
    else
    {
      setValue(IDENTIFICATION_8, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_8Writable()
  {
    return isWritable(IDENTIFICATION_8);
  }
  
  public boolean isIdentification_8Readable()
  {
    return isReadable(IDENTIFICATION_8);
  }
  
  public boolean isIdentification_8Modified()
  {
    return isModified(IDENTIFICATION_8);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_8Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_8).getAttributeMdDTO();
  }
  
  public Double getIdentification_9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION_9));
  }
  
  public void setIdentification_9(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION_9, "");
    }
    else
    {
      setValue(IDENTIFICATION_9, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentification_9Writable()
  {
    return isWritable(IDENTIFICATION_9);
  }
  
  public boolean isIdentification_9Readable()
  {
    return isReadable(IDENTIFICATION_9);
  }
  
  public boolean isIdentification_9Modified()
  {
    return isModified(IDENTIFICATION_9);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentification_9Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION_9).getAttributeMdDTO();
  }
  
  public Double getOutbreak_0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_0));
  }
  
  public void setOutbreak_0(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_0, "");
    }
    else
    {
      setValue(OUTBREAK_0, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_0Writable()
  {
    return isWritable(OUTBREAK_0);
  }
  
  public boolean isOutbreak_0Readable()
  {
    return isReadable(OUTBREAK_0);
  }
  
  public boolean isOutbreak_0Modified()
  {
    return isModified(OUTBREAK_0);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_0Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_0).getAttributeMdDTO();
  }
  
  public Double getOutbreak_1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_1));
  }
  
  public void setOutbreak_1(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_1, "");
    }
    else
    {
      setValue(OUTBREAK_1, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_1Writable()
  {
    return isWritable(OUTBREAK_1);
  }
  
  public boolean isOutbreak_1Readable()
  {
    return isReadable(OUTBREAK_1);
  }
  
  public boolean isOutbreak_1Modified()
  {
    return isModified(OUTBREAK_1);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_1Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_1).getAttributeMdDTO();
  }
  
  public Double getOutbreak_10()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_10));
  }
  
  public void setOutbreak_10(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_10, "");
    }
    else
    {
      setValue(OUTBREAK_10, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_10Writable()
  {
    return isWritable(OUTBREAK_10);
  }
  
  public boolean isOutbreak_10Readable()
  {
    return isReadable(OUTBREAK_10);
  }
  
  public boolean isOutbreak_10Modified()
  {
    return isModified(OUTBREAK_10);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_10Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_10).getAttributeMdDTO();
  }
  
  public Double getOutbreak_11()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_11));
  }
  
  public void setOutbreak_11(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_11, "");
    }
    else
    {
      setValue(OUTBREAK_11, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_11Writable()
  {
    return isWritable(OUTBREAK_11);
  }
  
  public boolean isOutbreak_11Readable()
  {
    return isReadable(OUTBREAK_11);
  }
  
  public boolean isOutbreak_11Modified()
  {
    return isModified(OUTBREAK_11);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_11Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_11).getAttributeMdDTO();
  }
  
  public Double getOutbreak_12()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_12));
  }
  
  public void setOutbreak_12(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_12, "");
    }
    else
    {
      setValue(OUTBREAK_12, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_12Writable()
  {
    return isWritable(OUTBREAK_12);
  }
  
  public boolean isOutbreak_12Readable()
  {
    return isReadable(OUTBREAK_12);
  }
  
  public boolean isOutbreak_12Modified()
  {
    return isModified(OUTBREAK_12);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_12Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_12).getAttributeMdDTO();
  }
  
  public Double getOutbreak_13()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_13));
  }
  
  public void setOutbreak_13(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_13, "");
    }
    else
    {
      setValue(OUTBREAK_13, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_13Writable()
  {
    return isWritable(OUTBREAK_13);
  }
  
  public boolean isOutbreak_13Readable()
  {
    return isReadable(OUTBREAK_13);
  }
  
  public boolean isOutbreak_13Modified()
  {
    return isModified(OUTBREAK_13);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_13Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_13).getAttributeMdDTO();
  }
  
  public Double getOutbreak_14()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_14));
  }
  
  public void setOutbreak_14(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_14, "");
    }
    else
    {
      setValue(OUTBREAK_14, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_14Writable()
  {
    return isWritable(OUTBREAK_14);
  }
  
  public boolean isOutbreak_14Readable()
  {
    return isReadable(OUTBREAK_14);
  }
  
  public boolean isOutbreak_14Modified()
  {
    return isModified(OUTBREAK_14);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_14Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_14).getAttributeMdDTO();
  }
  
  public Double getOutbreak_15()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_15));
  }
  
  public void setOutbreak_15(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_15, "");
    }
    else
    {
      setValue(OUTBREAK_15, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_15Writable()
  {
    return isWritable(OUTBREAK_15);
  }
  
  public boolean isOutbreak_15Readable()
  {
    return isReadable(OUTBREAK_15);
  }
  
  public boolean isOutbreak_15Modified()
  {
    return isModified(OUTBREAK_15);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_15Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_15).getAttributeMdDTO();
  }
  
  public Double getOutbreak_16()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_16));
  }
  
  public void setOutbreak_16(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_16, "");
    }
    else
    {
      setValue(OUTBREAK_16, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_16Writable()
  {
    return isWritable(OUTBREAK_16);
  }
  
  public boolean isOutbreak_16Readable()
  {
    return isReadable(OUTBREAK_16);
  }
  
  public boolean isOutbreak_16Modified()
  {
    return isModified(OUTBREAK_16);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_16Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_16).getAttributeMdDTO();
  }
  
  public Double getOutbreak_17()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_17));
  }
  
  public void setOutbreak_17(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_17, "");
    }
    else
    {
      setValue(OUTBREAK_17, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_17Writable()
  {
    return isWritable(OUTBREAK_17);
  }
  
  public boolean isOutbreak_17Readable()
  {
    return isReadable(OUTBREAK_17);
  }
  
  public boolean isOutbreak_17Modified()
  {
    return isModified(OUTBREAK_17);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_17Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_17).getAttributeMdDTO();
  }
  
  public Double getOutbreak_18()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_18));
  }
  
  public void setOutbreak_18(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_18, "");
    }
    else
    {
      setValue(OUTBREAK_18, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_18Writable()
  {
    return isWritable(OUTBREAK_18);
  }
  
  public boolean isOutbreak_18Readable()
  {
    return isReadable(OUTBREAK_18);
  }
  
  public boolean isOutbreak_18Modified()
  {
    return isModified(OUTBREAK_18);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_18Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_18).getAttributeMdDTO();
  }
  
  public Double getOutbreak_19()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_19));
  }
  
  public void setOutbreak_19(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_19, "");
    }
    else
    {
      setValue(OUTBREAK_19, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_19Writable()
  {
    return isWritable(OUTBREAK_19);
  }
  
  public boolean isOutbreak_19Readable()
  {
    return isReadable(OUTBREAK_19);
  }
  
  public boolean isOutbreak_19Modified()
  {
    return isModified(OUTBREAK_19);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_19Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_19).getAttributeMdDTO();
  }
  
  public Double getOutbreak_2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_2));
  }
  
  public void setOutbreak_2(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_2, "");
    }
    else
    {
      setValue(OUTBREAK_2, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_2Writable()
  {
    return isWritable(OUTBREAK_2);
  }
  
  public boolean isOutbreak_2Readable()
  {
    return isReadable(OUTBREAK_2);
  }
  
  public boolean isOutbreak_2Modified()
  {
    return isModified(OUTBREAK_2);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_2Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_2).getAttributeMdDTO();
  }
  
  public Double getOutbreak_20()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_20));
  }
  
  public void setOutbreak_20(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_20, "");
    }
    else
    {
      setValue(OUTBREAK_20, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_20Writable()
  {
    return isWritable(OUTBREAK_20);
  }
  
  public boolean isOutbreak_20Readable()
  {
    return isReadable(OUTBREAK_20);
  }
  
  public boolean isOutbreak_20Modified()
  {
    return isModified(OUTBREAK_20);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_20Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_20).getAttributeMdDTO();
  }
  
  public Double getOutbreak_21()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_21));
  }
  
  public void setOutbreak_21(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_21, "");
    }
    else
    {
      setValue(OUTBREAK_21, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_21Writable()
  {
    return isWritable(OUTBREAK_21);
  }
  
  public boolean isOutbreak_21Readable()
  {
    return isReadable(OUTBREAK_21);
  }
  
  public boolean isOutbreak_21Modified()
  {
    return isModified(OUTBREAK_21);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_21Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_21).getAttributeMdDTO();
  }
  
  public Double getOutbreak_22()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_22));
  }
  
  public void setOutbreak_22(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_22, "");
    }
    else
    {
      setValue(OUTBREAK_22, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_22Writable()
  {
    return isWritable(OUTBREAK_22);
  }
  
  public boolean isOutbreak_22Readable()
  {
    return isReadable(OUTBREAK_22);
  }
  
  public boolean isOutbreak_22Modified()
  {
    return isModified(OUTBREAK_22);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_22Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_22).getAttributeMdDTO();
  }
  
  public Double getOutbreak_23()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_23));
  }
  
  public void setOutbreak_23(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_23, "");
    }
    else
    {
      setValue(OUTBREAK_23, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_23Writable()
  {
    return isWritable(OUTBREAK_23);
  }
  
  public boolean isOutbreak_23Readable()
  {
    return isReadable(OUTBREAK_23);
  }
  
  public boolean isOutbreak_23Modified()
  {
    return isModified(OUTBREAK_23);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_23Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_23).getAttributeMdDTO();
  }
  
  public Double getOutbreak_24()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_24));
  }
  
  public void setOutbreak_24(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_24, "");
    }
    else
    {
      setValue(OUTBREAK_24, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_24Writable()
  {
    return isWritable(OUTBREAK_24);
  }
  
  public boolean isOutbreak_24Readable()
  {
    return isReadable(OUTBREAK_24);
  }
  
  public boolean isOutbreak_24Modified()
  {
    return isModified(OUTBREAK_24);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_24Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_24).getAttributeMdDTO();
  }
  
  public Double getOutbreak_25()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_25));
  }
  
  public void setOutbreak_25(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_25, "");
    }
    else
    {
      setValue(OUTBREAK_25, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_25Writable()
  {
    return isWritable(OUTBREAK_25);
  }
  
  public boolean isOutbreak_25Readable()
  {
    return isReadable(OUTBREAK_25);
  }
  
  public boolean isOutbreak_25Modified()
  {
    return isModified(OUTBREAK_25);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_25Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_25).getAttributeMdDTO();
  }
  
  public Double getOutbreak_26()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_26));
  }
  
  public void setOutbreak_26(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_26, "");
    }
    else
    {
      setValue(OUTBREAK_26, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_26Writable()
  {
    return isWritable(OUTBREAK_26);
  }
  
  public boolean isOutbreak_26Readable()
  {
    return isReadable(OUTBREAK_26);
  }
  
  public boolean isOutbreak_26Modified()
  {
    return isModified(OUTBREAK_26);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_26Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_26).getAttributeMdDTO();
  }
  
  public Double getOutbreak_27()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_27));
  }
  
  public void setOutbreak_27(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_27, "");
    }
    else
    {
      setValue(OUTBREAK_27, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_27Writable()
  {
    return isWritable(OUTBREAK_27);
  }
  
  public boolean isOutbreak_27Readable()
  {
    return isReadable(OUTBREAK_27);
  }
  
  public boolean isOutbreak_27Modified()
  {
    return isModified(OUTBREAK_27);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_27Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_27).getAttributeMdDTO();
  }
  
  public Double getOutbreak_28()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_28));
  }
  
  public void setOutbreak_28(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_28, "");
    }
    else
    {
      setValue(OUTBREAK_28, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_28Writable()
  {
    return isWritable(OUTBREAK_28);
  }
  
  public boolean isOutbreak_28Readable()
  {
    return isReadable(OUTBREAK_28);
  }
  
  public boolean isOutbreak_28Modified()
  {
    return isModified(OUTBREAK_28);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_28Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_28).getAttributeMdDTO();
  }
  
  public Double getOutbreak_29()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_29));
  }
  
  public void setOutbreak_29(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_29, "");
    }
    else
    {
      setValue(OUTBREAK_29, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_29Writable()
  {
    return isWritable(OUTBREAK_29);
  }
  
  public boolean isOutbreak_29Readable()
  {
    return isReadable(OUTBREAK_29);
  }
  
  public boolean isOutbreak_29Modified()
  {
    return isModified(OUTBREAK_29);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_29Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_29).getAttributeMdDTO();
  }
  
  public Double getOutbreak_3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_3));
  }
  
  public void setOutbreak_3(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_3, "");
    }
    else
    {
      setValue(OUTBREAK_3, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_3Writable()
  {
    return isWritable(OUTBREAK_3);
  }
  
  public boolean isOutbreak_3Readable()
  {
    return isReadable(OUTBREAK_3);
  }
  
  public boolean isOutbreak_3Modified()
  {
    return isModified(OUTBREAK_3);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_3Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_3).getAttributeMdDTO();
  }
  
  public Double getOutbreak_30()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_30));
  }
  
  public void setOutbreak_30(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_30, "");
    }
    else
    {
      setValue(OUTBREAK_30, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_30Writable()
  {
    return isWritable(OUTBREAK_30);
  }
  
  public boolean isOutbreak_30Readable()
  {
    return isReadable(OUTBREAK_30);
  }
  
  public boolean isOutbreak_30Modified()
  {
    return isModified(OUTBREAK_30);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_30Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_30).getAttributeMdDTO();
  }
  
  public Double getOutbreak_31()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_31));
  }
  
  public void setOutbreak_31(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_31, "");
    }
    else
    {
      setValue(OUTBREAK_31, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_31Writable()
  {
    return isWritable(OUTBREAK_31);
  }
  
  public boolean isOutbreak_31Readable()
  {
    return isReadable(OUTBREAK_31);
  }
  
  public boolean isOutbreak_31Modified()
  {
    return isModified(OUTBREAK_31);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_31Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_31).getAttributeMdDTO();
  }
  
  public Double getOutbreak_32()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_32));
  }
  
  public void setOutbreak_32(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_32, "");
    }
    else
    {
      setValue(OUTBREAK_32, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_32Writable()
  {
    return isWritable(OUTBREAK_32);
  }
  
  public boolean isOutbreak_32Readable()
  {
    return isReadable(OUTBREAK_32);
  }
  
  public boolean isOutbreak_32Modified()
  {
    return isModified(OUTBREAK_32);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_32Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_32).getAttributeMdDTO();
  }
  
  public Double getOutbreak_33()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_33));
  }
  
  public void setOutbreak_33(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_33, "");
    }
    else
    {
      setValue(OUTBREAK_33, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_33Writable()
  {
    return isWritable(OUTBREAK_33);
  }
  
  public boolean isOutbreak_33Readable()
  {
    return isReadable(OUTBREAK_33);
  }
  
  public boolean isOutbreak_33Modified()
  {
    return isModified(OUTBREAK_33);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_33Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_33).getAttributeMdDTO();
  }
  
  public Double getOutbreak_34()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_34));
  }
  
  public void setOutbreak_34(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_34, "");
    }
    else
    {
      setValue(OUTBREAK_34, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_34Writable()
  {
    return isWritable(OUTBREAK_34);
  }
  
  public boolean isOutbreak_34Readable()
  {
    return isReadable(OUTBREAK_34);
  }
  
  public boolean isOutbreak_34Modified()
  {
    return isModified(OUTBREAK_34);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_34Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_34).getAttributeMdDTO();
  }
  
  public Double getOutbreak_35()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_35));
  }
  
  public void setOutbreak_35(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_35, "");
    }
    else
    {
      setValue(OUTBREAK_35, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_35Writable()
  {
    return isWritable(OUTBREAK_35);
  }
  
  public boolean isOutbreak_35Readable()
  {
    return isReadable(OUTBREAK_35);
  }
  
  public boolean isOutbreak_35Modified()
  {
    return isModified(OUTBREAK_35);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_35Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_35).getAttributeMdDTO();
  }
  
  public Double getOutbreak_36()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_36));
  }
  
  public void setOutbreak_36(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_36, "");
    }
    else
    {
      setValue(OUTBREAK_36, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_36Writable()
  {
    return isWritable(OUTBREAK_36);
  }
  
  public boolean isOutbreak_36Readable()
  {
    return isReadable(OUTBREAK_36);
  }
  
  public boolean isOutbreak_36Modified()
  {
    return isModified(OUTBREAK_36);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_36Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_36).getAttributeMdDTO();
  }
  
  public Double getOutbreak_37()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_37));
  }
  
  public void setOutbreak_37(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_37, "");
    }
    else
    {
      setValue(OUTBREAK_37, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_37Writable()
  {
    return isWritable(OUTBREAK_37);
  }
  
  public boolean isOutbreak_37Readable()
  {
    return isReadable(OUTBREAK_37);
  }
  
  public boolean isOutbreak_37Modified()
  {
    return isModified(OUTBREAK_37);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_37Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_37).getAttributeMdDTO();
  }
  
  public Double getOutbreak_38()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_38));
  }
  
  public void setOutbreak_38(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_38, "");
    }
    else
    {
      setValue(OUTBREAK_38, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_38Writable()
  {
    return isWritable(OUTBREAK_38);
  }
  
  public boolean isOutbreak_38Readable()
  {
    return isReadable(OUTBREAK_38);
  }
  
  public boolean isOutbreak_38Modified()
  {
    return isModified(OUTBREAK_38);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_38Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_38).getAttributeMdDTO();
  }
  
  public Double getOutbreak_39()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_39));
  }
  
  public void setOutbreak_39(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_39, "");
    }
    else
    {
      setValue(OUTBREAK_39, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_39Writable()
  {
    return isWritable(OUTBREAK_39);
  }
  
  public boolean isOutbreak_39Readable()
  {
    return isReadable(OUTBREAK_39);
  }
  
  public boolean isOutbreak_39Modified()
  {
    return isModified(OUTBREAK_39);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_39Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_39).getAttributeMdDTO();
  }
  
  public Double getOutbreak_4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_4));
  }
  
  public void setOutbreak_4(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_4, "");
    }
    else
    {
      setValue(OUTBREAK_4, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_4Writable()
  {
    return isWritable(OUTBREAK_4);
  }
  
  public boolean isOutbreak_4Readable()
  {
    return isReadable(OUTBREAK_4);
  }
  
  public boolean isOutbreak_4Modified()
  {
    return isModified(OUTBREAK_4);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_4Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_4).getAttributeMdDTO();
  }
  
  public Double getOutbreak_40()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_40));
  }
  
  public void setOutbreak_40(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_40, "");
    }
    else
    {
      setValue(OUTBREAK_40, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_40Writable()
  {
    return isWritable(OUTBREAK_40);
  }
  
  public boolean isOutbreak_40Readable()
  {
    return isReadable(OUTBREAK_40);
  }
  
  public boolean isOutbreak_40Modified()
  {
    return isModified(OUTBREAK_40);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_40Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_40).getAttributeMdDTO();
  }
  
  public Double getOutbreak_41()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_41));
  }
  
  public void setOutbreak_41(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_41, "");
    }
    else
    {
      setValue(OUTBREAK_41, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_41Writable()
  {
    return isWritable(OUTBREAK_41);
  }
  
  public boolean isOutbreak_41Readable()
  {
    return isReadable(OUTBREAK_41);
  }
  
  public boolean isOutbreak_41Modified()
  {
    return isModified(OUTBREAK_41);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_41Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_41).getAttributeMdDTO();
  }
  
  public Double getOutbreak_42()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_42));
  }
  
  public void setOutbreak_42(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_42, "");
    }
    else
    {
      setValue(OUTBREAK_42, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_42Writable()
  {
    return isWritable(OUTBREAK_42);
  }
  
  public boolean isOutbreak_42Readable()
  {
    return isReadable(OUTBREAK_42);
  }
  
  public boolean isOutbreak_42Modified()
  {
    return isModified(OUTBREAK_42);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_42Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_42).getAttributeMdDTO();
  }
  
  public Double getOutbreak_43()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_43));
  }
  
  public void setOutbreak_43(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_43, "");
    }
    else
    {
      setValue(OUTBREAK_43, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_43Writable()
  {
    return isWritable(OUTBREAK_43);
  }
  
  public boolean isOutbreak_43Readable()
  {
    return isReadable(OUTBREAK_43);
  }
  
  public boolean isOutbreak_43Modified()
  {
    return isModified(OUTBREAK_43);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_43Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_43).getAttributeMdDTO();
  }
  
  public Double getOutbreak_44()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_44));
  }
  
  public void setOutbreak_44(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_44, "");
    }
    else
    {
      setValue(OUTBREAK_44, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_44Writable()
  {
    return isWritable(OUTBREAK_44);
  }
  
  public boolean isOutbreak_44Readable()
  {
    return isReadable(OUTBREAK_44);
  }
  
  public boolean isOutbreak_44Modified()
  {
    return isModified(OUTBREAK_44);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_44Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_44).getAttributeMdDTO();
  }
  
  public Double getOutbreak_45()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_45));
  }
  
  public void setOutbreak_45(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_45, "");
    }
    else
    {
      setValue(OUTBREAK_45, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_45Writable()
  {
    return isWritable(OUTBREAK_45);
  }
  
  public boolean isOutbreak_45Readable()
  {
    return isReadable(OUTBREAK_45);
  }
  
  public boolean isOutbreak_45Modified()
  {
    return isModified(OUTBREAK_45);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_45Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_45).getAttributeMdDTO();
  }
  
  public Double getOutbreak_46()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_46));
  }
  
  public void setOutbreak_46(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_46, "");
    }
    else
    {
      setValue(OUTBREAK_46, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_46Writable()
  {
    return isWritable(OUTBREAK_46);
  }
  
  public boolean isOutbreak_46Readable()
  {
    return isReadable(OUTBREAK_46);
  }
  
  public boolean isOutbreak_46Modified()
  {
    return isModified(OUTBREAK_46);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_46Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_46).getAttributeMdDTO();
  }
  
  public Double getOutbreak_47()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_47));
  }
  
  public void setOutbreak_47(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_47, "");
    }
    else
    {
      setValue(OUTBREAK_47, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_47Writable()
  {
    return isWritable(OUTBREAK_47);
  }
  
  public boolean isOutbreak_47Readable()
  {
    return isReadable(OUTBREAK_47);
  }
  
  public boolean isOutbreak_47Modified()
  {
    return isModified(OUTBREAK_47);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_47Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_47).getAttributeMdDTO();
  }
  
  public Double getOutbreak_48()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_48));
  }
  
  public void setOutbreak_48(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_48, "");
    }
    else
    {
      setValue(OUTBREAK_48, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_48Writable()
  {
    return isWritable(OUTBREAK_48);
  }
  
  public boolean isOutbreak_48Readable()
  {
    return isReadable(OUTBREAK_48);
  }
  
  public boolean isOutbreak_48Modified()
  {
    return isModified(OUTBREAK_48);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_48Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_48).getAttributeMdDTO();
  }
  
  public Double getOutbreak_49()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_49));
  }
  
  public void setOutbreak_49(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_49, "");
    }
    else
    {
      setValue(OUTBREAK_49, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_49Writable()
  {
    return isWritable(OUTBREAK_49);
  }
  
  public boolean isOutbreak_49Readable()
  {
    return isReadable(OUTBREAK_49);
  }
  
  public boolean isOutbreak_49Modified()
  {
    return isModified(OUTBREAK_49);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_49Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_49).getAttributeMdDTO();
  }
  
  public Double getOutbreak_5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_5));
  }
  
  public void setOutbreak_5(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_5, "");
    }
    else
    {
      setValue(OUTBREAK_5, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_5Writable()
  {
    return isWritable(OUTBREAK_5);
  }
  
  public boolean isOutbreak_5Readable()
  {
    return isReadable(OUTBREAK_5);
  }
  
  public boolean isOutbreak_5Modified()
  {
    return isModified(OUTBREAK_5);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_5Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_5).getAttributeMdDTO();
  }
  
  public Double getOutbreak_50()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_50));
  }
  
  public void setOutbreak_50(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_50, "");
    }
    else
    {
      setValue(OUTBREAK_50, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_50Writable()
  {
    return isWritable(OUTBREAK_50);
  }
  
  public boolean isOutbreak_50Readable()
  {
    return isReadable(OUTBREAK_50);
  }
  
  public boolean isOutbreak_50Modified()
  {
    return isModified(OUTBREAK_50);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_50Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_50).getAttributeMdDTO();
  }
  
  public Double getOutbreak_51()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_51));
  }
  
  public void setOutbreak_51(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_51, "");
    }
    else
    {
      setValue(OUTBREAK_51, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_51Writable()
  {
    return isWritable(OUTBREAK_51);
  }
  
  public boolean isOutbreak_51Readable()
  {
    return isReadable(OUTBREAK_51);
  }
  
  public boolean isOutbreak_51Modified()
  {
    return isModified(OUTBREAK_51);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_51Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_51).getAttributeMdDTO();
  }
  
  public Double getOutbreak_52()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_52));
  }
  
  public void setOutbreak_52(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_52, "");
    }
    else
    {
      setValue(OUTBREAK_52, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_52Writable()
  {
    return isWritable(OUTBREAK_52);
  }
  
  public boolean isOutbreak_52Readable()
  {
    return isReadable(OUTBREAK_52);
  }
  
  public boolean isOutbreak_52Modified()
  {
    return isModified(OUTBREAK_52);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_52Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_52).getAttributeMdDTO();
  }
  
  public Double getOutbreak_6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_6));
  }
  
  public void setOutbreak_6(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_6, "");
    }
    else
    {
      setValue(OUTBREAK_6, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_6Writable()
  {
    return isWritable(OUTBREAK_6);
  }
  
  public boolean isOutbreak_6Readable()
  {
    return isReadable(OUTBREAK_6);
  }
  
  public boolean isOutbreak_6Modified()
  {
    return isModified(OUTBREAK_6);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_6Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_6).getAttributeMdDTO();
  }
  
  public Double getOutbreak_7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_7));
  }
  
  public void setOutbreak_7(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_7, "");
    }
    else
    {
      setValue(OUTBREAK_7, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_7Writable()
  {
    return isWritable(OUTBREAK_7);
  }
  
  public boolean isOutbreak_7Readable()
  {
    return isReadable(OUTBREAK_7);
  }
  
  public boolean isOutbreak_7Modified()
  {
    return isModified(OUTBREAK_7);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_7Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_7).getAttributeMdDTO();
  }
  
  public Double getOutbreak_8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_8));
  }
  
  public void setOutbreak_8(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_8, "");
    }
    else
    {
      setValue(OUTBREAK_8, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_8Writable()
  {
    return isWritable(OUTBREAK_8);
  }
  
  public boolean isOutbreak_8Readable()
  {
    return isReadable(OUTBREAK_8);
  }
  
  public boolean isOutbreak_8Modified()
  {
    return isModified(OUTBREAK_8);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_8Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_8).getAttributeMdDTO();
  }
  
  public Double getOutbreak_9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(OUTBREAK_9));
  }
  
  public void setOutbreak_9(Double value)
  {
    if(value == null)
    {
      setValue(OUTBREAK_9, "");
    }
    else
    {
      setValue(OUTBREAK_9, java.lang.Double.toString(value));
    }
  }
  
  public boolean isOutbreak_9Writable()
  {
    return isWritable(OUTBREAK_9);
  }
  
  public boolean isOutbreak_9Readable()
  {
    return isReadable(OUTBREAK_9);
  }
  
  public boolean isOutbreak_9Modified()
  {
    return isModified(OUTBREAK_9);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOutbreak_9Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OUTBREAK_9).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.MalariaSeasonDTO getSeason()
  {
    if(getValue(SEASON) == null || getValue(SEASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.MalariaSeasonDTO.get(getRequest(), getValue(SEASON));
    }
  }
  
  public void setSeason(dss.vector.solutions.general.MalariaSeasonDTO value)
  {
    if(value == null)
    {
      setValue(SEASON, "");
    }
    else
    {
      setValue(SEASON, value.getId());
    }
  }
  
  public boolean isSeasonWritable()
  {
    return isWritable(SEASON);
  }
  
  public boolean isSeasonReadable()
  {
    return isReadable(SEASON);
  }
  
  public boolean isSeasonModified()
  {
    return isModified(SEASON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSeasonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEASON).getAttributeMdDTO();
  }
  
  public Boolean getThresholdType()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(THRESHOLDTYPE));
  }
  
  public void setThresholdType(Boolean value)
  {
    if(value == null)
    {
      setValue(THRESHOLDTYPE, "");
    }
    else
    {
      setValue(THRESHOLDTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isThresholdTypeWritable()
  {
    return isWritable(THRESHOLDTYPE);
  }
  
  public boolean isThresholdTypeReadable()
  {
    return isReadable(THRESHOLDTYPE);
  }
  
  public boolean isThresholdTypeModified()
  {
    return isModified(THRESHOLDTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getThresholdTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(THRESHOLDTYPE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.ThresholdDataViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.ThresholdDataViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.general.ThresholdDataView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.ThresholdDataViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.general.ThresholdDataView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.Double[] getCalculatedThresholds()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "getCalculatedThresholds", _declaredTypes);
    return (java.lang.Double[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Double[] getCalculatedThresholds(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "getCalculatedThresholds", _declaredTypes);
    return (java.lang.Double[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.Double[][] getCalculatedThresholdsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.MalariaSeasonDTO season, dss.vector.solutions.general.ThresholdDataViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.general.MalariaSeason", "[Ldss.vector.solutions.general.ThresholdDataView;"};
    Object[] _parameters = new Object[]{season, views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "getCalculatedThresholdsForViews", _declaredTypes);
    return (java.lang.Double[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.ThresholdDataViewDTO[] getFacilityViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "getFacilityViews", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.ThresholdDataViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdDataViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ThresholdDataViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ThresholdDataViewDTO) dto;
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
