package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBooleanDTO;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;

import dss.vector.solutions.ontology.TermDTO;

public class SelectableOptionFactory implements Reloadable
{
  private MdAttributeConcreteDTO mdAttribute;

  private String                 attributeName;

  private String                 type;

  private String                 label;

  private String                 attributeNamePrepend;

  public SelectableOptionFactory(String attributeName, String type)
  {
    this(null, attributeName, type);
  }

  public SelectableOptionFactory(MdAttributeConcreteDTO mdAttribute, String attributeName, String type)
  {
    this.mdAttribute = mdAttribute;
    this.attributeName = attributeName;
    this.type = type;
    this.label = "";
    this.attributeNamePrepend = "term";
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public void setAttributeNamePrepend(String attributeNamePrepend)
  {
    this.attributeNamePrepend = attributeNamePrepend;
  }

  public SelectableOption createOption(TermDTO term)
  {
    String termId = term.getId();
    String key = ( this.attributeName + "__" + this.type + "__" + termId ).replace(".", "_");
    String attributeName = attributeNamePrepend + termId.substring(0, 16);

    if (mdAttribute != null && mdAttribute instanceof MdAttributeBooleanDTO)
    {
      MdAttributeBooleanDTO mdAttributeBoolean = (MdAttributeBooleanDTO) mdAttribute;
      String positiveLabel = mdAttributeBoolean.getPositiveDisplayLabel().getValue();
      String negativeLabel = mdAttributeBoolean.getNegativeDisplayLabel().getValue();

      return new SelectableBooleanOption(attributeName, label + term.getDisplayLabel(), key, positiveLabel, negativeLabel);
    }

    return new SelectableIntegerOption(attributeName, label + term.getDisplayLabel(), key);
  }

}
