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

@com.runwaysdk.business.ClassSignature(hash = 1558655386)
public abstract class PupalCollectionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.PupalCollectionView";
  private static final long serialVersionUID = 1558655386;
  
  protected PupalCollectionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NOTES = "notes";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String PREMISEID = "premiseId";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String STARTDATE = "startDate";
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
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public String getNotes()
  {
    return getValue(NOTES);
  }
  
  public void setNotes(String value)
  {
    if(value == null)
    {
      setValue(NOTES, "");
    }
    else
    {
      setValue(NOTES, value);
    }
  }
  
  public boolean isNotesWritable()
  {
    return isWritable(NOTES);
  }
  
  public boolean isNotesReadable()
  {
    return isReadable(NOTES);
  }
  
  public boolean isNotesModified()
  {
    return isModified(NOTES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getNotesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(NOTES).getAttributeMdDTO();
  }
  
  public Integer getNumberExamined()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEREXAMINED));
  }
  
  public void setNumberExamined(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEREXAMINED, "");
    }
    else
    {
      setValue(NUMBEREXAMINED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberExaminedWritable()
  {
    return isWritable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedReadable()
  {
    return isReadable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedModified()
  {
    return isModified(NUMBEREXAMINED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberExaminedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEREXAMINED).getAttributeMdDTO();
  }
  
  public Integer getNumberInhabitants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERINHABITANTS));
  }
  
  public void setNumberInhabitants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERINHABITANTS, "");
    }
    else
    {
      setValue(NUMBERINHABITANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberInhabitantsWritable()
  {
    return isWritable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsReadable()
  {
    return isReadable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsModified()
  {
    return isModified(NUMBERINHABITANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberInhabitantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERINHABITANTS).getAttributeMdDTO();
  }
  
  public String getPremiseId()
  {
    return getValue(PREMISEID);
  }
  
  public void setPremiseId(String value)
  {
    if(value == null)
    {
      setValue(PREMISEID, "");
    }
    else
    {
      setValue(PREMISEID, value);
    }
  }
  
  public boolean isPremiseIdWritable()
  {
    return isWritable(PREMISEID);
  }
  
  public boolean isPremiseIdReadable()
  {
    return isReadable(PREMISEID);
  }
  
  public boolean isPremiseIdModified()
  {
    return isModified(PREMISEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPremiseIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PREMISEID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPremiseSize()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PREMISESIZE));
  }
  
  public void setPremiseSize(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PREMISESIZE, "");
    }
    else
    {
      setValue(PREMISESIZE, value.toString());
    }
  }
  
  public boolean isPremiseSizeWritable()
  {
    return isWritable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeReadable()
  {
    return isReadable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeModified()
  {
    return isModified(PREMISESIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPremiseSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PREMISESIZE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPremiseType()
  {
    if(getValue(PREMISETYPE) == null || getValue(PREMISETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PREMISETYPE));
    }
  }
  
  public String getPremiseTypeId()
  {
    return getValue(PREMISETYPE);
  }
  
  public void setPremiseType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value.getId());
    }
  }
  
  public boolean isPremiseTypeWritable()
  {
    return isWritable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeReadable()
  {
    return isReadable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeModified()
  {
    return isModified(PREMISETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPremiseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PREMISETYPE).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.PupalContainerViewDTO[] applyWithContainers(dss.vector.solutions.entomology.PupalContainerViewDTO[] containers, dss.vector.solutions.entomology.PupalContainerAmountViewDTO[][] amounts)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.PupalContainerView;", "[[Ldss.vector.solutions.entomology.PupalContainerAmountView;"};
    Object[] _parameters = new Object[]{containers, amounts};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "applyWithContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalContainerViewDTO[] applyWithContainers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.entomology.PupalContainerViewDTO[] containers, dss.vector.solutions.entomology.PupalContainerAmountViewDTO[][] amounts)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.entomology.PupalContainerView;", "[[Ldss.vector.solutions.entomology.PupalContainerAmountView;"};
    Object[] _parameters = new Object[]{id, containers, amounts};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "applyWithContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deletePremise()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "deletePremise", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deletePremise(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "deletePremise", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalCollectionViewDTO getCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.PupalCollectionViewDTO collection)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.PupalCollectionView"};
    Object[] _parameters = new Object[]{collection};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "getCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.PupalContainerViewDTO[] getContainers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "getContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalContainerViewDTO[] getContainers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "getContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalContainerViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalCollectionViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PupalCollectionViewQueryDTO searchCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.PupalCollectionViewDTO collection, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.PupalCollectionView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{collection, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.PupalCollectionViewDTO.CLASS, "searchCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.PupalCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PupalCollectionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PupalCollectionViewDTO) dto;
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
