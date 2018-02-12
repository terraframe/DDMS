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
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.io.MarkupWriter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.GeoEntity;

public class MDSSGenerationUtility implements Reloadable
{
  public static final String MESSAGE_TAG = "mdss:localize";

  public static final String C_SET = "c:set";


  static void writeFMTIncludes(MarkupWriter writer)
  {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("uri", "http://java.sun.com/jsp/jstl/fmt");
    map.put("prefix", "fmt");

    writer.writeEmptyTag("<%@", "%>", "taglib", map);

    map = new HashMap<String, String>();
    map.put("uri", "/WEB-INF/tlds/mdssLib.tld");
    map.put("prefix", "mdss");
    
    writer.writeEmptyTag("<%@", "%>", "taglib", map);
  }

  static void writeCommandLink(MarkupWriter writer, String commandLinkTag, String action, String name, String display)
  {
    HashMap<String, String> commandLinkMap = new HashMap<String, String>();
    commandLinkMap.put("action", action);
    commandLinkMap.put("name", name);

    writer.openEscapedTag(commandLinkTag, commandLinkMap);

    HashMap<String, String> messageMap = new HashMap<String, String>();
    messageMap.put("key", display.replaceAll(" ", "_"));

    writer.writeEmptyTag(MESSAGE_TAG, messageMap);
  }

  static void writeCommandLinkWithNoProperties(MarkupWriter writer, String commandLinkTag, String action, String name, String display)
  {
    HashMap<String, String> commandLinkMap = new HashMap<String, String>();
    commandLinkMap.put("action", action);
    commandLinkMap.put("name", name);

    // Open the command link tab
    writer.openEscapedTag(commandLinkTag, commandLinkMap);

    HashMap<String, String> messageMap = new HashMap<String, String>();
    messageMap.put("key", display.replaceAll(" ", "_"));

    writer.writeEmptyTag(MESSAGE_TAG, messageMap);

    // Close the command link tab
    writer.closeTag();
  }

  public static void writePageTitle(MarkupWriter writer, String title)
  {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("var", "page_title");
    map.put("value", title);
    map.put("scope", "request");

    writer.writeEmptyTag(C_SET, map);
  }
  
  public static void writeLocalizeTag(MarkupWriter writer, String key, String var)
  {
    HashMap<String, String> updateMap = new HashMap<String, String>();
    updateMap.put("key", key);
    updateMap.put("var", var);
    
    writer.writeEmptyEscapedTag(MESSAGE_TAG, updateMap);
  }

  public static boolean isAGeoEntity(MdBusinessDAOIF mdBusiness)
  {
    List<MdBusinessDAOIF> superClasses = mdBusiness.getSuperClasses();
    boolean contains = superClasses.contains(MdBusiness.getMdBusiness(GeoEntity.CLASS));

    return contains;
  }

  public static boolean definesAttribute(MdEntityDAOIF mdEntity, String attributeName)
  {
    for(MdEntityDAOIF superEntity : mdEntity.getSuperClasses())
    {
      if(superEntity.definesAttribute(attributeName) != null)
      {
        return true;
      }
    }

    return false;
  }

  public static boolean hasGeoEntityReference(MdEntityDAOIF mdEntity)
  {
    for(MdEntityDAOIF superEntity : mdEntity.getSuperClasses())
    {
      for(MdAttributeDAOIF mdAttribute : superEntity.definesAttributes())
      {
        if(mdAttribute instanceof MdAttributeReferenceDAOIF)
        {
          MdBusinessDAOIF mdBusiness = ((MdAttributeReferenceDAOIF) mdAttribute).getReferenceMdBusinessDAO();

          if(MDSSGenerationUtility.isAGeoEntity(mdBusiness))
          {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static boolean isATerm(MdBusinessDAOIF mdBusiness)
  {
    List<MdBusinessDAOIF> superClasses = mdBusiness.getSuperClasses();
    
    boolean contains = superClasses.contains(MdBusiness.getMdBusiness("dss.vector.solutions.ontology.Term"));

    return contains;
  }

}
