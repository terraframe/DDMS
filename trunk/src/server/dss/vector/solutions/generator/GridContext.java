package dss.vector.solutions.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.runwaysdk.ProblemIF;
import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.dataaccess.FieldConditionDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
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
  private Term[]                       roots;

  private Map<Term, List<ExcelColumn>> map;

  private HashMap<String, ExcelColumn> columnNameMapping;

  private List<FieldConditionDAOIF>    conditions;

  private MdBusinessDAOIF              mdClass;

  private MdAttributeDAOIF             mdAttribute;

  public GridContext(HSSFSheet sheet, String sheetName, HSSFSheet error, MdRelationshipDAOIF mdRelationship, MdWebSingleTermGridDAOIF mdCompositeField)
  {
    super(sheet, sheetName, error, mdRelationship);

    this.roots = TermRootCache.getRoots(mdCompositeField.getDefiningMdAttribute());
    this.map = new HashMap<Term, List<ExcelColumn>>();
    this.columnNameMapping = new HashMap<String, ExcelColumn>();
    this.conditions = mdCompositeField.getConditions();
    this.mdClass = mdRelationship.getParentMdBusiness();
    this.mdAttribute = mdCompositeField.getDefiningMdAttribute();

    for (Term root : roots)
    {
      this.map.put(root, new LinkedList<ExcelColumn>());
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

  public void readRow(HSSFRow row)
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
      String methodName = "add" + CommonGenerationUtil.upperFirstCharacter(mdRelationship.getParentMethod());
      Method method = clazz.getMethod(methodName, Term.class);

      for (Term term : roots)
      {
        Relationship relationship = (Relationship) method.invoke(instance, term);

        List<ExcelColumn> columns = this.getColumns(term);

        for (ExcelColumn column : columns)
        {
          AttributeColumn attributeColumn = (AttributeColumn) column;

          try
          {
            HSSFCell cell = row.getCell(column.getIndex());

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
    for (FieldConditionDAOIF condition : conditions)
    {
      boolean valid = condition.evaluate(instance);

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

}
