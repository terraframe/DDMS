package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.surveillance.ChildOption;

public class CollectionContainer extends CollectionContainerBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 228214925;
  
  public CollectionContainer(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CollectionContainer(dss.vector.solutions.entomology.PremiseTaxon parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
 
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
  
  @Override
  public void apply()
  {
    validateNumberWithWater();
    validateNumberImmatures();
    validateNumberLarvae();
    validateNumberPupae();
    
    super.apply();
  }
  
  @Override
  public void validateNumberWithWater()
  {
    if(this.getNumberWithWater() != null && this.getNumberContainers() != null)
    {
      if(this.getNumberWithWater() > this.getNumberContainers())
      {
        RangeValueProblem p = new RangeValueProblem();
        p.setNotification(this, NUMBERWITHWATER);
        p.setAttributeDisplayLabel(getNumberWithWaterMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberContainers());
        p.apply();
        
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateNumberImmatures()
  {
    if(this.getNumberImmatures() != null && this.getNumberWithWater() != null)
    {
      Integer _larvae = this.getNumberLarvae() != null ? this.getNumberLarvae() : 0;
      Integer _pupae = this.getNumberPupae() != null ? this.getNumberPupae() : 0;
      
      Integer max = this.getNumberWithWater();
      Integer min = Math.max(Math.max(0, _larvae), _pupae);
      
      if(this.getNumberImmatures() > max || this.getNumberImmatures() < min)
      {
        RangeValueProblem p = new RangeValueProblem();
        p.setNotification(this, NUMBERIMMATURES);
        p.setAttributeDisplayLabel(getNumberImmaturesMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setLowerLimit(min);
        p.setUpperLimit(max);
        p.apply();
        
        p.throwIt();
      }
    }
    
  }
  
  @Override
  public void validateNumberLarvae()
  {
    if(this.getNumberLarvae() != null && this.getNumberWithWater() != null)
    {
      if(this.getNumberLarvae() > this.getNumberWithWater())
      {
        RangeValueProblem p = new RangeValueProblem();
        p.setNotification(this, NUMBERLARVAE);
        p.setAttributeDisplayLabel(getNumberLarvaeMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberWithWater());
        p.apply();
        
        p.throwIt();
      }
    }
  }
    
  @Override
  public void validateNumberPupae()
  {
    if(this.getNumberPupae() != null && this.getNumberWithWater() != null)
    {
      if(this.getNumberPupae() > this.getNumberWithWater())
      {
        RangeValueProblem p = new RangeValueProblem();
        p.setNotification(this, NUMBERPUPAE);
        p.setAttributeDisplayLabel(getNumberPupaeMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberWithWater());
        p.apply();
        
        p.throwIt();
      }
    }
  }

  public CollectionContainerView getView()
  {
    CollectionContainerView view = new CollectionContainerView();
    
    view.populateView(this);
    
    return view;
  }
}
