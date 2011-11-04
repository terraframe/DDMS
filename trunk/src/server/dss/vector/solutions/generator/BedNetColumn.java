package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.general.UnknownValueException;

public class BedNetColumn extends AttributeColumn implements Reloadable
{
  public BedNetColumn(MdAttributeReferenceDAOIF mdAttribute)
  {
    super(mdAttribute);
  }

  @Override
  public String javaType()
  {
    return FormBedNet.class.getName();
  }

  @Override
  public Object getValue(HSSFCell cell) throws Exception
  {
    String netId = ExcelUtil.getString(cell);

    FormBedNet bedNet = FormBedNet.getByBedNetId(netId);

    if (bedNet != null)
    {
      return bedNet;
    }
    else
    {
      String msg = "Unknown net with netId [" + netId + "]";

      MdBusinessDAOIF mdBusiness = MdBusinessDAO.getMdBusinessDAO(FormBedNet.CLASS);
      String typeLabel = mdBusiness.getDisplayLabel(Session.getCurrentLocale());
      String attributeLabel = mdBusiness.definesAttribute(FormBedNet.NETID).getDisplayLabel(Session.getCurrentLocale());

      UnknownValueException ex = new UnknownValueException(msg);
      ex.setAttributeLabel(attributeLabel);
      ex.setTypeLabel(typeLabel);
      ex.setValue(netId);
      ex.apply();

      throw ex;
    }
  }
}