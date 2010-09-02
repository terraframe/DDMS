package dss.vector.solutions.export;

import java.util.Locale;

import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdEnumeration;

public class InvalidEnumProblem extends InvalidEnumProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1465188169;
  
  public InvalidEnumProblem()
  {
    super();
  }
  
  public InvalidEnumProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public void setLabelFromQualifiedName(String qualifiedName)
  {
    MdEnumeration mdEnumeration = MdEnumeration.getMdEnumeration(qualifiedName);
    String label = mdEnumeration.getDisplayLabel().getValue();
    this.setEnumLabel(label);
  }
  
}
