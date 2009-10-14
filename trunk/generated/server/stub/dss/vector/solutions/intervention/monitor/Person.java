package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.AgeConverter;

public class Person extends PersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641280013L;

  public Person()
  {
    super();
  }
    
  @Override
  protected String buildKey()
  {
    if(this.getPersonId() != null)
    {
      return this.getPersonId();
    }
    
    return this.getId();
  }

  @Override
  public void validatePregnant()
  {
    if(this.getPregnant() != null && this.getPregnant() == true)
    {
      //FIXME Get the real female key
//      String femaleId = "";
//      if(this.getSex() != null && this.getSex().getId().equals(femaleId))
//      {
//        String msg = "It is impossible for a human male to be pregnant";
//
//        PregnantProblem p = new PregnantProblem(msg);
//        p.apply();
//        p.throwIt();
//      }
    }
  }

  @Override
  public void apply()
  {
    validatePregnant();

    boolean first = this.isNew() && !this.isAppliedToDB();

    super.apply();

    if(first)
    {
      HouseholdPerson householdPerson = new HouseholdPerson(this.getHousehold(), this);
      householdPerson.apply();
    }
  }
  
  @Override
  @Transaction
  public PersonView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public PersonView unlockView()
  {
    this.unlock();

    return this.getView();
  }
  
  public PersonView getView()
  {
    PersonView view = new PersonView();
    
    this.populateView(view);
    
    return view;
  }

  public void populateView(PersonView view)
  {
    view.setConcreteId(this.getId());

    if(this.getDob() != null)
    {        
      view.setAge(new AgeConverter(this.getDob()).getAge());
    }

    view.setDob(this.getDob());
    view.setAnaemiaTreatment(this.getAnaemiaTreatment());
    view.setFeverTreatment(this.getFeverTreatment());
    view.setHaemoglobin(this.getHaemoglobin());
    view.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
    view.setHousehold(this.getHousehold());
    view.setIron(this.getIron());
    view.setMalariaTreatment(this.getMalariaTreatment());
    view.setPersonId(this.getPersonId());
    view.setPregnant(this.getPregnant());
    view.setRdtTreatment(this.getRdtTreatment());
    view.setSleptUnderNet(this.getSleptUnderNet());
    view.setBloodslide(this.getBloodslide());
    view.setFever(this.getFever());
    view.setMalaria(this.getMalaria());
    view.setPayment(this.getPayment());
    view.setPerformedRDT(this.getPerformedRDT());
    view.setSex(this.getSex());
  }

  public static PersonView getView(String id)
  {
    return Person.get(id).getView();
  }
}
