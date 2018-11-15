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

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeFloatDAOIF;
import com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLongDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.util.AttributeMetadata;
import dss.vector.solutions.util.ReadableAttributeView;

public class ODKMetadataAttribute extends ODKAttribute implements Reloadable
{
  protected MdAttributeDAOIF sourceMdAttr;

  protected MdAttributeDAOIF viewMdAttr;

  protected boolean          barcode;
  
  public static final String BARCODE_ATTR2_POSTFIX = "_BAP";
  
  // Barcodes include 2 attributes : First, the actual barcode attribute (which is always optional).
  // Second, a string, mock attribute which allows them to type in a value if the barcode is empty.
  // If the second attribute contains a value, then we set the value of the first attribute.
  protected ODKAttribute barcodeAttr2;
  
  public ODKMetadataAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    super(containingForm, viewMdAttr.definesAttribute(), viewMdAttr.getDisplayLabel(Session.getCurrentLocale()), viewMdAttr.getDescription(Session.getCurrentLocale()), calculateRequired(sourceMdAttr, viewMdAttr), ReadableAttributeView.isVisible(sourceMdAttr));
    initialize(sourceMdAttr, viewMdAttr);
  }

  public ODKMetadataAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type, String attributeName, String displayLabel, String description, boolean required)
  {
    super(containingForm, attributeName, displayLabel, description, required, ReadableAttributeView.isVisible(sourceMdAttr));
    initialize(sourceMdAttr, viewMdAttr);
  }

  public ODKMetadataAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type, String attributeName, String displayLabel, String description, boolean required, boolean visible)
  {
    super(containingForm, attributeName, displayLabel, description, required, visible);
    initialize(sourceMdAttr, viewMdAttr);
  }

  private void initialize(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    this.sourceMdAttr = sourceMdAttr;
    this.viewMdAttr = viewMdAttr;
    this.barcode = AttributeMetadata.isValidBarcode(sourceMdAttr);
    
    if (this.barcode)
    {
      barcodeAttr2 = new ODKAttribute(this.getContainingForm(), "string", this.getAttributeName() + BARCODE_ATTR2_POSTFIX, this.getDisplayLabel(), this.getDescription(), this.required, this.isVisible());
      barcodeAttr2.addRelevancy(new ODKAttributeRelevancy("string-length(" + this.getInstancePath() + ")=0"));
    }
    else
    {
      barcodeAttr2 = null;
    }
  }

  public static boolean calculateRequired(MdAttributeDAOIF sourceMdAttr2, MdAttributeDAOIF viewMdAttr2)
  {
    boolean required = sourceMdAttr2.isRequired() || viewMdAttr2.isRequired();

    MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
    if (mdDimension != null)
    {
      required = required || sourceMdAttr2.getMdAttributeConcrete().getMdAttributeDimension(mdDimension).isRequired();

      required = required || viewMdAttr2.getMdAttributeConcrete().getMdAttributeDimension(mdDimension).isRequired();
    }

    return required;
  }

  public MdAttributeDAOIF getSourceMdAttribute()
  {
    return this.sourceMdAttr;
  }

  public MdAttributeDAOIF getViewMdAttribute()
  {
    return this.viewMdAttr;
  }
  
  public boolean isRequired()
  {
    if (this.isBarcode())
    {
      return false;
    }
    
    return required;
  }
  
  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    super.writeBind(parent, document, title, maxDepth);
    
    if (barcodeAttr2 != null)
    {
      barcodeAttr2.writeBind(parent, document, title, maxDepth);
    }
  }
  
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    super.writeBody(parent, document, title, maxDepth);
    
    if (barcodeAttr2 != null)
    {
      barcodeAttr2.writeBody(parent, document, title, maxDepth);
    }
  }

  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    Element attrNode = document.createElement(attributeName);

    String def = sourceMdAttr.getDefaultValue();

    if (def == null || def.length() == 0)
    {
      def = sourceMdAttr.getMdAttributeConcrete().getDefaultValue();
    }

    if ((def == null || def.length() == 0) && Session.getCurrentDimension() != null)
    {
      MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
      MdAttributeDimensionDAOIF mdAttributeDimension = this.sourceMdAttr.getMdAttributeConcrete().getMdAttributeDimension(mdDimension);

      def = mdAttributeDimension.getDefaultValue();
    }

    attrNode.setTextContent(def);

    parent.appendChild(attrNode);
    
    if (barcodeAttr2 != null)
    {
      barcodeAttr2.writeInstance(parent, document, title, maxDepth);
    }
  }
  
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);
    
    if (barcodeAttr2 != null)
    {
      barcodeAttr2.writeTranslation(parent, document, title, maxDepth);
    }
  }

  public void setBarcode(boolean barcode)
  {
    this.barcode = barcode;
  }

  public boolean isBarcode()
  {
    return barcode;
  }

  public String getODKType()
  {
    return this.barcode ? "barcode" : ODKMetadataAttribute.getODKType(sourceMdAttr);
  }

  public static String getODKType(MdAttributeDAOIF attr)
  {
    if (attr instanceof MdAttributeVirtualDAOIF)
    {
      attr = attr.getMdAttributeConcrete();
    }

    if (attr instanceof MdAttributeFloatDAOIF)
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
    else if (attr instanceof MdAttributeReferenceDAOIF) // Merg form has some
                                                        // attr references
    {
      return "string";
    }

    return "string";
  }
}
