package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupBase;
import csu.mrc.ivcc.mdss.mo.IdentificationMethod;
import csu.mrc.ivcc.mdss.mo.Specie;

public class MorphologicalSpecieGroup extends MorphologicalSpecieGroupBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288141011L;

  public MorphologicalSpecieGroup()
  {
    super();
  }

  @Override
  public void validateQuantity()
  {
    super.validateQuantity();

    if (this.getCollection() != null && this.getCollection() instanceof MosquitoCollection)
    {
      if (this.getQuantity() != null && this.getQuantity() == 0)
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
      if (this.getCollection() instanceof MosquitoCollection && this.getIdentificationMethod() == null)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a identification method";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setAttributeLabel(getIdentificationMethodMd().getDisplayLabel());
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuantity() == 0 && this.getIdentificationMethod() != null)
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
      if (this.getCollection() instanceof MosquitoCollection && this.getSpecie() == null)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a specie";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setAttributeLabel(getSpecieMd().getDisplayLabel());
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuantity() == 0 && this.getSpecie() != null)
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
  
  public MorphologicalSpecieGroupView getView()
  {
    MorphologicalSpecieGroupView view = new MorphologicalSpecieGroupView();
    Specie specie = this.getSpecie();
    IdentificationMethod identificationMethod = this.getIdentificationMethod();
    
    view.setCollectionId(this.getId());
    view.setGroupId(this.getId());
    view.setQuantity(this.getQuantity());

    if(specie != null)
    {
      view.setSpecie(specie.getId());
    }
    
    if(identificationMethod != null)
    {
      view.setIdentificationMethod(identificationMethod.getId());
    }
    
    view.applyNoPersist();
    
    return view;
  }

  @Override
  public void apply()
  {
    validateQuantity();
    validateIdentificationMethod();
    validateSpecie();

    super.apply();
  }
}