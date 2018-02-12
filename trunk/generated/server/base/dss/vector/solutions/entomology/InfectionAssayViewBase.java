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
package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1378783980)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InfectionAssayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InfectionAssayViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InfectionAssayView";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISEASE = "disease";
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
  private static final long serialVersionUID = 1378783980;
  
  public InfectionAssayViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.entomology.MosquitoCollection getCollection()
  {
    if (getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getCollectionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollection value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
    }
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CONCRETEID);
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
  
  public dss.vector.solutions.general.Disease getDisease()
  {
    if (getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Disease.get(getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.Disease value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getIdentMethod()
  {
    if (getValue(IDENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(IDENTMETHOD));
    }
  }
  
  public String getIdentMethodId()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getIdentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(IDENTMETHOD);
  }
  
  public void setIdentMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value.getId());
    }
  }
  
  public Boolean getInfected()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(INFECTED));
  }
  
  public void validateInfected()
  {
    this.validateAttribute(INFECTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getInfectedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(INFECTED);
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
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void validateMosquitoId()
  {
    this.validateAttribute(MOSQUITOID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getMosquitoIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(MOSQUITOID);
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
  
  public Integer getNumberPositive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPOSITIVE));
  }
  
  public void validateNumberPositive()
  {
    this.validateAttribute(NUMBERPOSITIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberPositiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERPOSITIVE);
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
  
  public Integer getNumberTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERTESTED));
  }
  
  public void validateNumberTested()
  {
    this.validateAttribute(NUMBERTESTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getNumberTestedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(NUMBERTESTED);
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
  
  public dss.vector.solutions.ontology.Term getParasite()
  {
    if (getValue(PARASITE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(PARASITE));
    }
  }
  
  public String getParasiteId()
  {
    return getValue(PARASITE);
  }
  
  public void validateParasite()
  {
    this.validateAttribute(PARASITE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getParasiteMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(PARASITE);
  }
  
  public void setParasite(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(PARASITE, "");
    }
    else
    {
      setValue(PARASITE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSex()
  {
    if (getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getSexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSpecies()
  {
    if (getValue(SPECIES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SPECIES));
    }
  }
  
  public String getSpeciesId()
  {
    return getValue(SPECIES);
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getSpeciesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getTestMethod()
  {
    if (getValue(TESTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TESTMETHOD));
    }
  }
  
  public String getTestMethodId()
  {
    return getValue(TESTMETHOD);
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getTestMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value.getId());
    }
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void validateUniqueAssayId()
  {
    this.validateAttribute(UNIQUEASSAYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getUniqueAssayIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InfectionAssayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(UNIQUEASSAYID);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InfectionAssayView get(String id)
  {
    return (InfectionAssayView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.InfectionAssayView[] applyAll(dss.vector.solutions.entomology.InfectionAssayView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.InfectionAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.InfectionAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    InfectionAssayView _instance = InfectionAssayView.get(id);
    _instance.deleteConcrete();
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
