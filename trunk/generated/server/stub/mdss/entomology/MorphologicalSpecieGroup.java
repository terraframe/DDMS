package mdss.entomology;

public class MorphologicalSpecieGroup extends MorphologicalSpecieGroupBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288141011L;

  public MorphologicalSpecieGroup()
  {
    super();
  }

  @Override
  public void validateQuanity()
  {
    super.validateQuanity();

    if (this.getCollection() != null && this.getCollection() instanceof MosquitoCollection)
    {
      if (this.getQuanity() != null && this.getQuanity() == 0)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a quantity";
        InvalidMorphologicalQuantityProblem p = new InvalidMorphologicalQuantityProblem(msg);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateIdentificationMethod()
  {
    super.validateIdentificationMethod();

    if (this.getCollection() != null)
    {
      if (this.getCollection() instanceof MosquitoCollection && this.getIdentificationMethod().size() == 0)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a identification method";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setAttributeLabel(getIdentificationMethodMd().getDisplayLabel());
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuanity() == 0 && this.getIdentificationMethod().size() != 0)
        {
          String msg = "Morphological groups of a MosquitoCollectionPoint cannot have an IdentificationMethod when the quantity is zero";

          InvalidMorphologicalSpecieProblem p = new InvalidMorphologicalSpecieProblem(msg);
          p.setAttributeLabel(getIdentificationMethodMd().getDisplayLabel());
          p.apply();
          p.throwIt();
        }
      }
    }
  }

  @Override
  public void validateSpecie()
  {
    super.validateSpecie();

    if (this.getCollection() != null)
    {
      if (this.getCollection() instanceof MosquitoCollection && this.getSpecie().size() == 0)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a specie";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setAttributeLabel(getSpecieMd().getDisplayLabel());
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuanity() == 0 && this.getSpecie().size() != 0)
        {
          String msg = "Morphological groups of a MosquitoCollectionPoint cannot have a Specie when the quantity is zero";

          InvalidMorphologicalSpecieProblem p = new InvalidMorphologicalSpecieProblem(msg);
          p.setAttributeLabel(getSpecieMd().getDisplayLabel());
          p.apply();
          p.throwIt();
        }
      }
    }
  }

  @Override
  public void apply()
  {
    validateQuanity();
    validateIdentificationMethod();
    validateSpecie();

    boolean firstApply = !this.isAppliedToDB() & this.isNew();

    super.apply();

    if (firstApply)
    {
      AbstractMosquitoCollection c = this.getCollection();
      CollectionSpecie collectionSpecie = new CollectionSpecie(c, this);
      collectionSpecie.apply();
    }
  }
}
