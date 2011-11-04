package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebReferenceDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.StringFieldColumn;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;

public class FormSurveyColumnFactory extends FormColumnFactory implements Reloadable
{
  @Override
  public ExcelColumn getColumn(MdWebAttributeDAOIF mdField)
  {
    if (mdField instanceof MdWebReferenceDAOIF)
    {
      MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) mdField.getDefiningMdAttribute();

      if (mdAttribute.definesAttribute().equals(FormHousehold.SURVEY))
      {
        return new StringFieldColumn(mdField);
      }
    }

    return super.getColumn(mdField);  
  }
}
