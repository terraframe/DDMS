package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.intervention.HumanSex;

public class Person extends PersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641280013L;

  public Person()
  {
    super();
  }

  @Override
  public void validatePregnant()
  {
    if(this.getPregnant() != null && this.getPregnant() == true)
    {
      if(this.getSex().size() > 0 && !this.getSex().get(0).equals(HumanSex.FEMALE))
      {
        String msg = "It is impossible for a human male to be pregnant";

        PregnantProblem p = new PregnantProblem(msg);
        p.apply();
        p.throwIt();
      }
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
}
