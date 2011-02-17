package dss.vector.solutions.admin.shapefile;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.runwaysdk.manager.general.Localizer;

public class OptionContentProvider implements IStructuredContentProvider
{
  private Collection<LabeledValueBean> options;

  public OptionContentProvider()
  {
    this.options = new LinkedList<LabeledValueBean>();
    this.options.add(new LabeledValueBean(null, Localizer.getMessage("CHOOSE_OPTION")));
    this.options.add(new LabeledValueBean(LocatedInBean.Option.DELETE_EXISTING.name(), Localizer.getMessage("DELETE_EXISTING")));
    this.options.add(new LabeledValueBean(LocatedInBean.Option.PRESERVE_EXISTING.name(), Localizer.getMessage("PRESERVE_EXISTING")));
    this.options.add(new LabeledValueBean(LocatedInBean.Option.BUILD_ORPHANED.name(), Localizer.getMessage("BUILD_ORPHANED")));
  }

  @Override
  public Object[] getElements(Object element)
  {
    return options.toArray(new LabeledValueBean[options.size()]);
  }

  @Override
  public void dispose()
  {
    // Do nothing
  }

  @Override
  public void inputChanged(Viewer arg0, Object arg1, Object arg2)
  {
    // Do nothing
  }

}
