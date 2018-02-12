/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ResponseMaster;
import dss.vector.solutions.ValueGreaterLimitProblem;
import dss.vector.solutions.intervention.monitor.NotApplicableProblem;

public class SubCollection extends SubCollectionBase implements com.runwaysdk.generation.loader.Reloadable
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

    this.validateFemalesTotal();
    this.validateMale();
    this.validatePupae();
    this.validateLarvae();
    this.validateUnknowns();
    this.validateEggs();
    this.validateParous();

    super.apply();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }

  @Override
  public void validateParous()
  {
    if (this.getParous() != null && ( this.getDisected() == null || this.getParous() > this.getDisected() ))
    {
      ValueGreaterLimitProblem problem = new ValueGreaterLimitProblem();
      problem.setValueAttributeLabel(SubCollection.getParousMd().getDisplayLabel(Session.getCurrentLocale()));
      problem.setLimitAttributeLabel(SubCollection.getDisectedMd().getDisplayLabel(Session.getCurrentLocale()));
      problem.apply();

      problem.throwIt();
    }
  }

  @Override
  public void validateFemalesTotal()
  {
    if (this.getCollection() != null && this.getFemalesTotal() != null)
    {
      List<LifeStage> lifeStage = this.getCollection().getLifeStage();

      if (!lifeStage.contains(LifeStage.ADULT))
      {
        String value = ResponseMaster.getValueForErrorMsg(lifeStage);

        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, FEMALESTOTAL);
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

  public void populateTotal()
  {
    int total = 0;
    int totalFemale = 0;

    if (this.getFemalesFed() != null)
    {
      totalFemale += this.getFemalesFed();
    }

    if (this.getFemalesGravid() != null)
    {
      totalFemale += this.getFemalesGravid();
    }

    if (this.getFemalesHalfGravid() != null)
    {
      totalFemale += this.getFemalesHalfGravid();
    }

    if (this.getFemalesUnfed() != null)
    {
      totalFemale += this.getFemalesUnfed();
    }

    if (this.getFemalesUnknown() != null)
    {
      totalFemale += this.getFemalesUnknown();
    }

    List<LifeStage> lifeStage = this.getCollection().getLifeStage();

    if (lifeStage.contains(LifeStage.ADULT))
    {
      this.setFemalesTotal(totalFemale);
    }

    if (this.getFemalesTotal() != null)
    {
      total += this.getFemalesTotal();
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
