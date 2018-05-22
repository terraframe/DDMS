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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdStructDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class ODKStructAttribute extends AttributeColumn implements Reloadable
{
  List<ODKAttribute> structAttrs;
  
  public ODKStructAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, Set<String> exportedTerms)
  {
    super(sourceMdAttr, viewMdAttr, 0);
    
    structAttrs = new ArrayList<ODKAttribute>();
    
    MdAttributeStructDAOIF struct;
    if (!(this.sourceMdAttr instanceof MdAttributeConcreteDAOIF))
    {
      struct = (MdAttributeStructDAOIF) this.sourceMdAttr.getMdAttributeConcrete();
    }
    else
    {
      struct = (MdAttributeStructDAOIF) this.sourceMdAttr;
    }
    MdStructDAOIF mdStruct = struct.getMdStructDAOIF();
    List<? extends MdAttributeDAOIF> structAttributes = ExcelUtil.getAttributes(mdStruct, new Filter());
    
    for (MdAttributeDAOIF structAttribute : structAttributes)
    {
      structAttrs.add(ODKAttribute.factory(structAttribute, structAttribute, exportedTerms));
    }
  }
  
  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);
    
    for (ODKAttribute structAttr : structAttrs)
    {
      structAttr.writeTranslation(parent, document, title, maxDepth);
    }
  }
  
  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute structAttr : structAttrs)
    {
      structAttr.writeInstance(parent, document, title, maxDepth);
    }
  }

  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element group = document.createElement("group");
    parent.appendChild(group);
    
    Element label = document.createElement("label");
    group.appendChild(label);
    label.setTextContent(this.sourceMdAttr.getDisplayLabel(Session.getCurrentLocale()));
    
    for (ODKAttribute structAttr : structAttrs)
    {
      structAttr.writeBody(group, document, title, maxDepth);
    }
  }

  @Override
  public String getODKType()
  {
    return "group";
  }
}
