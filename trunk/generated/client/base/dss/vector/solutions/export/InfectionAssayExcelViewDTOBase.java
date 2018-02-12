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

@com.runwaysdk.business.ClassSignature(hash = 1601372748)
public abstract class InfectionAssayExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InfectionAssayExcelView";
  private static final long serialVersionUID = 1601372748;
  
  protected InfectionAssayExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String INFECTED = "infected";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERPOSITIVE = "numberPositive";
  public static java.lang.String NUMBERTESTED = "numberTested";
  public static java.lang.String PARASITE = "parasite";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String TESTMETHOD = "testMethod";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
  }
  
  public String getIdentMethod()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void setIdentMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value);
    }
  }
  
  public boolean isIdentMethodWritable()
  {
    return isWritable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodReadable()
  {
    return isReadable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodModified()
  {
    return isModified(IDENTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
  }
  
  public Boolean getInfected()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(INFECTED));
  }
  
  public void setInfected(Boolean value)
  {
    if(value == null)
    {
      setValue(INFECTED, "");
    }
    else
    {
      setValue(INFECTED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isInfectedWritable()
  {
    return isWritable(INFECTED);
  }
  
  public boolean isInfectedReadable()
  {
    return isReadable(INFECTED);
  }
  
  public boolean isInfectedModified()
  {
    return isModified(INFECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getInfectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(INFECTED).getAttributeMdDTO();
  }
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void setMosquitoId(String value)
  {
    if(value == null)
    {
      setValue(MOSQUITOID, "");
    }
    else
    {
      setValue(MOSQUITOID, value);
    }
  }
  
  public boolean isMosquitoIdWritable()
  {
    return isWritable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdReadable()
  {
    return isReadable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdModified()
  {
    return isModified(MOSQUITOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMosquitoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MOSQUITOID).getAttributeMdDTO();
  }
  
  public Integer getNumberPositive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPOSITIVE));
  }
  
  public void setNumberPositive(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPOSITIVE, "");
    }
    else
    {
      setValue(NUMBERPOSITIVE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPositiveWritable()
  {
    return isWritable(NUMBERPOSITIVE);
  }
  
  public boolean isNumberPositiveReadable()
  {
    return isReadable(NUMBERPOSITIVE);
  }
  
  public boolean isNumberPositiveModified()
  {
    return isModified(NUMBERPOSITIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPositiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPOSITIVE).getAttributeMdDTO();
  }
  
  public Integer getNumberTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERTESTED));
  }
  
  public void setNumberTested(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERTESTED, "");
    }
    else
    {
      setValue(NUMBERTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberTestedWritable()
  {
    return isWritable(NUMBERTESTED);
  }
  
  public boolean isNumberTestedReadable()
  {
    return isReadable(NUMBERTESTED);
  }
  
  public boolean isNumberTestedModified()
  {
    return isModified(NUMBERTESTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberTestedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERTESTED).getAttributeMdDTO();
  }
  
  public String getParasite()
  {
    return getValue(PARASITE);
  }
  
  public void setParasite(String value)
  {
    if(value == null)
    {
      setValue(PARASITE, "");
    }
    else
    {
      setValue(PARASITE, value);
    }
  }
  
  public boolean isParasiteWritable()
  {
    return isWritable(PARASITE);
  }
  
  public boolean isParasiteReadable()
  {
    return isReadable(PARASITE);
  }
  
  public boolean isParasiteModified()
  {
    return isModified(PARASITE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParasiteMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARASITE).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getSpecies()
  {
    return getValue(SPECIES);
  }
  
  public void setSpecies(String value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value);
    }
  }
  
  public boolean isSpeciesWritable()
  {
    return isWritable(SPECIES);
  }
  
  public boolean isSpeciesReadable()
  {
    return isReadable(SPECIES);
  }
  
  public boolean isSpeciesModified()
  {
    return isModified(SPECIES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSpeciesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIES).getAttributeMdDTO();
  }
  
  public String getTestMethod()
  {
    return getValue(TESTMETHOD);
  }
  
  public void setTestMethod(String value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value);
    }
  }
  
  public boolean isTestMethodWritable()
  {
    return isWritable(TESTMETHOD);
  }
  
  public boolean isTestMethodReadable()
  {
    return isReadable(TESTMETHOD);
  }
  
  public boolean isTestMethodModified()
  {
    return isModified(TESTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTestMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  public boolean isUniqueAssayIdWritable()
  {
    return isWritable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdReadable()
  {
    return isReadable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdModified()
  {
    return isModified(UNIQUEASSAYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniqueAssayIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIQUEASSAYID).getAttributeMdDTO();
  }
  
  public static InfectionAssayExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (InfectionAssayExcelViewDTO) dto;
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
