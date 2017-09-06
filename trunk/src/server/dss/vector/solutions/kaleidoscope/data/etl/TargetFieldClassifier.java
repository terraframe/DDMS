package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebAttribute;

import dss.vector.solutions.ontology.Term;

public class TargetFieldClassifier extends TargetFieldBasic implements TargetFieldIF, Reloadable
{
  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    String value = source.getValue(this.getSourceAttributeName());

    if (value != null && value.length() > 0)
    {
      MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttribute;

      Term classifier = Term.findTermAddIfNotExist(value.trim(), mdAttributeTerm);

      return new FieldValue(classifier.getId());
    }

    return new FieldValue();
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdAttribute sourceAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getSourceAttributeName());
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldClassifierBinding field = new TargetFieldClassifierBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setSourceAttribute(sourceAttribute);
    field.setColumnLabel(this.getLabel());
    field.setIsValidate(false);
    field.apply();
  }
}
