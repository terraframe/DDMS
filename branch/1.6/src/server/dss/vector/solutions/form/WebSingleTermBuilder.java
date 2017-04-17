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