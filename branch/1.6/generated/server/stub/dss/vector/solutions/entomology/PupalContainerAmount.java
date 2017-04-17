package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class PupalContainerAmount extends PupalContainerAmountBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = -535998456;
  
  public PupalContainerAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public PupalContainerAmount(PupalContainer parent, Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public PupalContainerAmountView getView()
  {
    PupalContainerAmountView view = new PupalContainerAmountView();
    
    view.populateView(this);
    
    return view;
  }
}
