package dss.vector.solutions.generator;

import com.runwaysdk.constants.MdAttributeIndicatorInfo;

public class MdWebIndicator extends MdWebIndicatorBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1235018726;

  public MdWebIndicator()
  {
    super();
  }

  public String getExpectedMdAttributeType()
  {
    return MdAttributeIndicatorInfo.CLASS;
  }
}
