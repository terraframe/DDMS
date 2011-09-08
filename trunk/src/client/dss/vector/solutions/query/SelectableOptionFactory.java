package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdFieldDTO;
import com.runwaysdk.system.metadata.MdWebBooleanDTO;

import dss.vector.solutions.ontology.TermDTO;

public class SelectableOptionFactory implements Reloadable
{
  private MdFieldDTO mdField;

  private String     attributeName;

  private String     type;

  private String     label;

  private String     attributeNamePrepend;

  public SelectableOptionFactory(String attributeName, String type)
  {
    this(null, attributeName, type);
  }

  public SelectableOptionFactory(MdFieldDTO mdField, String attributeName, String type)
  {
    this.mdField = mdField;
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

    if (mdField != null && mdField instanceof MdWebBooleanDTO)
    {
      // MdWebBooleanDTO mdFieldBoolean = (MdWebBooleanDTO) mdField;
      String positiveLabel = "true"; // mdFieldBoolean.getPositiveDisplayLabel().getValue();
      String negativeLabel = "false"; // mdFieldBoolean.getNegativeDisplayLabel().getValue();

      return new SelectableBooleanOption(attributeName, label + term.getDisplayLabel(), key, positiveLabel, negativeLabel);
    }

    return new SelectableIntegerOption(attributeName, label + term.getDisplayLabel(), key);
  }

}