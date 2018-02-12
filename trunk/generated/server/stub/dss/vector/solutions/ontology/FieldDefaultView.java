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
package dss.vector.solutions.ontology;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.MdAttributeDimensionInfo;
import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeRefDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeDimensionDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;

public class FieldDefaultView extends FieldDefaultViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257265959240L;

  public FieldDefaultView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    applyDefaultValue();
  }

  @Override
  @Transaction
  public void delete()
  {
    deleteConcrete();
  }

  @Override
  @Authenticate
  public void applyDefaultValue()
  {
    MdAttribute mdAttribute = this.getMdAttribute();

    if (mdAttribute != null)
    {
      String attributeId = mdAttribute.getId();
      MdAttributeDAOIF mdAttributeDAOIF = (MdAttributeDAOIF) MdAttributeDAO.get(attributeId).getMdAttributeConcrete();

      if (mdAttributeDAOIF instanceof MdAttributeRefDAOIF)
      {
        MdDimensionDAOIF mdDimension = Session.getCurrentDimension();
        MdAttributeDimensionDAO mdAttributeDimension = mdAttributeDAOIF.getMdAttributeDimension(mdDimension).getBusinessDAO();

        Term term = this.getDefaultValue();

        if (term != null)
        {
          mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, term.getId());
        }
        else
        {
          mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, null);
        }

        mdAttributeDimension.apply();
      }
    }
  }

  @Override
  @Authenticate
  public void deleteConcrete()
  {
    String attributeId = this.getMdAttribute().getId();
    MdAttributeDAOIF businessDAOIF = (MdAttributeDAOIF) MdAttributeDAO.get(attributeId);
    BusinessDAO mdAttributeDAO = businessDAOIF.getMdAttributeConcrete().getBusinessDAO();

    if (mdAttributeDAO instanceof MdAttributeRefDAOIF)
    {
      MdAttributeDAOIF mdAttributeDAOIF = (MdAttributeDAOIF) mdAttributeDAO;
      MdDimensionDAOIF mdDimension = Session.getCurrentDimension();

      MdAttributeDimensionDAO mdAttributeDimension = mdAttributeDAOIF.getMdAttributeDimension(mdDimension).getBusinessDAO();
      mdAttributeDimension.setValue(MdAttributeDimensionInfo.DEFAULT_VALUE, "");
      mdAttributeDimension.apply();
    }
  }

}
