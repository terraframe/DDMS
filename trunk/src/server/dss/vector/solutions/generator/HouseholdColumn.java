package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.general.UnknownValueException;

public class HouseholdColumn extends AttributeColumn implements Reloadable
{
  public HouseholdColumn(MdAttributeReferenceDAOIF mdAttribute)
  {
    super(mdAttribute);
  }

  @Override
  public String javaType()
  {
    return FormHousehold.class.getName();
  }

  @Override
  public Object getValue(HSSFCell cell) throws Exception
  {
    String householdId = ExcelUtil.getString(cell);

    FormHousehold household = FormHousehold.getByHouseholdId(householdId);

    if (household != null)
    {
      return household;
    }
    else
    {
      String msg = "Unknown household with householdId [" + householdId + "]";

      MdBusinessDAOIF mdBusiness = MdBusinessDAO.getMdBusinessDAO(FormHousehold.CLASS);
      String typeLabel = mdBusiness.getDisplayLabel(Session.getCurrentLocale());
      String attributeLabel = mdBusiness.definesAttribute(FormHousehold.HOUSEHOLDID).getDisplayLabel(Session.getCurrentLocale());

      UnknownValueException ex = new UnknownValueException(msg);
      ex.setAttributeLabel(attributeLabel);
      ex.setTypeLabel(typeLabel);
      ex.setValue(householdId);
      ex.apply();

      throw ex;
    }
  }
}