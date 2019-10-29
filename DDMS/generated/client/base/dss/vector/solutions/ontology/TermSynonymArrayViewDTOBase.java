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
package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 514181395)
public abstract class TermSynonymArrayViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermSynonymArrayView";
  private static final long serialVersionUID = 514181395;
  
  protected TermSynonymArrayViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMIDS = "synonymIds";
  public static java.lang.String SYNONYMNAMES = "synonymNames";
  public static java.lang.String TERM = "term";
  public static java.lang.String TERMDISPLAYLABEL = "termDisplayLabel";
  public static java.lang.String TERMINSTANCEID = "termInstanceId";
  public static java.lang.String TERMNAME = "termName";
  public String getSynonymIds()
  {
    return getValue(SYNONYMIDS);
  }
  
  public void setSynonymIds(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMIDS, "");
    }
    else
    {
      setValue(SYNONYMIDS, value);
    }
  }
  
  public boolean isSynonymIdsWritable()
  {
    return isWritable(SYNONYMIDS);
  }
  
  public boolean isSynonymIdsReadable()
  {
    return isReadable(SYNONYMIDS);
  }
  
  public boolean isSynonymIdsModified()
  {
    return isModified(SYNONYMIDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymIdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMIDS).getAttributeMdDTO();
  }
  
  public String getSynonymNames()
  {
    return getValue(SYNONYMNAMES);
  }
  
  public void setSynonymNames(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAMES, "");
    }
    else
    {
      setValue(SYNONYMNAMES, value);
    }
  }
  
  public boolean isSynonymNamesWritable()
  {
    return isWritable(SYNONYMNAMES);
  }
  
  public boolean isSynonymNamesReadable()
  {
    return isReadable(SYNONYMNAMES);
  }
  
  public boolean isSynonymNamesModified()
  {
    return isModified(SYNONYMNAMES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymNamesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMNAMES).getAttributeMdDTO();
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
  
  public String getTermDisplayLabel()
  {
    return getValue(TERMDISPLAYLABEL);
  }
  
  public void setTermDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(TERMDISPLAYLABEL, "");
    }
    else
    {
      setValue(TERMDISPLAYLABEL, value);
    }
  }
  
  public boolean isTermDisplayLabelWritable()
  {
    return isWritable(TERMDISPLAYLABEL);
  }
  
  public boolean isTermDisplayLabelReadable()
  {
    return isReadable(TERMDISPLAYLABEL);
  }
  
  public boolean isTermDisplayLabelModified()
  {
    return isModified(TERMDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getTermInstanceId()
  {
    return getValue(TERMINSTANCEID);
  }
  
  public void setTermInstanceId(String value)
  {
    if(value == null)
    {
      setValue(TERMINSTANCEID, "");
    }
    else
    {
      setValue(TERMINSTANCEID, value);
    }
  }
  
  public boolean isTermInstanceIdWritable()
  {
    return isWritable(TERMINSTANCEID);
  }
  
  public boolean isTermInstanceIdReadable()
  {
    return isReadable(TERMINSTANCEID);
  }
  
  public boolean isTermInstanceIdModified()
  {
    return isModified(TERMINSTANCEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermInstanceIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMINSTANCEID).getAttributeMdDTO();
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public boolean isTermNameWritable()
  {
    return isWritable(TERMNAME);
  }
  
  public boolean isTermNameReadable()
  {
    return isReadable(TERMNAME);
  }
  
  public boolean isTermNameModified()
  {
    return isModified(TERMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMNAME).getAttributeMdDTO();
  }
  
  public final void applyWithSynonyms(dss.vector.solutions.ontology.TermSynonymViewDTO[] synonyms)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.ontology.TermSynonymView;"};
    Object[] _parameters = new Object[]{synonyms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "applyWithSynonyms", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyWithSynonyms(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.ontology.TermSynonymViewDTO[] synonyms)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.ontology.TermSynonymView;"};
    Object[] _parameters = new Object[]{id, synonyms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "applyWithSynonyms", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermSynonymArrayViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.ontology.TermSynonymArrayViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermSynonymArrayViewDTO getTermSynonym(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String termId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{termId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "getTermSynonym", _declaredTypes);
    return (dss.vector.solutions.ontology.TermSynonymArrayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void lock()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "lock", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "lock", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermSynonymArrayViewQueryDTO searchByView(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.ontology.TermSynonymArrayViewDTO view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.ontology.TermSynonymArrayView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{view, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.ontology.TermSynonymArrayViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void unlock()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "unlock", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermSynonymArrayViewDTO.CLASS, "unlock", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TermSynonymArrayViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TermSynonymArrayViewDTO) dto;
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
