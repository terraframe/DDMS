package dss.vector.solutions.entomology;

import dss.vector.solutions.entomology.MorphologicalSpecieGroupBase;


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
    if (this.getCollection() != null && this.getCollection() instanceof MosquitoCollection)
    {
      if (this.getQuantity() != null && this.getQuantity() == 0)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a quantity";
        InvalidMorphologicalQuantityProblem p = new InvalidMorphologicalQuantityProblem(msg);
        p.setNotification(this, QUANTITY);
        p.apply();
        p.throwIt();
      }
    }
    
    if(this.getQuantityFemale() != null && this.getQuantityMale() != null && this.getQuantity() != (this.getQuantityFemale() + this.getQuantityMale()))
    {
        String msg = "The total number of mosquitos is not equal to the number of female and male mosquitos";
        QuantityMismatchProblem p = new QuantityMismatchProblem(msg);
        p.setQuantity(this.getQuantity());
        p.setQuantityFemale(this.getQuantityFemale());
        p.setQuantityMale(this.getQuantityMale());
        p.setNotification(this, QUANTITY);
        p.apply();
        p.throwIt();
    }
  }
  
  @Override
  public void validateQuantityFemale()
  {
    if(this.getQuantityFemale() != null && this.getQuantity() != null)
    {
      super.validateQuantityFemale();
      
      if(this.getQuantityFemale() > this.getQuantity())
      {
        String msg = "It is impossible to have more female mosquitos than the total number of mosquitos";
        InvalidFemaleQuantityProblem p = new InvalidFemaleQuantityProblem(msg);
        p.setQuantity(this.getQuantity());
        p.setQuantityFemale(this.getQuantityFemale());
        p.setNotification(this, QUANTITYFEMALE);
        p.apply();
        p.throwIt(); 
      }
    }
  }
  
  @Override
  public void validateQuantityMale()
  {
    if(this.getQuantityMale() != null && this.getQuantity() != null)
    {
      if(this.getQuantityMale() > this.getQuantity())
      {
        String msg = "It is impossible to have more male mosquitos than the total number of mosquitos";
        InvalidMaleQuantityProblem p = new InvalidMaleQuantityProblem(msg);
        p.setQuantity(this.getQuantity());
        p.setQuantityMale(this.getQuantityMale());
        p.setNotification(this, QUANTITYMALE);
        p.apply();
        p.throwIt(); 
      }
    }
  }


  @Override
  public void validateIdentificationMethod()
  {
    if (this.getCollection() != null)
    {
      if (this.getCollection() instanceof MosquitoCollection && this.getIdentificationMethod() == null)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a identification method";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setNotification(this, IDENTIFICATIONMETHOD);
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuantity() == 0 && this.getIdentificationMethod() != null)
        {
          String msg = "Morphological groups of a MosquitoCollectionPoint cannot have an IdentificationMethod when the quantity is zero";

          InvalidMorphologicalSpecieProblem p = new InvalidMorphologicalSpecieProblem(msg);
          p.setNotification(this, IDENTIFICATIONMETHOD);
          p.apply();
          p.throwIt();
        }
      }
    }
  }

  @Override
  public void validateSpecie()
  {
    if (this.getCollection() != null)
    {
      if (this.getCollection() instanceof MosquitoCollection && this.getSpecie() == null)
      {
        String msg = "Morphological groups of a Mosquito Collection must have a specie";

        EmptyValueProblem p = new EmptyValueProblem(msg);
        p.setNotification(this, SPECIE);
        p.apply();
        p.throwIt();
      }
      else if (this.getCollection() instanceof MosquitoCollectionPoint)
      {
        if (this.getQuantity() == 0 && this.getSpecie() != null)
        {
          String msg = "Morphological groups of a MosquitoCollectionPoint cannot have a Specie when the quantity is zero";

          InvalidMorphologicalSpecieProblem p = new InvalidMorphologicalSpecieProblem(msg);
          p.setNotification(this, SPECIE);
          p.apply();
          p.throwIt();
        }
      }
    }
  }  
  
  public MorphologicalSpecieGroupView getView()
  {
    MorphologicalSpecieGroupView view = new MorphologicalSpecieGroupView();
    
    view.setGroupId(this.getId());
    view.setCollection(this.getCollection());
    view.setQuantity(this.getQuantity());
    view.setSpecie(this.getSpecie());
    view.setIdentificationMethod(this.getIdentificationMethod());
    view.setDateCollected(this.getCollection().getDateCollected());
    view.setGeoEntity(this.getCollection().getGeoEntity());

    if(this.getQuantityFemale() != null)
    {
      view.setQuantityFemale(this.getQuantityFemale());
    }

    if(this.getQuantityMale() != null)
    {
      view.setQuantityMale(this.getQuantityMale());
    }

    view.applyNoPersist();
    
    return view;
  }

  @Override
  public void apply()
  {
    validateQuantity();
    validateQuantityFemale();
    validateQuantityMale();
    validateIdentificationMethod();
    validateSpecie();

    super.apply();
  }
}
