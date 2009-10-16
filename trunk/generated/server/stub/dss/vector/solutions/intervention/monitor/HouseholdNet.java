package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;


public class HouseholdNet extends HouseholdNetBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1239641285868L;

  public HouseholdNet(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public HouseholdNet(HouseholdView parent, Term child)
  {
    this(parent.getConcreteId(), child.getId());
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }

  public HouseholdNet clone(HouseholdView parent)
  {
    HouseholdNet clone = new HouseholdNet(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
  @Override
  public void validateAmount()
  {
    if(this.getAmount() != null)
    {
      Integer nets = this.getParent().getNets();
    
      if(nets != null && nets == 0 && this.getAmount() != 0)
      {
        String msg = "The individual household net amount may not be set when the total number of nets is 0";
        Term term = this.getChild();
        
        NetProblem p = new NetProblem(msg);
        p.setNetName(term.getTermName());
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void apply()
  {
    validateAmount();
    
    super.apply();
  }
}
