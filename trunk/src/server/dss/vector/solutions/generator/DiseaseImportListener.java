package dss.vector.solutions.generator;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.Disease;

public class DiseaseImportListener extends ExcelAdapter implements Reloadable
{
  @Override
  public void beforeApply(Mutable instance)
  {
    Disease disease = Disease.getCurrent();

    if (disease != null)
    {
      instance.setValue(MdFormUtil.DISEASE, disease.getId());
    }
  }
}
