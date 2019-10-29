/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
