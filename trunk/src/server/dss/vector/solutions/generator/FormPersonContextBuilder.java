package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormPerson;

public class FormPersonContextBuilder extends HouseholdReferenceContextBuilder implements Reloadable
{
  public FormPersonContextBuilder()
  {
    super((MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE));
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(FormBedNet.CLASS))
      {
        context.addExpectedColumn(new BedNetColumn(mdAttributeReference));
      }
      else
      {
        super.buildAttributeColumn(context, mdAttribute);
      }
    }
    else
    {
      super.buildAttributeColumn(context, mdAttribute);
    }
  }

}
