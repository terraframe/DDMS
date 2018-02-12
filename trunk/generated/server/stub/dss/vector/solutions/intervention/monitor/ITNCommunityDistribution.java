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
package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.Locale;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.ITNCommunityDistributionQB;

public class ITNCommunityDistribution extends ITNCommunityDistributionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252612170049L;

  public ITNCommunityDistribution()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    // ITN Community Distribution class has no attributes that can form a unique
    // identifier
    return this.getId();
  }

  public static ITNCommunityDistributionView getView(String id)
  {
    return ITNCommunityDistribution.get(id).getView();
  }

  public ITNCommunityDistributionView getView()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.populateView(this);

    return view;
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction

    for (ITNCommunityTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }

    for (ITNCommunityNet net : this.getAllNetsRel())
    {
      net.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Unlock the grid relationship also, this must be in the same transaction
    for (ITNCommunityTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }

    for (ITNCommunityNet net : this.getAllNetsRel())
    {
      net.unlock();
    }
  }

  @Override
  public ITNCommunityDistributionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ITNCommunityDistributionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    this.validateStartDate();
    this.validateEndDate();

    this.validateHouseholdAddress();
    this.validateHouseholdName();
    this.validateHouseholdSurname();
    this.validateResidents();

    this.validateDistributionLocation();

    // Validate the amount of currency recieved
    this.validateCurrencyReceived();
    this.validateNumberRetrieved();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public void validateHouseholdAddress()
  {
    if (this.getEntryType() != null && this.getHouseholdAddress() != null && !this.getEntryType())
    {
      String msg = "Household address is not applicable when the entry type is Distribution Location";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, HOUSEHOLDADDRESS);
      p.setInputAttribute(getEntryTypeMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getEntryTypeMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }

    if (this.getEntryType() != null && this.getEntryType() && this.getHouseholdAddress() == null)
    {
      String msg = "Household address must have a value when the entry type is by household";

      RequiredAttributeProblem p = new RequiredAttributeProblem(msg);
      p.setNotification(this, HOUSEHOLDADDRESS);
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateHouseholdName()
  {
    String value = this.getHouseholdName();

    if (this.getEntryType() != null && value != null && !value.equals("") && !this.getEntryType())
    {
      String msg = "Household head name is not applicable when the entry type is Distribution Location";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, HOUSEHOLDNAME);
      p.setInputAttribute(getEntryTypeMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getEntryTypeMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateHouseholdSurname()
  {
    String value = this.getHouseholdSurname();

    if (this.getEntryType() != null && value != null && !value.equals("") && !this.getEntryType())
    {
      String msg = "Household head surname is not applicable when the entry type is Distribution Location";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, HOUSEHOLDSURNAME);
      p.setInputAttribute(getEntryTypeMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getEntryTypeMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateResidents()
  {
    if (this.getEntryType() != null && this.getResidents() != null && !this.getEntryType())
    {
      String msg = "Household residents is not applicable when the entry type is Distribution Location";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, RESIDENTS);
      p.setInputAttribute(getEntryTypeMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getEntryTypeMd() ).getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateDistributionLocation()
  {
    if (this.getEntryType() != null && this.getDistributionLocation() != null && this.getEntryType())
    {
      String msg = "Distribution Location is not applicable when the entry type is by household";
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, DISTRIBUTIONLOCATION);
      p.setInputAttribute(getEntryTypeMd().getDisplayLabel(locale));
      p.setInputValue( ( (MdAttributeBooleanDAOIF) getEntryTypeMd() ).getPositiveDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }

    if (this.getEntryType() != null && !this.getEntryType() && this.getDistributionLocation() == null)
    {
      String msg = "Distribution Location must have a value when the entry type is by distribution location";

      RequiredAttributeProblem p = new RequiredAttributeProblem(msg);
      p.setNotification(this, DISTRIBUTIONLOCATION);
      p.apply();

      p.throwIt();
    }

  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateCurrencyReceived()
  {
    if (this.getCurrencyReceived() != null && ( this.getSold() == null || !this.getSold() ))
    {
      String msg = "Currency received cannot be set when the ITN's sold is false.";
      CurrencyAmountProblem p = new CurrencyAmountProblem(msg);
      p.setNotification(this, ITNData.CURRENCYRECEIVED);
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateNumberRetrieved()
  {
    if (this.getNumberRetrieved() != null && ( this.getRetrieved() == null || !this.getRetrieved() ))
    {
      String msg = "Number of nets retrieved is not applicable when the nets retrieved is no.";
      MdAttributeBooleanDAOIF retrievedMd = (MdAttributeBooleanDAOIF) getRetrievedMd();
      Locale locale = Session.getCurrentLocale();

      NotApplicableProblem p = new NotApplicableProblem(msg);
      p.setNotification(this, NUMBERRETRIEVED);
      p.setInputAttribute(retrievedMd.getDisplayLabel(locale));
      p.setInputValue(retrievedMd.getNegativeDisplayLabel(locale));
      p.apply();

      p.throwIt();
    }
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new ITNCommunityDistributionQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }

}
