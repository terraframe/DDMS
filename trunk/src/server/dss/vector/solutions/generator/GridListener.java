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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ColumnFactory;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.FieldColumn;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class GridListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private MdClassDAOIF             mdClass;

  private MdWebSingleTermGridDAOIF mdCompositeField;

  private List<Relationship>       relationships;

  private MdRelationshipDAOIF      mdRelationship;

  private ColumnFactory            factory;

  public GridListener(MdClassDAOIF mdClass, MdWebSingleTermGridDAOIF mdCompositeField, MdRelationshipDAOIF mdRelationship)
  {
    this.mdClass = mdClass;
    this.mdCompositeField = mdCompositeField;
    this.mdRelationship = mdRelationship;

    this.factory = new FormColumnFactory();
    this.relationships = new LinkedList<Relationship>();
  }

  @Override
  public void addColumns(List<ExcelColumn> extraColumns)
  {
  }

  @Override
  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    String compositeFieldName = mdCompositeField.getFieldName();
    MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(compositeFieldName);
    Term[] roots = TermRootCache.getRoots(mdAttribute);

    MdWebPrimitive[] fields = MdFormUtil.getCompositeFields(mdCompositeField.getId());

    Class<? extends Mutable> clazz = instance.getClass();
    String methodName = "add" + CommonGenerationUtil.upperFirstCharacter(this.mdRelationship.getParentMethod());
    Method method = clazz.getMethod(methodName, Term.class);

    try
    {
      for (Term term : roots)
      {
        Relationship relationship = (Relationship) method.invoke(instance, term);

        for (MdWebPrimitive field : fields)
        {
          MdAttributeConcrete mdAttributeConcrete = (MdAttributeConcrete) field.getDefiningMdAttribute();
          FieldColumn fieldColumn = (FieldColumn) factory.getColumn((MdWebAttributeDAOIF) BusinessFacade.getEntityDAO(field));
          String fieldName = field.getFieldName();

          for (ExcelColumn column : extraColumns)
          {
            String attributeName = this.getAttributeName(compositeFieldName, fieldName, term);

            if (column.getAttributeName().equals(attributeName))
            {
              Object value = fieldColumn.getValue(row.getCell(column.getIndex()));

              relationship.setValue(mdAttributeConcrete.getAttributeName(), value.toString());
            }
          }
        }

        relationships.add(relationship);
      }
    }
    catch (InvocationTargetException e)
    {
      Throwable cause = e.getCause();

      if (cause instanceof RuntimeException)
      {
        throw (RuntimeException) cause;
      }
      else
      {
        throw new RuntimeException(cause);
      }
    }
  }
  
  @Override
  public void afterApply(Mutable instance)
  {
    for (Relationship relationship : relationships)
    {
      relationship.apply();
    }

    relationships.clear();
  }

  private String getAttributeName(String compositeFieldName, String fieldName, Term root)
  {
    return compositeFieldName + " - " + fieldName + " - " + root.getTermId();
  }
}
