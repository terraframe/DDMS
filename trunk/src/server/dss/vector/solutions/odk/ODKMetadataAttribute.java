/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Runway SDK(tm). If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeFloatDAOIF;
import com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLongDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class ODKMetadataAttribute extends ODKAttribute implements Reloadable
{
  protected MdAttributeDAOIF sourceMdAttr;

  protected MdAttributeDAOIF viewMdAttr;

  public ODKMetadataAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, int index)
  {
    super(viewMdAttr.definesAttribute(), viewMdAttr.getDisplayLabel(Session.getCurrentLocale()), viewMdAttr.getDescription(Session.getCurrentLocale()), index, viewMdAttr.isRequired());
    this.sourceMdAttr = sourceMdAttr;
    this.viewMdAttr = viewMdAttr;
  }

  public ODKMetadataAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    this(sourceMdAttr, viewMdAttr, 0);
  }

  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    Element attrNode = document.createElement(attributeName);

    String def = sourceMdAttr.getDefaultValue();
    if (this.getODKType().equals("boolean") && def.length() == 0)
    {
      // ODK breaks if you don't provide a default value for booleans.
      def = "false";
    }
    attrNode.setTextContent(def);

    parent.appendChild(attrNode);
  }

  public String getODKType()
  {
    MdAttributeDAOIF attr = sourceMdAttr;

    if (sourceMdAttr instanceof MdAttributeVirtualDAOIF)
    {
      attr = sourceMdAttr.getMdAttributeConcrete();
    }

    if (attr instanceof MdAttributeBooleanDAOIF)
    {
      return "boolean";
    }
    else if (attr instanceof MdAttributeFloatDAOIF)
    {
      return "decimal";
    }
    else if (attr instanceof MdAttributeDoubleDAOIF)
    {
      return "decimal";
    }
    else if (attr instanceof MdAttributeDecimalDAOIF)
    {
      return "decimal";
    }
    else if (attr instanceof MdAttributeLongDAOIF)
    {
      return "int";
    }
    else if (attr instanceof MdAttributeIntegerDAOIF)
    {
      return "int";
    }
    else if (attr instanceof MdAttributeDateDAOIF)
    {
      return "date";
    }
    else if (attr instanceof MdAttributeReferenceDAOIF) // Merg form has some attr references
    {
      return "string";
    }

    return "string";
  }
}
