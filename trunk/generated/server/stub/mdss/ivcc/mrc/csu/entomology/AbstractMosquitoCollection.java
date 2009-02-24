package mdss.ivcc.mrc.csu.entomology;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionBase;
import mdss.ivcc.mrc.csu.entomology.MorphologicalSpecieGroupQuery;
import mdss.ivcc.mrc.csu.mo.IdentificationMethod;
import mdss.ivcc.mrc.csu.mo.Specie;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class AbstractMosquitoCollection extends AbstractMosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288138118L;
  
  public AbstractMosquitoCollection()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO The date format needs to be localizable
    DateFormat format = DateFormat.getDateInstance();
    return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
  }  
  
  @Override
  public String toString()
  {
    return this.buildKey();
  }
  
  @Override
  public MorphologicalSpecieGroupView[] getMorphologicalSpecieGroups()
  {
    List<MorphologicalSpecieGroupView> list = new LinkedList<MorphologicalSpecieGroupView>();

    MorphologicalSpecieGroupQuery query = new MorphologicalSpecieGroupQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);
    
    OIterator<? extends MorphologicalSpecieGroup> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      MorphologicalSpecieGroup group = iterator.next();
      MorphologicalSpecieGroupView view = new MorphologicalSpecieGroupView();
      Specie specie = group.getSpecie();
      IdentificationMethod identificationMethod = group.getIdentificationMethod();
      
      view.setCollectionId(this.getId());
      view.setGroupId(group.getId());
      view.setQuantity(group.getQuantity());

      if(specie != null)
      {
        view.setSpecie(specie.getId());
      }
      
      if(identificationMethod != null)
      {
        view.setIdentificationMethod(identificationMethod.getId());
      }
      
      view.applyNoPersist();
      
      list.add(view);
    }
    
    iterator.close();
    
    return list.toArray(new MorphologicalSpecieGroupView[list.size()]);
  }
  
  @Override
  public void delete()
  {
    for(MorphologicalSpecieGroupView view : this.getMorphologicalSpecieGroups())
    {
      MorphologicalSpecieGroup group = MorphologicalSpecieGroup.get(view.getGroupId());
      group.delete();
    }
    
    super.delete();
  }
}
