package csu.mrc.ivcc.mdss.entomology;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

import csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionBase;
import csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupQuery;

public abstract class ConcreteMosquitoCollection extends ConcreteMosquitoCollectionBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236104204872L;

  public ConcreteMosquitoCollection()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // TODO The date format needs to be localizable
    if (this.getDateCollected() != null)
    {
      DateFormat format = DateFormat.getDateInstance();
      return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
    }

    return this.getId();
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