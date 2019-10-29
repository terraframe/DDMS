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
/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.AttributeIF;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.attributes.ImmutableAttributeProblem;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MetadataCannotBeDeletedException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebReference;

import dss.vector.solutions.generator.MdFormUtil;

public class WebReferenceBuilder extends WebAttributeBuilder implements Reloadable
{
  public WebReferenceBuilder()
  {
    super();
  }

  public WebReferenceBuilder(MdWebReference mdWebReference)
  {
    super(mdWebReference);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebReferenceBuilder();
  }

  @Override
  public MdWebReference getMdField()
  {
    return (MdWebReference) super.getMdField();
  }

  @Override
  public MdAttributeConcrete newMdAttribute()
  {
    return null;
  }

  @Override
  protected void update()
  {
    MdWebReference mdWebAttribute = this.getMdField();

    if (mdWebAttribute.isModified(MdWebAttribute.REQUIRED))
    {
      MdClassDAOIF mdClass = mdWebAttribute.getMdClass();
      MdAttributeDAOIF mdAttributeIF = MdWebAttribute.getRequiredMd();
      EntityDAOIF mdWebAttributeDAO = BusinessFacade.getEntityDAO(mdWebAttribute);
      AttributeIF attribute = mdWebAttributeDAO.getAttributeIF(MdWebAttribute.REQUIRED);
      String msg = "The [" + MdWebAttribute.REQUIRED + "] flag on [" + MdFormUtil.OID + "] can not be changed.";

      ImmutableAttributeProblem problem = new ImmutableAttributeProblem(mdWebAttribute.getId(), mdClass, mdAttributeIF, msg, attribute);
      problem.throwIt();
    }

    super.update();
  }

  /*
   * IMPORTANT: The only reference fields in the system (e.g. survey, household,
   * net) exist on the MERG survey and none of them can be deleted. Deletion
   * will cause both the UI and the excel importer to behave in a incosistent
   * and potentially broken manner. As such we are preventing any deletes from
   * occuring regardless of the state of the removable flag.
   */
  @Override
  public void delete()
  {
    MdWebReference mdWebAttribute = this.getMdField();

    MdAttributeConcrete mdAttribute = (MdAttributeConcrete) mdWebAttribute.getDefiningMdAttribute();

    MdAttributeDAO mdAttributeDAO = (MdAttributeDAO) MdAttributeDAO.get(mdAttribute.getId()).getBusinessDAO();
    String msg = "The hard-coded field [" + mdAttributeDAO.definesAttribute() + "] cannot be deleted";

    throw new MetadataCannotBeDeletedException(msg, mdAttributeDAO);
  }
}
