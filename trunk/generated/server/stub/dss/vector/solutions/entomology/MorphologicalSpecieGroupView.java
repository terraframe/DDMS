package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class MorphologicalSpecieGroupView extends MorphologicalSpecieGroupViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234793969635L;

  public MorphologicalSpecieGroupView()
  {
    super();
  }

  @Transaction
  public void apply()
  {
    MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();

    if (this.hasConcreteId())
    {
      group = MorphologicalSpecieGroup.lock(this.getGroupId());
    }
    
    this.populateConcrete(group);
    
    group.apply();
    
    this.populateView(group);    
  }

  public void populateView(MorphologicalSpecieGroup group)
  {
    this.setGroupId(group.getId());
    this.setCollection(group.getCollection());
    this.setQuantity(group.getQuantity());
    this.setSpecie(group.getSpecie());
    this.setIdentificationMethod(group.getIdentificationMethod());

    if(group.getQuantityFemale() != null)
    {
      this.setQuantityFemale(group.getQuantityFemale());
    }

    if(group.getQuantityMale() != null)
    {
      this.setQuantityMale(group.getQuantityMale());
    }

    this.applyNoPersist();
  }

  protected void populateConcrete(MorphologicalSpecieGroup group)
  {
    group.setCollection(this.getCollection());
    group.setQuantity(this.getQuantity());
    group.setQuantityFemale(this.getQuantityFemale());
    group.setQuantityMale(this.getQuantityMale());
    group.setIdentificationMethod(this.getIdentificationMethod());
    group.setSpecie(this.getSpecie());
  }

  private boolean hasConcreteId()
  {
    return this.getGroupId() != null && !this.getGroupId().equals("");
  }

  @Override
  public void delete()
  {
    if(this.hasConcreteId())
    {
      MorphologicalSpecieGroup.get(this.getGroupId()).delete();
    }
  }

  @Transaction
  public static MorphologicalSpecieGroupView[] saveAll(MorphologicalSpecieGroupView[] array)
  {
    for (MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }

    return array;
  }
}
