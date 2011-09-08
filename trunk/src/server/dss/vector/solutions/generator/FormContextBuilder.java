package dss.vector.solutions.generator;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.io.excel.ContextBuilder;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.ImportContext;
import com.runwaysdk.dataaccess.io.excel.MdFieldFilter;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class FormContextBuilder extends ContextBuilder implements ContextBuilderIF, Reloadable
{
  private MdFormDAOIF   mdForm;

  private MdFieldFilter filter;

  public FormContextBuilder(MdFormDAOIF mdForm, MdFieldFilter filter)
  {
    this.mdForm = mdForm;
    this.filter = filter;
  }

  protected List<? extends MdAttributeDAOIF> getAttributes(ImportContext currentContext)
  {
    List<? extends MdFieldDAOIF> mdFields = this.mdForm.getSortedFields();
    List<MdAttributeDAOIF> mdAttributes = new LinkedList<MdAttributeDAOIF>();

    for (MdFieldDAOIF mdField : mdFields)
    {
      if (this.filter.accept(mdField))
      {
        MdWebAttributeDAOIF mdWebPrimitive = (MdWebAttributeDAOIF) mdField;

        mdAttributes.add(mdWebPrimitive.getDefiningMdAttribute());
      }
    }

    return mdAttributes;
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(Term.CLASS))
      {
        context.addExpectedColumn(new TermColumn(mdAttributeReference));
      }
    }
    else
    {
      super.buildAttributeColumn(context, mdAttribute);
    }
  }
}