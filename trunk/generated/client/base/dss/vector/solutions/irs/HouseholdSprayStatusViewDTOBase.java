package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1427640970)
public abstract class HouseholdSprayStatusViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.HouseholdSprayStatusView";
  private static final long serialVersionUID = 1427640970;
  
  protected HouseholdSprayStatusViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String CATTLESHEDS = "cattleSheds";
  public static java.lang.String CATTLESHEDSLOCKED = "cattleShedsLocked";
  public static java.lang.String CATTLESHEDSOTHER = "cattleShedsOther";
  public static java.lang.String CATTLESHEDSREFUSED = "cattleShedsRefused";
  public static java.lang.String CATTLESHEDSSPRAYED = "cattleShedsSprayed";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String NUMBERCHILDRENUNDERFIVEPROTECTED = "numberChildrenUnderFiveProtected";
  public static java.lang.String NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS = "numberChildrenUnderFiveSleepingUnderItns";
  public static java.lang.String NUMBERFEMALESPROTECTED = "numberFemalesProtected";
  public static java.lang.String NUMBERITNSINUSE = "numberItnsInUse";
  public static java.lang.String NUMBERMALESPROTECTED = "numberMalesProtected";
  public static java.lang.String NUMBEROFPEOPLE = "numberOfPeople";
  public static java.lang.String NUMBERPEOPLESLEEPINGUNDERITNS = "numberPeopleSleepingUnderItns";
  public static java.lang.String NUMBERPREGNANTWOMENPROTECTED = "numberPregnantWomenProtected";
  public static java.lang.String NUMBERPREGNANTWOMENSLEEPINGUNDERITNS = "numberPregnantWomenSleepingUnderItns";
  public static java.lang.String NUMBERROOMSNOTSPRAYEDSICK = "numberRoomsNotSprayedSick";
  public static java.lang.String OTHER = "other";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String REASONNOTSPRAYED = "reasonNotSprayed";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SPRAY = "spray";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTUREID = "structureId";
  public static java.lang.String STRUCTURETYPE = "structureType";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String STRUCTURESNOTSPRAYEDFUNERAL = "structuresNotSprayedFuneral";
  public static java.lang.String STRUCTURESNOTSPRAYEDLOCKED = "structuresNotSprayedLocked";
  public static java.lang.String STRUCTURESNOTSPRAYEDNOONEHOME = "structuresNotSprayedNoOneHome";
  public static java.lang.String STRUCTURESNOTSPRAYEDOTHER = "structuresNotSprayedOther";
  public static java.lang.String STRUCTURESNOTSPRAYEDREFUSED = "structuresNotSprayedRefused";
  public static java.lang.String STRUCTURESNOTSPRAYEDSICK = "structuresNotSprayedSick";
  public static java.lang.String VERANDAS = "verandas";
  public static java.lang.String VERANDASLOCKED = "verandasLocked";
  public static java.lang.String VERANDASOTHER = "verandasOther";
  public static java.lang.String VERANDASREFUSED = "verandasRefused";
  public static java.lang.String VERANDASSPRAYED = "verandasSprayed";
  public static java.lang.String WRONGSURFACE = "wrongSurface";
  public Integer getBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
  }
  
  public void setBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(BEDNETS, "");
    }
    else
    {
      setValue(BEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isBedNetsWritable()
  {
    return isWritable(BEDNETS);
  }
  
  public boolean isBedNetsReadable()
  {
    return isReadable(BEDNETS);
  }
  
  public boolean isBedNetsModified()
  {
    return isModified(BEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(BEDNETS).getAttributeMdDTO();
  }
  
  public Integer getCattleSheds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDS));
  }
  
  public void setCattleSheds(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDS, "");
    }
    else
    {
      setValue(CATTLESHEDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsWritable()
  {
    return isWritable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsReadable()
  {
    return isReadable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsModified()
  {
    return isModified(CATTLESHEDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDS).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSLOCKED));
  }
  
  public void setCattleShedsLocked(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSLOCKED, "");
    }
    else
    {
      setValue(CATTLESHEDSLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsLockedWritable()
  {
    return isWritable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedReadable()
  {
    return isReadable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedModified()
  {
    return isModified(CATTLESHEDSLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSLOCKED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSOTHER));
  }
  
  public void setCattleShedsOther(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSOTHER, "");
    }
    else
    {
      setValue(CATTLESHEDSOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsOtherWritable()
  {
    return isWritable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherReadable()
  {
    return isReadable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherModified()
  {
    return isModified(CATTLESHEDSOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSOTHER).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSREFUSED));
  }
  
  public void setCattleShedsRefused(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSREFUSED, "");
    }
    else
    {
      setValue(CATTLESHEDSREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsRefusedWritable()
  {
    return isWritable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedReadable()
  {
    return isReadable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedModified()
  {
    return isModified(CATTLESHEDSREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSREFUSED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSSPRAYED));
  }
  
  public void setCattleShedsSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSSPRAYED, "");
    }
    else
    {
      setValue(CATTLESHEDSSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsSprayedWritable()
  {
    return isWritable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedReadable()
  {
    return isReadable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedModified()
  {
    return isModified(CATTLESHEDSSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSSPRAYED).getAttributeMdDTO();
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
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public boolean isHouseholdIdWritable()
  {
    return isWritable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdReadable()
  {
    return isReadable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdModified()
  {
    return isModified(HOUSEHOLDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDID).getAttributeMdDTO();
  }
  
  public Integer getHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
  }
  
  public void setHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDS, "");
    }
    else
    {
      setValue(HOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHouseholdsWritable()
  {
    return isWritable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsReadable()
  {
    return isReadable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsModified()
  {
    return isModified(HOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
  }
  
  public void setLocked(Integer value)
  {
    if(value == null)
    {
      setValue(LOCKED, "");
    }
    else
    {
      setValue(LOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLockedWritable()
  {
    return isWritable(LOCKED);
  }
  
  public boolean isLockedReadable()
  {
    return isReadable(LOCKED);
  }
  
  public boolean isLockedModified()
  {
    return isModified(LOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOCKED).getAttributeMdDTO();
  }
  
  public Integer getNumberChildrenUnderFiveProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCHILDRENUNDERFIVEPROTECTED));
  }
  
  public void setNumberChildrenUnderFiveProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCHILDRENUNDERFIVEPROTECTED, "");
    }
    else
    {
      setValue(NUMBERCHILDRENUNDERFIVEPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberChildrenUnderFiveProtectedWritable()
  {
    return isWritable(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public boolean isNumberChildrenUnderFiveProtectedReadable()
  {
    return isReadable(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public boolean isNumberChildrenUnderFiveProtectedModified()
  {
    return isModified(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberChildrenUnderFiveProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCHILDRENUNDERFIVEPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberChildrenUnderFiveSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS));
  }
  
  public void setNumberChildrenUnderFiveSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsModified()
  {
    return isModified(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberChildrenUnderFiveSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberFemalesProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERFEMALESPROTECTED));
  }
  
  public void setNumberFemalesProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERFEMALESPROTECTED, "");
    }
    else
    {
      setValue(NUMBERFEMALESPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberFemalesProtectedWritable()
  {
    return isWritable(NUMBERFEMALESPROTECTED);
  }
  
  public boolean isNumberFemalesProtectedReadable()
  {
    return isReadable(NUMBERFEMALESPROTECTED);
  }
  
  public boolean isNumberFemalesProtectedModified()
  {
    return isModified(NUMBERFEMALESPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberFemalesProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERFEMALESPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberItnsInUse()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERITNSINUSE));
  }
  
  public void setNumberItnsInUse(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERITNSINUSE, "");
    }
    else
    {
      setValue(NUMBERITNSINUSE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberItnsInUseWritable()
  {
    return isWritable(NUMBERITNSINUSE);
  }
  
  public boolean isNumberItnsInUseReadable()
  {
    return isReadable(NUMBERITNSINUSE);
  }
  
  public boolean isNumberItnsInUseModified()
  {
    return isModified(NUMBERITNSINUSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberItnsInUseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERITNSINUSE).getAttributeMdDTO();
  }
  
  public Integer getNumberMalesProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERMALESPROTECTED));
  }
  
  public void setNumberMalesProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERMALESPROTECTED, "");
    }
    else
    {
      setValue(NUMBERMALESPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberMalesProtectedWritable()
  {
    return isWritable(NUMBERMALESPROTECTED);
  }
  
  public boolean isNumberMalesProtectedReadable()
  {
    return isReadable(NUMBERMALESPROTECTED);
  }
  
  public boolean isNumberMalesProtectedModified()
  {
    return isModified(NUMBERMALESPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberMalesProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERMALESPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberOfPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFPEOPLE));
  }
  
  public void setNumberOfPeople(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFPEOPLE, "");
    }
    else
    {
      setValue(NUMBEROFPEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfPeopleWritable()
  {
    return isWritable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleReadable()
  {
    return isReadable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleModified()
  {
    return isModified(NUMBEROFPEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFPEOPLE).getAttributeMdDTO();
  }
  
  public Integer getNumberPeopleSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPEOPLESLEEPINGUNDERITNS));
  }
  
  public void setNumberPeopleSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPEOPLESLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERPEOPLESLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPeopleSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPeopleSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPeopleSleepingUnderItnsModified()
  {
    return isModified(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPeopleSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPEOPLESLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantWomenProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTWOMENPROTECTED));
  }
  
  public void setNumberPregnantWomenProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTWOMENPROTECTED, "");
    }
    else
    {
      setValue(NUMBERPREGNANTWOMENPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWomenProtectedWritable()
  {
    return isWritable(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public boolean isNumberPregnantWomenProtectedReadable()
  {
    return isReadable(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public boolean isNumberPregnantWomenProtectedModified()
  {
    return isModified(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantWomenProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTWOMENPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantWomenSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS));
  }
  
  public void setNumberPregnantWomenSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsModified()
  {
    return isModified(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantWomenSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberRoomsNotSprayedSick()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERROOMSNOTSPRAYEDSICK));
  }
  
  public void setNumberRoomsNotSprayedSick(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERROOMSNOTSPRAYEDSICK, "");
    }
    else
    {
      setValue(NUMBERROOMSNOTSPRAYEDSICK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRoomsNotSprayedSickWritable()
  {
    return isWritable(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public boolean isNumberRoomsNotSprayedSickReadable()
  {
    return isReadable(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public boolean isNumberRoomsNotSprayedSickModified()
  {
    return isModified(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberRoomsNotSprayedSickMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERROOMSNOTSPRAYEDSICK).getAttributeMdDTO();
  }
  
  public Integer getOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
  }
  
  public void setOther(Integer value)
  {
    if(value == null)
    {
      setValue(OTHER, "");
    }
    else
    {
      setValue(OTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOtherWritable()
  {
    return isWritable(OTHER);
  }
  
  public boolean isOtherReadable()
  {
    return isReadable(OTHER);
  }
  
  public boolean isOtherModified()
  {
    return isModified(OTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHER).getAttributeMdDTO();
  }
  
  public Integer getPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void setPeople(Integer value)
  {
    if(value == null)
    {
      setValue(PEOPLE, "");
    }
    else
    {
      setValue(PEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeopleWritable()
  {
    return isWritable(PEOPLE);
  }
  
  public boolean isPeopleReadable()
  {
    return isReadable(PEOPLE);
  }
  
  public boolean isPeopleModified()
  {
    return isModified(PEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
  }
  
  public void setPrevSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedHouseholdsWritable()
  {
    return isWritable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsReadable()
  {
    return isReadable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsModified()
  {
    return isModified(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
  }
  
  public void setPrevSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(PREVSPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedStructuresWritable()
  {
    return isWritable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresReadable()
  {
    return isReadable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresModified()
  {
    return isModified(PREVSPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getReasonNotSprayed()
  {
    if(getValue(REASONNOTSPRAYED) == null || getValue(REASONNOTSPRAYED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(REASONNOTSPRAYED));
    }
  }
  
  public String getReasonNotSprayedId()
  {
    return getValue(REASONNOTSPRAYED);
  }
  
  public void setReasonNotSprayed(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(REASONNOTSPRAYED, "");
    }
    else
    {
      setValue(REASONNOTSPRAYED, value.getId());
    }
  }
  
  public boolean isReasonNotSprayedWritable()
  {
    return isWritable(REASONNOTSPRAYED);
  }
  
  public boolean isReasonNotSprayedReadable()
  {
    return isReadable(REASONNOTSPRAYED);
  }
  
  public boolean isReasonNotSprayedModified()
  {
    return isModified(REASONNOTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getReasonNotSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(REASONNOTSPRAYED).getAttributeMdDTO();
  }
  
  public Integer getRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
  }
  
  public void setRefused(Integer value)
  {
    if(value == null)
    {
      setValue(REFUSED, "");
    }
    else
    {
      setValue(REFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefusedWritable()
  {
    return isWritable(REFUSED);
  }
  
  public boolean isRefusedReadable()
  {
    return isReadable(REFUSED);
  }
  
  public boolean isRefusedModified()
  {
    return isModified(REFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFUSED).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void setRooms(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMS, "");
    }
    else
    {
      setValue(ROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWritable()
  {
    return isWritable(ROOMS);
  }
  
  public boolean isRoomsReadable()
  {
    return isReadable(ROOMS);
  }
  
  public boolean isRoomsModified()
  {
    return isModified(ROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public Integer getRoomsWithBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
  }
  
  public void setRoomsWithBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMSWITHBEDNETS, "");
    }
    else
    {
      setValue(ROOMSWITHBEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWithBedNetsWritable()
  {
    return isWritable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsReadable()
  {
    return isReadable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsModified()
  {
    return isModified(ROOMSWITHBEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsWithBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMSWITHBEDNETS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.OperatorSprayDTO getSpray()
  {
    if(getValue(SPRAY) == null || getValue(SPRAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.OperatorSprayDTO.get(getRequest(), getValue(SPRAY));
    }
  }
  
  public String getSprayId()
  {
    return getValue(SPRAY);
  }
  
  public void setSpray(dss.vector.solutions.irs.OperatorSprayDTO value)
  {
    if(value == null)
    {
      setValue(SPRAY, "");
    }
    else
    {
      setValue(SPRAY, value.getId());
    }
  }
  
  public boolean isSprayWritable()
  {
    return isWritable(SPRAY);
  }
  
  public boolean isSprayReadable()
  {
    return isReadable(SPRAY);
  }
  
  public boolean isSprayModified()
  {
    return isModified(SPRAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAY).getAttributeMdDTO();
  }
  
  public Integer getSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
  }
  
  public void setSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(SPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedHouseholdsWritable()
  {
    return isWritable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsReadable()
  {
    return isReadable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsModified()
  {
    return isModified(SPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getSprayedRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
  }
  
  public void setSprayedRooms(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDROOMS, "");
    }
    else
    {
      setValue(SPRAYEDROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedRoomsWritable()
  {
    return isWritable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsReadable()
  {
    return isReadable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsModified()
  {
    return isModified(SPRAYEDROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDROOMS).getAttributeMdDTO();
  }
  
  public Integer getSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
  }
  
  public void setSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(SPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedStructuresWritable()
  {
    return isWritable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresReadable()
  {
    return isReadable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresModified()
  {
    return isModified(SPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
    }
  }
  
  public boolean isStructureIdWritable()
  {
    return isWritable(STRUCTUREID);
  }
  
  public boolean isStructureIdReadable()
  {
    return isReadable(STRUCTUREID);
  }
  
  public boolean isStructureIdModified()
  {
    return isModified(STRUCTUREID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getStructureIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(STRUCTUREID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getStructureType()
  {
    if(getValue(STRUCTURETYPE) == null || getValue(STRUCTURETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(STRUCTURETYPE));
    }
  }
  
  public String getStructureTypeId()
  {
    return getValue(STRUCTURETYPE);
  }
  
  public void setStructureType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(STRUCTURETYPE, "");
    }
    else
    {
      setValue(STRUCTURETYPE, value.getId());
    }
  }
  
  public boolean isStructureTypeWritable()
  {
    return isWritable(STRUCTURETYPE);
  }
  
  public boolean isStructureTypeReadable()
  {
    return isReadable(STRUCTURETYPE);
  }
  
  public boolean isStructureTypeModified()
  {
    return isModified(STRUCTURETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStructureTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STRUCTURETYPE).getAttributeMdDTO();
  }
  
  public Integer getStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
  }
  
  public void setStructures(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURES, "");
    }
    else
    {
      setValue(STRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresWritable()
  {
    return isWritable(STRUCTURES);
  }
  
  public boolean isStructuresReadable()
  {
    return isReadable(STRUCTURES);
  }
  
  public boolean isStructuresModified()
  {
    return isModified(STRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedFuneral()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDFUNERAL));
  }
  
  public void setStructuresNotSprayedFuneral(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDFUNERAL, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDFUNERAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedFuneralWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public boolean isStructuresNotSprayedFuneralReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public boolean isStructuresNotSprayedFuneralModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedFuneralMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDFUNERAL).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDLOCKED));
  }
  
  public void setStructuresNotSprayedLocked(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDLOCKED, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedLockedWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public boolean isStructuresNotSprayedLockedReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public boolean isStructuresNotSprayedLockedModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDLOCKED).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedNoOneHome()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDNOONEHOME));
  }
  
  public void setStructuresNotSprayedNoOneHome(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDNOONEHOME, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDNOONEHOME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedNoOneHomeWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public boolean isStructuresNotSprayedNoOneHomeReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public boolean isStructuresNotSprayedNoOneHomeModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedNoOneHomeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDNOONEHOME).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDOTHER));
  }
  
  public void setStructuresNotSprayedOther(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDOTHER, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedOtherWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public boolean isStructuresNotSprayedOtherReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public boolean isStructuresNotSprayedOtherModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDOTHER).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDREFUSED));
  }
  
  public void setStructuresNotSprayedRefused(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDREFUSED, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedRefusedWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public boolean isStructuresNotSprayedRefusedReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public boolean isStructuresNotSprayedRefusedModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDREFUSED).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedSick()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDSICK));
  }
  
  public void setStructuresNotSprayedSick(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDSICK, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDSICK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedSickWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public boolean isStructuresNotSprayedSickReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public boolean isStructuresNotSprayedSickModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedSickMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDSICK).getAttributeMdDTO();
  }
  
  public Integer getVerandas()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDAS));
  }
  
  public void setVerandas(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDAS, "");
    }
    else
    {
      setValue(VERANDAS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasWritable()
  {
    return isWritable(VERANDAS);
  }
  
  public boolean isVerandasReadable()
  {
    return isReadable(VERANDAS);
  }
  
  public boolean isVerandasModified()
  {
    return isModified(VERANDAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDAS).getAttributeMdDTO();
  }
  
  public Integer getVerandasLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASLOCKED));
  }
  
  public void setVerandasLocked(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASLOCKED, "");
    }
    else
    {
      setValue(VERANDASLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasLockedWritable()
  {
    return isWritable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedReadable()
  {
    return isReadable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedModified()
  {
    return isModified(VERANDASLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASLOCKED).getAttributeMdDTO();
  }
  
  public Integer getVerandasOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASOTHER));
  }
  
  public void setVerandasOther(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASOTHER, "");
    }
    else
    {
      setValue(VERANDASOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasOtherWritable()
  {
    return isWritable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherReadable()
  {
    return isReadable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherModified()
  {
    return isModified(VERANDASOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASOTHER).getAttributeMdDTO();
  }
  
  public Integer getVerandasRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASREFUSED));
  }
  
  public void setVerandasRefused(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASREFUSED, "");
    }
    else
    {
      setValue(VERANDASREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasRefusedWritable()
  {
    return isWritable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedReadable()
  {
    return isReadable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedModified()
  {
    return isModified(VERANDASREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASREFUSED).getAttributeMdDTO();
  }
  
  public Integer getVerandasSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASSPRAYED));
  }
  
  public void setVerandasSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASSPRAYED, "");
    }
    else
    {
      setValue(VERANDASSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasSprayedWritable()
  {
    return isWritable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedReadable()
  {
    return isReadable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedModified()
  {
    return isModified(VERANDASSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASSPRAYED).getAttributeMdDTO();
  }
  
  public Integer getWrongSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WRONGSURFACE));
  }
  
  public void setWrongSurface(Integer value)
  {
    if(value == null)
    {
      setValue(WRONGSURFACE, "");
    }
    else
    {
      setValue(WRONGSURFACE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWrongSurfaceWritable()
  {
    return isWritable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceReadable()
  {
    return isReadable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceModified()
  {
    return isModified(WRONGSURFACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWrongSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WRONGSURFACE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.HouseholdSprayStatusView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String[] getGeneratedIds(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusViewDTO.CLASS, "getGeneratedIds", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static HouseholdSprayStatusViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (HouseholdSprayStatusViewDTO) dto;
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
