package dss.vector.solutions.general;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.ontology.Term;

public class MenuItem extends MenuItemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1188314482;

  public MenuItem()
  {
    super();

  }

  @Override
  public void lock()
  {
    InstallProperties.validateMasterOperation();

    super.lock();
  }

  @Override
  public void unlock()
  {
    InstallProperties.validateMasterOperation();

    super.unlock();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && !this.isAppliedToDB() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    InstallProperties.validateMasterOperation();

    validateTermAsLeaf();

    super.apply();
  }

  /**
   * Checks that a MenuItem Term can only point to a leaf node.
   */
  @AbortIfProblem
  private void validateTermAsLeaf()
  {
    Term term = this.getTerm();
    OIterator<? extends Term> iter = term.getAllChildTerm();

    try
    {
      if (iter.hasNext())
      {
        throw new MenuItemLeafException();
      }
    }
    finally
    {
      iter.close();
    }
  }

  @Override
  public void delete()
  {
    InstallProperties.validateMasterOperation();

    super.delete();
  }

  @Override
  public String toString()
  {
    Term term = this.getTerm();
    if (term != null)
    {
      return term.getTermDisplayLabel().getValue();
    }
    else
    {
      return super.toString();
    }
  }

  public static List<? extends MenuItem> findMenuItems(SystemURL url)
  {
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getUrl().EQ(url));

    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }
}
