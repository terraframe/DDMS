/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Entity;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdWebMultipleTermDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.dataaccess.metadata.FieldValidationProblem;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.export.AbstractExcelAdapter;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class MultiTermListener extends AbstractExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private MdWebMultipleTermDAOIF    mdField;

  private List<Relationship>        relationships;

  private String                    relationshipMethod;

  private List<FieldConditionDAOIF> conditions;

  public MultiTermListener(MdWebMultipleTermDAOIF mdField, String relationshipName)
  {
    this.mdField = mdField;
    this.relationshipMethod = relationshipName;

    this.relationships = new LinkedList<Relationship>();
    this.conditions = mdField.getConditions();
  }

  @Override
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    String fieldName = mdField.getFieldName();
    MdAttributeDAOIF mdAttribute = mdField.getDefiningMdAttribute();
    String fieldDisplayLabel = mdField.getDisplayLabel(Session.getCurrentLocale());
    Term[] roots = TermRootCache.getRoots(mdAttribute);
    
    this.addGridColumns(extraColumns, (MdAttributeReferenceDAOIF) mdAttribute, fieldDisplayLabel);
//
//
//    for (Term root : roots)
//    {
//      String rootDisplayLabel = root.getTermDisplayLabel().getValue();
//
//      extraColumns.add(new ExcelColumn(this.getAttributeName(fieldName, root), fieldDisplayLabel + " " + rootDisplayLabel));
//    }
  }

  @Override
  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    String fieldName = mdField.getFieldName();
    MdAttributeDAOIF mdAttribute = mdField.getDefiningMdAttribute();
    Term[] roots = TermRootCache.getRoots(mdAttribute);

    Class<? extends Mutable> clazz = instance.getClass();
    String methodName = "add" + CommonGenerationUtil.upperFirstCharacter(this.relationshipMethod);
    Method method = clazz.getMethod(methodName, Term.class);

    String getMethodName = "get" + CommonGenerationUtil.upperFirstCharacter(this.relationshipMethod) + "Rel";
    Method getMethod = clazz.getMethod(getMethodName, Term.class);

    try
    {
      for (Term term : roots)
      {
        Relationship relationship = (Relationship) getMethod.invoke(instance, term);

        for (ExcelColumn column : extraColumns)
        {
          String attributeName = this.getAttributeName(fieldName, term);

          if (column.getAttributeName().equals(attributeName) && ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            if (relationship == null)
            {
              relationship = (Relationship) method.invoke(instance, term);
            }
            relationships.add(relationship);
          }
          else
          {
            if (relationship != null && !relationship.isNew())
            {
              relationship.delete();
            }
          }
        }
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

  private void validateConditions(Mutable instance, HashMap<String, List<Entity>> entities)
  {
    for (FieldConditionDAOIF condition : conditions)
    {
      boolean valid = condition.evaluate(instance, entities);

      if (!valid)
      {
        MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(instance.getType());
        MdAttributeDAOIF mdAttribute = mdField.getDefiningMdAttribute();

        String formattedString = condition.getFormattedString();
        String msg = "Attribute is not applicable when [" + condition + "] does not evaluate to true";

        FieldValidationProblem problem = new FieldValidationProblem(instance.getId(), mdClass, mdAttribute, msg);
        problem.setCondition(formattedString);
        problem.throwIt();
      }
    }
  }

  @Override
  public void addAdditionalEntities(HashMap<String, List<Entity>> map)
  {
    map.put(this.mdField.getId(), new LinkedList<Entity>(this.relationships));
  }

  @Override
  public void validate(Mutable instance, HashMap<String, List<Entity>> entities)
  {
    if (this.relationships.size() > 0)
    {
      // Validate any conditions on the field
      this.validateConditions(instance, entities);
    }
  }

  @Override
  public void afterApply(Mutable instance)
  {
    for (Relationship relationship : relationships)
    {
      if (relationship.isNew())
      {
        relationship.apply();
      }
    }

    relationships.clear();
  }

  private String getAttributeName(String fieldName, Term root)
  {
    return fieldName + " - " + root.getTermId();
  }
}
