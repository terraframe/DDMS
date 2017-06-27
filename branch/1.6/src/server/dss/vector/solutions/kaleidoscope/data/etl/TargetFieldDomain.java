package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdFieldDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdWebAttribute;

import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.Term;

public class TargetFieldDomain extends TargetFieldBasic implements TargetFieldIF, TargetFieldValidationIF, Reloadable
{
  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttribute;

      Term classifier = Term.findByDisplayLabel(value, mdAttributeTerm);

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
      
      MdWebAttribute mdField = MdWebAttribute.getByKey(this.getKey());

      MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) MdAttributeDAO.get(mdField.getDefiningMdAttributeId());

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
