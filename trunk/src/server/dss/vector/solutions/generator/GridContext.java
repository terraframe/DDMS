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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.ProblemIF;
import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Entity;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.business.RelationshipQuery;
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebMultipleTermDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImportProgressMonitorIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.FieldValidationProblem;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.RequestState;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.UnknownValueException;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class GridContext extends ImportContext implements Reloadable
{
  private Term[]                                               roots;

  private Map<Term, List<ExcelColumn>>                         map;

  private HashMap<String, ExcelColumn>                         columnNameMapping;

  private List<FieldConditionDAOIF>                            conditions;

  private HashMap<MdWebMultipleTermDAOIF, MdRelationshipDAOIF> multiTermFields;

  private MdBusinessDAOIF                                      mdClass;

  private MdAttributeDAOIF                                     mdAttribute;

  public GridContext(Sheet sheet, String sheetName, Sheet error, MdRelationshipDAOIF mdRelationship, MdWebSingleTermGridDAOIF mdCompositeField)
  {
    super(sheet, sheetName, error, mdRelationship);

    this.roots = TermRootCache.getRoots(mdCompositeField.getDefiningMdAttribute());
    this.map = new HashMap<Term, List<ExcelColumn>>();
    this.columnNameMapping = new HashMap<String, ExcelColumn>();
    this.conditions = mdCompositeField.getConditions();
    this.mdClass = mdRelationship.getParentMdBusiness();
    this.mdAttribute = mdCompositeField.getDefiningMdAttribute();
    this.multiTermFields = new HashMap<MdWebMultipleTermDAOIF, MdRelationshipDAOIF>();

    for (Term root : roots)
    {
      this.map.put(root, new LinkedList<ExcelColumn>());
    }

    List<? extends MdFieldDAOIF> fields = mdCompositeField.getMdForm().getAllMdFields();

    for (MdFieldDAOIF mdField : fields)
    {
      if (mdField instanceof MdWebMultipleTermDAOIF)
      {
        MdWebMultipleTermDAOIF mdWebMultiTerm = (MdWebMultipleTermDAOIF) mdField;
        MdRelationshipDAOIF termRelationship = MdFormUtil.getMdRelationship(mdWebMultiTerm);

        this.multiTermFields.put(mdWebMultiTerm, termRelationship);
      }
    }
  }

  @Override
  public MdRelationshipDAOIF getMdClass()
  {
    return (MdRelationshipDAOIF) super.getMdClass();
  }

  @Override
  public void addExtraColumn(ExcelColumn column)
  {
    String name = column.getAttributeName();

    for (Term root : roots)
    {
      if (name.contains(" - " + root.getTermId()))
      {
        this.map.get(root).add(column);
      }
    }

    this.columnNameMapping.put(name, column);

    super.addExtraColumn(column);
  }

  public ExcelColumn getColumn(String columnName)
  {
    return this.columnNameMapping.get(columnName);
  }

  public List<ExcelColumn> getColumns(Term term)
  {
    return this.map.get(term);
  }

  @Override
  public void readRow(Row row, ExcelImportProgressMonitorIF monitor)
  {
    MdRelationshipDAOIF mdRelationship = this.getMdClass();

    ExcelColumn idColumn = this.getColumn(GridExcelAdapter.PARENT_COLUMN_NAME);
    String oid = ExcelUtil.getString(row.getCell(idColumn.getIndex()));

    Mutable instance = this.getInstance(oid);

    boolean hasGridValues = false;
    List<Relationship> relationships = new LinkedList<Relationship>();

    try
    {
      Class<? extends Mutable> clazz = instance.getClass();
      
      String addMethodName = "add" + CommonGenerationUtil.upperFirstCharacter(mdRelationship.getParentMethod());
      Method addMethod = clazz.getMethod(addMethodName, Term.class);
      
      String getMethodName = "get" + CommonGenerationUtil.upperFirstCharacter(mdRelationship.getParentMethod()) + "Rel";
      Method getMethod = clazz.getMethod(getMethodName, Term.class);
      

      for (Term term : roots)
      {
        Relationship relationship = (Relationship) getMethod.invoke(instance, term);

        if(relationship == null) 
        {
          relationship = (Relationship) addMethod.invoke(instance, term);
        }
        else
        {
          relationship.lock();
        }

        List<ExcelColumn> columns = this.getColumns(term);

        for (ExcelColumn column : columns)
        {
          AttributeColumn attributeColumn = (AttributeColumn) column;

          try
          {
            Cell cell = row.getCell(column.getIndex());

            if (cell != null)
            {
              Object value = attributeColumn.getValue(cell);

              if (value != null)
              {
                attributeColumn.setInstanceValue(relationship, value);
                hasGridValues = true;
              }
            }
          }
          catch (InvocationTargetException e)
          {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof Exception)
            {
              this.addException(attributeColumn.getDisplayLabel(), attributeColumn.getMdAttribute(), (Exception) targetException);
            }
            else
            {
              this.addException(attributeColumn.getDisplayLabel(), attributeColumn.getMdAttribute(), e);
            }
          }
          catch (Exception e)
          {
            this.addException(attributeColumn.getDisplayLabel(), attributeColumn.getMdAttribute(), e);
          }

          List<ProblemIF> problemsInTransaction = RequestState.getProblemsInCurrentRequest();

          for (ProblemIF problem : problemsInTransaction)
          {
            this.addProblem(column.getDisplayLabel(), attributeColumn.getMdAttribute(), problem);
          }

          problemsInTransaction.clear();
        }

        relationships.add(relationship);
      }

      if (hasGridValues)
      {
        this.validateConditions(instance);

        for (Relationship relationship : relationships)
        {
          relationship.apply();
        }
        
        if (monitor != null)
        {
          monitor.entityImported(instance, null);
        }
      }
    }
    catch (InvocationTargetException e)
    {
      Throwable targetException = e.getTargetException();
      if (targetException instanceof Exception)
      {
        this.addException("", null, (Exception) targetException);
      }
      else
      {
        this.addException("", null, e);
      }
    }
    catch (Exception e)
    {
      this.addException("", null, e);
    }
  }

  private Business getInstance(String oid)
  {
    MdRelationshipDAOIF mdRelationship = this.getMdClass();
    MdBusinessDAOIF parentMdBusiness = mdRelationship.getParentMdBusiness();

    if (oid != null && oid.length() > 0)
    {
      String parentType = parentMdBusiness.definesType();

      QueryFactory factory = new QueryFactory();
      BusinessQuery query = factory.businessQuery(parentType);
      query.WHERE(query.aCharacter(MdFormUtil.OID).EQ(oid));

      OIterator<Business> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          return it.next();
        }
      }
      finally
      {
        it.close();
      }
    }

    // Unable to find an instance of the parent with the oid value provided in
    // the schema file
    Locale locale = Session.getCurrentLocale();
    MdAttributeConcreteDAOIF mdAttribute = parentMdBusiness.definesAttribute(MdFormUtil.OID);

    UnknownValueException e = new UnknownValueException();
    e.setAttributeLabel(mdAttribute.getDisplayLabel(locale));
    e.setTypeLabel(parentMdBusiness.getDisplayLabel(locale));
    e.setValue(oid);
    e.apply();

    throw e;
  }

  private void validateConditions(Mutable instance)
  {
    // First build the extra entity mapping of the instance object. We need to
    // do this in order for conditions on multi term fields to evaluate
    // correctly
    HashMap<String, List<Entity>> extraEntities = this.getExtraEntities(instance);

    for (FieldConditionDAOIF condition : conditions)
    {
      boolean valid = condition.evaluate(instance, extraEntities);

      if (!valid)
      {
        String formattedString = condition.getFormattedString();
        String msg = "Attribute is not applicable when [" + condition + "] does not evaluate to true";

        FieldValidationProblem problem = new FieldValidationProblem(instance.getId(), mdClass, mdAttribute, msg);
        problem.setCondition(formattedString);
        problem.throwIt();
      }
    }
  }

  private HashMap<String, List<Entity>> getExtraEntities(Mutable instance)
  {
    HashMap<String, List<Entity>> extraFieldEntities = new HashMap<String, List<Entity>>();

    Set<MdWebMultipleTermDAOIF> multiTermFields = this.multiTermFields.keySet();
    for (MdWebMultipleTermDAOIF mdWebMultiTerm : multiTermFields)
    {
      MdRelationshipDAOIF mdRelationship = this.multiTermFields.get(mdWebMultiTerm);

      List<Entity> entities = new LinkedList<Entity>();

      QueryFactory factory = new QueryFactory();

      RelationshipQuery query = factory.relationshipQuery(mdRelationship.definesType());
      query.WHERE(query.parentId().EQ(instance.getId()));

      OIterator<Relationship> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          entities.add(it.next());
        }

        extraFieldEntities.put(mdWebMultiTerm.getId(), entities);
      }
      finally
      {
        it.close();
      }

    }
    return extraFieldEntities;
  }
}
