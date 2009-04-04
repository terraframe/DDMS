package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class ConcreteMosquitoCollection extends ConcreteMosquitoCollectionBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236104204872L;

  public ConcreteMosquitoCollection()
  {
    super();
  }

  @Override
  public void validateDateCollected()
  {
    if (this.getDateCollected() != null)
    {
      super.validateDateCollected();

      Date current = new Date();

      if (current.before(this.getDateCollected()))
      {
        String msg = "It is impossible to have a collection date before the current date";        
        
        InvalidCollectionDateProblem p = new InvalidCollectionDateProblem(msg);
        p.setCollectionDate(this.getDateCollected());
        p.setCurrentDate(current);
        p.setNotification(this, DATECOLLECTED);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void apply()
  {
    validateDateCollected();
    
    super.apply();
  }

  @Override
  public MorphologicalSpecieGroupView[] getMorphologicalSpecieGroups()
  {
    List<MorphologicalSpecieGroupView> list = new LinkedList<MorphologicalSpecieGroupView>();

    MorphologicalSpecieGroupQuery query = new MorphologicalSpecieGroupQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);

    OIterator<? extends MorphologicalSpecieGroup> iterator = query.getIterator();

    while (iterator.hasNext())
    {
      list.add(iterator.next().getView());
    }

    iterator.close();

    return list.toArray(new MorphologicalSpecieGroupView[list.size()]);
  }

  @Override
  public void delete()
  {
    for (MorphologicalSpecieGroupView view : this.getMorphologicalSpecieGroups())
    {
      view.delete();
    }

    super.delete();
  }

}