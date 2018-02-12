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
package dss.vector.solutions;

import java.util.HashMap;

import com.runwaysdk.business.generation.view.ContentListener;
import com.runwaysdk.business.generation.view.CreateComponentListener;
import com.runwaysdk.constants.GeneratedActions;
import com.runwaysdk.constants.MdActionInfo;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class MDSSCreateComponentListener extends CreateComponentListener implements ContentListener, Reloadable
{
  public MDSSCreateComponentListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  public void header()
  {
    writeIncludes();

    MDSSGenerationUtility.writePageTitle(getWriter(), "Create_" + this.getMdEntity().getTypeName());

    // Display all Warnings at the top of the page
    writeMessages();
  }

  @Override
  protected void writeIncludes()
  {
    super.writeIncludes();

    MDSSGenerationUtility.writeFMTIncludes(getWriter());
  }

  @Override
  protected void writeCommandLink(String action, String name, String display)
  {
    MDSSGenerationUtility.writeCommandLink(getWriter(), COMMAND_LINK_TAG, action, name, display);
  }

  @Override
  protected void writeCommandLinkWithNoProperties(String action, String name, String display)
  {
    MDSSGenerationUtility.writeCommandLinkWithNoProperties(getWriter(), COMMAND_LINK_TAG, action, name, display);
  }
  
  @Override
  public void beforeCloseForm()
  {
    super.beforeCloseForm();
    
    // Write a cancel button for all GeoEntity subclasses
    MdEntityDAOIF mdEntity = this.getMdEntity();
    if(mdEntity instanceof MdBusinessDAOIF && MDSSGenerationUtility.isAGeoEntity((MdBusinessDAOIF) mdEntity))
    {
      String type = mdEntity.definesType();
      String link = type + CONTROLLER_SUFFIX + "." + GeneratedActions.CANCEL_ACTION.getName() + MdActionInfo.ACTION_SUFFIX;
      String linkName = type + ".form.cancel.button";
      String linkDisplay = "Cancel";

      writeCommand(link, linkName, linkDisplay);
    }
  }
  

  @Override
  protected void writeCommand(String action, String name, String value)
  {
    String localizedValue = value + "_Localize";
    
    MDSSGenerationUtility.writeLocalizeTag(getWriter(), value, localizedValue);
    
    HashMap<String, String> updateMap = new HashMap<String, String>();
    updateMap.put("action", action);
    updateMap.put("name", name);
    updateMap.put("value", "${" + localizedValue + "}");

    getWriter().writeEmptyEscapedTag(COMMAND_TAG, updateMap);
  }
}
