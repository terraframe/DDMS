package dss.vector.solutions.export;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.IndividualIPT;
import dss.vector.solutions.intervention.monitor.IndividualIPTCase;
import dss.vector.solutions.intervention.monitor.IndividualIPTCaseQuery;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.IndividualIPTViewQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class IndividualIPTExcelView extends IndividualIPTExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
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
    IndividualIPTCase iptCase = searchForCase();
    String facilityGeoId = this.getFacility().getGeoId();
    Date service = this.getServiceDate();
    
    IndividualIPTView view = new IndividualIPTView();
    view.setIptCase(iptCase);
    view.setFacility(facilityGeoId);
    view.setServiceDate(service);
    
    IndividualIPTViewQuery query = new IndividualIPTViewQuery(new QueryFactory());
    query.WHERE(query.getIptCase().EQ(iptCase));
    query.WHERE(query.getFacility().EQ(facilityGeoId));
    query.WHERE(query.getServiceDate().EQ(service));
    OIterator<? extends IndividualIPTView> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      view = IndividualIPT.lockView(iterator.next().getConcreteId());
    }
    iterator.close();
    
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
    GeoEntity residential = this.getResidentialLocation();
    GeoEntity workGeo = this.getWorkGeoEntity();
    String firstName = this.getPatientFirstName();
    String lastName = this.getPatientLastName();
    Date dob = this.getPatientDOB();
    String sexString = this.getPatientSex();
    Term sex = Term.validateByDisplayLabel(sexString, PersonView.getSexMd());
    
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
    if (sex!=null)
    {
      personQuery.WHERE(personQuery.getSex().EQ(sex));
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
      person.setSex(sex);
      person.setResidentialGeoEntity(residential);
      person.setWorkGeoEntity(workGeo);
      person.setWorkInformation(this.getWorkInformation());
      person.apply();
    }
    personIterator.close();
    
    IPTRecipient recipient = person.getIptRecipientDelegate();
    if (recipient==null)
    {
      recipient = new IPTRecipient();
      recipient.setPerson(person);
      recipient.apply();
      
      person.lock();
      person.setIptRecipientDelegate(recipient);
      person.apply();
    }
    
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
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(PATIENTFIRSTNAME);
    list.add(PATIENTLASTNAME);
    list.add(PATIENTDOB);
    list.add(PATIENTSEX);
    list.add(WORKINFORMATION);
    list.add(SERVICEDATE);
    list.add(PATIENTTYPE);
    list.add(ISANCVISIT);
    list.add(VISITNUMBER);
    list.add(DOSENUMBER);
    list.add(DOSETYPE);
    list.add(RECEIVEDSUPPLEMENT);
    list.add(RECEIVEDITN);
    list.add(NUMBEROFRECEIVEDITNS);
    list.add(ADMINISTRATORNAME);
    list.add(ADMINISTRATORSURNAME);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(RESIDENTIALLOCATION));
    exporter.addListener(createExcelGeoListener(WORKGEOENTITY));
    exporter.addListener(createExcelHealthFacilityListener());
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener(RESIDENTIALLOCATION));
    context.addListener(createExcelGeoListener(WORKGEOENTITY));
    context.addListener(createExcelHealthFacilityListener());
  }
  
  private static DynamicGeoColumnListener createExcelHealthFacilityListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, FACILITY, builder);
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(String attributeName)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, attributeName, builder);
  }
  
}
