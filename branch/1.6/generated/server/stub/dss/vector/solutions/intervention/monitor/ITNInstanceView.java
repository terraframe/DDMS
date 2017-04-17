package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;

import dss.vector.solutions.MonthOfYear;
import dss.vector.solutions.Response;

public class ITNInstanceView extends ITNInstanceViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1491762002;

  public ITNInstanceView()
  {
    super();
  }

  public void populateView(ITNInstance concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setHousehold(concrete.getHousehold());
    this.setNetId(concrete.getNetId());
    this.setNetBrand(concrete.getNetBrand());

    this.clearMonthReceived();
    for (MonthOfYear month : concrete.getMonthReceived())
    {
      this.addMonthReceived(month);
    }

    this.setYearReceived(concrete.getYearReceived());
    this.setObtained(concrete.getObtained());
    this.setPrice(concrete.getPrice());
    this.setRetreated(concrete.getRetreated());

    this.clearMonthRetreated();
    for (MonthOfYear month : concrete.getMonthRetreated())
    {
      this.addMonthRetreated(month);
    }

    this.setYearRetreated(concrete.getYearRetreated());
    this.setDamaged(concrete.getDamaged());
    this.setHanging(concrete.getHanging());
    this.setNotUsedForSleeping(concrete.getNotUsedForSleeping());
    this.setPurpose(concrete.getPurpose());
    this.setPurposeComments(concrete.getPurposeComments());

    this.clearWashed();
    for (Response response : concrete.getWashed())
    {
      this.addWashed(response);
    }

    this.setWashFrequency(concrete.getWashFrequency());
    this.setWashPeriod(concrete.getWashPeriod());
    this.setSleptUnderNet(concrete.getSleptUnderNet());

    long count = SurveyedPerson.getCount(concrete);
    this.setIsNetUsed(count > 0);
  }

  private void populateConcrete(ITNInstance concrete)
  {
    concrete.setHousehold(this.getHousehold());
    concrete.setNetId(this.getNetId());
    concrete.setNetBrand(this.getNetBrand());

    concrete.clearMonthReceived();
    for (MonthOfYear month : this.getMonthReceived())
    {
      concrete.addMonthReceived(month);
    }

    concrete.setYearReceived(this.getYearReceived());
    concrete.setObtained(this.getObtained());
    concrete.setPrice(this.getPrice());
    concrete.setRetreated(this.getRetreated());

    concrete.clearMonthRetreated();
    for (MonthOfYear month : this.getMonthRetreated())
    {
      concrete.addMonthRetreated(month);
    }

    concrete.setYearRetreated(this.getYearRetreated());
    concrete.setDamaged(this.getDamaged());
    concrete.setHanging(this.getHanging());
    concrete.setNotUsedForSleeping(this.getNotUsedForSleeping());
    concrete.setPurpose(this.getPurpose());
    concrete.setPurposeComments(this.getPurposeComments());

    concrete.clearWashed();
    for (Response response : this.getWashed())
    {
      concrete.addWashed(response);
    }

    concrete.setWashFrequency(this.getWashFrequency());
    concrete.setWashPeriod(this.getWashPeriod());
    concrete.setSleptUnderNet(this.getSleptUnderNet());
  }

  private void buildAttributeMap(ITNInstance concrete)
  {
    new AttributeNotificationMap(concrete, ITNInstance.ID, this, ITNInstanceView.CONCRETEID);
    new AttributeNotificationMap(concrete, ITNInstance.HOUSEHOLD, this, ITNInstanceView.HOUSEHOLD);
    new AttributeNotificationMap(concrete, ITNInstance.NETID, this, ITNInstanceView.NETID);
    new AttributeNotificationMap(concrete, ITNInstance.NETBRAND, this, ITNInstanceView.NETBRAND);
    new AttributeNotificationMap(concrete, ITNInstance.MONTHRECEIVED, this, ITNInstanceView.MONTHRECEIVED);
    new AttributeNotificationMap(concrete, ITNInstance.YEARRECEIVED, this, ITNInstanceView.YEARRECEIVED);
    new AttributeNotificationMap(concrete, ITNInstance.OBTAINED, this, ITNInstanceView.OBTAINED);
    new AttributeNotificationMap(concrete, ITNInstance.PRICE, this, ITNInstanceView.PRICE);
    new AttributeNotificationMap(concrete, ITNInstance.RETREATED, this, ITNInstanceView.RETREATED);
    new AttributeNotificationMap(concrete, ITNInstance.MONTHRETREATED, this, ITNInstanceView.MONTHRETREATED);
    new AttributeNotificationMap(concrete, ITNInstance.YEARRETREATED, this, ITNInstanceView.YEARRETREATED);
    new AttributeNotificationMap(concrete, ITNInstance.DAMAGED, this, ITNInstanceView.DAMAGED);
    new AttributeNotificationMap(concrete, ITNInstance.HANGING, this, ITNInstanceView.HANGING);
    new AttributeNotificationMap(concrete, ITNInstance.NOTUSEDFORSLEEPING, this, ITNInstanceView.NOTUSEDFORSLEEPING);
    new AttributeNotificationMap(concrete, ITNInstance.PURPOSE, this, ITNInstanceView.PURPOSE);
    new AttributeNotificationMap(concrete, ITNInstance.PURPOSECOMMENTS, this, ITNInstanceView.PURPOSECOMMENTS);
    new AttributeNotificationMap(concrete, ITNInstance.WASHED, this, ITNInstanceView.WASHED);
    new AttributeNotificationMap(concrete, ITNInstance.WASHFREQUENCY, this, ITNInstanceView.WASHFREQUENCY);
    new AttributeNotificationMap(concrete, ITNInstance.WASHPERIOD, this, ITNInstanceView.WASHPERIOD);
    new AttributeNotificationMap(concrete, ITNInstance.SLEPTUNDERNET, this, ITNInstanceView.SLEPTUNDERNET);
  }

  @Override
  public void apply()
  {
    ITNInstance concrete = new ITNInstance();

    if (this.hasConcrete())
    {
      concrete = ITNInstance.get(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ITNInstance.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
}
