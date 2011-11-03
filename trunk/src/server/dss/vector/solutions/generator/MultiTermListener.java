package dss.vector.solutions.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Relationship;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class MultiTermListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private MdClassDAOIF       mdClass;

  private MdFieldDAOIF       mdField;

  private List<Relationship> relationships;

  private String             relationsihpMethod;

  public MultiTermListener(MdClassDAOIF mdClass, MdFieldDAOIF mdField, String relationshipName)
  {
    this.mdClass = mdClass;
    this.mdField = mdField;
    this.relationsihpMethod = relationshipName;

    this.relationships = new LinkedList<Relationship>();
  }

  @Override
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    String fieldName = mdField.getFieldName();
    MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(fieldName);
    String fieldDisplayLabel = mdField.getDisplayLabel(Session.getCurrentLocale());
    Term[] roots = TermRootCache.getRoots(mdAttribute);

    for (Term root : roots)
    {
      String rootDisplayLabel = root.getTermDisplayLabel().getValue();

      extraColumns.add(new ExcelColumn(this.getAttributeName(fieldName, root), fieldDisplayLabel + " " + rootDisplayLabel));
    }
  }

  @Override
  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    String fieldName = mdField.getFieldName();
    MdAttributeDAOIF mdAttribute = mdClass.definesAttribute(fieldName);
    Term[] roots = TermRootCache.getRoots(mdAttribute);

    Class<? extends Mutable> clazz = instance.getClass();
    String methodName = "add" + CommonGenerationUtil.upperFirstCharacter(this.relationsihpMethod);
    Method method = clazz.getMethod(methodName, Term.class);

    try
    {
      for (Term term : roots)
      {
        for (ExcelColumn column : extraColumns)
        {
          String attributeName = this.getAttributeName(fieldName, term);

          if (column.getAttributeName().equals(attributeName) && ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            Relationship relationship = (Relationship) method.invoke(instance, term);
            relationships.add(relationship);
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

  @Override
  public void afterApply(Mutable instance)
  {
    for (Relationship relationship : relationships)
    {
      relationship.apply();
    }

    relationships.clear();
  }

  private String getAttributeName(String fieldName, Term root)
  {
    return fieldName + " - " + root.getTermId();
  }
}
