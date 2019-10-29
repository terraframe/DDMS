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

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LocalProperty;

/**
 * Interface for assay classes whose records are uniquely identified
 * by a field that follows the DDMS auto-generated specification (unless 
 * a custom identifier is provided). See {@link LocalProperty#getNextId()}
 * for specification details.
 * 
 * Because the assay classes don't extend a common domain class, this 
 * Interface serves more as a marker than provide anything
 * functional.
 */
public interface UniqueAssay extends Reloadable
{
  
  /**
   * The name of the unique assay id field on all implementing classes.
   */
  public static final String UNIQUE_ASSAY_ID = "uniqueAssayId";
  
  /**
   * 
   * @return
   */
  public boolean isNew();
  
  /**
   * 
   * @return
   */
  public String getUniqueAssayId();
  
  /**
   * 
   * @param value
   */
  public void setUniqueAssayId(String value);
}
