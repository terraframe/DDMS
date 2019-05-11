package dss.vector.solutions.migration;

import java.util.Calendar;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.Person;
import dss.vector.solutions.odk.ODKFacade;
import dss.vector.solutions.odk.ODKUser;
import dss.vector.solutions.ontology.Term;

public class Patcher4009 implements DDMSPatchIF, Reloadable
{
  public static void main(String[] args) {
    mainInRequest();
  }
  @Request
  public static void mainInRequest()
  {
    new Patcher4009().doIt(); 
  }
  
  @Override
  public Integer getPatchVersion()
  {
    return 9248;
  }

  @Override
  public void doIt()
  {
    createOdkUser();
  }
  
  @Transaction
  public static void createOdkUser()
  {
    String UNKNOWN = "MDSS:0000320";

    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(Calendar.YEAR, 2009);
    calendar.set(Calendar.MONTH, 1);
    calendar.set(Calendar.DAY_OF_YEAR, 1);
    
    Person person = new Person();
    person.setDateOfBirth(calendar.getTime());
    person.setFirstName("odkuser");
    person.setLastName("odkuser");
    person.setSex(Term.getByTermId(UNKNOWN));
    person.apply();

    ODKUser user = new ODKUser();
    user.setUsername("odkuser");
    user.setOdkUsername(ODKFacade.USERNAME);
    user.setPassword(ODKFacade.PASSWORD);
    user.setSessionLimit(25);
    user.setPerson(person);
    user.apply(false);
    
    person.setUserDelegate(user);
    person.apply();
  }

  @Override
  public void undoIt()
  {
    throw new UnsupportedOperationException();
  }
}
