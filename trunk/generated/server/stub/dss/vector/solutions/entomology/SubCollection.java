package dss.vector.solutions.entomology;

import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.ResponseMaster;
import dss.vector.solutions.intervention.monitor.NotApplicableProblem;

public class SubCollection extends SubCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1584021814;

  public SubCollection()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public void apply()
  {
    this.populateTotal();

    this.validateFemale();
    this.validateMale();
    this.validatePupae();
    this.validateLarvae();
    this.validateUnknowns();
    this.validateEggs();

    super.apply();
  }

  @Override
  public void validateFemale()
  {
    if (this.getCollection() != null && this.getFemale() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.ADULT))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, FEMALE);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  @Override
  public void validateMale()
  {
    if (this.getCollection() != null && this.getMale() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.ADULT))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, MALE);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  @Override
  public void validatePupae()
  {
    if (this.getCollection() != null && this.getPupae() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.IMMATURE))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, PUPAE);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  @Override
  public void validateLarvae()
  {
    if (this.getCollection() != null && this.getLarvae() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.IMMATURE))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, LARVAE);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  @Override
  public void validateUnknowns()
  {
    if (this.getCollection() != null && this.getUnknowns() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.IMMATURE) && !lifeStage.contains(LifeStage.ADULT))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, UNKNOWNS);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  @Override
  public void validateEggs()
  {
    if (this.getCollection() != null && this.getEggs() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();
      
      if (!lifeStage.contains(LifeStage.EGG))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);
        
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, EGGS);
        p.setInputAttribute(MosquitoCollection.getLifeStageMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(value);
        p.apply();
        p.throwIt();        
      }
    }
  }

  private void populateTotal()
  {
    int total = 0;

    if (this.getFemale() != null)
    {
      total += this.getFemale();
    }

    if (this.getMale() != null)
    {
      total += this.getMale();
    }

    if (this.getLarvae() != null)
    {
      total += this.getLarvae();
    }

    if (this.getPupae() != null)
    {
      total += this.getPupae();
    }

    if (this.getUnknowns() != null)
    {
      total += this.getUnknowns();
    }

    if (this.getEggs() != null)
    {
      total += this.getEggs();
    }

    this.setTotal(total);
  }

  @Override
  @Transaction
  public SubCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public SubCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public SubCollectionView getView()
  {
    SubCollectionView view = new SubCollectionView();

    view.populateView(this);

    return view;
  }
}
