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
package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -1892866633)
public abstract class CaseDiseaseManifestationViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseDiseaseManifestationView";
  private static final long serialVersionUID = -1892866633;
  
  protected CaseDiseaseManifestationViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGGREGATEDCASE = "aggregatedCase";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISEASECATEGORY = "diseaseCategory";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  public dss.vector.solutions.surveillance.AggregatedCaseDTO getAggregatedCase()
  {
    if(getValue(AGGREGATEDCASE) == null || getValue(AGGREGATEDCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedCaseDTO.get(getRequest(), getValue(AGGREGATEDCASE));
    }
  }
  
  public String getAggregatedCaseId()
  {
    return getValue(AGGREGATEDCASE);
  }
  
  public void setAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO value)
  {
    if(value == null)
    {
      setValue(AGGREGATEDCASE, "");
    }
    else
    {
      setValue(AGGREGATEDCASE, value.getId());
    }
  }
  
  public boolean isAggregatedCaseWritable()
  {
    return isWritable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseReadable()
  {
    return isReadable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseModified()
  {
    return isModified(AGGREGATEDCASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAggregatedCaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGGREGATEDCASE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getDiseaseCategory()
  {
    if(getValue(DISEASECATEGORY) == null || getValue(DISEASECATEGORY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISEASECATEGORY));
    }
  }
  
  public String getDiseaseCategoryId()
  {
    return getValue(DISEASECATEGORY);
  }
  
  public void setDiseaseCategory(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISEASECATEGORY, "");
    }
    else
    {
      setValue(DISEASECATEGORY, value.getId());
    }
  }
  
  public boolean isDiseaseCategoryWritable()
  {
    return isWritable(DISEASECATEGORY);
  }
  
  public boolean isDiseaseCategoryReadable()
  {
    return isReadable(DISEASECATEGORY);
  }
  
  public boolean isDiseaseCategoryModified()
  {
    return isModified(DISEASECATEGORY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseCategoryMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASECATEGORY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTerm()
  {
    if(getValue(TERM) == null || getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TERM));
    }
  }
  
  public String getTermId()
  {
    return getValue(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public boolean isTermWritable()
  {
    return isWritable(TERM);
  }
  
  public boolean isTermReadable()
  {
    return isReadable(TERM);
  }
  
  public boolean isTermModified()
  {
    return isModified(TERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TERM).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[] getAmounts()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO.CLASS, "getAmounts", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[] getAmounts(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO.CLASS, "getAmounts", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[][] getAmountsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] ids)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{ids};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.surveillance.CaseDiseaseManifestationViewDTO.CLASS, "getAmountsForViews", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static CaseDiseaseManifestationViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CaseDiseaseManifestationViewDTO) dto;
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
