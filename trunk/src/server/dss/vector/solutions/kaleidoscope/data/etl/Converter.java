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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.ExpressionException;
import com.runwaysdk.business.InvalidExpressionSyntaxException;
import com.runwaysdk.business.Transient;
import com.runwaysdk.constants.MdAttributeBooleanUtil;
import com.runwaysdk.constants.MdAttributeDateTimeUtil;
import com.runwaysdk.constants.MdAttributeDateUtil;
import com.runwaysdk.constants.MdAttributeTimeUtil;
import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeNumberDAOIF;
import com.runwaysdk.dataaccess.MdAttributePrimitiveDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTimeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;
import ognl.ExpressionSyntaxException;
import ognl.Ognl;
import ognl.OgnlClassResolver;
import ognl.OgnlContext;
import ognl.OgnlException;

public class Converter implements ConverterIF, Reloadable
{
  private TargetContextIF      context;

  private Disease              disease;

  private ProgressMonitorIF    monitor;

  private Workbook             errors;

  private Set<ImportProblemIF> problems;

  public Converter(TargetContextIF context, Disease disease, ProgressMonitorIF monitor)
  {
    this.context = context;
    this.disease = disease;
    this.monitor = monitor;
    this.problems = new TreeSet<ImportProblemIF>();
  }

  @Override
  public TargetContextIF getTargetContext()
  {
    return this.context;
  }

  @Override
  @Transaction
  public void create(Transient source)
  {
    try
    {
      List<TargetFieldIF> fields = this.context.getFields(source.getType());
      String oid = this.getOid(source, fields);

      BusinessDAO business = this.context.getOrCreateMutable(source.getType(), oid);
      boolean hasValues = false;
      boolean valid = true;

      for (TargetFieldIF field : fields)
      {
        String attributeName = field.getName();

        MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) business.getMdAttributeDAO(attributeName);

        // get value can intentionally fail if attempting to get the value of a
        // location that is on the
        // location exclusion list. Note the custom effor after this TRY
        // statement.
        FieldValue fValue = field.getValue(mdAttribute, source);

        if (! ( fValue instanceof ImportProblemIF ))
        {
          Object value = fValue.getValue();

          if (value != null)
          {
            business.setValue(attributeName, value);
          }

          hasValues = hasValues || !fValue.isBlank();
        }
        else
        {
          this.problems.add((ImportProblemIF) fValue);

          valid = false;
        }
      }

      business.setValue(MdFormUtil.DISEASE, this.disease.getId());

      if (oid == null)
      {
        business.setValue(MdFormUtil.OID, LocalProperty.getNextId());
      }

      /*
       * Before apply ensure that at least one source field was not blank
       */
      if (hasValues)
      {
        TargetDefinitionIF definition = this.context.getDefinition(source.getType());
        PersistenceStrategyIF strategy = definition.getStrategy();

        this.calculate(business);

        strategy.handle(business);

        if (valid)
        {
          this.monitor.entityImported(business.getId());
        }
      }
    }
    catch (ExclusionException e)
    {
      // Do nothing. It's likely that a source value was not found because of
      // location exclusions
    }
  }

  private void calculate(BusinessDAO businessDAO)
  {
    Business business = null;

    // Time to process the expression attributes.
    List<? extends MdAttributeConcreteDAOIF> mdAttrList = businessDAO.getMdAttributeDAOs();

    for (MdAttributeConcreteDAOIF mdAttributeConcreteDAOIF : mdAttrList)
    {
      if (mdAttributeConcreteDAOIF instanceof MdAttributePrimitiveDAOIF)
      {
        MdAttributePrimitiveDAOIF mdAttributePrimitiveDAOIF = (MdAttributePrimitiveDAOIF) mdAttributeConcreteDAOIF;

        if (mdAttributePrimitiveDAOIF.isExpression())
        {
          String attributeName = mdAttributePrimitiveDAOIF.definesAttribute();
          // Clear the existing value
          businessDAO.setValue(attributeName, "");

          String expressionString = mdAttributePrimitiveDAOIF.getExpression();

          try
          {
            Object expression;
            try
            {
              expression = Ognl.parseExpression(expressionString);
            }
            catch (ExpressionSyntaxException e)
            {
              String devMessage = "The attribute [" + mdAttributePrimitiveDAOIF.definesAttribute() + "] has an invalid expression syntax:\n" + e.getLocalizedMessage();
              throw new InvalidExpressionSyntaxException(devMessage, mdAttributePrimitiveDAOIF, e);
            }

            Object expressionValue;

            try
            {
              if (business == null)
              {
                business = BusinessFacade.get(businessDAO);
              }

              // I am offended that I even have to do this. OGNL stores
              // reflection method definitions which cause
              // problems when classes are reloaded.
              OgnlClassResolver.clearOgnlRuntimeMethodCache();
              expressionValue = Ognl.getValue(expression, new OgnlContext(), business);
            }
            catch (RuntimeException e)
            {
              String devMessage = "The expression on attribute [" + mdAttributePrimitiveDAOIF.definesAttribute() + "] has an error:\n" + e.getLocalizedMessage();
              throw new ExpressionException(devMessage, mdAttributePrimitiveDAOIF, e);
            }

            Object setterValue;

            if (mdAttributePrimitiveDAOIF instanceof MdAttributeNumberDAOIF && ( expressionValue instanceof Integer || expressionValue instanceof Long || expressionValue instanceof Float || expressionValue instanceof Double || expressionValue instanceof BigDecimal ))
            {
              setterValue = expressionValue.toString();
            }
            else if (mdAttributePrimitiveDAOIF instanceof MdAttributeDateTimeDAOIF && expressionValue instanceof Date)
            {
              setterValue = MdAttributeDateTimeUtil.getTypeUnsafeValue((Date) expressionValue);
            }
            else if (mdAttributePrimitiveDAOIF instanceof MdAttributeDateDAOIF && expressionValue instanceof Date)
            {
              setterValue = MdAttributeDateUtil.getTypeUnsafeValue((Date) expressionValue);
            }
            else if (mdAttributePrimitiveDAOIF instanceof MdAttributeTimeDAOIF && expressionValue instanceof Date)
            {
              setterValue = MdAttributeTimeUtil.getTypeUnsafeValue((Date) expressionValue);
            }
            else if (mdAttributePrimitiveDAOIF instanceof MdAttributeBooleanDAOIF && expressionValue instanceof Integer)
            {
              setterValue = MdAttributeBooleanUtil.format((Integer) expressionValue);
            }
            else if (mdAttributePrimitiveDAOIF instanceof MdAttributeCharDAOIF && expressionValue instanceof String)
            {
              setterValue = expressionValue;
            }
            else if (expressionValue == null)
            {
              setterValue = "";
            }
            else
            {
              setterValue = expressionValue.toString();
            }

            // Clear the existing value
            businessDAO.setValue(attributeName, setterValue);
          }
          catch (OgnlException e)
          {
            String devMessage = "The expression on attribute [" + mdAttributePrimitiveDAOIF.definesAttribute() + "] has an error:\n" + e.getLocalizedMessage();
            throw new ExpressionException(devMessage, mdAttributePrimitiveDAOIF, e);
          }
        }
      }
    }
  }

  private String getOid(Transient source, List<TargetFieldIF> fields)
  {
    for (TargetFieldIF field : fields)
    {
      if (field.getName().equals(MdFormUtil.OID))
      {
        MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) MdAttributeConcreteDAO.getByKey(field.getKey());

        FieldValue fValue = field.getValue(mdAttribute, source);
        return fValue.getValue().toString();
      }
    }
    return null;
  }

  public Collection<ImportProblemIF> getProblems()
  {
    return problems;
  }

  @Override
  public void setErrors(Workbook errors)
  {
    this.errors = errors;
  }

  @Override
  public Workbook getErrors()
  {
    return this.errors;
  }

}
