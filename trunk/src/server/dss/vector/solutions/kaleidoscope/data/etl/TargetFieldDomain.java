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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebAttribute;

import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.Term;

public class TargetFieldDomain extends TargetFieldBasic implements TargetFieldIF, TargetFieldValidationIF, Reloadable
{
  private MdAttributeReferenceDAOIF mdAttribute;

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttribute;

      Term classifier = Term.findByDisplayLabel(value.trim(), mdAttributeTerm);

      if (classifier == null)
      {
        throw new ExclusionException("Unable to find matching term with label [" + value + "]");
      }

      return new FieldValue(classifier.getId());
    }

    return new FieldValue();
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdAttribute sourceAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getSourceAttributeName());
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldDomainBinding field = new TargetFieldDomainBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setSourceAttribute(sourceAttribute);
    field.setColumnLabel(this.getLabel());
    field.apply();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ImportProblemIF validate(Transient source, Map<String, Object> parameters)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      Map<String, Set<String>> exclusions = (Map<String, Set<String>>) parameters.get("categoryExclusions");

      MdAttributeReferenceDAOIF mdAttributeTerm = this.getMdAttribute();

      if (!this.isExcluded(exclusions, mdAttributeTerm, value))
      {
        Term classifier = Term.findByDisplayLabel(value.trim(), mdAttributeTerm);

        if (classifier == null)
        {
          List<BrowserRootView> roots = BrowserRoot.getDirectAttributeRoots(mdAttributeTerm.definedByClass().definesType(), mdAttributeTerm.definesAttribute());
          String attributeLabel = mdAttributeTerm.getDisplayLabel(Session.getCurrentLocale());

          return new CategoryProblem(value.trim(), roots.get(0).getTermId(), mdAttributeTerm.getId(), attributeLabel);
        }
      }
    }

    return null;
  }

  private MdAttributeReferenceDAOIF getMdAttribute()
  {
    if (this.mdAttribute == null)
    {
      MdWebAttribute mdField = MdWebAttribute.getByKey(this.getKey());

      this.mdAttribute = (MdAttributeReferenceDAOIF) MdAttributeDAO.get(mdField.getDefiningMdAttributeId());
    }

    return this.mdAttribute;
  }

  private boolean isExcluded(Map<String, Set<String>> exclusions, MdAttributeReferenceDAOIF mdAttributeTerm, String label)
  {
    if (exclusions.containsKey(mdAttributeTerm.getId()))
    {
      Set<String> labels = exclusions.get(mdAttributeTerm.getId());

      return labels.contains(label);
    }

    return false;
  }

}
