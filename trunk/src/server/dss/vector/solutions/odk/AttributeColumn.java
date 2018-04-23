/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.business.ontology.Term;
import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributeFloatDAOIF;
import com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLongDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.session.Session;

public class AttributeColumn extends ODKAttribute
{
  protected MdAttributeDAOIF mdAttribute;

  public AttributeColumn(MdAttributeDAOIF mdAttribute, int index)
  {
    super(mdAttribute.definesAttribute(), mdAttribute.getDisplayLabel(Session.getCurrentLocale()), mdAttribute.getDescription(Session.getCurrentLocale()), index, mdAttribute.isRequired());
    this.mdAttribute = mdAttribute;
  }

  public AttributeColumn(MdAttributeDAOIF mdAttribute)
  {
    this(mdAttribute, 0);
  }

  /**
   * @return The name of the setter method (in the generated source) for this
   *         column
   */
  public String getSetterMethodName()
  {
    String attributeName = CommonGenerationUtil.upperFirstCharacter(mdAttribute.definesAttribute());

    if (mdAttribute instanceof MdAttributeEnumerationDAOIF)
    {
      return "add" + attributeName;
    }
    else
    {
      return CommonGenerationUtil.SET + attributeName;
    }
  }

  public boolean isEnum()
  {
    return mdAttribute instanceof MdAttributeEnumerationDAOIF;
  }

  public MdAttributeDAOIF getMdAttribute()
  {
    return mdAttribute;
  }
  
  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    Element attrNode = document.createElement(attributeName);
    
    String def = mdAttribute.getDefaultValue();
    if (this.getODKType().equals("boolean") && def.length() == 0) // TODO : subtypes?
    {
      def = "false";
    }
    attrNode.setTextContent(def);
    
    parent.appendChild(attrNode);
  }
  
  public String getODKType()
  {
    MdAttributeDAOIF attr = mdAttribute;
    
    if (mdAttribute instanceof MdAttributeVirtualDAOIF)
    {
      attr = mdAttribute.getMdAttributeConcrete();
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

    return "string";
  }
}
