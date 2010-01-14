package dss.vector.solutions.export;

import java.util.Date;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.geo.generated.SettlementSubdivision;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.IndividualIPTCase;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseQuery;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualIPTExcelView extends IndividualIPTExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256862976072L;
  
  public IndividualIPTExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    IndividualIPTView view = new IndividualIPTView();
    view.setIptCase(searchForCase());
    view.setFacility(this.getFacility().getGeoId());
    view.setServiceDate(this.getServiceDate());
    view.setPatientType(Term.validateByDisplayLabel(this.getPatientType(), IndividualIPTView.getPatientTypeMd()));
    view.setIsANCVisit(this.getIsANCVisit());
    view.setVisitNumber(Term.validateByDisplayLabel(this.getVisitNumber(), IndividualIPTView.getVisitNumberMd()));
    view.setDoseNumber(Term.validateByDisplayLabel(this.getDoseNumber(), IndividualIPTView.getDoseNumberMd()));
    view.setDoseType(Term.validateByDisplayLabel(this.getDoseType(), IndividualIPTView.getDoseTypeMd()));
    view.setReceivedSupplement(this.getReceivedSupplement());
    view.setReceivedITN(this.getReceivedITN());
    view.setNumberOfReceivedITNs(this.getNumberOfReceivedITNs());
    view.setAdministratorName(this.getAdministratorName());
    view.setAdministratorSurname(this.getAdministratorSurname());
    view.apply();
  }
  
  private IndividualIPTCase searchForCase()
  {
    String firstName = this.getPatientFirstName();
    String lastName = this.getPatientLastName();
    Date dob = this.getPatientDOB();
    
    PersonQuery personQuery = new PersonQuery(new QueryFactory());
    if (firstName!=null && firstName.length()>0)
    {
      personQuery.WHERE(personQuery.getFirstName().EQ(firstName));
    }
    if (lastName!=null && lastName.length()>0)
    {
      personQuery.WHERE(personQuery.getLastName().EQ(lastName));
    }
    if (dob!=null)
    {
      personQuery.WHERE(personQuery.getDateOfBirth().EQ(dob));
    }
    
    if (personQuery.getCount()>1)
    {
      AmbiguousRecipientProblem problem = new AmbiguousRecipientProblem();
      problem.setFirstName(firstName);
      problem.setLastName(lastName);
      problem.setDob(dob);
      problem.throwIt();
      return null;
    }
    
    Person person;
    OIterator<? extends Person> personIterator = personQuery.getIterator();
    if (personIterator.hasNext())
    {
      person = personIterator.next();
    }
    else
    {
      person = new Person();
      person.setFirstName(firstName);
      person.setLastName(lastName);
      person.setDateOfBirth(dob);
      person.apply();
    }
    personIterator.close();
    
    IPTRecipient recipient = person.getIptRecipientDelegate();
    if (recipient==null)
    {
      recipient = new IPTRecipient();
      recipient.setPerson(person);
      recipient.apply();
      
      person.setIptRecipientDelegate(recipient);
      person.apply();
    }
    
    GeoEntity residential = this.getResidentialLocation();
    IndividualIPTCaseQuery caseQuery = new IndividualIPTCaseQuery(new QueryFactory());
    caseQuery.WHERE(caseQuery.getResidentialLocation().EQ(residential));
    caseQuery.WHERE(caseQuery.getPatient().EQ(recipient));
    
    IndividualIPTCase kase;
    if (caseQuery.getCount()==0)
    {
      kase = new IndividualIPTCase();
      kase.setResidentialLocation(residential);
      kase.setPatient(recipient);
      kase.apply();
    }
    else
    {
      OIterator<? extends IndividualIPTCase> caseIterator = caseQuery.getIterator();
      kase = caseIterator.next();
      caseIterator.close();
    }
    
    return kase;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(createExcelHealthFacilityListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
    importer.addListener(createExcelHealthFacilityListener());
  }
  
  private static DynamicGeoColumnListener createExcelHealthFacilityListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, FACILITY, builder);
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SettlementSubdivision.CLASS));
    return new DynamicGeoColumnListener(CLASS, RESIDENTIALLOCATION, builder);
  }
  
}
