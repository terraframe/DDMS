package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdView;
import com.runwaysdk.system.metadata.MdWebForm;

public class TargetBinding extends TargetBindingBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2043607150;

  public TargetBinding()
  {
    super();
  }

  @Override
  @Transaction
  public void delete()
  {
    List<TargetFieldBinding> fields = this.getFields();

    for (TargetFieldBinding field : fields)
    {
      field.delete();
    }

    super.delete();

    MdView mdView = this.getSourceView();

    ExcelSourceBinding source = ExcelSourceBinding.getBinding(mdView.definesType());
    source.delete();
  }

  public List<TargetFieldBinding> getFields()
  {
    List<TargetFieldBinding> list = new LinkedList<TargetFieldBinding>();

    TargetFieldBindingQuery query = new TargetFieldBindingQuery(new QueryFactory());
    query.WHERE(query.getTarget().EQ(this));

    OIterator<? extends TargetFieldBinding> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }

      return list;
    }
    finally
    {
      iterator.close();
    }
  }

  public TargetDefinitionIF getDefinition()
  {
    MdView sourceView = this.getSourceView();
    MdWebForm targetBusiness = this.getTargetBusiness();
    PersistenceStrategy strategy = this.getStrategy();

    TargetDefinition definition = new TargetDefinition();
    definition.setSourceType(sourceView.definesType());
    definition.setTargetType(targetBusiness.definesType());
    definition.setStrategy(strategy);
    definition.setNew(false);
    definition.setApplied(true);

    List<TargetFieldBinding> fields = this.getFields();

    for (TargetFieldBinding field : fields)
    {
      definition.addField(field.getTargetField());
    }

    return definition;
  }

  public static TargetBinding getBinding(String type)
  {
    return TargetBinding.getBindingForTarget(MdWebForm.getByKey(type));
  }

  public static TargetBinding getBindingForTarget(MdWebForm mdClass)
  {
    TargetBindingQuery query = new TargetBindingQuery(new QueryFactory());
    query.WHERE(query.getTargetBusiness().EQ(mdClass));

    OIterator<? extends TargetBinding> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        TargetBinding binding = iterator.next();

        return binding;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public static TargetBinding getBindingForSource(MdView mdView)
  {
    TargetBindingQuery query = new TargetBindingQuery(new QueryFactory());
    query.WHERE(query.getSourceView().EQ(mdView));

    OIterator<? extends TargetBinding> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        TargetBinding binding = iterator.next();

        return binding;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

}
