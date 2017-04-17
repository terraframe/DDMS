package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 18006699)
public abstract class ResourceTargetExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ResourceTargetExcelView";
  private static final long serialVersionUID = 18006699;
  
  protected ResourceTargetExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SEASONNAME = "seasonName";
  public static java.lang.String TARGET_0 = "target_0";
  public static java.lang.String TARGET_1 = "target_1";
  public static java.lang.String TARGET_10 = "target_10";
  public static java.lang.String TARGET_11 = "target_11";
  public static java.lang.String TARGET_12 = "target_12";
  public static java.lang.String TARGET_13 = "target_13";
  public static java.lang.String TARGET_14 = "target_14";
  public static java.lang.String TARGET_15 = "target_15";
  public static java.lang.String TARGET_16 = "target_16";
  public static java.lang.String TARGET_17 = "target_17";
  public static java.lang.String TARGET_18 = "target_18";
  public static java.lang.String TARGET_19 = "target_19";
  public static java.lang.String TARGET_2 = "target_2";
  public static java.lang.String TARGET_20 = "target_20";
  public static java.lang.String TARGET_21 = "target_21";
  public static java.lang.String TARGET_22 = "target_22";
  public static java.lang.String TARGET_23 = "target_23";
  public static java.lang.String TARGET_24 = "target_24";
  public static java.lang.String TARGET_25 = "target_25";
  public static java.lang.String TARGET_26 = "target_26";
  public static java.lang.String TARGET_27 = "target_27";
  public static java.lang.String TARGET_28 = "target_28";
  public static java.lang.String TARGET_29 = "target_29";
  public static java.lang.String TARGET_3 = "target_3";
  public static java.lang.String TARGET_30 = "target_30";
  public static java.lang.String TARGET_31 = "target_31";
  public static java.lang.String TARGET_32 = "target_32";
  public static java.lang.String TARGET_33 = "target_33";
  public static java.lang.String TARGET_34 = "target_34";
  public static java.lang.String TARGET_35 = "target_35";
  public static java.lang.String TARGET_36 = "target_36";
  public static java.lang.String TARGET_37 = "target_37";
  public static java.lang.String TARGET_38 = "target_38";
  public static java.lang.String TARGET_39 = "target_39";
  public static java.lang.String TARGET_4 = "target_4";
  public static java.lang.String TARGET_40 = "target_40";
  public static java.lang.String TARGET_41 = "target_41";
  public static java.lang.String TARGET_42 = "target_42";
  public static java.lang.String TARGET_43 = "target_43";
  public static java.lang.String TARGET_44 = "target_44";
  public static java.lang.String TARGET_45 = "target_45";
  public static java.lang.String TARGET_46 = "target_46";
  public static java.lang.String TARGET_47 = "target_47";
  public static java.lang.String TARGET_48 = "target_48";
  public static java.lang.String TARGET_49 = "target_49";
  public static java.lang.String TARGET_5 = "target_5";
  public static java.lang.String TARGET_50 = "target_50";
  public static java.lang.String TARGET_51 = "target_51";
  public static java.lang.String TARGET_52 = "target_52";
  public static java.lang.String TARGET_6 = "target_6";
  public static java.lang.String TARGET_7 = "target_7";
  public static java.lang.String TARGET_8 = "target_8";
  public static java.lang.String TARGET_9 = "target_9";
  public static java.lang.String TARGETERID = "targeterId";
  public String getSeasonName()
  {
    return getValue(SEASONNAME);
  }
  
  public void setSeasonName(String value)
  {
    if(value == null)
    {
      setValue(SEASONNAME, "");
    }
    else
    {
      setValue(SEASONNAME, value);
    }
  }
  
  public boolean isSeasonNameWritable()
  {
    return isWritable(SEASONNAME);
  }
  
  public boolean isSeasonNameReadable()
  {
    return isReadable(SEASONNAME);
  }
  
  public boolean isSeasonNameModified()
  {
    return isModified(SEASONNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSeasonNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEASONNAME).getAttributeMdDTO();
  }
  
  public Integer getTarget_0()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_0));
  }
  
  public void setTarget_0(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_0, "");
    }
    else
    {
      setValue(TARGET_0, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_0Writable()
  {
    return isWritable(TARGET_0);
  }
  
  public boolean isTarget_0Readable()
  {
    return isReadable(TARGET_0);
  }
  
  public boolean isTarget_0Modified()
  {
    return isModified(TARGET_0);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_0Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_0).getAttributeMdDTO();
  }
  
  public Integer getTarget_1()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_1));
  }
  
  public void setTarget_1(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_1, "");
    }
    else
    {
      setValue(TARGET_1, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_1Writable()
  {
    return isWritable(TARGET_1);
  }
  
  public boolean isTarget_1Readable()
  {
    return isReadable(TARGET_1);
  }
  
  public boolean isTarget_1Modified()
  {
    return isModified(TARGET_1);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_1Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_1).getAttributeMdDTO();
  }
  
  public Integer getTarget_10()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_10));
  }
  
  public void setTarget_10(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_10, "");
    }
    else
    {
      setValue(TARGET_10, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_10Writable()
  {
    return isWritable(TARGET_10);
  }
  
  public boolean isTarget_10Readable()
  {
    return isReadable(TARGET_10);
  }
  
  public boolean isTarget_10Modified()
  {
    return isModified(TARGET_10);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_10Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_10).getAttributeMdDTO();
  }
  
  public Integer getTarget_11()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_11));
  }
  
  public void setTarget_11(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_11, "");
    }
    else
    {
      setValue(TARGET_11, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_11Writable()
  {
    return isWritable(TARGET_11);
  }
  
  public boolean isTarget_11Readable()
  {
    return isReadable(TARGET_11);
  }
  
  public boolean isTarget_11Modified()
  {
    return isModified(TARGET_11);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_11Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_11).getAttributeMdDTO();
  }
  
  public Integer getTarget_12()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_12));
  }
  
  public void setTarget_12(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_12, "");
    }
    else
    {
      setValue(TARGET_12, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_12Writable()
  {
    return isWritable(TARGET_12);
  }
  
  public boolean isTarget_12Readable()
  {
    return isReadable(TARGET_12);
  }
  
  public boolean isTarget_12Modified()
  {
    return isModified(TARGET_12);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_12Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_12).getAttributeMdDTO();
  }
  
  public Integer getTarget_13()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_13));
  }
  
  public void setTarget_13(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_13, "");
    }
    else
    {
      setValue(TARGET_13, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_13Writable()
  {
    return isWritable(TARGET_13);
  }
  
  public boolean isTarget_13Readable()
  {
    return isReadable(TARGET_13);
  }
  
  public boolean isTarget_13Modified()
  {
    return isModified(TARGET_13);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_13Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_13).getAttributeMdDTO();
  }
  
  public Integer getTarget_14()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_14));
  }
  
  public void setTarget_14(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_14, "");
    }
    else
    {
      setValue(TARGET_14, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_14Writable()
  {
    return isWritable(TARGET_14);
  }
  
  public boolean isTarget_14Readable()
  {
    return isReadable(TARGET_14);
  }
  
  public boolean isTarget_14Modified()
  {
    return isModified(TARGET_14);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_14Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_14).getAttributeMdDTO();
  }
  
  public Integer getTarget_15()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_15));
  }
  
  public void setTarget_15(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_15, "");
    }
    else
    {
      setValue(TARGET_15, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_15Writable()
  {
    return isWritable(TARGET_15);
  }
  
  public boolean isTarget_15Readable()
  {
    return isReadable(TARGET_15);
  }
  
  public boolean isTarget_15Modified()
  {
    return isModified(TARGET_15);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_15Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_15).getAttributeMdDTO();
  }
  
  public Integer getTarget_16()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_16));
  }
  
  public void setTarget_16(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_16, "");
    }
    else
    {
      setValue(TARGET_16, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_16Writable()
  {
    return isWritable(TARGET_16);
  }
  
  public boolean isTarget_16Readable()
  {
    return isReadable(TARGET_16);
  }
  
  public boolean isTarget_16Modified()
  {
    return isModified(TARGET_16);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_16Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_16).getAttributeMdDTO();
  }
  
  public Integer getTarget_17()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_17));
  }
  
  public void setTarget_17(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_17, "");
    }
    else
    {
      setValue(TARGET_17, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_17Writable()
  {
    return isWritable(TARGET_17);
  }
  
  public boolean isTarget_17Readable()
  {
    return isReadable(TARGET_17);
  }
  
  public boolean isTarget_17Modified()
  {
    return isModified(TARGET_17);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_17Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_17).getAttributeMdDTO();
  }
  
  public Integer getTarget_18()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_18));
  }
  
  public void setTarget_18(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_18, "");
    }
    else
    {
      setValue(TARGET_18, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_18Writable()
  {
    return isWritable(TARGET_18);
  }
  
  public boolean isTarget_18Readable()
  {
    return isReadable(TARGET_18);
  }
  
  public boolean isTarget_18Modified()
  {
    return isModified(TARGET_18);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_18Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_18).getAttributeMdDTO();
  }
  
  public Integer getTarget_19()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_19));
  }
  
  public void setTarget_19(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_19, "");
    }
    else
    {
      setValue(TARGET_19, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_19Writable()
  {
    return isWritable(TARGET_19);
  }
  
  public boolean isTarget_19Readable()
  {
    return isReadable(TARGET_19);
  }
  
  public boolean isTarget_19Modified()
  {
    return isModified(TARGET_19);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_19Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_19).getAttributeMdDTO();
  }
  
  public Integer getTarget_2()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_2));
  }
  
  public void setTarget_2(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_2, "");
    }
    else
    {
      setValue(TARGET_2, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_2Writable()
  {
    return isWritable(TARGET_2);
  }
  
  public boolean isTarget_2Readable()
  {
    return isReadable(TARGET_2);
  }
  
  public boolean isTarget_2Modified()
  {
    return isModified(TARGET_2);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_2Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_2).getAttributeMdDTO();
  }
  
  public Integer getTarget_20()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_20));
  }
  
  public void setTarget_20(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_20, "");
    }
    else
    {
      setValue(TARGET_20, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_20Writable()
  {
    return isWritable(TARGET_20);
  }
  
  public boolean isTarget_20Readable()
  {
    return isReadable(TARGET_20);
  }
  
  public boolean isTarget_20Modified()
  {
    return isModified(TARGET_20);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_20Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_20).getAttributeMdDTO();
  }
  
  public Integer getTarget_21()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_21));
  }
  
  public void setTarget_21(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_21, "");
    }
    else
    {
      setValue(TARGET_21, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_21Writable()
  {
    return isWritable(TARGET_21);
  }
  
  public boolean isTarget_21Readable()
  {
    return isReadable(TARGET_21);
  }
  
  public boolean isTarget_21Modified()
  {
    return isModified(TARGET_21);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_21Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_21).getAttributeMdDTO();
  }
  
  public Integer getTarget_22()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_22));
  }
  
  public void setTarget_22(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_22, "");
    }
    else
    {
      setValue(TARGET_22, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_22Writable()
  {
    return isWritable(TARGET_22);
  }
  
  public boolean isTarget_22Readable()
  {
    return isReadable(TARGET_22);
  }
  
  public boolean isTarget_22Modified()
  {
    return isModified(TARGET_22);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_22Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_22).getAttributeMdDTO();
  }
  
  public Integer getTarget_23()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_23));
  }
  
  public void setTarget_23(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_23, "");
    }
    else
    {
      setValue(TARGET_23, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_23Writable()
  {
    return isWritable(TARGET_23);
  }
  
  public boolean isTarget_23Readable()
  {
    return isReadable(TARGET_23);
  }
  
  public boolean isTarget_23Modified()
  {
    return isModified(TARGET_23);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_23Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_23).getAttributeMdDTO();
  }
  
  public Integer getTarget_24()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_24));
  }
  
  public void setTarget_24(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_24, "");
    }
    else
    {
      setValue(TARGET_24, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_24Writable()
  {
    return isWritable(TARGET_24);
  }
  
  public boolean isTarget_24Readable()
  {
    return isReadable(TARGET_24);
  }
  
  public boolean isTarget_24Modified()
  {
    return isModified(TARGET_24);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_24Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_24).getAttributeMdDTO();
  }
  
  public Integer getTarget_25()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_25));
  }
  
  public void setTarget_25(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_25, "");
    }
    else
    {
      setValue(TARGET_25, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_25Writable()
  {
    return isWritable(TARGET_25);
  }
  
  public boolean isTarget_25Readable()
  {
    return isReadable(TARGET_25);
  }
  
  public boolean isTarget_25Modified()
  {
    return isModified(TARGET_25);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_25Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_25).getAttributeMdDTO();
  }
  
  public Integer getTarget_26()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_26));
  }
  
  public void setTarget_26(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_26, "");
    }
    else
    {
      setValue(TARGET_26, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_26Writable()
  {
    return isWritable(TARGET_26);
  }
  
  public boolean isTarget_26Readable()
  {
    return isReadable(TARGET_26);
  }
  
  public boolean isTarget_26Modified()
  {
    return isModified(TARGET_26);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_26Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_26).getAttributeMdDTO();
  }
  
  public Integer getTarget_27()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_27));
  }
  
  public void setTarget_27(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_27, "");
    }
    else
    {
      setValue(TARGET_27, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_27Writable()
  {
    return isWritable(TARGET_27);
  }
  
  public boolean isTarget_27Readable()
  {
    return isReadable(TARGET_27);
  }
  
  public boolean isTarget_27Modified()
  {
    return isModified(TARGET_27);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_27Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_27).getAttributeMdDTO();
  }
  
  public Integer getTarget_28()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_28));
  }
  
  public void setTarget_28(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_28, "");
    }
    else
    {
      setValue(TARGET_28, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_28Writable()
  {
    return isWritable(TARGET_28);
  }
  
  public boolean isTarget_28Readable()
  {
    return isReadable(TARGET_28);
  }
  
  public boolean isTarget_28Modified()
  {
    return isModified(TARGET_28);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_28Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_28).getAttributeMdDTO();
  }
  
  public Integer getTarget_29()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_29));
  }
  
  public void setTarget_29(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_29, "");
    }
    else
    {
      setValue(TARGET_29, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_29Writable()
  {
    return isWritable(TARGET_29);
  }
  
  public boolean isTarget_29Readable()
  {
    return isReadable(TARGET_29);
  }
  
  public boolean isTarget_29Modified()
  {
    return isModified(TARGET_29);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_29Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_29).getAttributeMdDTO();
  }
  
  public Integer getTarget_3()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_3));
  }
  
  public void setTarget_3(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_3, "");
    }
    else
    {
      setValue(TARGET_3, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_3Writable()
  {
    return isWritable(TARGET_3);
  }
  
  public boolean isTarget_3Readable()
  {
    return isReadable(TARGET_3);
  }
  
  public boolean isTarget_3Modified()
  {
    return isModified(TARGET_3);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_3Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_3).getAttributeMdDTO();
  }
  
  public Integer getTarget_30()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_30));
  }
  
  public void setTarget_30(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_30, "");
    }
    else
    {
      setValue(TARGET_30, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_30Writable()
  {
    return isWritable(TARGET_30);
  }
  
  public boolean isTarget_30Readable()
  {
    return isReadable(TARGET_30);
  }
  
  public boolean isTarget_30Modified()
  {
    return isModified(TARGET_30);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_30Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_30).getAttributeMdDTO();
  }
  
  public Integer getTarget_31()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_31));
  }
  
  public void setTarget_31(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_31, "");
    }
    else
    {
      setValue(TARGET_31, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_31Writable()
  {
    return isWritable(TARGET_31);
  }
  
  public boolean isTarget_31Readable()
  {
    return isReadable(TARGET_31);
  }
  
  public boolean isTarget_31Modified()
  {
    return isModified(TARGET_31);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_31Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_31).getAttributeMdDTO();
  }
  
  public Integer getTarget_32()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_32));
  }
  
  public void setTarget_32(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_32, "");
    }
    else
    {
      setValue(TARGET_32, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_32Writable()
  {
    return isWritable(TARGET_32);
  }
  
  public boolean isTarget_32Readable()
  {
    return isReadable(TARGET_32);
  }
  
  public boolean isTarget_32Modified()
  {
    return isModified(TARGET_32);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_32Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_32).getAttributeMdDTO();
  }
  
  public Integer getTarget_33()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_33));
  }
  
  public void setTarget_33(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_33, "");
    }
    else
    {
      setValue(TARGET_33, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_33Writable()
  {
    return isWritable(TARGET_33);
  }
  
  public boolean isTarget_33Readable()
  {
    return isReadable(TARGET_33);
  }
  
  public boolean isTarget_33Modified()
  {
    return isModified(TARGET_33);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_33Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_33).getAttributeMdDTO();
  }
  
  public Integer getTarget_34()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_34));
  }
  
  public void setTarget_34(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_34, "");
    }
    else
    {
      setValue(TARGET_34, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_34Writable()
  {
    return isWritable(TARGET_34);
  }
  
  public boolean isTarget_34Readable()
  {
    return isReadable(TARGET_34);
  }
  
  public boolean isTarget_34Modified()
  {
    return isModified(TARGET_34);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_34Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_34).getAttributeMdDTO();
  }
  
  public Integer getTarget_35()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_35));
  }
  
  public void setTarget_35(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_35, "");
    }
    else
    {
      setValue(TARGET_35, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_35Writable()
  {
    return isWritable(TARGET_35);
  }
  
  public boolean isTarget_35Readable()
  {
    return isReadable(TARGET_35);
  }
  
  public boolean isTarget_35Modified()
  {
    return isModified(TARGET_35);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_35Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_35).getAttributeMdDTO();
  }
  
  public Integer getTarget_36()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_36));
  }
  
  public void setTarget_36(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_36, "");
    }
    else
    {
      setValue(TARGET_36, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_36Writable()
  {
    return isWritable(TARGET_36);
  }
  
  public boolean isTarget_36Readable()
  {
    return isReadable(TARGET_36);
  }
  
  public boolean isTarget_36Modified()
  {
    return isModified(TARGET_36);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_36Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_36).getAttributeMdDTO();
  }
  
  public Integer getTarget_37()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_37));
  }
  
  public void setTarget_37(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_37, "");
    }
    else
    {
      setValue(TARGET_37, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_37Writable()
  {
    return isWritable(TARGET_37);
  }
  
  public boolean isTarget_37Readable()
  {
    return isReadable(TARGET_37);
  }
  
  public boolean isTarget_37Modified()
  {
    return isModified(TARGET_37);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_37Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_37).getAttributeMdDTO();
  }
  
  public Integer getTarget_38()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_38));
  }
  
  public void setTarget_38(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_38, "");
    }
    else
    {
      setValue(TARGET_38, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_38Writable()
  {
    return isWritable(TARGET_38);
  }
  
  public boolean isTarget_38Readable()
  {
    return isReadable(TARGET_38);
  }
  
  public boolean isTarget_38Modified()
  {
    return isModified(TARGET_38);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_38Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_38).getAttributeMdDTO();
  }
  
  public Integer getTarget_39()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_39));
  }
  
  public void setTarget_39(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_39, "");
    }
    else
    {
      setValue(TARGET_39, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_39Writable()
  {
    return isWritable(TARGET_39);
  }
  
  public boolean isTarget_39Readable()
  {
    return isReadable(TARGET_39);
  }
  
  public boolean isTarget_39Modified()
  {
    return isModified(TARGET_39);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_39Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_39).getAttributeMdDTO();
  }
  
  public Integer getTarget_4()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_4));
  }
  
  public void setTarget_4(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_4, "");
    }
    else
    {
      setValue(TARGET_4, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_4Writable()
  {
    return isWritable(TARGET_4);
  }
  
  public boolean isTarget_4Readable()
  {
    return isReadable(TARGET_4);
  }
  
  public boolean isTarget_4Modified()
  {
    return isModified(TARGET_4);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_4Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_4).getAttributeMdDTO();
  }
  
  public Integer getTarget_40()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_40));
  }
  
  public void setTarget_40(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_40, "");
    }
    else
    {
      setValue(TARGET_40, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_40Writable()
  {
    return isWritable(TARGET_40);
  }
  
  public boolean isTarget_40Readable()
  {
    return isReadable(TARGET_40);
  }
  
  public boolean isTarget_40Modified()
  {
    return isModified(TARGET_40);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_40Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_40).getAttributeMdDTO();
  }
  
  public Integer getTarget_41()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_41));
  }
  
  public void setTarget_41(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_41, "");
    }
    else
    {
      setValue(TARGET_41, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_41Writable()
  {
    return isWritable(TARGET_41);
  }
  
  public boolean isTarget_41Readable()
  {
    return isReadable(TARGET_41);
  }
  
  public boolean isTarget_41Modified()
  {
    return isModified(TARGET_41);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_41Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_41).getAttributeMdDTO();
  }
  
  public Integer getTarget_42()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_42));
  }
  
  public void setTarget_42(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_42, "");
    }
    else
    {
      setValue(TARGET_42, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_42Writable()
  {
    return isWritable(TARGET_42);
  }
  
  public boolean isTarget_42Readable()
  {
    return isReadable(TARGET_42);
  }
  
  public boolean isTarget_42Modified()
  {
    return isModified(TARGET_42);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_42Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_42).getAttributeMdDTO();
  }
  
  public Integer getTarget_43()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_43));
  }
  
  public void setTarget_43(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_43, "");
    }
    else
    {
      setValue(TARGET_43, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_43Writable()
  {
    return isWritable(TARGET_43);
  }
  
  public boolean isTarget_43Readable()
  {
    return isReadable(TARGET_43);
  }
  
  public boolean isTarget_43Modified()
  {
    return isModified(TARGET_43);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_43Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_43).getAttributeMdDTO();
  }
  
  public Integer getTarget_44()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_44));
  }
  
  public void setTarget_44(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_44, "");
    }
    else
    {
      setValue(TARGET_44, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_44Writable()
  {
    return isWritable(TARGET_44);
  }
  
  public boolean isTarget_44Readable()
  {
    return isReadable(TARGET_44);
  }
  
  public boolean isTarget_44Modified()
  {
    return isModified(TARGET_44);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_44Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_44).getAttributeMdDTO();
  }
  
  public Integer getTarget_45()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_45));
  }
  
  public void setTarget_45(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_45, "");
    }
    else
    {
      setValue(TARGET_45, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_45Writable()
  {
    return isWritable(TARGET_45);
  }
  
  public boolean isTarget_45Readable()
  {
    return isReadable(TARGET_45);
  }
  
  public boolean isTarget_45Modified()
  {
    return isModified(TARGET_45);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_45Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_45).getAttributeMdDTO();
  }
  
  public Integer getTarget_46()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_46));
  }
  
  public void setTarget_46(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_46, "");
    }
    else
    {
      setValue(TARGET_46, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_46Writable()
  {
    return isWritable(TARGET_46);
  }
  
  public boolean isTarget_46Readable()
  {
    return isReadable(TARGET_46);
  }
  
  public boolean isTarget_46Modified()
  {
    return isModified(TARGET_46);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_46Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_46).getAttributeMdDTO();
  }
  
  public Integer getTarget_47()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_47));
  }
  
  public void setTarget_47(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_47, "");
    }
    else
    {
      setValue(TARGET_47, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_47Writable()
  {
    return isWritable(TARGET_47);
  }
  
  public boolean isTarget_47Readable()
  {
    return isReadable(TARGET_47);
  }
  
  public boolean isTarget_47Modified()
  {
    return isModified(TARGET_47);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_47Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_47).getAttributeMdDTO();
  }
  
  public Integer getTarget_48()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_48));
  }
  
  public void setTarget_48(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_48, "");
    }
    else
    {
      setValue(TARGET_48, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_48Writable()
  {
    return isWritable(TARGET_48);
  }
  
  public boolean isTarget_48Readable()
  {
    return isReadable(TARGET_48);
  }
  
  public boolean isTarget_48Modified()
  {
    return isModified(TARGET_48);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_48Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_48).getAttributeMdDTO();
  }
  
  public Integer getTarget_49()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_49));
  }
  
  public void setTarget_49(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_49, "");
    }
    else
    {
      setValue(TARGET_49, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_49Writable()
  {
    return isWritable(TARGET_49);
  }
  
  public boolean isTarget_49Readable()
  {
    return isReadable(TARGET_49);
  }
  
  public boolean isTarget_49Modified()
  {
    return isModified(TARGET_49);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_49Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_49).getAttributeMdDTO();
  }
  
  public Integer getTarget_5()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_5));
  }
  
  public void setTarget_5(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_5, "");
    }
    else
    {
      setValue(TARGET_5, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_5Writable()
  {
    return isWritable(TARGET_5);
  }
  
  public boolean isTarget_5Readable()
  {
    return isReadable(TARGET_5);
  }
  
  public boolean isTarget_5Modified()
  {
    return isModified(TARGET_5);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_5Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_5).getAttributeMdDTO();
  }
  
  public Integer getTarget_50()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_50));
  }
  
  public void setTarget_50(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_50, "");
    }
    else
    {
      setValue(TARGET_50, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_50Writable()
  {
    return isWritable(TARGET_50);
  }
  
  public boolean isTarget_50Readable()
  {
    return isReadable(TARGET_50);
  }
  
  public boolean isTarget_50Modified()
  {
    return isModified(TARGET_50);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_50Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_50).getAttributeMdDTO();
  }
  
  public Integer getTarget_51()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_51));
  }
  
  public void setTarget_51(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_51, "");
    }
    else
    {
      setValue(TARGET_51, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_51Writable()
  {
    return isWritable(TARGET_51);
  }
  
  public boolean isTarget_51Readable()
  {
    return isReadable(TARGET_51);
  }
  
  public boolean isTarget_51Modified()
  {
    return isModified(TARGET_51);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_51Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_51).getAttributeMdDTO();
  }
  
  public Integer getTarget_52()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_52));
  }
  
  public void setTarget_52(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_52, "");
    }
    else
    {
      setValue(TARGET_52, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_52Writable()
  {
    return isWritable(TARGET_52);
  }
  
  public boolean isTarget_52Readable()
  {
    return isReadable(TARGET_52);
  }
  
  public boolean isTarget_52Modified()
  {
    return isModified(TARGET_52);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_52Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_52).getAttributeMdDTO();
  }
  
  public Integer getTarget_6()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_6));
  }
  
  public void setTarget_6(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_6, "");
    }
    else
    {
      setValue(TARGET_6, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_6Writable()
  {
    return isWritable(TARGET_6);
  }
  
  public boolean isTarget_6Readable()
  {
    return isReadable(TARGET_6);
  }
  
  public boolean isTarget_6Modified()
  {
    return isModified(TARGET_6);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_6Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_6).getAttributeMdDTO();
  }
  
  public Integer getTarget_7()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_7));
  }
  
  public void setTarget_7(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_7, "");
    }
    else
    {
      setValue(TARGET_7, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_7Writable()
  {
    return isWritable(TARGET_7);
  }
  
  public boolean isTarget_7Readable()
  {
    return isReadable(TARGET_7);
  }
  
  public boolean isTarget_7Modified()
  {
    return isModified(TARGET_7);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_7Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_7).getAttributeMdDTO();
  }
  
  public Integer getTarget_8()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_8));
  }
  
  public void setTarget_8(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_8, "");
    }
    else
    {
      setValue(TARGET_8, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_8Writable()
  {
    return isWritable(TARGET_8);
  }
  
  public boolean isTarget_8Readable()
  {
    return isReadable(TARGET_8);
  }
  
  public boolean isTarget_8Modified()
  {
    return isModified(TARGET_8);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_8Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_8).getAttributeMdDTO();
  }
  
  public Integer getTarget_9()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET_9));
  }
  
  public void setTarget_9(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET_9, "");
    }
    else
    {
      setValue(TARGET_9, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTarget_9Writable()
  {
    return isWritable(TARGET_9);
  }
  
  public boolean isTarget_9Readable()
  {
    return isReadable(TARGET_9);
  }
  
  public boolean isTarget_9Modified()
  {
    return isModified(TARGET_9);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTarget_9Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET_9).getAttributeMdDTO();
  }
  
  public String getTargeterId()
  {
    return getValue(TARGETERID);
  }
  
  public void setTargeterId(String value)
  {
    if(value == null)
    {
      setValue(TARGETERID, "");
    }
    else
    {
      setValue(TARGETERID, value);
    }
  }
  
  public boolean isTargeterIdWritable()
  {
    return isWritable(TARGETERID);
  }
  
  public boolean isTargeterIdReadable()
  {
    return isReadable(TARGETERID);
  }
  
  public boolean isTargeterIdModified()
  {
    return isModified(TARGETERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTargeterIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TARGETERID).getAttributeMdDTO();
  }
  
  public static ResourceTargetExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ResourceTargetExcelViewDTO) dto;
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
