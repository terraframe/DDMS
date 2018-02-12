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
package dss.vector.solutions.util.yui;

import java.util.Map;

import com.runwaysdk.ClientException;
import com.runwaysdk.business.ViewDTO;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.TermDTO;

public class TermSetup implements Reloadable
{
  private String labelKey;

  private String termKey;

  public TermSetup()
  {
    this("", null);
  }

  public TermSetup(String labelKey, String termKey)
  {
    this.labelKey = CommonGenerationUtil.upperFirstCharacter(labelKey);
    this.termKey = CommonGenerationUtil.upperFirstCharacter(termKey);
  }

  public String getLabelKey()
  {
    return labelKey;
  }

  public void setLabelKey(String labelKey)
  {
    this.labelKey = labelKey;
  }

  public void prepare(TermDTO termDTO, Map<String, ColumnSetup> map, ViewDTO view)
  {
    if (map.containsKey(labelKey))
    {
      ColumnSetup setup = map.get(labelKey);

      setup.setLabel(termDTO.getTermDisplayLabel().getValue());
    }

    if(termKey != null)
    {

      try
      {
        DTOFacade facade = new DTOFacade(termKey, view);
        facade.setValue(termDTO);
      }
      catch (Exception e)
      {
        throw new ClientException(e);
      }
    }
  }
}
