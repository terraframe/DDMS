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
import com.runwaysdk.dataaccess.metadata.MdFieldDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributePrimitive;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;

import dss.vector.solutions.generator.MdFormUtil;

public abstract class WebPrimitiveBuilder extends WebAttributeBuilder implements Reloadable
{
  private MdWebSingleTermGrid mdWebSingleTermGrid;

  public WebPrimitiveBuilder()
  {
    super();
  }

  public WebPrimitiveBuilder(MdWebPrimitive mdWebPrimitive)
  {
    super(mdWebPrimitive);
  }

  @Override
  public MdWebPrimitive getMdField()
  {
    return (MdWebPrimitive) super.getMdField();
  }

  @Override
  protected MdAttributeConcrete newMdAttribute()
  {
    String mdAttributeType = this.getMdField().getExpectedMdAttributeType();

    return (MdAttributeConcrete) BusinessFacade.newBusiness(mdAttributeType);

  }

  @Override
  protected Integer getFieldOrder()
  {
    if (this.getMdWebForm() == null && this.mdWebSingleTermGrid != null)
    {
      Integer order = this.getMdField().getFieldOrder();

      if (order == null)
      {
        MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(this.mdWebSingleTermGrid.getId());

        return fields.length;
      }
      else
      {
        return order;
      }
    }

    return super.getFieldOrder();
  }

  public void setMdWebSingleTermGrid(MdWebSingleTermGrid mdWebSingleTermGrid)
  {
    this.mdWebSingleTermGrid = mdWebSingleTermGrid;
  }

  public MdWebSingleTermGrid getMdWebSingleTermGrid()
  {
    return mdWebSingleTermGrid;
  }

  @Override
  protected void create()
  {
    boolean isComposite = this.getMdWebForm() == null && this.mdWebSingleTermGrid != null;

    if (isComposite)
    {
      this.setupAndValidateMdField();

      MdWebPrimitive mdField = this.getMdField();
      mdField.setKeyName(MdFieldDAO.buildKey(mdWebSingleTermGrid.getKey(), mdField.getFieldName()));
    }

    super.create();

    if (isComposite)
    {
      mdWebSingleTermGrid.addMdFields(this.getMdField()).apply();
    }
  }
  
  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdWebPrimitive mdWebPrimitive = this.getMdField();
    
    MdAttributePrimitive mdAttributePrimitive = (MdAttributePrimitive) mdAttribute;
    mdAttributePrimitive.setIsExpression(mdWebPrimitive.getIsExpression());
    mdAttributePrimitive.setExpression(mdWebPrimitive.getExpression());

    super.updateMdAttribute(mdAttributePrimitive);
  }

}
