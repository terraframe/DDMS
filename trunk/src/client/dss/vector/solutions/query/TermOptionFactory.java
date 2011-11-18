package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBooleanDTO;
import com.runwaysdk.system.metadata.MdFieldDTO;
import com.runwaysdk.system.metadata.MdWebBooleanDTO;
import com.runwaysdk.system.metadata.MdWebDecimalDTO;
import com.runwaysdk.system.metadata.MdWebDoubleDTO;
import com.runwaysdk.system.metadata.MdWebFloatDTO;
import com.runwaysdk.system.metadata.MdWebLongDTO;

import dss.vector.solutions.ontology.TermDTO;

public class TermOptionFactory implements Reloadable
{
  private MdFieldDTO mdField;

  private String     attributeName;

  private String     type;

  private String     label;

  private String     attributeNamePrepend;

  public TermOptionFactory(String attributeName, String type)
  {
    this(null, attributeName, type);
  }

  public TermOptionFactory(MdFieldDTO mdField, String attributeName, String type)
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
      MdWebBooleanDTO mdFieldBoolean = (MdWebBooleanDTO) mdField;
      MdAttributeBooleanDTO mdAttributeBoolean = (MdAttributeBooleanDTO) mdFieldBoolean.getDefiningMdAttribute();

      String positiveLabel = mdAttributeBoolean.getPositiveDisplayLabel().getValue();
      String negativeLabel = mdAttributeBoolean.getNegativeDisplayLabel().getValue();

      return new TermBooleanOption(attributeName, label + term.getDisplayLabel(), key, positiveLabel, negativeLabel);
    }
    else if (mdField != null && mdField instanceof MdWebDecimalDTO)
    {
      return new TermDecimalOption(attributeName, label + term.getDisplayLabel(), key);
    }
    else if (mdField != null && mdField instanceof MdWebDoubleDTO)
    {
      return new TermDoubleOption(attributeName, label + term.getDisplayLabel(), key);
    }
    else if (mdField != null && mdField instanceof MdWebFloatDTO)
    {
      return new TermFloatOption(attributeName, label + term.getDisplayLabel(), key);
    }
    else if (mdField != null && mdField instanceof MdWebLongDTO)
    {
      return new TermLongOption(attributeName, label + term.getDisplayLabel(), key);
    }
    return new TermIntegerOption(attributeName, label + term.getDisplayLabel(), key);
  }

}