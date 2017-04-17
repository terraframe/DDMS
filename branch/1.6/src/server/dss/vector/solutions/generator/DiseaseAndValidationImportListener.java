package dss.vector.solutions.generator;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.excel.FormValidationImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;

public class DiseaseAndValidationImportListener extends FormValidationImportListener implements Reloadable
{

  public DiseaseAndValidationImportListener(MdFormDAOIF mdForm)
  {
    super(mdForm);
  }

  public void beforeApply(Mutable instance)
  {
    Disease disease = Disease.getCurrent();

    if (disease != null)
    {
      instance.setValue(MdFormUtil.DISEASE, disease.getId());
    }

    super.beforeApply(instance);
  }
}
