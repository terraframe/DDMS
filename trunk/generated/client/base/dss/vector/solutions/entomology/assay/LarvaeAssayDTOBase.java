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
package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1590672110)
public abstract class LarvaeAssayDTOBase extends dss.vector.solutions.entomology.assay.CollectionAssayDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.LarvaeAssay";
  private static final long serialVersionUID = 1590672110;
  
  protected LarvaeAssayDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LarvaeAssayDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String STARTPOINT = "startPoint";
  public dss.vector.solutions.ontology.TermDTO getEndPoint()
  {
    if(getValue(ENDPOINT) == null || getValue(ENDPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ENDPOINT));
    }
  }
  
  public String getEndPointId()
  {
    return getValue(ENDPOINT);
  }
  
  public void setEndPoint(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ENDPOINT, "");
    }
    else
    {
      setValue(ENDPOINT, value.getId());
    }
  }
  
  public boolean isEndPointWritable()
  {
    return isWritable(ENDPOINT);
  }
  
  public boolean isEndPointReadable()
  {
    return isReadable(ENDPOINT);
  }
  
  public boolean isEndPointModified()
  {
    return isModified(ENDPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEndPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENDPOINT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getStartPoint()
  {
    if(getValue(STARTPOINT) == null || getValue(STARTPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(STARTPOINT));
    }
  }
  
  public String getStartPointId()
  {
    return getValue(STARTPOINT);
  }
  
  public void setStartPoint(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(STARTPOINT, "");
    }
    else
    {
      setValue(STARTPOINT, value.getId());
    }
  }
  
  public boolean isStartPointWritable()
  {
    return isWritable(STARTPOINT);
  }
  
  public boolean isStartPointReadable()
  {
    return isReadable(STARTPOINT);
  }
  
  public boolean isStartPointModified()
  {
    return isModified(STARTPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStartPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STARTPOINT).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.LarvaeAssayQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
