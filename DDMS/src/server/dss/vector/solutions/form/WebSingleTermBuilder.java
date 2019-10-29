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

import java.util.List;

import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebSingleTerm;

import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.Term;

public class WebSingleTermBuilder extends WebAttributeBuilder implements Reloadable
{
  public WebSingleTermBuilder()
  {
    super();
  }

  public WebSingleTermBuilder(MdWebSingleTerm mdWebSingleTerm)
  {
    super(mdWebSingleTerm);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebSingleTermBuilder();
  }

  @Override
  public MdWebSingleTerm getMdField()
  {
    return (MdWebSingleTerm) super.getMdField();
  }

  @Override
  protected void create()
  {
    super.create();

    MdWebSingleTerm mdWebSingleTerm = this.getMdField();
    MdAttributeReference mdAttributeReference = (MdAttributeReference) mdWebSingleTerm.getDefiningMdAttribute();

    BrowserField field = new BrowserField();
    field.setMdAttribute(mdAttributeReference);
    field.apply();
  }

  @Override
  protected MdAttributeConcrete newMdAttribute()
  {
    return new MdAttributeReference();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    super.updateMdAttribute(mdAttribute);

    mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, MdClass.getMdClass(Term.CLASS).getId());
  }

  @Override
  public void delete()
  {
    MdWebSingleTerm mdWebSingleTerm = this.getMdField();
    MdAttribute mdAttribute = mdWebSingleTerm.getDefiningMdAttribute();

    BrowserField field = BrowserField.getBrowserField(mdAttribute);

    OIterator<? extends BrowserRoot> it = field.getAllroot();
    try
    {
      List<? extends BrowserRoot> roots = it.getAll();

      for (BrowserRoot root : roots)
      {
        root.delete();
      }
    }
    finally
    {
      it.close();
    }

    field.delete();

    super.delete();
  }
}
