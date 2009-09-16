package dss.vector.solutions.intervention.monitor;

import java.util.Locale;

import com.terraframe.mojo.dataaccess.MdAttributeBooleanDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.surveillance.ChildOption;

public class ITNHouseholdSurveyNonUseReason extends ITNHouseholdSurveyNonUseReasonBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1252970218215L;
  
  public ITNHouseholdSurveyNonUseReason(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNHouseholdSurveyNonUseReason(ITNHouseholdSurvey parent, NonUseReasonGrid child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNHouseholdSurveyNonUseReason clone(ITNHouseholdSurveyView parent)
  {
    ITNHouseholdSurveyNonUseReason clone = new ITNHouseholdSurveyNonUseReason(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }    
  
  @Override
  public void apply()
  {
    validateAmount();
    
    super.apply();
  }
  
  @Override
  public void validateAmount()
  {
    ITNHouseholdSurvey parent = this.getParent();
    
    if(this.getAmount() != null && !this.getAmount().equals(0) && parent != null )
    {
      if(parent.getUsedEveryNight() != null && parent.getUsedEveryNight())
      {
        String msg = "Non use reason is not applicable when nets are used every night";
        
        MdAttributeDAOIF attributeMd = ITNHouseholdSurvey.getUsedEveryNightMd();
        Locale locale = Session.getCurrentLocale();

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, AMOUNT);
        p.setInputAttribute(attributeMd.getDisplayLabel(locale));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) attributeMd ).getPositiveDisplayLabel(locale));
        p.apply();

        p.throwIt();
      }      
    }
  }
}
