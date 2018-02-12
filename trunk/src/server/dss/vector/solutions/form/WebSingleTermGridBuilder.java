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

import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.constants.MdTreeInfo;
import com.runwaysdk.dataaccess.metadata.MdTreeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdRelationship;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.generator.FormSystemURLBuilder;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.Term;

public class WebSingleTermGridBuilder extends WebAttributeBuilder implements Reloadable
{
  public WebSingleTermGridBuilder()
  {
    super();
  }

  public WebSingleTermGridBuilder(MdWebSingleTermGrid mdWebSingleTermGrid)
  {
    super(mdWebSingleTermGrid);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebSingleTermGridBuilder();
  }

  @Override
  public MdWebSingleTermGrid getMdField()
  {
    return (MdWebSingleTermGrid) super.getMdField();
  }

  @Override
  protected void create()
  {
    super.create();

    MdWebSingleTermGrid mdWebSingleTermGrid = this.getMdField();
    MdAttributeReference mdAttributeReference = (MdAttributeReference) mdWebSingleTermGrid.getDefiningMdAttribute();

    // Create the browser field
    BrowserField field = new BrowserField();
    field.setMdAttribute(mdAttributeReference);
    field.apply();

    String typeName = DDMSFieldBuilders.getTermRelationshipTypeName(this.getMdWebForm(), mdWebSingleTermGrid);

    MdClass mdClass = this.getMdClass();

    // Create the relationship
    MdTreeDAO mdTree = MdTreeDAO.newInstance();
    mdTree.setValue(MdTreeInfo.PACKAGE, MDSSInfo.GENERATED_FORM_TREE_PACKAGE);
    mdTree.setValue(MdTreeInfo.NAME, typeName);
    mdTree.setStructValue(MdTreeInfo.DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, typeName);
    mdTree.setValue(MdTreeInfo.ABSTRACT, MdAttributeBooleanInfo.FALSE);
    mdTree.setValue(MdTreeInfo.EXTENDABLE, MdAttributeBooleanInfo.TRUE);
    mdTree.setValue(MdTreeInfo.PUBLISH, MdAttributeBooleanInfo.TRUE);
    mdTree.setValue(MdTreeInfo.PARENT_MD_BUSINESS, mdClass.getId());
    mdTree.setValue(MdTreeInfo.PARENT_METHOD, typeName);
    mdTree.setValue(MdTreeInfo.PARENT_CARDINALITY, "*");
    mdTree.setStructValue(MdTreeInfo.PARENT_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdClass.getDisplayLabel().getValue());
    mdTree.setValue(MdTreeInfo.CHILD_MD_BUSINESS, MdClass.getMdClass(Term.CLASS).getId());
    mdTree.setValue(MdTreeInfo.CHILD_METHOD, typeName);
    mdTree.setValue(MdTreeInfo.CHILD_CARDINALITY, "*");
    mdTree.setStructValue(MdTreeInfo.CHILD_DISPLAY_LABEL, MdAttributeLocalInfo.DEFAULT_LOCALE, mdClass.getDisplayLabel().getValue());
    mdTree.setGenerateMdController(false);
    mdTree.apply();
    
    FormSystemURLBuilder builder = new FormSystemURLBuilder(mdWebSingleTermGrid.getDefiningMdForm());
    builder.add(mdTree);
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
    MdWebSingleTermGrid mdWebGrid = this.getMdField();

    // Delete the browser field and all of its roots
    this.deleteBrowserField();

    // Delete all of the fields which exist on the composite
    this.deleteFields();

    MdRelationship mdRelationship = MdFormUtil.getMdRelationship(mdWebGrid);
    mdRelationship.delete();

    super.delete();
  }

  private void deleteBrowserField()
  {
    MdWebSingleTermGrid mdWebGrid = this.getMdField();
    MdAttribute mdAttribute = mdWebGrid.getDefiningMdAttribute();

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
  }

  private void deleteFields()
  {
    MdWebSingleTermGrid mdWebGrid = this.getMdField();
    MdWebPrimitive[] mdFields = MdFormUtil.getCompositeFields(mdWebGrid.getId());

    for (MdWebPrimitive mdField : mdFields)
    {
      mdField.delete();
    }
  }
}
