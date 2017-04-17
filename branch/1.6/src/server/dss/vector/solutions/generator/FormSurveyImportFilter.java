package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdWebPrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdWebReferenceDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermDAOIF;
import com.runwaysdk.dataaccess.io.excel.MdFieldFilter;
import com.runwaysdk.generation.loader.Reloadable;

public class FormSurveyImportFilter implements MdFieldFilter, Reloadable
{
  @Override
  public boolean accept(MdFieldDAOIF mdField)
  {
    return ( ( mdField instanceof MdWebPrimitiveDAOIF ) || ( mdField instanceof MdWebSingleTermDAOIF ) || ( mdField instanceof MdWebReferenceDAOIF ) );
  }
}
