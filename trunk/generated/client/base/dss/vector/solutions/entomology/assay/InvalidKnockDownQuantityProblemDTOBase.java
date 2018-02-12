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

@com.runwaysdk.business.ClassSignature(hash = 1326299693)
public abstract class InvalidKnockDownQuantityProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem";
  private static final long serialVersionUID = 1326299693;
  
  public InvalidKnockDownQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidKnockDownQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String QUANTITYKNOCKDOWN = "quantityKnockDown";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public Integer getQuantityKnockDown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYKNOCKDOWN));
  }
  
  public void setQuantityKnockDown(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYKNOCKDOWN, "");
    }
    else
    {
      setValue(QUANTITYKNOCKDOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityKnockDownWritable()
  {
    return isWritable(QUANTITYKNOCKDOWN);
  }
  
  public boolean isQuantityKnockDownReadable()
  {
    return isReadable(QUANTITYKNOCKDOWN);
  }
  
  public boolean isQuantityKnockDownModified()
  {
    return isModified(QUANTITYKNOCKDOWN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityKnockDownMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYKNOCKDOWN).getAttributeMdDTO();
  }
  
  public Integer getQuantityTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityTestedWritable()
  {
    return isWritable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedReadable()
  {
    return isReadable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedModified()
  {
    return isModified(QUANTITYTESTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityTestedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYTESTED).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{quantityKnockDown}", this.getQuantityKnockDown().toString());
    template = template.replace("{quantityTested}", this.getQuantityTested().toString());
    
    return template;
  }
  
}
