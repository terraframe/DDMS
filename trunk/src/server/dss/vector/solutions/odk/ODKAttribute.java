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

import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.io.excel.DefaultExcelAttributeFilter;
import com.runwaysdk.dataaccess.io.excel.MdAttributeFilter;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.ontology.RootTerm;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;

public class ODKAttribute implements Reloadable
{
  protected String               attributeName;

  protected String               displayLabel;

  private String                 description = null;

  private int                    index;

  private boolean                required;

  private ODKAttributeRelevancy  relevancy;

  private ODKAttributeConstraint constraint;

  private String                 type;

  /**
   * Optional name of attribute in which to copy this value into the override
   * map
   */
  private String                 copyAttribute;

  private ODKForm                containingForm;

  /**
   * Flag denoting the value should be stored in the override map instead of on
   * the mutable
   */
  private boolean                isOverride;

  private boolean                visible;

  public ODKAttribute(ODKForm containingForm, String type, String attributeName, String displayLabel, String description, boolean required, boolean visible)
  {
    this.type = type;
    this.attributeName = this.sanitizeAttributeName(attributeName);
    this.displayLabel = displayLabel;
    this.description = description;
    this.required = required;
    this.copyAttribute = null;
    this.isOverride = false;
    this.containingForm = containingForm;
    this.visible = visible;
  }

  public ODKAttribute(ODKForm containingForm, String attributeName, String displayLabel, String description, boolean required, boolean visible)
  {
    this(containingForm, "string", attributeName, displayLabel, description, required, visible);
  }

  public String sanitizeAttributeName(String attrName)
  {
    return attrName.replaceAll(":", "_");
  }

  public ODKAttributeRelevancy getRelevancy()
  {
    return relevancy;
  }

  public void addRelevancy(ODKAttributeRelevancy condition)
  {
    if (this.relevancy != null)
    {
      this.relevancy = new ODKAttributeRelevancyComposite(relevancy, ODKAttributeConditionOperation.AND, condition);
    }
    else
    {
      this.relevancy = condition;
    }
  }

  public ODKAttributeRelevancy getConstraint()
  {
    return relevancy;
  }

  public void addConstraint(ODKAttributeConstraint condition)
  {
    if (this.constraint != null)
    {
      this.constraint = new ODKAttributeConstraintComposite(constraint, ODKAttributeConditionOperation.AND, condition);
    }
    else
    {
      this.constraint = condition;
    }
  }

  public ODKForm getContainingForm()
  {
    return this.containingForm;
  }

  public static class Filter extends DefaultExcelAttributeFilter implements MdAttributeFilter, Reloadable
  {

    @Override
    public boolean accept(MdAttributeDAOIF mdAttribute)
    {
      if (mdAttribute instanceof MdAttributeReferenceDAOIF)
      {
        MdEntityDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) mdAttribute ).getReferenceMdBusinessDAO().getRootMdClassDAO();

        String type = referenceMdBusiness.definesType();

        return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS);
      }

      return super.accept(mdAttribute);
    }

  }

  public static ODKAttribute factory(ODKForm containingForm, MdAttributeDAOIF source, MdAttributeDAOIF viewAttr, Set<String> exportedTerms)
  {
    MdAttributeConcreteDAOIF concrete = source.getMdAttributeConcrete();

    if (concrete instanceof MdAttributeEnumerationDAOIF)
    {
      return new ODKEnumAttribute(containingForm, source, viewAttr, exportedTerms);
    }
    else if (concrete instanceof MdAttributeReferenceDAOIF)
    {
      MdEntityDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) concrete ).getReferenceMdBusinessDAO().getRootMdClassDAO();
      if (referenceMdBusiness.definesType().equals(GeoEntity.CLASS))
      {
        return new ODKGeoAttribute(containingForm, source, viewAttr);
      }
      else if (referenceMdBusiness.definesType().equals(InsecticideBrand.CLASS))
      {
        return new ODKInsecticideBrandAttribute(containingForm, source, viewAttr, exportedTerms);
      }
      else if (referenceMdBusiness.definesType().equals(AggregatedAgeGroup.CLASS))
      {
        return new ODKAgeGroupAttribute(containingForm, source, viewAttr, exportedTerms);
      }
      else if (referenceMdBusiness.definesType().equals(Term.CLASS) || referenceMdBusiness.definesType().equals(RootTerm.CLASS))
      {
        return new ODKTermAttribute(containingForm, source, viewAttr, exportedTerms);
      }
      else
      {
        return new ODKMetadataAttribute(containingForm, source, viewAttr);
      }
    }
    else if (concrete instanceof MdAttributeStructDAOIF)
    {
      return new ODKStructAttribute(containingForm, source, viewAttr, exportedTerms);
    }
    else if (concrete instanceof MdAttributeBooleanDAOIF)
    {
      return new ODKAttributeBoolean(containingForm, (MdAttributeBooleanDAOIF) concrete, viewAttr);
    }
    else
    {
      return new ODKMetadataAttribute(containingForm, source, viewAttr);
    }
  }

  public String getDisplayLabel()
  {
    return displayLabel;
  }

  public void setDisplayLabel(String displayLabel)
  {
    this.displayLabel = displayLabel;
  }

  public String getAttributeName()
  {
    return attributeName;
  }

  public void setAttributeName(String attributeName)
  {
    this.attributeName = attributeName;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getIndex()
  {
    return index;
  }

  public void setIndex(int index)
  {
    this.index = index;
  }

  public boolean isRequired()
  {
    return required;
  }

  public void setRequired(boolean required)
  {
    this.required = required;
  }

  public boolean isVisible()
  {
    return visible;
  }

  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    Element text = document.createElement("text");

    text.setAttribute("id", "/" + title + "/" + attributeName + ":label");

    Element value = document.createElement("value");
    value.setTextContent(this.displayLabel);
    text.appendChild(value);

    parent.appendChild(text);
  }

  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    Element attrNode = document.createElement(attributeName);

    parent.appendChild(attrNode);
  }

  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    Element bind = document.createElement("bind");

    bind.setAttribute("nodeset", "/" + title + "/" + attributeName);
    bind.setAttribute("type", this.getODKType());

    if (isRequired())
    {
      bind.setAttribute("required", "true()");
    }

    if (this.relevancy != null)
    {
      bind.setAttribute("relevant", this.relevancy.getBindRelevant());
    }
    if (this.constraint != null)
    {
      bind.setAttribute("constraint", this.constraint.getBindConstraint());
      bind.setAttribute("jr:constraintMsg", this.constraint.getConstraintMsg());
    }

    parent.appendChild(bind);
  }

  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element input = document.createElement("input");
    parent.appendChild(input);
    input.setAttribute("ref", "/" + title + "/" + attributeName);

    Element label = document.createElement("label");
    input.appendChild(label);
    label.setAttribute("ref", "jr:itext('/" + title + "/" + attributeName + ":label')");
  }

  public String getODKType()
  {
    return type;
  }

  public boolean isValid()
  {
    return this.visible;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof ODKAttribute)
    {
      ODKAttribute other = (ODKAttribute) obj;
      return other.getAttributeName().equals(this.getAttributeName());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return displayLabel + " (" + attributeName + ")";
  }

  public void setCopyAttribute(String columnName)
  {
    this.copyAttribute = columnName;
  }

  public String getCopyAttribute()
  {
    return copyAttribute;
  }

  public String getInstancePath()
  {
    return containingForm.getInstancePath() + "/" + this.attributeName;
  }

  public boolean isOverride()
  {
    return this.isOverride;
  }

  public void setIsOverride(boolean isOverride)
  {
    this.isOverride = isOverride;
  }
}
